package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    Image cafeteriaImg = new Image(getClass().getResource("/classroom.jpg").toExternalForm());
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

    NPC classmate = new NPC("/student1.png");
    classmate.setPosition(100, 380);

    AnchorPane.setLeftAnchor(classmate, 100.0);
    AnchorPane.setTopAnchor(classmate, 380.0);

    NPC teacher = new NPC("/teacher.png");

    teacher.setPosition(175, 250);

    AnchorPane.setLeftAnchor(teacher, 175.0);
    AnchorPane.setTopAnchor(teacher, 250.0);

    List<String> options;
    List<String> npcOptions;
    String activeClassroomTask;

    if (Tasks.isTaskActive("classroom_homework")) {
      options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_HOMEWORK);
      npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_HOMEWORK);
      activeClassroomTask = "classroom_homework";
    } else if (Tasks.isTaskActive("classroom_problem")) {
      options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM_PROBLEM);
      npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM_PROBLEM);
      activeClassroomTask = "classroom_problem";
    } else {
      options = List.of("...");
      npcOptions = List.of("...");
      activeClassroomTask = "";
    }

    DialogueUI dialogue = new DialogueUI(options, npcOptions);

    teacher.setOnMouseClicked(
        e -> {
          if (!activeClassroomTask.equals("")) {
            dialogue.showOptions();
          }
        });

    dialogue.setOption1Action(
        () -> {
          HelloWorld.handleChoice(1, activeClassroomTask, stage);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.handleChoice(2, activeClassroomTask, stage);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.handleChoice(3, activeClassroomTask, stage);
        });

    back.setOnAction(e -> NavigationHelper.returnToMainScene(stage));

    root.getChildren().addAll(label, back, teacher, classmate);

    dialogue.addToRoot(root);

    return root;
  }
}
