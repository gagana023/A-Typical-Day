package app;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;


public class Cafeteria extends JoinGroupHere{
   public Scene buildPrototypeCafeteria(Stage stage) {
       AnchorPane root = new AnchorPane();
       stage.setTitle("Cafeteria");
       Line l1 = new Line(0, 0, 150, 50);
       Line l2 = new Line(800, 0, 650, 50);
       Line l3 = new Line(0, 450, 150, 400);
       Line l4 = new Line(800, 450, 650, 400);
       Line l5 = new Line(150, 50, 650, 50);
       Line l6 = new Line(150, 50, 150, 400);
       Line l7 = new Line(650, 50, 650, 400);
       Line l8 = new Line(650, 400, 150, 400);
       Rectangle table1 = new Rectangle(25, 450, 200, 50);
       Rectangle tableLegs1 = new Rectangle(115, 450, 20, 75);
       Rectangle table2 = new Rectangle(375, 400, 200, 50);
       Rectangle tableLegs2 = new Rectangle(465, 400, 20, 75);
       Button back = new Button("Back");
       back.setLayoutX(20);
       back.setLayoutY(20);

        //Rectangle npc = new Rectangle(40, 60, Color.BLUE);
        NPC npc = new NPC("/student_sprite.png");

        npc.setPosition(210, 380);
        AnchorPane.setLeftAnchor(npc, 210.0);
        AnchorPane.setTopAnchor(npc, 380.0);

        List<String> options = DialogueEngine.getOptions(DialogueEngine.Room.CAFETERIA);

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

       root.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7, l8, table1, tableLegs1, table2, tableLegs2, back, npc, option1, option2, option3);       

       return new Scene(root, 800, 600);
   }


  
}
