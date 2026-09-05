package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple help/instructions pane that shows scrollable text for the animation project. The text is
 * read-only at runtime, but students can update the default content in code as the project evolves.
 *
 * <p>This pane is not animated. It is intended to be used as a "Help" tab alongside the animated
 * panes.
 */
public class Help extends VBox {

  private final TextArea helpArea;
  private Scene returnScene;
  private boolean resumeUser;

  /**
   * Constructs a HelpPane with default project directions. Students can update the DEFAULT_TEXT
   * constant or use setHelpText(...) in code to customize instructions.
   */
  public Help(Stage stage) {
    this.helpArea = new TextArea();
    this.setStyle(
        "-fx-background-color: linear-gradient(to bottom, #101223, #1b1f3a);"
            + "-fx-padding: 20;"
            + "-fx-spacing: 15;");
    helpArea.setEditable(false);
    helpArea.setWrapText(true);
    helpArea.setStyle(
        "-fx-control-inner-background: rgba(18, 20, 35, 0.94);"
            + "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 15px;"
            + "-fx-font-family: 'Arial';"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 14;"
            + "-fx-background-radius: 14;"
            + "-fx-padding: 12;");

    helpArea.setText(DEFAULT_TEXT.trim());

    ScrollPane scrollPane = new ScrollPane(helpArea);
    scrollPane.setStyle(
        "-fx-background: transparent;"
            + "-fx-background-color: transparent;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 14;"
            + "-fx-background-radius: 14;");
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    this.setFillWidth(true);
    VBox.setVgrow(scrollPane, Priority.ALWAYS);
    scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    helpArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    Label title = new Label("Help");
    title.setStyle("-fx-text-fill: white;" + "-fx-font-size: 32px;" + "-fx-font-weight: bold;");
    Button back = new Button("Back");
    back.setLayoutX(20);
    back.setLayoutY(20);
    styleButton(back);

    getChildren().add(title);
    getChildren().add(back);
    getChildren().add(scrollPane);

    back.setOnAction(
        e -> {
          stage.setScene(returnScene);
          if (resumeUser) {
            HelloWorld.user.stop();
            HelloWorld.user.resume(returnScene);
          }
        });
  }

  /**
   * Replace the entire help text with new content.
   *
   * @param text the text to display in the help pane
   */
  public void setHelpText(String text) {
    helpArea.setText(text == null ? "" : text);
    helpArea.positionCaret(0); // scroll to top
  }

  /**
   * Append additional text to the end of the help area.
   *
   * @param text text to append (will start on a new line)
   */
  public void appendHelpText(String text) {
    if (text == null || text.isEmpty()) {
      return;
    }
    if (!helpArea.getText().isEmpty()) {
      helpArea.appendText("\n");
    }
    helpArea.appendText(text);
  }

  /** Default instructions text. Students can update this as we add requirements for each pane. */
  private static final String DEFAULT_TEXT =
      """
          Our game raises awareness about how challenging it can be for students with
          different support needs to get through a school day.

          Your goal is to complete the 4 tasks shown on the task list before the timer runs out.
          You have 1 minute and 30 seconds.

          Go to the correct room and click the correct object, person, or area to complete each task.
          When you click the right place, three dialogue options will appear.

          The dialogue options fade at different speeds:
          - The first option fades the fastest. It is usually the most socially acceptable response,
            but it drains more social battery.
          - The second option fades more slowly. It is usually a safer or more neutral response,
            but it may lower social standing.
          - The third option stays the longest. It is usually the least socially acceptable response,
            and it can lower both social battery and social standing.

          Choose the response that works best for your stats.

          If your social battery or social standing reaches 0, the game ends.
          If the timer reaches 0, the game also ends.
          Watch out for students moving through the hallway. If they bump into you, your stats go down.

          Also, make sure to avoid the bullies! They reduce both of your bars!
          Finally, if one or both your bars get to less than 50%, the panic mode is activated!
            - You will have to complete the rest of the game with a shaking screen to be careful and pick 
              the option that works best for you!
              
          HINTS

          OFFICE:
          - To ask to meet the counselor, click the counselor name tag on the desk.
          - To pick up a counselor form, click the papers/forms on the desk.
          - To ask about your schedule, click the reminder/schedule board.

          CLASSROOM:
          - To turn in late homework, click the TURN IN bin.
          - To solve the board problem, click the whiteboard.

          LIBRARY:
          - To check out a book, click the CHECKOUT sign/desk area.
          - To return a book, click the return-books cart.
          - To join a study group, click the study group table.

          CAFETERIA:
          - To order food, click the cafeteria counter/café area.
          - To ask to join a table, click the table group.

          You can open Help again from the main hallway or room screens.
          When you finish your tasks, click Stats to see how you did!
      """;

  /**
   * Creates and returns the scene used to display the help screen.
   *
   * @param stage the main stage for the application
   * @return a Scene containing the help screen
   */
  public Scene getHelp(Stage stage, Scene returnScene, boolean resumeUser) {
    Help help = new Help(stage);
    help.returnScene = returnScene;
    help.resumeUser = resumeUser;
    return new Scene(help, 800, 600);
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
