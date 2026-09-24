package app;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** A short, playable prologue that teaches the actual game loop through practice. */
public class Tutorial {

  private enum State {
    MOVEMENT,
    INTERACTION,
    TASK,
    BULLY,
    CHOICE,
    COMPLETE
  }

  private static final double WIDTH = 800;
  private static final double HEIGHT = 600;
  private static final double GUIDE_X = 585;
  private static final double GUIDE_Y = 270;
  private static final double TASK_X = 175;
  private static final double TASK_Y = 205;

  private final Stage stage;
  private final Scene gameScene;
  private final PlayerInput input = new PlayerInput();
  private final MovementController movement = new MovementController(input);
  private final PlayerAnimation playerAnimation =
      new PlayerAnimation("/walk-front.png", 4, 3, 12);
  private final Player player = new Player(playerAnimation.getCanvas());
  private final CollisionChecker collisionChecker = new CollisionChecker();

  private State state = State.MOVEMENT;
  private boolean hasMoved;
  private boolean paused;
  private boolean bullyWasClose;
  private int focus = 3;
  private AnimationTimer timer;
  private Label objective;
  private Label feedback;
  private Label status;
  private Label controls;
  private Label taskProgress;
  private Circle guide;
  private Rectangle taskMarker;
  private Bully bully;
  private Button spaceButton;
  private Button groupButton;
  private Button continueButton;
  private Button skipButton;

  /** Creates a tutorial that hands control to the prepared hallway scene. */
  public Tutorial(Stage stage, Scene gameScene) {
    this.stage = stage;
    this.gameScene = gameScene;
  }

