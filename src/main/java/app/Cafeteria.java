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
 * Builds and manages the cafeteria room scene.
 *
 * <p>The cafeteria room includes a background image, a back button, an NPC friend, and two
 * multi-step interactions: ordering food and asking to join a table. Each interaction is a chain of
 * dialogue steps handled by {@link StepDialogueGroup}.
 */
public class Cafeteria extends JoinGroupHere {
  /**
   * Creates and returns the root layout for the cafeteria scene.
   *
   * <p>This method sets up the cafeteria background, places the NPC, wires up the two multi-step
   * task interactions, and adds navigation back to the main scene.
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
    npc.setPosition(260, 440);
    AnchorPane.setLeftAnchor(npc, 260.0);
    AnchorPane.setTopAnchor(npc, 440.0);

    // "Ask to join a table" -> "Sit down with the group" -> "Introduce yourself"
    StepDialogueGroup joinTableDialogue =
        new StepDialogueGroup(
            "cafeteria_join_table",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_JOIN_TABLE),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_JOIN_TABLE_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_JOIN_TABLE_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_JOIN_TABLE),
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_JOIN_TABLE_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_JOIN_TABLE_STEP3)));

    // "Order based on diet restrictions" -> "Choose a meal" -> "Pay and say thank you"
    StepDialogueGroup orderDialogue =
        new StepDialogueGroup(
            "cafeteria_order",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.CAFETERIA_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA),
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.CAFETERIA_STEP3)));

    Rectangle orderClickArea = new Rectangle(430, 210);
    orderClickArea.setFill(Color.TRANSPARENT);
    orderClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(orderClickArea, 0.0);
    AnchorPane.setTopAnchor(orderClickArea, 300.0);

    orderClickArea.setOnMouseClicked(e -> orderDialogue.handleClick());

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

    Rectangle friendClickArea = new Rectangle(250, 250);
    friendClickArea.setFill(Color.TRANSPARENT);
    friendClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(friendClickArea, 550.0);
    AnchorPane.setTopAnchor(friendClickArea, 350.0);

    friendClickArea.setOnMouseClicked(e -> joinTableDialogue.handleClick());

    VBox roomTaskBar = HelloWorld.createRoomTaskBar();
    Button taskToggle = HelloWorld.createRoomTaskToggle(roomTaskBar);

    AnchorPane.setTopAnchor(roomTaskBar, 20.0);
    AnchorPane.setRightAnchor(roomTaskBar, 20.0);

    AnchorPane.setTopAnchor(taskToggle, 25.0);
    AnchorPane.setRightAnchor(taskToggle, 20.0);

    root.getChildren()
        .addAll(
            friendClickArea,
            orderClickArea,
            back,
            npc,
            roomTimerLabel,
            help,
            roomTaskBar,
            taskToggle);

    joinTableDialogue.addToRoot(root);
    orderDialogue.addToRoot(root);

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