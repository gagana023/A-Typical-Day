package app;

import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds and displays the player's stats screen.
 *
 * <p>This class creates a stats menu with sections for game stats, tasks, social battery, social
 * standing, and time. It also creates progress bars that connect to the player's current stats.
 */
public class Stats {
  /** Random number generator for possible stats-related values. */
  Random rand = new Random();

  /**
   * Builds and returns the home and stats scene.
   *
   * <p>This method creates the background line design, stats menu, and back button. The back button
   * returns the player to the main game scene.
   *
   * @param stage the main stage used to display the stats scene
   * @return the Scene containing the stats screen
   */
  public Scene buildPrototypeHomeAndStats(Stage stage) {
    return buildStatsScreen(stage, false);
  }

  public Scene buildFinalStats(Stage stage) {
    return buildStatsScreen(stage, true);
  }

  private Scene buildStatsScreen(Stage stage, boolean finalScreen) {
    stage.setTitle("Home");
    Image kitchenImg = new Image(ResourceLoader.getUrl("/kitchen.png").toExternalForm());
    ImageView background = new ImageView(kitchenImg);
    background.setFitWidth(800);
    background.setFitHeight(600);
    background.setPreserveRatio(false);
    NPC npc = new NPC("/mama!.png", 187, 230);
    npc.setPosition(350, 210);

    StackPane dialoguePane = new StackPane();
    dialoguePane.setPrefSize(800, 120);
    dialoguePane.setStyle("-fx-background-color: white; -fx-border-color: black;");
    AnchorPane.setBottomAnchor(dialoguePane, 0.0);
    AnchorPane.setLeftAnchor(dialoguePane, 0.0);

    VBox dialogueContent = new VBox();
    dialogueContent.setStyle("-fx-alignment: center;");

    Label dialogueText = new Label("How was your day?");
    dialogueText.setStyle("-fx-font-size: 18px;");

    dialogueContent.getChildren().add(dialogueText);
    dialoguePane.getChildren().add(dialogueContent);

    Button back = new Button("Back");
    Button navButton;

    if (finalScreen) {
      navButton = new Button("Play Again");
      navButton.setLayoutX(620);
      navButton.setLayoutY(20);
      navButton.setPrefWidth(150);

      styleButton(navButton);

      navButton.setOnAction(
          e -> {
            HelloWorld.restartGame(stage);
          });
    } else {
      navButton = new Button("Back");
      navButton.setLayoutX(670);
      navButton.setLayoutY(20);
      styleButton(navButton);

      navButton.setOnAction(
          e -> {
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            stage.setTitle("A Typical Day");
          });
    }
    Pane menuBox = getMenu();
    AnchorPane root = new AnchorPane();

    root.getChildren().addAll(background, menuBox, npc, dialoguePane, navButton);

    return new Scene(root, 800, 600);
  }

  /**
   * Creates and returns the stats menu layout.
   *
   * <p>The menu contains sections for game stats, tasks, social battery, social standing, and time.
   *
   * @return the Pane containing the stats menu
   */
  public Pane getMenu() {

    VBox menuBox = new VBox();
    menuBox.setPrefSize(200, 200);
    menuBox.setStyle("-fx-background-color: white;");

    AnchorPane anchorPane = new AnchorPane();
    anchorPane.getChildren().add(menuBox);
    AnchorPane.setBottomAnchor(menuBox, 0.0);

    StackPane section1 = createSection("GAME STATS");
    // StackPane section2 = createSection("Tasks");
    StackPane section3 = createBarSection("Social Battery");
    StackPane section4 = createBarSection("Social Standing");
    // StackPane section5 = createSection("Time");

    menuBox.getChildren().addAll(section1, section3, section4);

    return anchorPane;
  }

  /**
   * Creates a basic labeled section for the stats menu.
   *
   * @param text the text displayed in the section
   * @return a StackPane containing the labeled section
   */
  public StackPane createSection(String text) {

    StackPane pane = new StackPane();
    pane.setPrefHeight(50);
    pane.setStyle("-fx-border-color: black;");

    Label label = new Label(text);

    pane.getChildren().add(label);

    return pane;
  }

  /**
   * Creates a labeled stats section with a progress bar.
   *
   * <p>If the section is for social battery or social standing, the progress bar is connected to
   * the matching player stat.
   *
   * @param text the label text for the progress bar section
   * @return a StackPane containing the label and progress bar
   */
  public StackPane createBarSection(String text) {

    StackPane pane = new StackPane();
    pane.setPrefHeight(50);
    pane.setStyle("-fx-border-color: black;");

    VBox content = new VBox();

    Label label = new Label(text);

    ProgressBar bar = new ProgressBar(0);
    bar.setPrefWidth(150);

    if (text.equals("Social Battery")) {
      bar.setProgress(HelloWorld.user.getHealth());
      HelloWorld.socialBatteryBar = bar;
    } else if (text.equals("Social Standing")) {
      bar.setProgress(HelloWorld.user.getSocial());
      HelloWorld.socialStandingBar = bar;
    }

    content.getChildren().addAll(label, bar);

    pane.getChildren().add(content);

    return pane;
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
