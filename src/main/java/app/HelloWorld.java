package app;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main application class for the ATypical Day JavaFX game.
 *
 * <p>This class creates the main hallway scene, initializes the player, doors, task display, stats
 * menu, help menu, and story intro. It also controls switching between the main scene and the
 * different room scenes.
 */
public class HelloWorld extends Application {

  public static Stage stage;
  public static Scene scene;
  public static User user;
  public static Label task1;
  public static Label task2;
  public static Label task3;
  public static Label task4;
  public static ProgressBar socialBatteryBar;
  public static ProgressBar socialStandingBar;
  public static Label timerLabel;
  private static Timeline gameTimer;
  private static int timeRemaining = 90;
  private static boolean timerStarted = false;
  public static StringProperty timerText = new SimpleStringProperty("Time: 1:30");
  public static Panic currentPanic;

  /**
   * Starts the JavaFX application and builds the main game scene.
   *
   * <p>This method initializes the player, hallway background, room doors, task bar, stats display,
   * help button, room navigation, and story intro scene.
   *
   * @param stage the main stage used to display all game scenes
   */
  @Override
  public void start(Stage stage) {
    HelloWorld.stage = stage;

    Tasks.chooseRandomTasks(4);
    AnchorPane root = new AnchorPane();
    root.setFocusTraversable(true);
    Canvas bgCanvas = new Canvas(800, 600);
    bgCanvas.setId("gameCanvas");
    root.setStyle("-fx-background-image: url('/hallway_background.png')");
    user = new User();

    NPC topL = new NPC("/door.png", 100, 160);
    NPC topR = new NPC("/door.png", 100, 160);
    NPC botL = new NPC("/door.png", 100, 160);
    NPC botR = new NPC("/door.png", 100, 160);

    user.setRooms(topL, botL, topR, botR, stage);

    Button stats = new Button("Stats");
    Button help = new Button("Help");
    styleMenuButton(stats);
    styleMenuButton(help);

    AnchorPane.setTopAnchor(topL, 100.0);
    AnchorPane.setLeftAnchor(topL, 100.0);
    topL.setWidth(100);
    topL.setHeight(160);

    AnchorPane.setLeftAnchor(topR, 550.0);
    AnchorPane.setTopAnchor(topR, 100.0);
    topR.setWidth(100);
    topR.setHeight(160);

    AnchorPane.setLeftAnchor(botL, 100.0);
    AnchorPane.setTopAnchor(botL, 400.0);
    botL.setWidth(100);
    botL.setHeight(160);

    AnchorPane.setLeftAnchor(botR, 550.0);
    AnchorPane.setTopAnchor(botR, 400.0);
    botR.setWidth(100);
    botR.setHeight(160);

    Label officeLabel = createRoomLabel("Office");
    AnchorPane.setLeftAnchor(officeLabel, 125.0);
    AnchorPane.setTopAnchor(officeLabel, 265.0);

    Label classroomLabel = createRoomLabel("Library");
    AnchorPane.setLeftAnchor(classroomLabel, 560.0);
    AnchorPane.setTopAnchor(classroomLabel, 265.0);

    Label libraryLabel = createRoomLabel("Classroom");
    AnchorPane.setLeftAnchor(libraryLabel, 120.0);
    AnchorPane.setTopAnchor(libraryLabel, 565.0);

    Label cafeteriaLabel = createRoomLabel("Cafeteria");
    AnchorPane.setLeftAnchor(cafeteriaLabel, 560.0);
    AnchorPane.setTopAnchor(cafeteriaLabel, 565.0);

    AnchorPane.setTopAnchor(stats, 500.0);
    AnchorPane.setRightAnchor(stats, 20.0);

    AnchorPane.setTopAnchor(help, 450.0);
    AnchorPane.setRightAnchor(help, 20.0);
    VBox taskBar = new VBox(5);
    taskBar.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-padding: 10;");
    Label title = new Label("Tasks");

    title.setTextFill(Color.WHITE);
    task1 = new Label();
    task2 = new Label();
    task3 = new Label();
    task4 = new Label();

    timerLabel = HelloWorld.createTimerLabel();

    AnchorPane.setTopAnchor(timerLabel, 80.0);
    AnchorPane.setRightAnchor(timerLabel, 20.0);

    task1.setTextFill(Color.WHITE);
    task2.setTextFill(Color.WHITE);
    task3.setTextFill(Color.WHITE);
    task4.setTextFill(Color.WHITE);

    updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
    Button toggle = new Button("▼");
    toggle.setOnAction(
        e -> {
          taskBar.setVisible(!taskBar.isVisible());
        });

    taskBar.getChildren().addAll(title, task1, task2, task3, task4);

    AnchorPane.setTopAnchor(taskBar, 0.0);
    AnchorPane.setLeftAnchor(taskBar, 0.0);

    AnchorPane.setTopAnchor(toggle, 0.0);
    AnchorPane.setRightAnchor(toggle, 10.0);

    scene = new Scene(root, 800, 600);

    user.connect(scene);

    Stats s = new Stats();

    VBox topStatsMenu = new VBox(5);
    topStatsMenu
        .getChildren()
        .addAll(s.createBarSection("Social Battery"), s.createBarSection("Social Standing"));

    AnchorPane.setTopAnchor(topStatsMenu, 0.0);
    AnchorPane.setLeftAnchor(topStatsMenu, 315.0);
    topStatsMenu.setStyle(
        "-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width:"
            + " 1;");

    Help h = new Help(stage);

    stats.setOnAction(
        e -> {
          Scene statsScene = s.buildPrototypeHomeAndStats(stage);
          user.stop();
          stage.setScene(statsScene);
        });

    help.setOnAction(
        e -> {
          user.stop();
          stage.setScene(h.getHelp(stage, stage.getScene(), true));
        });

    Bully bully1 = new Bully();
    bully1.setWidth(50);
    bully1.setHeight(103);

    Bully bully2 = new Bully();
    bully2.setWidth(50);
    bully2.setHeight(103);
    bully2.setVisible(false);

    root.getChildren()
        .addAll(
            bgCanvas,
            topL,
            topR,
            botL,
            botR,
            user.getInAddAllForm(),
            stats,
            help,
            taskBar,
            toggle,
            topStatsMenu,
            bully1,
            bully2,
            timerLabel,
            officeLabel,
            classroomLabel,
            libraryLabel,
            cafeteriaLabel);

    StoryIntro intro = new StoryIntro();
    Scene introScene = intro.build(stage, scene, user);
    bully1.spawnRandomly(scene);
    bully2.spawnRandomly(scene);
    user.setBullies(List.of(bully1, bully2));

    stage.setScene(introScene);
    stage.setTitle("A Typical Day");
    stage.show();
    intro.play();

    introScene.setOnMouseClicked(
        e -> {
          stage.setScene(scene);
          user.start();
          HelloWorld.startGameTimer();

          Platform.runLater(
              () -> {
                scene.getRoot().requestFocus();
              });
        });
  }

