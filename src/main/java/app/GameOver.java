package app;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
   * <p>The scene displays a black background with red "GAME OVER" text.
   *
   * @param stage the main stage used by the application
   * @return the Scene containing the game-over screen
   */
  public Scene getScene(Stage stage) {

    StackPane root = new StackPane();
    root.setStyle("-fx-background-color: black;");

    Label gameOverText = new Label("GAME OVER");
    gameOverText.setTextFill(Color.RED);
    gameOverText.setFont(new Font("Arial", 64));

    root.getChildren().add(gameOverText);

    return new Scene(root, 800, 600);
  }
}
