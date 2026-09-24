# Final project for Advanced Programming Topics

See directions for final project [here](https://nchs-cs.github.io/advanced-topics/final-project/)

All documentation for this project can be found [here](doc/README.md)

Claude was used to help us debug our issues, mainly the ones tending to having God classes that we learned from our pmd. We also used it in the inital phase to help us plan out our class structure.

ChatGPT was used to help us understand our errors or the messages we would get from the PMD and the CPD and that helped us understand what to do when breaking up our classes.

Stack Overflow was used to design the PANIC feature because we originally did not know how to make the actual screen shake.

## AI dialogue setup

Typed player responses are classified through a local AI proxy instead of calling OpenAI directly from
the Java client. The game sends the response to a small Python service, and that service owns the
OpenAI API key on the server side.

To run the AI feature locally:

1. Open a terminal in the project root.
2. Set the key in that shell only:

```powershell
$env:OPENAI_API_KEY = "your-api-key"
```

3. Start the proxy:

```powershell
python ai_proxy.py
```

4. In another terminal, start the JavaFX game. If needed, set the proxy URL explicitly:

```powershell
$env:AI_PROXY_URL = "http://127.0.0.1:8001/analyze"
```

The game defaults to `http://127.0.0.1:8001/analyze`, so the key never needs to live in the repo or
in the client runtime environment.

The optional `OPENAI_MODEL` variable selects a model and defaults to `gpt-4o-mini` on the backend
server only.
