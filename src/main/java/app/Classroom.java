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
 * Builds and manages the classroom scene.
 *
 * <p>The classroom includes a background image, a teacher NPC, a classmate NPC, a back button, and
 * dialogue options connected to active classroom tasks.
 */
public class Classroom extends Room {

  /**
   * Creates and returns the root layout for the classroom scene.
   *
   * <p>This method sets up the classroom background, places the teacher and classmate NPCs, checks
   * which classroom task is currently active, creates the correct dialogue options, and adds
   * navigation back to the main scene.
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

    AnchorPane.setTopAnchor(label, 40.0);
    AnchorPane.setLeftAnchor(label, 320.0);

    AnchorPane.setTopAnchor(back, 20.0);
    AnchorPane.setLeftAnchor(back, 20.0);

    NPC classmate = new NPC("/player still.png", 70, 170);
    classmate.setPosition(100, 380);

    AnchorPane.setLeftAnchor(classmate, 670.0);
    AnchorPane.setTopAnchor(classmate, 390.0);

    NPC teacher = new NPC("/teacher.png", 70, 170);

    teacher.setPosition(175, 250);

    AnchorPane.setLeftAnchor(teacher, 400.0);
    AnchorPane.setTopAnchor(teacher, 210.0);
    stage.setTitle("Classroom");

    List<String> homeworkOptions =
        UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_HOMEWORK);
    List<String> homeworkNpcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_HOMEWORK);

    DialogueUI homeworkDialogue = new DialogueUI(homeworkOptions, homeworkNpcOptions);

    homeworkDialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "classroom_homework", stage);
        });

    homeworkDialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "classroom_homework", stage);
        });

    homeworkDialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "classroom_homework", stage);
        });

    Rectangle homeworkClickArea = new Rectangle(120, 90);
    homeworkClickArea.setFill(Color.TRANSPARENT);
    homeworkClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(homeworkClickArea, 185.0);
    AnchorPane.setTopAnchor(homeworkClickArea, 275.0);

    homeworkClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("classroom_homework")) {
            homeworkDialogue.showOptions();
          }
        });

    List<String> problemOptions =
        UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_PROBLEM);
    List<String> problemNpcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_PROBLEM);

    DialogueUI problemDialogue = new DialogueUI(problemOptions, problemNpcOptions);

    problemDialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, "classroom_problem", stage);
        });

    problemDialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, "classroom_problem", stage);
        });

    problemDialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, "classroom_problem", stage);
        });

    Rectangle boardClickArea = new Rectangle(310, 180);
    boardClickArea.setFill(Color.TRANSPARENT);
    boardClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(boardClickArea, 195.0);
    AnchorPane.setTopAnchor(boardClickArea, 80.0);

    boardClickArea.setOnMouseClicked(
        e -> {
          if (Tasks.isTaskActive("classroom_problem")) {
            problemDialogue.showOptions();
          }
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
        .addAll(label, back, teacher, classmate, roomTimerLabel, homeworkClickArea, boardClickArea);

    homeworkDialogue.addToRoot(root);
    problemDialogue.addToRoot(root);

    return root;
  }
}
