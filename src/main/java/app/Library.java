package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Builds and manages the library room scene.
 *
 * <p>The library includes a background image, a back button, an NPC, a bookshelf interaction for
 * the book task, and a study group interaction with dialogue options for the study task.
 */
public class Library extends JoinGroupHere {
  /**
   * Creates and returns the root layout for the library scene.
   *
   * <p>This method sets up the library background, clickable bookshelf area, checkout button, study
   * group dialogue area, NPC, and navigation back to the main scene. It also completes
   * library-related tasks when the correct interactions are used.
   *
   * @param stage the main stage used to display the library scene
   * @return the root AnchorPane containing all library scene elements
   */
  @Override
  public AnchorPane getRoot(Stage stage) {
    AnchorPane root = new AnchorPane();

    Image libraryImg = new Image(getClass().getResource("/library.png").toExternalForm());
    ImageView background = new ImageView(libraryImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    stage.setTitle("Library");

    Button back = new Button("Back");
    back.setLayoutX(20);
    back.setLayoutY(20);

    NPC npc = new NPC("/player11.png", 40, 100);
    npc.setPosition(320, 310);
    AnchorPane.setLeftAnchor(npc, 320.0);
    AnchorPane.setTopAnchor(npc, 310.0);

    Rectangle bookshelfClickArea = new Rectangle(400, 600);
    bookshelfClickArea.setFill(Color.TRANSPARENT);
    bookshelfClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(bookshelfClickArea, 0.0);
    AnchorPane.setTopAnchor(bookshelfClickArea, 0.0);

    Button checkoutButton = new Button("Checkout");
    checkoutButton.setVisible(false);

    AnchorPane.setLeftAnchor(checkoutButton, 430.0);
    AnchorPane.setTopAnchor(checkoutButton, 280.0);

    bookshelfClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("library_book")) {
            checkoutButton.setVisible(true);
          }
        });

    checkoutButton.setOnAction(
        e -> {
          Tasks.completeTask("library_book");
          checkoutButton.setVisible(false);
          HelloWorld.updateTasks(
              HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
        });

    Rectangle studyClickArea = new Rectangle(250, 180);
    studyClickArea.setFill(Color.TRANSPARENT);
    studyClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(studyClickArea, 250.0);
    AnchorPane.setTopAnchor(studyClickArea, 340.0);

    List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY);
    List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY);

    DialogueUI dialogue = new DialogueUI(options, npcOptions);

    studyClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("library_study")) {
            dialogue.showOptions();
          }
        });

    dialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "library_study", stage);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "library_study", stage);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "library_study", stage);
        });

    Label roomTimerLabel = HelloWorld.createTimerLabel();
    AnchorPane.setTopAnchor(roomTimerLabel, 80.0);
    AnchorPane.setRightAnchor(roomTimerLabel, 20.0);

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(
              HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
          stage.setTitle("A Typical Day");
        });

    root.getChildren()
        .addAll(bookshelfClickArea, studyClickArea, back, npc, checkoutButton, roomTimerLabel);
    dialogue.addToRoot(root);

    return root;
  }
}
