package app;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    Image bathroomImg = new Image(getClass().getResource("/bathroom.png").toExternalForm());
    Image bedroomImg = new Image(getClass().getResource("/bedroom.png").toExternalForm());
    Image downstairsImg = new Image(getClass().getResource("/downstairs.png").toExternalForm());
    Image drivingImg = new Image(getClass().getResource("/driving.png").toExternalForm());
    sceneView.setImage(bedroomImg);
    Rectangle flash = new Rectangle(800, 600, Color.WHITE);

    introRoot.getChildren().addAll(rect, sceneView, clock, time, flash);

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

    PauseTransition t1 = new PauseTransition(Duration.seconds(1));
    t1.setOnFinished(
        e -> {
          sceneView.setVisible(true);
          clock.setOpacity(0);
          time.setOpacity(0);
          sceneView.setImage(bathroomImg);
        });

    PauseTransition t2 = new PauseTransition(Duration.seconds(1));
    t2.setOnFinished(e -> sceneView.setImage(bedroomImg));

    PauseTransition t3 = new PauseTransition(Duration.seconds(1));
    t3.setOnFinished(e -> sceneView.setImage(downstairsImg));

    PauseTransition t4 = new PauseTransition(Duration.seconds(1));
    t4.setOnFinished(e -> sceneView.setImage(drivingImg));

    PauseTransition t5 = new PauseTransition(Duration.seconds(1));
    t5.setOnFinished(e -> sceneView.setImage(drivingImg));

    VBox storyLayout = new VBox(20);
    storyLayout.setStyle("-fx-background-color: white; -fx-alignment: center;");

    Label story =
        new Label(
            "To see what it is like for someone with special needs to make it through a day of"
                + " school\n\n"
                + "Four doors stand before you.\n"
                + "Each one holds a task.\n\n"
                + "Complete your tasks before time runs out.");
    story.setTextFill(Color.BLACK);
    story.setStyle("-fx-font-size: 18px; -fx-text-alignment: center;");

    Button next = new Button("Next");
    Button help = new Button("Help");

    storyLayout.getChildren().addAll(story, next, help);
    Scene storyScene = new Scene(storyLayout, 800, 600);

    next.setOnAction(
        e -> {
          stage.setScene(gameScene);
          user.start();
          gameScene.getRoot().requestFocus();
        });
    Help h = new Help(stage);

    help.setOnAction(
        e -> {
          user.stop();
          stage.setScene(h.getHelp(stage));
        });

    storySequence = new SequentialTransition(introAnim, t1, t2, t3, t4, t5);

    storySequence.setOnFinished(
        e -> {
          System.out.println("intro done");
          stage.setScene(storyScene);
        });

    Scene introScene = new Scene(introRoot, 800, 600);
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
