package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

        DialogueUI dialogue = new DialogueUI(userOptions, npcOptions);

        dialogue.setOption1Action(() -> {
            HelloWorld.user.changeStats(1);
            Tasks.completeTask("cafeteria_friend");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        dialogue.setOption2Action(() -> {
            HelloWorld.user.changeStats(2);
            Tasks.completeTask("cafeteria_friend");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        dialogue.setOption3Action(() -> {
            HelloWorld.user.changeStats(3);
            Tasks.completeTask("cafeteria_friend");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        Rectangle friendClickArea = new Rectangle(250, 250);
        friendClickArea.setFill(Color.TRANSPARENT);
        friendClickArea.setStroke(Color.TRANSPARENT);

        AnchorPane.setLeftAnchor(friendClickArea, 550.0);
        AnchorPane.setTopAnchor(friendClickArea, 350.0);

        friendClickArea.setOnMouseClicked(e -> {
            if (Tasks.isTaskActive("cafeteria_friend")) {
                dialogue.showOptions();
            }
        });

        root.getChildren().addAll(friendClickArea, back, npc);
        dialogue.addToRoot(root);
       return new Scene(root, 800, 600);
   }


  
}
