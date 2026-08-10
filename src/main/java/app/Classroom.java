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
 * Builds and manages the classroom scene.
 *
 * <p>The classroom includes a background image, a teacher NPC, a classmate NPC, a back button, and
 * two multi-step interactions: turning in late homework and solving the board problem. Each
 * interaction is a chain of dialogue steps handled by {@link StepDialogueGroup}.
 */
public class Classroom extends Room {

  /**
   * Creates and returns the root layout for the classroom scene.
   *
   * <p>This method sets up the classroom background, places the teacher and classmate NPCs, wires
   * up the two multi-step task interactions, and adds navigation back to the main scene.
   *
   * @param stage the main stage used to display the classroom scene
   * @return the root AnchorPane containing all classroom scene elements
   */
  public AnchorPane getRoot(Stage stage) {

    AnchorPane root = new AnchorPane();

    Image cafeteriaImg = new Image(getClass().getResource("/classroom.png").toExternalForm());
    ImageView background = new ImageView(cafeteriaImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    Label label = new Label("Welcome to the Classroom");
    Button back = new Button("Back");
    styleButton(back);

    AnchorPane.setTopAnchor(label, 40.0);
    AnchorPane.setLeftAnchor(label, 320.0);

    AnchorPane.setTopAnchor(back, 20.0);
    AnchorPane.setLeftAnchor(back, 20.0);

    Button help = new Button("Help");
    help.setLayoutX(130);
    help.setLayoutY(20);
    styleButton(help);

    Help h = new Help(stage);

    help.setOnAction(
        e -> {
          stage.setScene(h.getHelp(stage));
        });

    NPC classmate = new NPC("/player still.png", 70, 170);
    classmate.setPosition(100, 380);

    AnchorPane.setLeftAnchor(classmate, 670.0);
    AnchorPane.setTopAnchor(classmate, 390.0);

    NPC teacher = new NPC("/teacher.png", 70, 170);

    teacher.setPosition(175, 250);

    AnchorPane.setLeftAnchor(teacher, 400.0);
    AnchorPane.setTopAnchor(teacher, 210.0);
    stage.setTitle("Classroom");

    // "Turn in late homework" -> "Explain why it's late" -> "Ask if there's a penalty"
    StepDialogueGroup homeworkDialogue =
        new StepDialogueGroup(
            "classroom_homework",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_HOMEWORK),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_HOMEWORK_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_HOMEWORK_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_HOMEWORK),
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_HOMEWORK_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_HOMEWORK_STEP3)));

    Rectangle homeworkClickArea = new Rectangle(120, 90);
    homeworkClickArea.setFill(Color.TRANSPARENT);
    homeworkClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(homeworkClickArea, 185.0);
    AnchorPane.setTopAnchor(homeworkClickArea, 275.0);

    homeworkClickArea.setOnMouseClicked(e -> homeworkDialogue.handleClick());

    // "Solve the board problem" -> "Tell the teacher if you can solve it" -> "Try the first step"
    StepDialogueGroup problemDialogue =
        new StepDialogueGroup(
            "classroom_problem",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_PROBLEM),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_PROBLEM_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_PROBLEM_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_PROBLEM),
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_PROBLEM_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_PROBLEM_STEP3)));

    Rectangle boardClickArea = new Rectangle(310, 180);
    boardClickArea.setFill(Color.TRANSPARENT);
    boardClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(boardClickArea, 195.0);
    AnchorPane.setTopAnchor(boardClickArea, 80.0);

    boardClickArea.setOnMouseClicked(e -> problemDialogue.handleClick());

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

    VBox roomTaskBar = HelloWorld.createRoomTaskBar();
    Button taskToggle = HelloWorld.createRoomTaskToggle(roomTaskBar);

    AnchorPane.setTopAnchor(roomTaskBar, 20.0);
    AnchorPane.setRightAnchor(roomTaskBar, 20.0);

    AnchorPane.setTopAnchor(taskToggle, 25.0);
    AnchorPane.setRightAnchor(taskToggle, 20.0);

    root.getChildren()
        .addAll(
            label,
            back,
            teacher,
            classmate,
            roomTimerLabel,
            homeworkClickArea,
            boardClickArea,
            help,
            roomTaskBar,
            taskToggle);

    homeworkDialogue.addToRoot(root);
    problemDialogue.addToRoot(root);

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