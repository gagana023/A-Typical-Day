package app;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class Cafeteria extends JoinGroupHere{
   public Scene buildPrototypeCafeteria(Stage stage) {
       AnchorPane root = new AnchorPane();


        Image cafeteriaImg = new Image(getClass().getResource("/cafeteria.jpg").toExternalForm());
        ImageView background = new ImageView(cafeteriaImg);

        background.setFitWidth(800);
        background.setFitHeight(600);
        background.setPreserveRatio(false);

        root.getChildren().add(background);


       stage.setTitle("Cafeteria");
       
       Button back = new Button("Back");
       back.setLayoutX(20);
       back.setLayoutY(20);

        NPC npc = new NPC("/student_sprite.png");

        npc.setPosition(210, 380);
        AnchorPane.setLeftAnchor(npc, 210.0);
        AnchorPane.setTopAnchor(npc, 380.0);

        List<String> userOptions = UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA);
        List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA);

        Button option1 = new Button(userOptions.get(0));
        Button option2 = new Button(userOptions.get(1));
        Button option3 = new Button(userOptions.get(2));
        //root.getChildren().addAll(option1, option2, option3);
        Text op1Text = new Text(npcOptions.get(0));
        Text op2Text = new Text(npcOptions.get(1));
        Text op3Text = new Text(npcOptions.get(2));

        // Wrap text so it stays inside box
        op1Text.setWrappingWidth(240);
        op2Text.setWrappingWidth(240);
        op3Text.setWrappingWidth(240);

        // Background boxes
        Rectangle box1 = new Rectangle(260, 70);
        box1.setFill(Color.WHITE);
        box1.setStroke(Color.BLACK);
        box1.setArcWidth(15); // rounded corners
        box1.setArcHeight(15);

        Rectangle box2 = new Rectangle(260, 70);
        box2.setFill(Color.WHITE);
        box2.setStroke(Color.BLACK);
        box2.setArcWidth(15);
        box2.setArcHeight(15);

        Rectangle box3 = new Rectangle(260, 70);
        box3.setFill(Color.WHITE);
        box3.setStroke(Color.BLACK);
        box3.setArcWidth(15);
        box3.setArcHeight(15);

        // Stack text on top of box
        StackPane op1 = new StackPane(box1, op1Text);
        StackPane op2 = new StackPane(box2, op2Text);
        StackPane op3 = new StackPane(box3, op3Text);

        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        
        AnchorPane.setTopAnchor(op1, 100.0);
        AnchorPane.setLeftAnchor(op1, 250.0);

        AnchorPane.setTopAnchor(op2, 100.0);
        AnchorPane.setLeftAnchor(op2, 250.0);

        AnchorPane.setTopAnchor(op3, 100.0);
        AnchorPane.setLeftAnchor(op3, 250.0);

        FadeTransition fastFade = new FadeTransition(Duration.seconds(3.5), option1);
            fastFade.setToValue(0);
            fastFade.play();

        FadeTransition mediumFade = new FadeTransition(Duration.seconds(7), option2);
            mediumFade.setToValue(0);
            mediumFade.play();

        option1.setOnAction(e -> {
                System.out.println("Clicked option 1"); // DEBUG
            op1.setVisible(true);
            HelloWorld.user.changeStats(1);
            Tasks.completeTask("cafeteria_lunch");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
            //stage.setScene(new Stats().buildPrototypeHomeAndStats(stage));
        });

        option2.setOnAction(e -> {
            op2.setVisible(true);
            HelloWorld.user.changeStats(2);
            Tasks.completeTask("cafeteria_lunch");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
            //stage.setScene(new Stats().buildPrototypeHomeAndStats(stage));
        });

        option3.setOnAction(e -> {
            op3.setVisible(true);
            HelloWorld.user.changeStats(3);
            Tasks.completeTask("cafeteria_lunch");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3); 
            //stage.setScene(new Stats().buildPrototypeHomeAndStats(stage));
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

        root.getChildren().addAll(back, npc, option1, option2, option3, op1, op2, op3);
       return new Scene(root, 800, 600);
   }


  
}
