package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Builds and manages the cafeteria room scene.
 *
 * <p>The cafeteria room includes a background image, a back button, an NPC friend, and a clickable
 * area that opens dialogue options for completing the cafeteria friend task.
 */
public class Cafeteria extends JoinGroupHere {
  /**
   * Creates and returns the root layout for the cafeteria scene.
   *
   * <p>This method sets up the cafeteria background, places the NPC, creates the dialogue options,
   * connects each dialogue option to stat changes and task completion, and adds navigation back to
   * the main scene.
   *
   * @param stage the main stage used to display the cafeteria scene
   * @return the root {@link AnchorPane} containing all cafeteria scene elements
   */
  @Override
  public AnchorPane getRoot(Stage stage) {
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

    NPC npc = new NPC("/student1.png");
    npc.setPosition(210, 380);
    AnchorPane.setLeftAnchor(npc, 210.0);
    AnchorPane.setTopAnchor(npc, 380.0);

    List<String> userOptions = UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA);
    List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA);

    DialogueUI dialogue = new DialogueUI(userOptions, npcOptions);

    dialogue.setOption1Action(
        () -> {
          boolean isOver = HelloWorld.user.changeStats(1);
          Tasks.completeTask("cafeteria_friend");
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
          Tasks.completeTask("cafeteria_friend");
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
          Tasks.completeTask("cafeteria_friend");
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
          if (isOver)
          {
            System.out.println("Game Over");
            stage.setScene(new GameOver().getScene(stage));
          }
        });

    back.setOnAction(e -> NavigationHelper.returnToMainScene(stage));

    Rectangle friendClickArea = new Rectangle(250, 250);
    friendClickArea.setFill(Color.TRANSPARENT);
    friendClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(friendClickArea, 550.0);
    AnchorPane.setTopAnchor(friendClickArea, 350.0);

    friendClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("cafeteria_friend")) {
            dialogue.showOptions();
          }
        });

    root.getChildren().addAll(friendClickArea, back, npc);
    dialogue.addToRoot(root);

    return root;
  }
}