  public static Stage getStage() {
    return stage;
  }

  /**
   * Launches the JavaFX application.
   *
   * @param args the command-line arguments passed to the program
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Updates the social battery and social standing progress bars.
   *
   * <p>If the user or progress bars have not been created yet, this method safely skips updating
   * those values.
   */
  public static void updateStatsBars() {
    if (user == null) {
      return;
    }

    if (socialBatteryBar != null) {
      socialBatteryBar.setProgress(user.getHealth());
    }

    if (socialStandingBar != null) {
      socialStandingBar.setProgress(user.getSocial());
    }
  }

  /**
   * Updates the task labels shown in the task bar.
   *
   * <p>This method displays the active tasks, marks completed tasks with a strikethrough style, and
   * clears unused task labels.
   *
   * @param t1 the first task label
   * @param t2 the second task label
   * @param t3 the third task label
   */
  public static void updateTasks(Label t1, Label t2, Label t3, Label t4) {
    Label[] labels = {t1, t2, t3, t4};

    for (int i = 0; i < labels.length; i++) {
      if (i < Tasks.getActiveTasks().size()) {
        Tasks.Task task = Tasks.getActiveTasks().get(i);

        labels[i].setText(task.getDescription());

        if (task.isDone()) {
          labels[i].setStyle("-fx-strikethrough: true; -fx-text-fill: gray;");
          labels[i].setTextFill(Color.GRAY);
        } else {
          labels[i].setStyle("-fx-strikethrough: false; -fx-text-fill: white;");
          labels[i].setTextFill(Color.WHITE);
        }
      } else {
        labels[i].setText("");
      }
    }
  }

