package app;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver {

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