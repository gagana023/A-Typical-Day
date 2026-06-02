package app;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Creates and plays the opening story introduction for the game.
 *
 * <p>The intro shows a short animated sequence, transitions to a story screen, and then allows the
 * player to continue into the main game scene.
 */
public class StoryIntro {
  /** The sequence of animations used for the story introduction. */
  private SequentialTransition storySequence;

  /**
   * Builds and returns the opening intro scene.
   *
   * <p>This method creates the intro animation, story screen, next button, and help button. The
   * next button switches to the main game scene and starts the user.
   *
   * @param stage the main stage used to switch between scenes
   * @param gameScene the main game scene shown after the intro
   * @param user the user/player controlled during the game
   * @return the intro Scene that plays before the story screen
   */
  public Scene build(Stage stage, Scene gameScene, User user) {
    StackPane introRoot = new StackPane();
    introRoot.setStyle("-fx-background-color: black;");

    Rectangle rect = new Rectangle(800, 600, Color.BLACK);
    Rectangle clock = new Rectangle(200, 100, Color.BROWN);

    Text time = new Text("7:00");
    time.setFill(Color.WHITE);
    time.setStyle("-fx-font-size: 40px;");
    time.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    ImageView sceneView = new ImageView();
    sceneView.setFitWidth(800);
    sceneView.setFitHeight(600);
    sceneView.setVisible(false);

    Image titleImg = new Image(getClass().getResource("/title.png").toExternalForm());
    sceneView.setImage(titleImg);
    Rectangle flash = new Rectangle(800, 600, Color.WHITE);

    Button next = new Button("Next");
    Button help = new Button("Help");
    next.setPrefSize(140, 40);
    help.setPrefSize(140, 40);
    next.setStyle(
        "-fx-font-size: 20px; -fx-background-color: #eb4084 ; -fx-text-fill: white;"
            + " -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");
    help.setStyle(
        "-fx-font-size: 20px; -fx-background-color: #eb4084 ; -fx-text-fill: white;"
            + " -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");
    HBox storyLayout = new HBox(20, next, help);
    storyLayout.setStyle("-fx-alignment: bottom-center; -fx-padding: 0 0 115 0;");
    storyLayout.setVisible(false);
    next.setOnAction(
        e -> {
          stage.setScene(gameScene);
          user.start();
          HelloWorld.startGameTimer();
          gameScene.getRoot().requestFocus();
        });
    Help h = new Help(stage);

    help.setOnAction(
        e -> {
          user.stop();
          stage.setScene(h.getHelp(stage));
        });

    introRoot.getChildren().addAll(rect, sceneView, clock, time, flash, storyLayout);
    Scene introScene = new Scene(introRoot, 800, 600);

    FadeTransition clockS = new FadeTransition(Duration.seconds(1), clock);
    clockS.setToValue(1);

    FadeTransition clockT = new FadeTransition(Duration.seconds(1), time);
    clockT.setToValue(1);

    FadeTransition f1 = new FadeTransition(Duration.seconds(0.2), flash);
    f1.setToValue(0.8);

    FadeTransition f2 = new FadeTransition(Duration.seconds(0.2), flash);
    f2.setToValue(0);

    FadeTransition f3 = new FadeTransition(Duration.seconds(0.2), flash);
    f3.setToValue(0.8);

    FadeTransition f4 = new FadeTransition(Duration.seconds(0.2), flash);
    f4.setToValue(0);

    FadeTransition f5 = new FadeTransition(Duration.seconds(0.2), flash);
    f5.setToValue(0.8);

    FadeTransition f6 = new FadeTransition(Duration.seconds(0.2), flash);
    f6.setToValue(0);

    SequentialTransition flashes = new SequentialTransition(f1, f2, f3, f4, f5, f6);

    SequentialTransition introAnim = new SequentialTransition(clockS, clockT, flashes);

    PauseTransition showTitle = new PauseTransition(Duration.seconds(0.5));
    showTitle.setOnFinished(
        e -> {
          sceneView.setVisible(true);
          clock.setOpacity(0);
          time.setOpacity(0);
          sceneView.setImage(titleImg);
        });

    PauseTransition titlePause = new PauseTransition(Duration.seconds(1));

    Label story =
        new Label(
            "To see what it is like for someone with special needs to make it through a day of"
                + " school\n\n"
                + "Four doors stand before you.\n"
                + "Each one holds a task.\n\n"
                + "Complete your tasks before time runs out.");
    story.setTextFill(Color.BLACK);
    story.setStyle("-fx-font-size: 18px; -fx-text-alignment: center;");

    storySequence = new SequentialTransition(introAnim, showTitle, titlePause);

    storySequence.setOnFinished(
        e -> {
          storyLayout.setVisible(true);
        });

    return introScene;
  }

  /**
   * Plays the story introduction animation.
   *
   * <p>If the animation sequence has not been created yet, this method does nothing.
   */
  public void play() {
    if (storySequence != null) {
      storySequence.play();
    }
  }
}
