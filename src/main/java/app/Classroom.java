package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Classroom extends Room {

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

    // // BLACKBOARD
    // Rectangle blackBoard = new Rectangle(700, 200);
    // blackBoard.setFill(Color.DARKGREEN);

    // AnchorPane.setTopAnchor(blackBoard, 80.0);
    // AnchorPane.setLeftAnchor(blackBoard, 150.0);

    // // Math problem on board
    // Label mathProblem = new Label("Solve: 3x² + 5x - 2 = 0");
    // mathProblem.setTextFill(Color.WHITE);
    // mathProblem.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    // AnchorPane.setTopAnchor(mathProblem, 160.0);
    // AnchorPane.setLeftAnchor(mathProblem, 350.0);

    // // DESK (table top)
    // Rectangle desk = new Rectangle(400, 40);
    // desk.setFill(Color.BURLYWOOD);

    // AnchorPane.setTopAnchor(desk, 350.0);
    // AnchorPane.setLeftAnchor(desk, 300.0);

    // // TABLE LEGS
    // Rectangle leg1 = new Rectangle(20, 100, Color.SADDLEBROWN);
    // Rectangle leg2 = new Rectangle(20, 100, Color.SADDLEBROWN);
    // Rectangle leg3 = new Rectangle(20, 100, Color.SADDLEBROWN);
    // Rectangle leg4 = new Rectangle(20, 100, Color.SADDLEBROWN);

    // AnchorPane.setTopAnchor(leg1, 390.0);
    // AnchorPane.setLeftAnchor(leg1, 300.0);

    // AnchorPane.setTopAnchor(leg2, 390.0);
    // AnchorPane.setLeftAnchor(leg2, 680.0);

    // AnchorPane.setTopAnchor(leg3, 390.0);
    // AnchorPane.setLeftAnchor(leg3, 420.0);

    // AnchorPane.setTopAnchor(leg4, 390.0);
    // AnchorPane.setLeftAnchor(leg4, 560.0);

    NPC classmate = new NPC("/student_sprite.png");
    classmate.setPosition(100, 380);

    AnchorPane.setLeftAnchor(classmate, 100.0);
    AnchorPane.setTopAnchor(classmate, 380.0);

    NPC teacher = new NPC("/teacher_sprite.png");

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
          HelloWorld.user.changeStats(1);
          Tasks.completeTask(activeClassroomTask);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.user.changeStats(2);
          Tasks.completeTask(activeClassroomTask);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.user.changeStats(3);
          Tasks.completeTask(activeClassroomTask);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    root.getChildren().addAll(label, back, teacher, classmate);

    return root;
  }
}
