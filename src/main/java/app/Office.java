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
 * Builds and manages the office room scene.
 *
 * <p>The office includes a background image, a back button, an NPC, and three multi-step
 * interactions: asking the counselor for an extension, picking up a counselor form, and asking
 * about your schedule. Each interaction is a chain of dialogue steps handled by {@link
 * StepDialogueGroup}.
 */
public class Office extends Room {
  /**
   * Creates and returns the root layout for the office scene.
   *
   * <p>This method sets up the office background, title label, back button, NPC, the three
   * multi-step task interactions, and navigation back to the main scene.
   *
   * @param stage the main stage used to display the office scene
   * @return the root AnchorPane containing all office scene elements
   */
  public AnchorPane getRoot(Stage stage) {

    AnchorPane root = new AnchorPane();
    root.setId("officeRoot");

    Image cafeteriaImg = new Image(ResourceLoader.getUrl("/office.png").toExternalForm());
    ImageView background = new ImageView(cafeteriaImg);

    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);

    root.getChildren().add(background);

    Label label = new Label("Welcome to the Office");
    Button back = new Button("Back");
    styleButton(back);

    Button help = new Button("Help");
    help.setLayoutX(130);
    help.setLayoutY(20);
    styleButton(help);

    Help h = new Help(stage);

    help.setOnAction(
        e -> {
                    stage.setScene(h.getHelp(stage, stage.getScene(), true));
        });

    AnchorPane.setTopAnchor(label, 40.0);
    AnchorPane.setLeftAnchor(label, 320.0);

    AnchorPane.setTopAnchor(back, 20.0);
    AnchorPane.setLeftAnchor(back, 20.0);
    stage.setTitle("Office");

    NPC npc = new NPC("/player still.png", 70, 170);

    npc.setPosition(200, 550);
    AnchorPane.setLeftAnchor(npc, 490.0);
    AnchorPane.setTopAnchor(npc, 275.0);

    NPC counselor = new NPC("/counselor.png", 70, 170);
    counselor.setPosition(300, 360);
    AnchorPane.setLeftAnchor(counselor, 410.0);
    AnchorPane.setTopAnchor(counselor, 275.0);

    // "Ask to see counselor" -> "Explain why you need an extension" -> "Thank the counselor"
    StepDialogueGroup extensionDialogue =
        new StepDialogueGroup(
            "office_extension",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_STEP3)));

    Rectangle counselorTagClickArea = new Rectangle(120, 45);
    counselorTagClickArea.setFill(Color.TRANSPARENT);
    counselorTagClickArea.setStroke(Color.TRANSPARENT);

    // Adjust these numbers until the rectangle is over the counselor tag on the desk
    AnchorPane.setLeftAnchor(counselorTagClickArea, 250.0);
    AnchorPane.setTopAnchor(counselorTagClickArea, 330.0);

    counselorTagClickArea.setOnMouseClicked(e -> extensionDialogue.handleClick());

    // "Pick up counselor form" -> "Fill out the form" -> "Return the completed form"
    StepDialogueGroup formDialogue =
        new StepDialogueGroup(
            "office_form",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_FORM),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_FORM_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_FORM_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_FORM),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_FORM_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_FORM_STEP3)));

    Rectangle formClickArea = new Rectangle(130, 90);
    formClickArea.setFill(Color.TRANSPARENT);
    formClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(formClickArea, 280.0);
    AnchorPane.setTopAnchor(formClickArea, 260.0);

    formClickArea.setOnMouseClicked(e -> formDialogue.handleClick());

    // "Ask about your schedule" -> "Point out what looks wrong" -> "Thank them for checking it"
    StepDialogueGroup scheduleDialogue =
        new StepDialogueGroup(
            "office_schedule",
            stage,
            List.of(
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_SCHEDULE),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_SCHEDULE_STEP2),
                UserDialogueEngine.getOptions(UserDialogueEngine.Room.OFFICE_SCHEDULE_STEP3)),
            List.of(
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_SCHEDULE),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_SCHEDULE_STEP2),
                NPCDialogue.getOptions(NPCDialogue.Room.OFFICE_SCHEDULE_STEP3)));

    Rectangle scheduleClickArea = new Rectangle(180, 150);
    scheduleClickArea.setFill(Color.TRANSPARENT);
    scheduleClickArea.setStroke(Color.TRANSPARENT);

    AnchorPane.setLeftAnchor(scheduleClickArea, 205.0);
    AnchorPane.setTopAnchor(scheduleClickArea, 70.0);

    scheduleClickArea.setOnMouseClicked(e -> scheduleDialogue.handleClick());

    Label roomTimerLabel = HelloWorld.createTimerLabel();
    AnchorPane.setTopAnchor(roomTimerLabel, 20.0);
    AnchorPane.setLeftAnchor(roomTimerLabel, 240.0);

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          // HelloWorld.user.stop();
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
            npc,
            counselor,
            formClickArea,
            scheduleClickArea,
            counselorTagClickArea,
            roomTimerLabel,
            help,
            roomTaskBar,
            taskToggle);

    extensionDialogue.addToRoot(root);
    formDialogue.addToRoot(root);
    scheduleDialogue.addToRoot(root);

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