  /** Builds the playable prologue scene. */
  public Scene build() {
    AnchorPane root = new AnchorPane();
    root.setPrefSize(WIDTH, HEIGHT);
    root.setStyle(
        "-fx-background-color: linear-gradient(to bottom, #13243b, #274052);"
            + " -fx-font-family: 'Trebuchet MS';");

    Rectangle playArea = new Rectangle(32, 112, 736, 402);
    playArea.setArcWidth(20);
    playArea.setArcHeight(20);
    playArea.setFill(Color.web("#36586a"));
    playArea.setStroke(Color.web("#6c9aaa"));
    playArea.setStrokeWidth(2);

    Label title = label("FIRST DAY", 22, Color.WHITE);
    Label subtitle = label("A quiet place to learn the rhythm of the day", 14, Color.web("#d9e8ef"));
    AnchorPane.setTopAnchor(title, 22.0);
    AnchorPane.setLeftAnchor(title, 32.0);
    AnchorPane.setTopAnchor(subtitle, 55.0);
    AnchorPane.setLeftAnchor(subtitle, 34.0);

    objective = label("OBJECTIVE\nMove around with W A S D.", 17, Color.WHITE);
    objective.setWrapText(true);
    objective.setStyle(panelStyle());
    AnchorPane.setTopAnchor(objective, 18.0);
    AnchorPane.setRightAnchor(objective, 32.0);

    status = label("FOCUS   3 / 3", 14, Color.WHITE);
    status.setStyle("-fx-background-color: rgba(12,22,35,.88); -fx-padding: 9 12;");
    AnchorPane.setTopAnchor(status, 72.0);
    AnchorPane.setRightAnchor(status, 32.0);

    controls = label("W A S D  move     E  interact     P  pause", 13, Color.web("#d9e8ef"));
    AnchorPane.setBottomAnchor(controls, 22.0);
    AnchorPane.setLeftAnchor(controls, 34.0);

    feedback = label("", 14, Color.web("#d5f5df"));
    feedback.setWrapText(true);
    feedback.setMaxWidth(390);
    AnchorPane.setBottomAnchor(feedback, 55.0);
    AnchorPane.setLeftAnchor(feedback, 34.0);

    guide = new Circle(22, Color.web("#e7b96d"));
    guide.setStroke(Color.WHITE);
    guide.setStrokeWidth(2);
    AnchorPane.setLeftAnchor(guide, GUIDE_X);
    AnchorPane.setTopAnchor(guide, GUIDE_Y);
    //AnchorPane.setLeftAnchor(guideName, GUIDE_X - 10);
    //AnchorPane.setTopAnchor(guideName, GUIDE_Y + 30);

    taskMarker = new Rectangle(40, 40, Color.web("#83d6b2"));
    taskMarker.setArcWidth(10);
    taskMarker.setArcHeight(10);
    taskMarker.setStroke(Color.WHITE);
    taskMarker.setVisible(false);
    AnchorPane.setLeftAnchor(taskMarker, TASK_X);
    AnchorPane.setTopAnchor(taskMarker, TASK_Y);
    Label taskName = label("TASK", 12, Color.WHITE);
    taskName.setVisible(false);
    AnchorPane.setLeftAnchor(taskName, TASK_X + 5);
    AnchorPane.setTopAnchor(taskName, TASK_Y + 44);

    taskProgress = label("", 13, Color.WHITE);
    taskProgress.setWrapText(true);
    taskProgress.setMaxWidth(270);
    taskProgress.setStyle(panelStyle());
    taskProgress.setVisible(false);
    AnchorPane.setLeftAnchor(taskProgress, TASK_X + 55);
    AnchorPane.setTopAnchor(taskProgress, TASK_Y - 4);

    spaceButton = button("Ask for a little space");
    groupButton = button("Stay with the group");
    continueButton = button("Start the school day");
    skipButton = button("Skip");
    skipButton.setMinWidth(90);
    skipButton.setStyle(
        "-fx-background-color: #173047; -fx-text-fill: white; -fx-font-size: 13px;"
            + " -fx-padding: 7 12; -fx-border-color: #b9e3ed; -fx-border-width: 1;"
            + " -fx-background-radius: 6; -fx-border-radius: 6;");
    spaceButton.setVisible(false);
    groupButton.setVisible(false);
    continueButton.setVisible(false);
    skipButton.setVisible(true);
    spaceButton.setOnAction(e -> chooseResponse("You made room to notice what you need.", 3));
    groupButton.setOnAction(e -> chooseResponse("You stayed connected. That can be right for you, too.", 2));
    continueButton.setOnAction(e -> complete());
    skipButton.setOnAction(e -> complete());
    AnchorPane.setRightAnchor(spaceButton, 32.0);
    AnchorPane.setRightAnchor(groupButton, 32.0);
    AnchorPane.setRightAnchor(continueButton, 32.0);
    AnchorPane.setRightAnchor(skipButton, 32.0);
    AnchorPane.setBottomAnchor(spaceButton, 145.0);
    AnchorPane.setBottomAnchor(groupButton, 93.0);
    AnchorPane.setBottomAnchor(continueButton, 42.0);
    AnchorPane.setBottomAnchor(skipButton, 20.0);

    root.getChildren()
        .addAll(playArea, title, subtitle, objective, status, controls, feedback, guide,
            taskMarker, taskName, taskProgress, player.getCanvas(), spaceButton, groupButton,
            continueButton, skipButton);

    Scene scene = new Scene(root, WIDTH, HEIGHT);
    player.setCoordinates(120, 300);
    player.updateCanvasPosition();
    input.connect(scene);
    scene.addEventHandler(
        javafx.scene.input.KeyEvent.KEY_PRESSED,
        event -> {
          if (event.getCode() == KeyCode.P) {
            paused = !paused;
            feedback.setText(paused ? "Paused. Press P when you are ready." : "");
          } else if (event.getCode() == KeyCode.E) {
            interact();
          }
        });

    timer =
        new AnimationTimer() {
          @Override
          public void handle(long now) {
            if (paused || state == State.COMPLETE) {
              return;
            }
            boolean moving = movement.update(player, scene);
            playerAnimation.updateAnimation(moving);
            playerAnimation.renderFrame();
            player.updateCanvasPosition();
            updateTutorial(moving, scene);
          }
        };
    timer.start();
    Platform.runLater(root::requestFocus);
    return scene;
  }

  private void updateTutorial(boolean moving, Scene scene) {
    if (state == State.MOVEMENT && moving && !hasMoved) {
      hasMoved = true;
      state = State.INTERACTION;
      objective.setText("OBJECTIVE\nWalk near the gold guide, then press E.");
      feedback.setText("Movement learned. The prompt changed because you completed the step.");
    }

    if (state == State.TASK && near(TASK_X, TASK_Y)) {
      controls.setText("E  interact with the task marker     P  pause");
    } else if (state == State.INTERACTION && near(GUIDE_X, GUIDE_Y)) {
      controls.setText("E  talk to the guide     P  pause");
    }

    if (state == State.BULLY && bully != null) {
      bully.moveTowardPlayer(scene, player);
      bullyWasClose |= distanceTo(bully) < 170;
      if (collisionChecker.isCollidingWithPadding(player.getCanvas(), bully, 8, 8)) {
        focus = Math.max(1, focus - 1);
        updateStatus();
        bully.spawnRandomly(scene);
        feedback.setText("That was a bump, not a failure. Move away and try again.");
      } else if (bullyWasClose && distanceTo(bully) > 210) {
        state = State.CHOICE;
        bully.setVisible(false);
        objective.setText("OBJECTIVE\nChoose how you want to handle a busy moment.");
        controls.setText("Choose a response on the right     P  pause");
        feedback.setText("You created space. In the real game, bullies can drain your social battery.");
        spaceButton.setVisible(true);
        groupButton.setVisible(true);
      }
    }
  }

