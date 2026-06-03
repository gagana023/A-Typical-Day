package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    styleButton(back);

    Button help = new Button("Help");
    help.setLayoutX(130);
    help.setLayoutY(20);
    styleButton(help);

    Help h = new Help(stage);

    help.setOnAction(
        e -> {
          stage.setScene(h.getHelp(stage));
        });

    NPC npc = new NPC("/player still.png", 40, 100);
    npc.setPosition(320, 310);
    AnchorPane.setLeftAnchor(npc, 320.0);
    AnchorPane.setTopAnchor(npc, 310.0);

    Rectangle checkoutClickArea = new Rectangle(230, 90);
    checkoutClickArea.setFill(Color.TRANSPARENT);
    checkoutClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(checkoutClickArea, 60.0);
    AnchorPane.setTopAnchor(checkoutClickArea, 300.0);

    List<String> bookOptions = UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_BOOK);
    List<String> bookNpcOptions = NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_BOOK);

    DialogueUI bookDialogue = new DialogueUI(bookOptions, bookNpcOptions);

    bookDialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "library_book", stage);
        });

    bookDialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "library_book", stage);
        });

    bookDialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "library_book", stage);
        });
    checkoutClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("library_book")) {
            bookDialogue.showOptions();
          }
        });

    Rectangle studyClickArea = new Rectangle(300, 190);
    studyClickArea.setFill(Color.TRANSPARENT);
    studyClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(studyClickArea, 400.0);
    AnchorPane.setTopAnchor(studyClickArea, 260.0);

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
    AnchorPane.setTopAnchor(roomTimerLabel, 20.0);
    AnchorPane.setLeftAnchor(roomTimerLabel, 240.0);

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(
              HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
          stage.setTitle("A Typical Day");
        });
    Rectangle returnClickArea = new Rectangle(120, 120);
    returnClickArea.setFill(Color.TRANSPARENT);
    returnClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(returnClickArea, 0.0);
    AnchorPane.setTopAnchor(returnClickArea, 395.0);

    List<String> returnOptions =
        UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_RETURN);
    List<String> returnNpcOptions = NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_RETURN);

    DialogueUI returnDialogue = new DialogueUI(returnOptions, returnNpcOptions);

    returnDialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "library_return", stage);
        });

    returnDialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "library_return", stage);
        });

    returnDialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "library_return", stage);
        });

    returnClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("library_return")) {
            returnDialogue.showOptions();
          }
        });

    VBox roomTaskBar = HelloWorld.createRoomTaskBar();
    Button taskToggle = HelloWorld.createRoomTaskToggle(roomTaskBar);

    AnchorPane.setTopAnchor(roomTaskBar, 20.0);
    AnchorPane.setRightAnchor(roomTaskBar, 20.0);

    AnchorPane.setTopAnchor(taskToggle, 25.0);
    AnchorPane.setRightAnchor(taskToggle, 20.0);
    root.getChildren()
        .addAll(
            checkoutClickArea,
            returnClickArea,
            studyClickArea,
            back,
            npc,
            roomTimerLabel,
            help,
            roomTaskBar,
            taskToggle);
    dialogue.addToRoot(root);
    returnDialogue.addToRoot(root);
    bookDialogue.addToRoot(root);

    return root;
  }

  private void styleButton(Button button) {
    String normalStyle =
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 15px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 12;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 12;"
            + "-fx-padding: 8 18 8 18;";

    String hoverStyle =
        "-fx-background-color: #4b5bdc;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 15px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 12;"
            + "-fx-border-color: white;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 12;"
            + "-fx-padding: 8 18 8 18;";

    button.setStyle(normalStyle);
    button.setPrefWidth(100);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
  }
}
