package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Office extends Room {

  public AnchorPane getRoot(Stage stage) {

    AnchorPane root = new AnchorPane();
    root.setId("officeRoot");

    Image cafeteriaImg = new Image(getClass().getResource("/office-image.jpg").toExternalForm());
    ImageView background = new ImageView(cafeteriaImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    Label label = new Label("Welcome to the Office");
    Button back = new Button("Back");

    AnchorPane.setTopAnchor(label, 40.0);
    AnchorPane.setLeftAnchor(label, 320.0);

    AnchorPane.setTopAnchor(back, 20.0);
    AnchorPane.setLeftAnchor(back, 20.0);

    NPC npc = new NPC("/student1.png");

    npc.setPosition(100, 315);
    AnchorPane.setLeftAnchor(npc, 100.0);
    AnchorPane.setTopAnchor(npc, 315.0);

    List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE);
    List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.OFFICE);

    DialogueUI dialogue = new DialogueUI(options, npcOptions);

    Button askExtensionButton = new Button("Ask for Extension");
    askExtensionButton.setVisible(Tasks.isTaskActive("office_extension"));

    AnchorPane.setLeftAnchor(askExtensionButton, 20.0);
    AnchorPane.setTopAnchor(askExtensionButton, 520.0);

    askExtensionButton.setOnAction(
        e -> {
          dialogue.showOptions();
        });

    dialogue.setOption1Action(
        () -> {
          boolean isOver = HelloWorld.user.changeStats(1);
          Tasks.completeTask("office_extension");
          askExtensionButton.setVisible(false);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
          if (isOver)
          {
            System.out.println("Game Over");
            stage.setScene(new GameOver().getScene(stage));
          }
        });

    dialogue.setOption2Action(
        () -> {
          boolean isOver = HelloWorld.user.changeStats(2);
          Tasks.completeTask("office_extension");
          askExtensionButton.setVisible(false);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
          if (isOver)
          {
            System.out.println("Game Over");
            stage.setScene(new GameOver().getScene(stage));
          }
        });

    dialogue.setOption3Action(
        () -> {
          boolean isOver = HelloWorld.user.changeStats(3);
          Tasks.completeTask("office_extension");
          askExtensionButton.setVisible(false);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
          if (isOver)
          {
            System.out.println("Game Over");
            stage.setScene(new GameOver().getScene(stage));
          }
        });

    back.setOnAction(
        e -> {
          System.out.println("Back Clicked");
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });
    // root.getChildren().addAll(
    //         label,
    //         back,
    //         desk,
    //         leg1,
    //         leg2,
    //         leg3,
    //         leg4,
    //         monitor,
    //         stand,
    //         base,
    //         registrar,
    //         npc,
    //         option1,
    //         option2,
    //         option3,
    //         op1, op2, op3
    // );

    root.getChildren().addAll(label, back, npc, askExtensionButton);

    dialogue.addToRoot(root);

    return root;
  }
}
