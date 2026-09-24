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

/** Sends player dialogue to the AI proxy and converts it into one of the game's three outcomes. */
public final class OpenAIResponseService {

  private static final HttpClient CLIENT = HttpClient.newHttpClient();
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
    String proxyUrl = System.getenv().getOrDefault("AI_PROXY_URL", "https://a-typical-day.onrender.com/analyze");

    String body = createRequestBody(taskId, step, playerResponse);
    HttpRequest request =
        HttpRequest.newBuilder(URI.create(proxyUrl))
            .timeout(Duration.ofSeconds(90))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

    return CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(OpenAIResponseService::parseResponse);
  }

  private static String createRequestBody(String taskId, int step, String playerResponse) {
    return "{\"taskId\":\""
        + escapeJson(taskId)
        + "\",\"step\":"
        + step
        + ",\"playerResponse\":\""
        + escapeJson(playerResponse)
        + "\"}";
  }

  private static Analysis parseResponse(HttpResponse<String> response) {
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw new IllegalStateException(
          "AI proxy request failed with status " + response.statusCode() + ": " + response.body());
    }

    String content = response.body();
    Matcher categoryMatcher = CATEGORY_PATTERN.matcher(content);
    Matcher npcResponseMatcher = NPC_RESPONSE_PATTERN.matcher(content);
    if (!categoryMatcher.find() || !npcResponseMatcher.find()) {
      throw new IllegalStateException("AI proxy returned an invalid dialogue classification.");
    }

    int category = Integer.parseInt(categoryMatcher.group(1));
    if (category < 1 || category > 3) {
      throw new IllegalStateException("AI proxy returned an invalid dialogue category.");
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
