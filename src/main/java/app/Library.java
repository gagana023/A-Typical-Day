package app;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** Builds the library room and handles the book and study group tasks. */
public class Library extends JoinGroupHere {

  @Override
  public AnchorPane getRoot(Stage stage) {
    AnchorPane root = new AnchorPane();

    Image libraryImg = new Image(getClass().getResource("/library.jpg").toExternalForm());
    ImageView background = new ImageView(libraryImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    stage.setTitle("Library");

    Button back = new Button("Back");
    back.setLayoutX(20);
    back.setLayoutY(20);

    NPC npc = new NPC("/student_sprite.png");
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
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
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
          HelloWorld.user.changeStats(1);
          Tasks.completeTask("library_study");
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    dialogue.setOption2Action(
        () -> {
          HelloWorld.user.changeStats(2);
          Tasks.completeTask("library_study");
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    dialogue.setOption3Action(
        () -> {
          HelloWorld.user.changeStats(3);
          Tasks.completeTask("library_study");
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
          HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

    root.getChildren().addAll(bookshelfClickArea, studyClickArea, back, npc, checkoutButton);
    dialogue.addToRoot(root);

    return root;
  }
}
