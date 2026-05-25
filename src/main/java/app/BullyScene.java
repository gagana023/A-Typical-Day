package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BullyScene {
    public Scene getScene (Stage stage)
    {
      AnchorPane root = new AnchorPane();

      NPC user = new NPC("/walk-front.png");
      AnchorPane.setLeftAnchor(user, 100.0);
      AnchorPane.setBottomAnchor(user, 200.0);

      Bully bully = new Bully();
      AnchorPane.setRightAnchor(bully, 100.0);
      AnchorPane.setBottomAnchor(bully, 200.0);

      Rectangle dialogueBox = new Rectangle(500, 120);
      dialogueBox.setFill(Color.GRAY);

      AnchorPane.setLeftAnchor(dialogueBox, 150.0);
      AnchorPane.setTopAnchor(dialogueBox, 420.0);

      Button continueButton = new Button("Continue");

      AnchorPane.setLeftAnchor(continueButton, 360.0);
      AnchorPane.setTopAnchor(continueButton, 500.0);

      continueButton.setOnAction(e ->
      {
        HelloWorld.user.stop();
        HelloWorld.user.getPlayer().setCoordinates(350, 250);
        stage.setScene(HelloWorld.scene);
        HelloWorld.user.resume(HelloWorld.scene);
      });

      root.getChildren().addAll(user, bully, dialogueBox, continueButton);

      return new Scene(root, 800, 600);
    }
}
