package app;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

public class StoryIntro 
{
    public Scene build(Stage stage, Scene gameScene, User user)
    {
        StackPane introRoot = new StackPane();
        introRoot.setStyle("-fx-background-color: black;");
        
        Rectangle rect = new Rectangle(800, 600, Color.BLACK);
        Rectangle clock = new Rectangle(200, 100, Color.BROWN);
        
        Text time = new Text("7:00");
        time.setFill(Color.WHITE);
        time.setStyle("-fx-font-size: 40px;");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        //temporary until i can find a good png

        Rectangle flash = new Rectangle(800, 600, Color.WHITE);

        //Text title = new Text("ATypical Day");
        //title.setFill(Color.WHITE);
        //title.setStyle("-fx-font-size: 50px;");

        introRoot.getChildren().addAll(rect, clock, time, flash);

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

        SequentialTransition flashes = new SequentialTransition(f1,f2,f3,f4,f5,f6);

        SequentialTransition introAnim = new SequentialTransition(
                clockS,
                clockT,
                flashes
        );

        VBox storyLayout = new VBox(20);
        storyLayout.setStyle("-fx-background-color: black; -fx-alignment: center;");

        Label story = new Label(
                "To see what it is like for someone with special needs to make it through a day of school\n\n" +
                "Four doors stand before you.\n" +
                "Each one holds a task.\n\n" +
                "Complete your tasks before time runs out."
        );
        story.setTextFill(Color.WHITE);
        story.setStyle("-fx-font-size: 18px; -fx-text-alignment: center;");

        Button next = new Button("Next");

        storyLayout.getChildren().addAll(story, next);
        Scene storyScene = new Scene(storyLayout, 800, 600);

        next.setOnAction(e -> {
            stage.setScene(gameScene);
            user.start();
            gameScene.getRoot().requestFocus();
        });

        introAnim.setOnFinished(e -> stage.setScene(storyScene));

        Scene introScene = new Scene(introRoot, 800, 600);

        introAnim.play();

        return introScene;        
    }  
}
