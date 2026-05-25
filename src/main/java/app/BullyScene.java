package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BullyScene {
    public Scene getScene (Stage stage)
    {
      AnchorPane root = new AnchorPane();

      NPC user = new NPC("/player_option.png");
      AnchorPane.setLeftAnchor(user, 100.0);
      AnchorPane.setBottomAnchor(user, 200.0);

      Bully bully = new Bully();
      AnchorPane.setRightAnchor(bully, 100.0);
      AnchorPane.setBottomAnchor(bully, 200.0);

      Rectangle dialogueBox = new Rectangle(500, 120);
      dialogueBox.setFill(Color.GRAY);

      Label dialogueText = new Label("Mean stuff...");
      dialogueText.setTextFill(Color.BLACK);

      AnchorPane.setLeftAnchor(dialogueBox, 150.0);
      AnchorPane.setTopAnchor(dialogueBox, 420.0);

      AnchorPane.setLeftAnchor(dialogueText, 150.0);
      AnchorPane.setTopAnchor(dialogueText, 420.0);

      Button continueButton = new Button("Continue");

      AnchorPane.setLeftAnchor(continueButton, 360.0);
      AnchorPane.setTopAnchor(continueButton, 500.0);

      continueButton.setOnAction(e ->
      {
        //HelloWorld.user.stop();
        stage.setScene(HelloWorld.scene);
        //HelloWorld.user.getPlayer().setCoordinates(350, 250);
        HelloWorld.scene.getRoot().requestFocus();
        HelloWorld.user.resume(HelloWorld.scene);
      });

      root.getChildren().addAll(user, bully, dialogueText, dialogueBox, continueButton);

      return new Scene(root, 800, 600);
    }
}
