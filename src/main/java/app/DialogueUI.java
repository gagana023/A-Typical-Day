package app;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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

    option1.setVisible(false);
    option2.setVisible(false);
    option3.setVisible(false);

    response1 = createResponseBubble(npcOptions.get(0));
    response2 = createResponseBubble(npcOptions.get(1));
    response3 = createResponseBubble(npcOptions.get(2));

    response1.setVisible(false);
    response2.setVisible(false);
    response3.setVisible(false);

    setPositions();
  }

  /**
   * Creates a speech bubble for an NPC response.
   *
   * @param responseText the text displayed inside the response bubble
   * @return a StackPane containing the response bubble and its text
   */
  private StackPane createResponseBubble(String responseText) {
    Text text = new Text(responseText);
    text.setWrappingWidth(240);

    Rectangle box = new Rectangle(260, 70);
    box.setFill(Color.WHITE);
    box.setStroke(Color.BLACK);
    box.setArcWidth(15);
    box.setArcHeight(15);

    return new StackPane(box, text);
  }

  /** Sets the screen positions of the dialogue options and response bubbles. */
  private void setPositions() {
    AnchorPane.setTopAnchor(response1, 100.0);
    AnchorPane.setLeftAnchor(response1, 250.0);

    AnchorPane.setTopAnchor(response2, 100.0);
    AnchorPane.setLeftAnchor(response2, 250.0);

    AnchorPane.setTopAnchor(response3, 100.0);
    AnchorPane.setLeftAnchor(response3, 250.0);

    AnchorPane.setTopAnchor(option1, 200.0);
    AnchorPane.setLeftAnchor(option1, 300.0);

    AnchorPane.setTopAnchor(option2, 250.0);
    AnchorPane.setLeftAnchor(option2, 300.0);

    AnchorPane.setTopAnchor(option3, 300.0);
    AnchorPane.setLeftAnchor(option3, 300.0);
  }

  /**
   * Adds the dialogue buttons and response bubbles to the given root layout.
   *
   * @param root the AnchorPane that receives the dialogue UI elements
   */
  public void addToRoot(AnchorPane root) {
    root.getChildren().addAll(option1, option2, option3, response1, response2, response3);
  }

  /** Shows the dialogue option buttons and starts their fade animations. */
  public void showOptions() {
    option1.setOpacity(1);
    option2.setOpacity(1);
    option3.setOpacity(1);

    option1.setVisible(true);
    option2.setVisible(true);
    option3.setVisible(true);

    startOptionFades();
  }

  /** Hides all dialogue option buttons. */
  public void hideOptions() {
    option1.setVisible(false);
    option2.setVisible(false);
    option3.setVisible(false);
  }

  /**
   * Starts fade animations for each dialogue option button.
   *
   * <p>Each option fades at a different speed so the choices disappear gradually.
   */
  private void startOptionFades() {
    FadeTransition fade1 = new FadeTransition(Duration.seconds(3.5), option1);
    fade1.setToValue(0);

    FadeTransition fade2 = new FadeTransition(Duration.seconds(7), option2);
    fade2.setToValue(0);

    FadeTransition fade3 = new FadeTransition(Duration.seconds(10), option3);
    fade3.setToValue(0);

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
          response1.setVisible(true);
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
          response2.setVisible(true);
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
          response3.setVisible(true);
          action.run();
          hideOptions();
        });
  }
}
