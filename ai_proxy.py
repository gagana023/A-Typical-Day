import json
import os
import sys
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from urllib import request, error

PORT = int(os.getenv("PORT", os.getenv("AI_PROXY_PORT", "8001")))
API_URL = os.getenv("OPENAI_API_URL", "https://api.openai.com/v1/chat/completions")
MODEL = os.getenv("OPENAI_MODEL", "gpt-4o-mini")
API_KEY = os.getenv("OPENAI_API_KEY")


def build_prompt(task_id: str, step: int, player_response: str) -> str:
    system_prompt = (
        "You are an NPC in a school social-skills game. Classify the player's tone and reply "
        "naturally in character. Return only valid JSON with integer category 1, 2, or 3 and "
        "string npcResponse. Category 1 is kind and polite: social standing increases and social "
        "battery decreases. Category 2 is blunt but not deliberately rude: social standing decreases "
        "and social battery increases. Category 3 is rude or hostile: both social standing and "
        "social battery decrease. Do not include markdown. Keep the NPC reply to two sentences or fewer."
    )
    user_prompt = f"Task: {task_id}, step: {step}. Player response: {player_response}"
    payload = {
        "model": MODEL,
        "temperature": 0.7,
        "response_format": {"type": "json_object"},
        "messages": [
            {"role": "system", "content": system_prompt},
            {"role": "user", "content": user_prompt},
        ],
    }
    return json.dumps(payload).encode("utf-8")


def call_openai(task_id: str, step: int, player_response: str):
    if not API_KEY:
        raise RuntimeError("OpenAI API key is not configured on the server.")

    payload = build_prompt(task_id, step, player_response)
    req = request.Request(
        API_URL,
        data=payload,
        headers={
            "Content-Type": "application/json",
            "Authorization": f"Bearer {API_KEY}",
        },
        method="POST",
    )

    try:
        with request.urlopen(req, timeout=30) as response:
            body = response.read().decode("utf-8")
            data = json.loads(body)
    except error.HTTPError as exc:
        body = exc.read().decode("utf-8", errors="replace")
        raise RuntimeError(f"OpenAI request failed with status {exc.code}: {body}") from exc
    except Exception as exc:  # pragma: no cover - network failures
        raise RuntimeError(f"OpenAI request failed: {exc}") from exc

    try:
        content = data["choices"][0]["message"]["content"]
        parsed = json.loads(content)
        category = int(parsed["category"])
        npc_response = str(parsed["npcResponse"])
        if category not in (1, 2, 3):
            raise ValueError("Invalid category")
        return {"category": category, "npcResponse": npc_response}
    except (KeyError, TypeError, ValueError, json.JSONDecodeError) as exc:
        raise RuntimeError(f"OpenAI returned an invalid response: {data}") from exc


class ProxyHandler(BaseHTTPRequestHandler):
    server_version = "ATypicalDayProxy/1.0"

    def do_HEAD(self):
        if self.path == "/":
            self.send_response(200)
            self.end_headers()
            return
        self.send_error(404, "Not Found")

    def do_GET(self):
        if self.path == "/":
            response = b"AI proxy is running."
            self.send_response(200)
            self.send_header("Content-Type", "text/plain")
            self.send_header("Content-Length", str(len(response)))
            self.end_headers()
            self.wfile.write(response)
            return
        self.send_error(404, "Not Found")

    def do_POST(self):
        if self.path not in ("/analyze", "/api/analyze"):
            self.send_error(404, "Not Found")
            return

        try:
            content_length = int(self.headers.get("Content-Length", "0"))
            raw_body = self.rfile.read(content_length)
            payload = json.loads(raw_body.decode("utf-8"))
            task_id = str(payload.get("taskId", "unknown"))
            step = int(payload.get("step", 0))
            player_response = str(payload.get("playerResponse", ""))

            result = call_openai(task_id, step, player_response)
            response = json.dumps(result).encode("utf-8")
            self.send_response(200)
            self.send_header("Content-Type", "application/json")
            self.send_header("Content-Length", str(len(response)))
            self.end_headers()
            self.wfile.write(response)
        except Exception as exc:  # pragma: no cover - middleware error
            message = str(exc)
            error_payload = json.dumps({"error": message}).encode("utf-8")
            self.send_response(503 if "API key" in message or "failed with status 401" in message else 500)
            self.send_header("Content-Type", "application/json")
            self.send_header("Content-Length", str(len(error_payload)))
            self.end_headers()
            self.wfile.write(error_payload)

    def log_message(self, format, *args):
        sys.stderr.write("%s - - [%s] %s\n" % (self.address_string(), self.log_date_time_string(), format % args))


if __name__ == "__main__":
    print(f"AI proxy running on http://0.0.0.0:{PORT}/analyze")
    print("Set OPENAI_API_KEY in this shell before starting the proxy.")
    httpd = ThreadingHTTPServer(("0.0.0.0", PORT), ProxyHandler)
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("\nStopping AI proxy.")
        httpd.server_close()
