package app;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.List;
import java.util.Random;


public class Library extends JoinGroupHere{
   public Scene buildPrototypeLibrary(Stage stage) {
      AnchorPane root = new AnchorPane();
      stage.setTitle("Library");
      Line l1 = new Line(0, 100, 300, 150);
      Line l2 = new Line(800, 100, 300, 150);
      Line l3 = new Line(0, 500, 300, 450);
      Line l4 = new Line(800, 500, 300, 450);
      Line l5 = new Line(300, 150, 300, 450);
      Ellipse table = new Ellipse(250, 450, 100, 50);
      Rectangle tableLegs = new Rectangle(225, 450, 50, 75);
      Button back = new Button("Back");
      back.setLayoutX(20);
      back.setLayoutY(20);

      NPC npc = new NPC("/student_sprite.png");
      // Random rand = new Random();
      //   double minX = 100;
      //   double maxX = 700;
      //   double minY = 150;
      //   double maxY = 500;

      //   double randomX = minX + rand.nextDouble() * (maxX - minX);
      //   double randomY = minY + rand.nextDouble() * (maxY - minY);
      //   npc.setPosition(randomX,randomY);
      //   AnchorPane.setLeftAnchor(npc, randomX);
      //   AnchorPane.setTopAnchor(npc, randomY);

      npc.setPosition(320, 310);
        AnchorPane.setLeftAnchor(npc, 320.0);
        AnchorPane.setTopAnchor(npc, 310.0);

      List<String> options = DialogueEngine.getOptions(DialogueEngine.Room.LIBRARY);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));
        //root.getChildren().addAll(option1, option2, option3);

        FadeTransition fastFade = new FadeTransition(Duration.seconds(3.5), option1);
            fastFade.setToValue(0);
            fastFade.play();

        FadeTransition mediumFade = new FadeTransition(Duration.seconds(7), option2);
            mediumFade.setToValue(0);
            mediumFade.play();

        option1.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);        
        });

        AnchorPane.setTopAnchor(option1, 200.0);
        AnchorPane.setLeftAnchor(option1, 300.0);

        AnchorPane.setTopAnchor(option2, 250.0);
        AnchorPane.setLeftAnchor(option2, 300.0);

        AnchorPane.setTopAnchor(option3, 300.0);
        AnchorPane.setLeftAnchor(option3, 300.0);
        

      back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
      });
        
      root.getChildren().addAll(l1, l2, l3, l4, l5, table, tableLegs, back, npc, option1, option2, option3);
      
      return new Scene(root, 800, 600);
   }
}
