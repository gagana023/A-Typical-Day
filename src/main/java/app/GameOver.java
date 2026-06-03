package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Builds the game-over screen for the game.
 *
 * <p>This screen appears when the player's stats reach a game-over condition.
 */
public class GameOver {
  /**
   * Creates and returns the game-over scene.
   *
   * <p>The scene displays a black background with red "GAME OVER" text and a play again button.
   *
   * @param stage the main stage used by the application
   * @return the Scene containing the game-over screen
   */
  public Scene getScene(Stage stage) {

    VBox root = new VBox(30);
    root.setStyle("-fx-background-color: black; -fx-alignment: center;");

    Label gameOverText = new Label("GAME OVER");
    gameOverText.setTextFill(Color.RED);
    gameOverText.setFont(new Font("Arial", 64));

    Button playAgain = new Button("Play Again");
    styleButton(playAgain);

    playAgain.setOnAction(
        e -> {
          HelloWorld.restartGame(stage);
        });

    root.getChildren().addAll(gameOverText, playAgain);

    return new Scene(root, 800, 600);
  }

  private void styleButton(Button button) {
    String normalStyle =
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 18px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 12;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 12;"
            + "-fx-padding: 10 24 10 24;";

    String hoverStyle =
        "-fx-background-color: #4b5bdc;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 18px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 12;"
            + "-fx-border-color: white;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 12;"
            + "-fx-padding: 10 24 10 24;";

    button.setStyle(normalStyle);
    button.setPrefWidth(160);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
  }
}
