package app;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BullyScene {
  public Scene getScene(Stage stage) {
    AnchorPane root = new AnchorPane();

    NPC user = new NPC("/player still.png", 34, 98);
    // FIXME - sreshta - if you could please scale this (player still) up/tell me how i can scale it
    // up without it being blurry, i'd be very grateful... - aadi
    AnchorPane.setLeftAnchor(user, 100.0);
    AnchorPane.setBottomAnchor(user, 200.0);

    Bully bully = new Bully();
    AnchorPane.setRightAnchor(bully, 100.0);
    AnchorPane.setBottomAnchor(bully, 200.0);

    Rectangle dialogueBox = new Rectangle(500, 120);
    dialogueBox.setFill(Color.GRAY);

    // TODO - aadi - WIP
    ArrayList<Label> dialogueTexts = new ArrayList<>();
    dialogueTexts.add(
        new Label(
            "Man, I can't believe I missed so many points on that test, and for such small\n"
                + "mistakes! I'm so STUPID!...\n"
                + "Hmm? Oh, hey, what's up?...why are you looking at me like that?"));
    dialogueTexts.add(
        new Label(
            "Well, excuse you. Yeah, you're the one who bumped into me.\n"
                + "...\n"
                + "...what a rude person..."));

    Random random = new Random();

    Label dialogueText = dialogueTexts.get(random.nextInt(dialogueTexts.size()));
    dialogueText.setTextFill(Color.WHITE);

    AnchorPane.setLeftAnchor(dialogueBox, 150.0);
    AnchorPane.setTopAnchor(dialogueBox, 420.0);

    AnchorPane.setLeftAnchor(dialogueText, 150.0);
    AnchorPane.setTopAnchor(dialogueText, 420.0);

    Button continueButton = new Button("Continue");
    styleButton(continueButton);

    AnchorPane.setLeftAnchor(continueButton, 360.0);
    AnchorPane.setTopAnchor(continueButton, 500.0);

    continueButton.setOnAction(
        e -> {
          // HelloWorld.user.stop();
          stage.setScene(HelloWorld.scene);
          // HelloWorld.user.getPlayer().setCoordinates(350, 250);
          HelloWorld.scene.getRoot().requestFocus();
          HelloWorld.user.resume(HelloWorld.scene);
        });

    root.getChildren().addAll(user, bully, dialogueBox, dialogueText, continueButton);

    return new Scene(root, 800, 600);
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
    button.setPrefWidth(130);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
  }
}
