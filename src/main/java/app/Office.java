package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Builds and manages the office room scene.
 *
 * <p>The office includes a background image, a back button, an NPC, and an extension request
 * interaction. If the office extension task is active, the player can ask for an extension and
 * choose from dialogue options that affect player stats.
 */
public class Office extends Room {
  /**
   * Creates and returns the root layout for the office scene.
   *
   * <p>This method sets up the office background, title label, back button, NPC, extension request
   * button, dialogue options, task completion behavior, and navigation back to the main scene.
   *
   * @param stage the main stage used to display the office scene
   * @return the root AnchorPane containing all office scene elements
   */
  public AnchorPane getRoot(Stage stage) {

    AnchorPane root = new AnchorPane();
    root.setId("officeRoot");

    Image cafeteriaImg = new Image(getClass().getResource("/office.png").toExternalForm());
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

    NPC npc = new NPC("/student1.png", 40, 100);

    npc.setPosition(100, 315);
    AnchorPane.setLeftAnchor(npc, 100.0);
    AnchorPane.setTopAnchor(npc, 315.0);

    List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE);
    List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.OFFICE);

    DialogueUI dialogue = new DialogueUI(options, npcOptions);

    Button askExtensionButton = new Button("Ask for Counselor Meeting");
    askExtensionButton.setVisible(Tasks.isTaskActive("office_extension"));

    AnchorPane.setLeftAnchor(askExtensionButton, 20.0);
    AnchorPane.setTopAnchor(askExtensionButton, 520.0);

    askExtensionButton.setOnAction(
        e -> {
          dialogue.showOptions();
        });

    dialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "office_extension", stage);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "office_extension", stage);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "office_extension", stage);
        });

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          // HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });
    root.getChildren().addAll(label, back, npc, askExtensionButton);

    dialogue.addToRoot(root);

    return root;
  }
}
