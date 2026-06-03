package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Panic {
  // Source - https://stackoverflow.com/a/16644342
  // Posted by Shreyas Dave, modified by community. See post 'Timeline' for change history
  // Retrieved 2026-05-14, License - CC BY-SA 3.0

  private int x = 0;
  private int y = 0;
  private Stage primaryStage;
  private Timeline timelineX;
  private Timeline timelineY;
  private double originalX;
  private double originalY;

  public void shakeStage() {
    timelineX =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent t) {
                    if (x == 0) {
                      primaryStage.setX(primaryStage.getX() + 10);
                      x = 1;
                    } else {
                      primaryStage.setX(primaryStage.getX() - 10);
                      x = 0;
                    }
                  }
                }));

    timelineX.setCycleCount(Timeline.INDEFINITE);
    timelineX.setAutoReverse(false);
    timelineX.play();

    timelineY =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent t) {
                    if (y == 0) {
                      primaryStage.setY(primaryStage.getY() + 10);
                      y = 1;
                    } else {
                      primaryStage.setY(primaryStage.getY() - 10);
                      y = 0;
                    }
                  }
                }));

    timelineY.setCycleCount(Timeline.INDEFINITE);
    timelineY.setAutoReverse(false);
    timelineY.play();
  }

  public void darkenStage() {
    Parent root = primaryStage.getScene().getRoot();

    if (root instanceof AnchorPane pane) {
      Rectangle darken = new Rectangle(pane.getWidth(), pane.getHeight());
      darken.setFill(javafx.scene.paint.Color.color(0, 0, 0, 0.5));
      darken.setMouseTransparent(true);

      pane.getChildren().add(darken);
    }
  }

  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.originalX = primaryStage.getX();
    this.originalY = primaryStage.getY();
  }

  public void stopPanic() {
    if (timelineX != null) {
      timelineX.stop();
    }

    if (timelineY != null) {
      timelineY.stop();
    }

    if (primaryStage != null) {
      primaryStage.setX(originalX);
      primaryStage.setY(originalY);
    }
  }
}
