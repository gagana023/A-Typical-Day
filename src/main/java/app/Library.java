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
 * <p>The library includes a background image, a back button, an NPC, and three multi-step
 * interactions: checking out a book, joining a study group, and returning a book. Each interaction
 * is a chain of dialogue steps handled by {@link StepDialogueGroup}.
 */
public class Library extends JoinGroupHere {
  /**
   * Creates and returns the root layout for the library scene.
   *
   * <p>This method sets up the library background, clickable bookshelf area, study group area,
   * return area, NPC, and navigation back to the main scene. It also wires up the three multi-step
   * task interactions.
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

    NPC npc = new NPC("/player still.png", 70, 170);
    npc.setPosition(320, 310);
    AnchorPane.setLeftAnchor(npc, 320.0);
    AnchorPane.setTopAnchor(npc, 310.0);

    // "Checkout a book" -> "Hand over your library card" -> "Confirm the due date"
    StepDialogueGroup bookDialogue =
        new StepDialogueGroup(
            "library_book",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_BOOK),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_BOOK_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_BOOK_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_BOOK),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_BOOK_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_BOOK_STEP3)));

    Rectangle checkoutClickArea = new Rectangle(230, 90);
    checkoutClickArea.setFill(Color.TRANSPARENT);
    checkoutClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(checkoutClickArea, 60.0);
    AnchorPane.setTopAnchor(checkoutClickArea, 300.0);

    checkoutClickArea.setOnMouseClicked(e -> bookDialogue.handleClick());

    // "Join a study group" -> "Pull up a chair" -> "Ask the group a question"
    StepDialogueGroup studyDialogue =
        new StepDialogueGroup(
            "library_study",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_STEP3)));

    Rectangle studyClickArea = new Rectangle(300, 190);
    studyClickArea.setFill(Color.TRANSPARENT);
    studyClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(studyClickArea, 400.0);
    AnchorPane.setTopAnchor(studyClickArea, 260.0);

    studyClickArea.setOnMouseClicked(e -> studyDialogue.handleClick());

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

    // "Return a book" -> "Place it in the return area" -> "Confirm it was received"
    StepDialogueGroup returnDialogue =
        new StepDialogueGroup(
            "library_return",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_RETURN),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_RETURN_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY_RETURN_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_RETURN),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_RETURN_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY_RETURN_STEP3)));

    Rectangle returnClickArea = new Rectangle(120, 120);
    returnClickArea.setFill(Color.TRANSPARENT);
    returnClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(returnClickArea, 0.0);
    AnchorPane.setTopAnchor(returnClickArea, 395.0);

    returnClickArea.setOnMouseClicked(e -> returnDialogue.handleClick());

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

    studyDialogue.addToRoot(root);
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