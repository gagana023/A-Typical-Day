package app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Sends player dialogue to OpenAI and converts it into one of the game's three outcomes. */
public final class OpenAIResponseService {

  private static final HttpClient CLIENT = HttpClient.newHttpClient();
  private static final Pattern CONTENT_PATTERN =
      Pattern.compile("\\\"content\\\"\\s*:\\s*\\\"((?:\\\\.|[^\\\"\\\\])*)\\\"");
  private static final Pattern CATEGORY_PATTERN = Pattern.compile("\\\"category\\\"\\s*:\\s*(\\d+)");
  private static final Pattern NPC_RESPONSE_PATTERN =
      Pattern.compile("\\\"npcResponse\\\"\\s*:\\s*\\\"((?:\\\\.|[^\\\"\\\\])*)\\\"");

  private OpenAIResponseService() {}

  /** The classification and generated response returned by the model. */
  public record Analysis(int category, String npcResponse) {}

  /**
   * Classifies a player's typed response without blocking the JavaFX application thread.
   *
   * @param taskId the current task identifier
   * @param step the current task step
   * @param playerResponse the response typed by the player
   * @return a future containing the classified response
   */
  public static CompletableFuture<Analysis> analyze(
      String taskId, int step, String playerResponse) {
    String apiKey = System.getenv("OPENAI_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      return CompletableFuture.failedFuture(
          new IllegalStateException("OPENAI_API_KEY is not set."));
    }

    String model = System.getenv().getOrDefault("OPENAI_MODEL", "gpt-4o-mini");
    String endpoint = System.getenv().getOrDefault("OPENAI_API_URL", "https://api.openai.com/v1/chat/completions");
    String body = createRequestBody(model, taskId, step, playerResponse);

    HttpRequest request =
        HttpRequest.newBuilder(URI.create(endpoint))
            .timeout(Duration.ofSeconds(30))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

    return CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(OpenAIResponseService::parseResponse);
  }

  private static String createRequestBody(
      String model, String taskId, int step, String playerResponse) {
    String systemPrompt =
        "You are an NPC in a school social-skills game. Classify the player's tone and reply "
            + "naturally in character. Return only valid JSON with integer category 1, 2, or 3 "
            + "and string npcResponse. Category 1 is kind and polite: social standing increases "
            + "and social battery decreases. Category 2 is blunt but not deliberately rude: social "
            + "standing decreases and social battery increases. Category 3 is rude or hostile: both "
            + "social standing and social battery decrease. Do not include markdown. Keep the NPC "
            + "reply to two sentences or fewer.";
    String userPrompt =
        "Task: " + taskId + ", step: " + step + ". Player response: " + playerResponse;

    return "{\"model\":\""
        + escapeJson(model)
        + "\",\"temperature\":0.7,\"response_format\":{\"type\":\"json_object\"},\"messages\":[{\"role\":\"system\",\"content\":\""
        + escapeJson(systemPrompt)
        + "\"},{\"role\":\"user\",\"content\":\""
        + escapeJson(userPrompt)
        + "\"}]}";
  }

  private static Analysis parseResponse(HttpResponse<String> response) {
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw new IllegalStateException(
          "OpenAI request failed with status " + response.statusCode() + ": " + response.body());
    }

    Matcher contentMatcher = CONTENT_PATTERN.matcher(response.body());
    if (!contentMatcher.find()) {
      throw new IllegalStateException("OpenAI response did not contain message content.");
    }

    String content = unescapeJson(contentMatcher.group(1));
    Matcher categoryMatcher = CATEGORY_PATTERN.matcher(content);
    Matcher npcResponseMatcher = NPC_RESPONSE_PATTERN.matcher(content);
    if (!categoryMatcher.find() || !npcResponseMatcher.find()) {
      throw new IllegalStateException("OpenAI returned an invalid dialogue classification.");
    }

    int category = Integer.parseInt(categoryMatcher.group(1));
    if (category < 1 || category > 3) {
      throw new IllegalStateException("OpenAI returned an invalid dialogue category.");
    }

    return new Analysis(category, unescapeJson(npcResponseMatcher.group(1)));
  }

  private static String escapeJson(String value) {
    return value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\r", "\\r").replace("\n", "\\n");
  }

  private static String unescapeJson(String value) {
    return value.replace("\\n", "\n").replace("\\r", "\r").replace("\\\"", "\"").replace("\\\\", "\\");
  }
}
