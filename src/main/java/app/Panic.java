package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Panic 
{
  // Source - https://stackoverflow.com/a/16644342
// Posted by Shreyas Dave, modified by community. See post 'Timeline' for change history
// Retrieved 2026-05-14, License - CC BY-SA 3.0

  private int x = 0;
  private int y = 0;
  private Stage primaryStage;

  public void shakeStage() {
    Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() 
    {
      @Override
        public void handle(ActionEvent t) 
        {
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


    Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() 
    {
        @Override
        public void handle(ActionEvent t) {
        if (y == 0) 
        {
            primaryStage.setY(primaryStage.getY() + 10);
            y = 1;
        } 
        else 
        {
            primaryStage.setY(primaryStage.getY() - 10);
            y = 0;
        }
    }
    }));

        timelineY.setCycleCount(Timeline.INDEFINITE);
        timelineY.setAutoReverse(false);
        timelineY.play();
    }   

    public void darkenStage()
    {
        Parent root = primaryStage.getScene().getRoot();

        if (root instanceof AnchorPane pane) {
            Rectangle darken = new Rectangle(pane.getWidth(), pane.getHeight());
            darken.setFill(javafx.scene.paint.Color.color(0, 0, 0, 0.5));

            pane.getChildren().add(darken);
        }
        Button back = new Button("Back");
        AnchorPane.setTopAnchor(back, 20.0);
        AnchorPane.setLeftAnchor(back, 20.0);
        back.setOnAction(e -> {
            // go to main screen
            primaryStage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
        });

        if (root instanceof AnchorPane pane) {
            pane.getChildren().add(back);
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

