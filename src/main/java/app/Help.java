package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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

  /**
   * Constructs a HelpPane with default project directions. Students can update the DEFAULT_TEXT
   * constant or use setHelpText(...) in code to customize instructions.
   */
  public Help(Stage stage) {
    this.helpArea = new TextArea();

    helpArea.setEditable(false);
    helpArea.setWrapText(true);
    helpArea.setStyle("-fx-font-family: 'Consolas', 'Monospaced'; -fx-font-size: 12;");

    helpArea.setText(DEFAULT_TEXT.trim());

    ScrollPane scrollPane = new ScrollPane(helpArea);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    this.setFillWidth(true);
    VBox.setVgrow(scrollPane, Priority.ALWAYS);
    scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    helpArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    Button back = new Button("Back");
    back.setLayoutX(20);
    back.setLayoutY(20);

    getChildren().add(back);
    getChildren().add(scrollPane);

    back.setOnAction(
        e -> {
          stage.setScene(HelloWorld.scene);
          HelloWorld.user.stop();
          HelloWorld.user.resume(HelloWorld.scene);
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
          Our game is raising awareness for students with special needs and
          how challening it can be to go through a school day.

          Your job is to look at the tasks and go to the 4 rooms to find where you need tp
          complete the tasks. There will be a button indicating so. Once you press it, three
          dialogue options will pop up, one that fades fast, one that fades a little slowly and
          one that stays. These represent the energy taken to give the response. The one that
          fades quickly is the most "socially" acceptable answer but it will drain the health bar
          and the one that fades slowest is not very "socially acceptable", so it will drain the
          social standing bar and the health bar. The medium one drains only social standing
          Make sure to pick the option that works best for your statistics.

          You can always come back and access the help at the bottom right of the main screen.

          HINTS
          For Library checkout, press on the left side, and a checkour button will appear.
          For finding a study group, press on the desk and the dialogues will appear.
          For ordering food, press on the table closest to the Cafe.
          For classroom tasks, press on the teacher to activate the dialogues.

      """;

  /**
   * Creates and returns the scene used to display the help screen.
   *
   * @param stage the main stage for the application
   * @return a Scene containing the help screen
   */
  public Scene getHelp(Stage stage) {
    return new Scene(this, 800, 600);
  }
}
