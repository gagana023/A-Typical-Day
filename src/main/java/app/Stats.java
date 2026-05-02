package app;

import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
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
    stage.setTitle("Home");
    Line l1 = new Line(0, 100, 600, 100);
    Line l2 = new Line(800, 0, 600, 100);
    Line l3 = new Line(600, 400, 600, 100);
    Line l4 = new Line(800, 500, 600, 400);
    Line l5 = new Line(600, 400, 300, 400);
    Line l6 = new Line(300, 400, 100, 500);
    Line l7 = new Line(100, 500, 100, 400);
    Line l8 = new Line(100, 400, 300, 300);
    Line l9 = new Line(300, 300, 300, 400);
    Line l10 = new Line(300, 300, 600, 300);
    Line l11 = new Line(100, 500, 50, 500);
    Line l12 = new Line(100, 400, 50, 400);
    Line l13 = new Line(50, 500, 50, 400);
    Line l14 = new Line(50, 400, 300, 275);
    Line l15 = new Line(300, 275, 600, 275);
    Button back = new Button("Back");
    back.setLayoutX(730);
    back.setLayoutY(20);
    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
        });
    Pane menuBox = getMenu();
    Group group =
        new Group(menuBox, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, back);
    Scene scene = new Scene(group, 800, 600);
    return scene;
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

    // TODO: anyone (aadi, if time available) - check if an AnchorPane needs to be made in other
    // classes; could inadvertently be creating logic errors. - aadi
    AnchorPane anchorPane = new AnchorPane();
    anchorPane.getChildren().add(menuBox);
    // FIXME: anyone - for whatever reason, despite anchoring the VBox to the bottom left, it sits
    // in the top left. has to be fixed; not sure what's wrong. - aadi
    AnchorPane.setBottomAnchor(menuBox, 0.0);

    StackPane section1 = createSection("GAME STATS");
    StackPane section2 = createSection("Tasks");
    StackPane section3 = createBarSection("Social Battery");
    StackPane section4 = createBarSection("Social Standing");
    StackPane section5 = createSection("Time");

    menuBox.getChildren().addAll(section1, section2, section3, section4, section5);

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
}
