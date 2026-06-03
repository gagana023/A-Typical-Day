package app;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Creates and manages the dialogue user interface for NPC conversations.
 *
 * <p>This class displays three user dialogue options and three possible NPC response bubbles. It
 * also controls showing, hiding, fading, and assigning actions to dialogue buttons.
 */
public class DialogueUI {

  private Button option1;
  private Button option2;
  private Button option3;

  private StackPane response1;
  private StackPane response2;
  private StackPane response3;

  private VBox optionBox;

  /**
   * Creates a dialogue interface using the given user options and NPC responses.
   *
   * @param userOptions the list of dialogue choices shown to the player
   * @param npcOptions the list of NPC responses shown after a choice is selected
   */
  public DialogueUI(List<String> userOptions, List<String> npcOptions) {
    option1 = new Button(userOptions.get(0));
    option2 = new Button(userOptions.get(1));
    option3 = new Button(userOptions.get(2));

    styleOptionButton(option1);
    styleOptionButton(option2);
    styleOptionButton(option3);

    optionBox = new VBox(10);
    optionBox.setPrefSize(620, 170);
    optionBox.setStyle(
        "-fx-background-color: rgba(18, 20, 35, 0.94);"
            + "-fx-background-radius: 18;"
            + "-fx-border-color: white;"
            + "-fx-border-width: 2;"
            + "-fx-border-radius: 18;"
            + "-fx-padding: 15;");

    optionBox.getChildren().addAll(option1, option2, option3);
    optionBox.setVisible(false);

    response1 = createResponseBubble(npcOptions.get(0));
    response2 = createResponseBubble(npcOptions.get(1));
    response3 = createResponseBubble(npcOptions.get(2));

    response1.setVisible(false);
    response2.setVisible(false);
    response3.setVisible(false);

    setPositions();
  }

  /**
   * Styles one dialogue option button.
   *
   * @param button the button to style
   */
  private void styleOptionButton(Button button) {
    button.setMaxWidth(Double.MAX_VALUE);
    button.setWrapText(true);

    String normalStyle =
        "-fx-background-color: #30364f;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 14px;"
            + "-fx-background-radius: 10;"
            + "-fx-border-color: #9aa7ff;"
            + "-fx-border-radius: 10;"
            + "-fx-padding: 8;";

    String hoverStyle =
        "-fx-background-color: #4b5bdc;"
            + "-fx-text-fill: white;"
            + "-fx-font-size: 14px;"
            + "-fx-background-radius: 10;"
            + "-fx-border-color: white;"
            + "-fx-border-radius: 10;"
            + "-fx-padding: 8;";

    button.setStyle(normalStyle);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
  }

  /**
   * Creates a speech bubble for an NPC response.
   *
   * @param responseText the text displayed inside the response bubble
   * @return a StackPane containing the response bubble and its text
   */
  private StackPane createResponseBubble(String responseText) {
    Text text = new Text(responseText);
    text.setWrappingWidth(560);
    text.setFill(Color.WHITE);
    text.setStyle("-fx-font-size: 16px;");

    Rectangle box = new Rectangle(620, 120);
    box.setFill(Color.rgb(18, 20, 35, 0.94));
    box.setStroke(Color.WHITE);
    box.setStrokeWidth(2);
    box.setArcWidth(25);
    box.setArcHeight(25);

    return new StackPane(box, text);
  }

  private void showResponse(StackPane response) {
    response.setOpacity(1);
    response.setVisible(true);

    PauseTransition wait = new PauseTransition(Duration.seconds(2));

    FadeTransition fade = new FadeTransition(Duration.seconds(2), response);
    fade.setToValue(0);

    wait.setOnFinished(e -> fade.play());

    fade.setOnFinished(
        e -> {
          response.setVisible(false);
          response.setOpacity(1);
        });

    wait.play();
  }

  /** Sets the screen positions of the dialogue options and response bubbles. */
  private void setPositions() {
    AnchorPane.setTopAnchor(optionBox, 390.0);
    AnchorPane.setLeftAnchor(optionBox, 90.0);

    AnchorPane.setTopAnchor(response1, 400.0);
    AnchorPane.setLeftAnchor(response1, 90.0);

    AnchorPane.setTopAnchor(response2, 400.0);
    AnchorPane.setLeftAnchor(response2, 90.0);

    AnchorPane.setTopAnchor(response3, 400.0);
    AnchorPane.setLeftAnchor(response3, 90.0);
  }

  /**
   * Adds the dialogue buttons and response bubbles to the given root layout.
   *
   * @param root the AnchorPane that receives the dialogue UI elements
   */
  public void addToRoot(AnchorPane root) {
    root.getChildren().addAll(optionBox, response1, response2, response3);
  }

  /** Shows the dialogue option buttons and starts their fade animations. */
  public void showOptions() {
    response1.setVisible(false);
    response2.setVisible(false);
    response3.setVisible(false);

    response1.setOpacity(1);
    response2.setOpacity(1);
    response3.setOpacity(1);

    optionBox.setOpacity(1);
    optionBox.setVisible(true);

    option1.setOpacity(1);
    option2.setOpacity(1);
    option3.setOpacity(1);

    option1.setDisable(false);
    option2.setDisable(false);
    option3.setDisable(false);

    startOptionFades();
  }

  /** Hides all dialogue option buttons. */
  public void hideOptions() {
    optionBox.setVisible(false);
  }

  /**
   * Starts fade animations for the whole option box.
   *
   * <p>The option box fades out if the player waits too long.
   */
  private void startOptionFades() {
    FadeTransition fade1 = new FadeTransition(Duration.seconds(3.5), option1);
    fade1.setToValue(0);
    fade1.setOnFinished(e -> option1.setDisable(true));

    FadeTransition fade2 = new FadeTransition(Duration.seconds(7), option2);
    fade2.setToValue(0);
    fade2.setOnFinished(e -> option2.setDisable(true));

    FadeTransition fade3 = new FadeTransition(Duration.seconds(10), option3);
    fade3.setToValue(0);
    fade3.setOnFinished(
        e -> {
          option3.setDisable(true);
          optionBox.setVisible(false);
        });

    fade1.play();
    fade2.play();
    fade3.play();
  }

  /**
   * Sets the action that runs when the first dialogue option is selected.
   *
   * @param action the action to run after the first option is clicked
   */
  public void setOption1Action(Runnable action) {
    option1.setOnAction(
        e -> {
          showResponse(response1);
          action.run();
          hideOptions();
        });
  }

  /**
   * Sets the action that runs when the second dialogue option is selected.
   *
   * @param action the action to run after the second option is clicked
   */
  public void setOption2Action(Runnable action) {
    option2.setOnAction(
        e -> {
          showResponse(response2);
          action.run();
          hideOptions();
        });
  }

  /**
   * Sets the action that runs when the third dialogue option is selected.
   *
   * @param action the action to run after the third option is clicked
   */
  public void setOption3Action(Runnable action) {
    option3.setOnAction(
        e -> {
          showResponse(response3);
          action.run();
          hideOptions();
        });
  }
}
