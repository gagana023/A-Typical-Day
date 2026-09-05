package app;

import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/** Creates and manages the typed player dialogue interface and NPC response bubble. */
public class DialogueUI {

  private final VBox optionBox;
  private final TextField responseInput;
  private final Button submitButton;
  private final StackPane responseBubble;
  private final Text responseText;

  /** Creates a typed dialogue interface. */
  public DialogueUI(List<String> userOptions, List<String> npcOptions) {
    optionBox = new VBox(10);
    optionBox.setPrefSize(620, 180);
    optionBox.setStyle(
        "-fx-background-color: rgba(18,20,35,0.94);"
            + "-fx-background-radius: 18;"
            + "-fx-border-color: white;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 18;"
            + "-fx-padding: 15;");

    Label prompt = new Label("Type your response:");
    prompt.setTextFill(Color.WHITE);
    prompt.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    responseInput = new TextField();
    responseInput.setPromptText("What would you say?");
    responseInput.setStyle(
        "-fx-background-color: white; -fx-text-fill: #101223; -fx-font-size: 15px;"
            + " -fx-padding: 10;");

    submitButton = new Button("Submit");
    styleButton(submitButton);
    responseInput.setOnAction(e -> submitButton.fire());
    optionBox.getChildren().addAll(prompt, responseInput, submitButton);
    optionBox.setVisible(false);

    responseText = new Text();
    responseText.setWrappingWidth(560);
    responseText.setFill(Color.WHITE);
    responseText.setStyle("-fx-font-size: 16px;");

    Rectangle responseBackground = new Rectangle(620, 120);
    responseBackground.setFill(Color.rgb(18, 20, 35, 0.94));
    responseBackground.setStroke(Color.WHITE);
    responseBackground.setStrokeWidth(2);
    responseBackground.setArcWidth(25);
    responseBackground.setArcHeight(25);
    responseBubble = new StackPane(responseBackground, responseText);
    responseBubble.setVisible(false);

    AnchorPane.setTopAnchor(optionBox, 390.0);
    AnchorPane.setLeftAnchor(optionBox, 90.0);
    AnchorPane.setTopAnchor(responseBubble, 400.0);
    AnchorPane.setLeftAnchor(responseBubble, 90.0);
  }

  private void styleButton(Button button) {
    String normalStyle =
        "-fx-background-color: #30364f; -fx-text-fill: white; -fx-font-size: 14px;"
            + " -fx-background-radius: 10; -fx-border-color: #9aa7ff;"
            + " -fx-border-radius: 10; -fx-padding: 8 18 8 18;";
    String hoverStyle =
        "-fx-background-color: #4b5bdc; -fx-text-fill: white; -fx-font-size: 14px;"
            + " -fx-background-radius: 10; -fx-border-color: white;"
            + " -fx-border-radius: 10; -fx-padding: 8 18 8 18;";
    button.setStyle(normalStyle);
    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
  }

  /** Adds the dialogue controls to a room root. */
  public void addToRoot(AnchorPane root) {
    root.getChildren().addAll(optionBox, responseBubble);
  }

  /** Shows the typed response controls. */
  public void showOptions() {
    responseBubble.setVisible(false);
    optionBox.setVisible(true);
    responseInput.clear();
    enableSubmission();
    responseInput.requestFocus();
  }

  /** Hides the typed response controls. */
  public void hideOptions() {
    optionBox.setVisible(false);
  }

  /** Shows the generated NPC response. */
  public void showResponse(String text) {
    responseText.setText(text);
    responseBubble.setVisible(true);
  }

  /** Sets the action that runs when the player submits typed dialogue. */
  public void setSubmitAction(Consumer<String> action) {
    submitButton.setOnAction(
        e -> {
          String response = responseInput.getText().trim();
          if (response.isEmpty()) {
            return;
          }
          responseInput.setDisable(true);
          submitButton.setDisable(true);
          submitButton.setText("Thinking...");
          action.accept(response);
        });
  }

  /** Re-enables submission after a failed request. */
  public void enableSubmission() {
    responseInput.setDisable(false);
    submitButton.setDisable(false);
    submitButton.setText("Submit");
  }
}