  /**
   * Handles the result of selecting a dialogue option.
   *
   * <p>This method updates the user's stats, advances the matching task by one step (or completes
   * it if it was on its last step), refreshes the task display, and switches to the game-over scene
   * if the stat change causes a game-over condition.
   *
   * @param option the selected dialogue option number
   * @param taskId the id of the task advanced by the dialogue choice
   * @param stage the main stage used to switch to the game-over scene
   */
  public static void handleChoice(int option, String taskId, Stage stage) {
    boolean isOver = HelloWorld.user.changeStats(option);
    boolean completed = Tasks.completeTask(taskId);

    HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
    if (completed) {

      HelloWorld.showTaskCompleteNotification(stage);
    }

    if (isOver) {
      System.out.println("Game Over");
      HelloWorld.restartGame(stage);
      stopGameTimer();
      stopPanicMode();
      stage.setScene(new GameOver().getScene(stage));
      return;
    }

    checkWin(stage);
  }

  /** Sends a typed response to OpenAI, then applies its category to the existing game rules. */
  public static void handleTypedChoice(
      String response, String taskId, Stage stage, DialogueUI dialogueUI) {
    OpenAIResponseService.analyze(taskId, Tasks.getCurrentStep(taskId), response)
        .whenComplete(
            (analysis, error) ->
                Platform.runLater(
                    () -> {
                      if (error != null) {
                        dialogueUI.showResponse(
                          "I couldn't process that response. "
                            + getDialogueErrorMessage(error));
                        dialogueUI.enableSubmission();
                        return;
                      }

                      dialogueUI.hideOptions();
                      dialogueUI.showResponse(analysis.npcResponse());
                      handleChoice(analysis.category(), taskId, stage);
                    }));
  }

  private static String getDialogueErrorMessage(Throwable error) {
    Throwable cause = error;
    while (cause.getCause() != null) {
      cause = cause.getCause();
    }

    if (cause instanceof IllegalStateException
        && cause.getMessage() != null
        && cause.getMessage().contains("OPENAI_API_KEY is not set")) {
      return "The OpenAI key is not available. Set OPENAI_API_KEY (not OPEN_API_KEY), then restart VS Code.";
    }

    if (cause.getMessage() != null && cause.getMessage().contains("status 401")) {
      return "OpenAI rejected the API key (401). Create a new key and check that it is active.";
    }

    if (cause.getMessage() != null && cause.getMessage().contains("status 429")) {
      return "OpenAI quota or rate limit reached (429). Check billing and usage for the API account.";
    }

    if (cause.getMessage() != null && cause.getMessage().contains("status 400")) {
      return "OpenAI rejected the request (400). Check the selected model and API account access.";
    }

    if (cause.getMessage() != null && cause.getMessage().contains("status 5")) {
      return "OpenAI is temporarily unavailable. Check the service status and try again.";
    }

    return "OpenAI request failed. Check your key, internet connection, and API account.";
  }

  public static void startGameTimer() {
    if (timerStarted) {
      return;
    }

    timerStarted = true;
    timeRemaining = 90;
    updateTimerLabel();

    gameTimer =
        new Timeline(
            new KeyFrame(
                Duration.seconds(1),
                e -> {
                  timeRemaining--;
                  updateTimerLabel();

                  if (timeRemaining <= 0) {
                    gameTimer.stop();
                    user.stop();
                    stage.setScene(new GameOver().getScene(stage));
                  }
                }));

    gameTimer.setCycleCount(Timeline.INDEFINITE);
    gameTimer.play();
  }

  private static void updateTimerLabel() {
    int minutes = timeRemaining / 60;
    int seconds = timeRemaining % 60;

    timerText.set(String.format("Time: %d:%02d", minutes, seconds));
  }

  public static void stopGameTimer() {
    if (gameTimer != null) {
      gameTimer.stop();
    }
  }

  public static void pauseGameTimer() {
    if (gameTimer != null) {
      gameTimer.pause();
    }
  }

