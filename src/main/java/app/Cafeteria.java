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

    Image cafeteriaImg = new Image(getClass().getResource("/cafeteria.png").toExternalForm());
    ImageView background = new ImageView(cafeteriaImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    stage.setTitle("Cafeteria");

    Button back = new Button("Back");
    back.setLayoutX(20);
    back.setLayoutY(20);

    NPC npc = new NPC("/player still.png", 70, 170);
    npc.setPosition(260, 440);
    AnchorPane.setLeftAnchor(npc, 260.0);
    AnchorPane.setTopAnchor(npc, 440.0);

    List<String> userOptions =
        UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_JOIN_TABLE);
    List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_JOIN_TABLE);

    DialogueUI dialogue = new DialogueUI(userOptions, npcOptions);

    List<String> orderUserOptions =
        UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA);

    List<String> orderNpcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA);

    DialogueUI orderDialogue = new DialogueUI(orderUserOptions, orderNpcOptions);

    dialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "cafeteria_join_table", stage);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "cafeteria_join_table", stage);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "cafeteria_join_table", stage);
        });

    orderDialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "cafeteria_order", stage);
        });

    orderDialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "cafeteria_order", stage);
        });

    orderDialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "cafeteria_order", stage);
        });

    Rectangle orderClickArea = new Rectangle(430, 210);
    orderClickArea.setFill(Color.TRANSPARENT);
    orderClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(orderClickArea, 0.0);
    AnchorPane.setTopAnchor(orderClickArea, 300.0);

    orderClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("cafeteria_order")) {
            orderDialogue.showOptions();
          }
        });

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
          stage.setTitle("A Typical Day");
        });

    Rectangle friendClickArea = new Rectangle(250, 250);
    friendClickArea.setFill(Color.TRANSPARENT);
    friendClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(friendClickArea, 550.0);
    AnchorPane.setTopAnchor(friendClickArea, 350.0);

    friendClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("cafeteria_join_table")) {
            dialogue.showOptions();
          }
        });

    root.getChildren().addAll(friendClickArea, orderClickArea, back, npc);
    dialogue.addToRoot(root);
    orderDialogue.addToRoot(root);
    

    return root;
  }
}