  private void interact() {
    if (state == State.INTERACTION && near(GUIDE_X, GUIDE_Y)) {
      state = State.TASK;
      guide.setFill(Color.web("#9ee2b0"));
      taskMarker.setVisible(true);
      taskProgress.setVisible(true);
      taskProgress.setText(practiceTaskText());
      objective.setText("OBJECTIVE\nComplete the practice task: walk to the green marker and press E.");
      feedback.setText("Tasks are completed one step at a time. The task text changes after each step.");
      controls.setText("W A S D  move     E  interact with the task     P  pause");
      return;
    }

    if (state == State.TASK && near(TASK_X, TASK_Y)) {
      taskMarker.setFill(Color.web("#d5f5df"));
      if (!Tasks.getActiveTasks().isEmpty()) {
        Tasks.completeTask(Tasks.getActiveTasks().get(0).getId());
      }
      taskProgress.setText("TASK STEP COMPLETE\nThe task list advances to the next step.");
      state = State.BULLY;
      bullyWasClose = false;
      objective.setText("OBJECTIVE\nA bully is approaching. Move away until you have space.");
      feedback.setText("Task step complete. Watch the task list in the real game for your next step.");
      controls.setText("W A S D  move away     P  pause");
      bully = new Bully();
      bully.setWidth(42);
      bully.setHeight(84);
      bully.setVisible(true);
      ((AnchorPane) taskMarker.getParent()).getChildren().add(bully);
      bully.setLayoutX(300);
      bully.setLayoutY(250);
    }
  }

  private void chooseResponse(String response, int newFocus) {
    focus = newFocus;
    updateStatus();
    feedback.setText(response + " Choices can affect social battery and social standing.");
    spaceButton.setVisible(false);
    groupButton.setVisible(false);
    continueButton.setVisible(true);
    objective.setText("OBJECTIVE\nYou completed the tutorial. Start the school day when ready.");
  }

  private void complete() {
    state = State.COMPLETE;
    timer.stop();
    movement.stop();
    stage.setScene(gameScene);
    gameScene.getRoot().requestFocus();
    Tasks.chooseRandomTasks(4);
    HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
    HelloWorld.user.resume(gameScene);
    HelloWorld.startGameTimer();
  }

  private boolean near(double x, double y) {
    return Math.hypot(player.getPosition().getX() - x, player.getPosition().getY() - y) < 90;
  }

  private double distanceTo(Bully target) {
    return Math.hypot(player.getPosition().getX() - target.getLayoutX(),
        player.getPosition().getY() - target.getLayoutY());
  }

  private void updateStatus() {
    status.setText("FOCUS   " + focus + " / 3");
  }

  private String practiceTaskText() {
    if (Tasks.getActiveTasks().isEmpty()) {
      return "PRACTICE TASK\nInteract with the marker.";
    }
    return "PRACTICE TASK\n" + Tasks.getActiveTasks().get(0).getDescription();
  }

  private Label label(String text, int size, Color color) {
    Label result = new Label(text);
    result.setTextFill(color);
    result.setStyle("-fx-font-size: " + size + "px;");
    return result;
  }

  private String panelStyle() {
    return "-fx-background-color: rgba(12,22,35,.94); -fx-padding: 10 13;"
        + " -fx-border-color: #b9e3ed; -fx-border-width: 1; -fx-background-radius: 7;";
  }

  private Button button(String text) {
    Button result = new Button(text);
    result.setMinWidth(220);
    result.setStyle(
        "-fx-background-color: #173047; -fx-text-fill: white; -fx-font-size: 14px;"
            + " -fx-padding: 9 14; -fx-border-color: #b9e3ed; -fx-border-width: 1;"
            + " -fx-background-radius: 6; -fx-border-radius: 6;");
    return result;
  }
}
