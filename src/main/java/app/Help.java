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
public class Help extends MenuScreen {

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

    // setPadding(new Insets(10));
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

          For our prototype, we currently have one dummy task, ask for extension

          The classroom room (bottom left) still does not work so don't click on that door.

          Your job is to go through the three working rooms, and find the room with the
          button "ask for extension". You can click on any of the two buttons, the one that
          fades fast and the one that doesn't.

      """;

  public Scene getHelp(Stage stage) {
    return new Scene(this, 800, 600);
  }
}