  public static void resumeGameTimer() {
    if (gameTimer != null && timerStarted && timeRemaining > 0) {
      gameTimer.play();
    }
  }

  public static Label createTimerLabel() {
    Label label = new Label();
    label.textProperty().bind(timerText);
    label.setTextFill(Color.WHITE);
    label.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-padding: 10; -fx-font-size: 18px;");

    return label;
  }

  private void styleMenuButton(Button button) {
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

  public static VBox createRoomTaskBar() {
    VBox taskBar = new VBox(5);
    taskBar.setStyle(
        "-fx-background-color: rgba(0,0,0,0.85);"
            + "-fx-padding: 10;"
            + "-fx-background-radius: 12;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 12;");

    taskBar.setVisible(false);
    taskBar.setPrefWidth(260);

    Label title = new Label("Tasks");
    title.setTextFill(Color.WHITE);
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

    Label roomTask1 = new Label();
    Label roomTask2 = new Label();
    Label roomTask3 = new Label();
    Label roomTask4 = new Label();

    roomTask1.setTextFill(Color.WHITE);
    roomTask2.setTextFill(Color.WHITE);
    roomTask3.setTextFill(Color.WHITE);
    roomTask4.setTextFill(Color.WHITE);

    roomTask1.setWrapText(true);
    roomTask2.setWrapText(true);
    roomTask3.setWrapText(true);
    roomTask4.setWrapText(true);

    updateTasks(roomTask1, roomTask2, roomTask3, roomTask4);

    taskBar.getChildren().addAll(title, roomTask1, roomTask2, roomTask3, roomTask4);

    return taskBar;
  }

  public static Button createRoomTaskToggle(VBox taskBar) {
    Button toggle = new Button("▼");
    toggle.setPrefWidth(45);

    toggle.setStyle(
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 14px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 10;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 10;");

    toggle.setOnAction(
        e -> {
          updateTasks(
              (Label) taskBar.getChildren().get(1),
              (Label) taskBar.getChildren().get(2),
              (Label) taskBar.getChildren().get(3),
              (Label) taskBar.getChildren().get(4));

          boolean showing = taskBar.isVisible();
          taskBar.setVisible(!showing);

          if (showing) {
            toggle.setText("▼");
          } else {
            toggle.setText("▲");
          }
        });

    return toggle;
  }

  public static void stopPanicMode() {
    if (currentPanic != null) {
      currentPanic.stopPanic();
      currentPanic = null;
    }
  }

  public static void checkWin(Stage stage) {
    if (Tasks.areAllActiveTasksDone()) {
      stopGameTimer();
      stopPanicMode();
      user.stop();

      Stats statsScreen = new Stats();
      stage.setScene(statsScreen.buildFinalStats(stage));
    }
  }

  public static void restartGame(Stage stage) {
    stopGameTimer();
    stopPanicMode();

    timerStarted = false;
    timeRemaining = 90;
    updateTimerLabel();

    HelloWorld newGame = new HelloWorld();
    newGame.start(stage);
  }

  private Label createRoomLabel(String text) {
    Label label = new Label(text);
    label.setStyle(
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 12px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 10;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 1;"
            + "-fx-border-radius: 10;"
            + "-fx-padding: 2 7 2 7;");
    return label;
  }

  public static void showTaskCompleteNotification(Stage stage) {
    if (!(stage.getScene().getRoot() instanceof AnchorPane root)) {
      return;
    }

    Label notification = new Label("Task completed!");
    notification.setTextFill(Color.WHITE);
    notification.setStyle(
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-font-size: 16px;"
            + "-fx-font-weight: bold;"
            + "-fx-background-radius: 14;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 14;"
            + "-fx-padding: 10 18 10 18;");

    AnchorPane.setTopAnchor(notification, 90.0);
    AnchorPane.setRightAnchor(notification, 20.0);

    notification.setOpacity(1);
    root.getChildren().add(notification);

    FadeTransition fade = new FadeTransition(Duration.seconds(2), notification);
    fade.setFromValue(1);
    fade.setToValue(0);

    fade.setOnFinished(
        e -> {
          root.getChildren().remove(notification);
        });

    fade.play();
  }
}