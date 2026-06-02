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
    public Scene getScene (Stage stage)
    {
      AnchorPane root = new AnchorPane();

      NPC user = new NPC("/player11.png", 34, 98);
      // FIXME - sreshta - if you could please scale this (player still) up/tell me how i can scale it up without it being blurry, i'd be very grateful... - aadi
      AnchorPane.setLeftAnchor(user, 100.0);
      AnchorPane.setBottomAnchor(user, 200.0);

      Bully bully = new Bully();
      AnchorPane.setRightAnchor(bully, 100.0);
      AnchorPane.setBottomAnchor(bully, 200.0);

      Rectangle dialogueBox = new Rectangle(500, 120);
      dialogueBox.setFill(Color.GRAY);

      // TODO - aadi - WIP
      ArrayList<Label> dialogueTexts = new ArrayList<>();
      dialogueTexts.add(new Label("Man, I can't believe I missed so many points on that test, and for such small\nmistakes! I'm so SPED...\nHmm? Oh, hey, what's up?...why are you looking at me like that?"));
      dialogueTexts.add(new Label("I've never met anyone more pathetic in my life.\nCan't you do anything??"));
      dialogueTexts.add(new Label("Well, excuse you. Yeah, you're the one who bumped into me.\n...\n...what a rude person..."));

      Random random = new Random();
      
      Label dialogueText = dialogueTexts.get(random.nextInt(dialogueTexts.size()));
      dialogueText.setTextFill(Color.WHITE);

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

      root.getChildren().addAll(user, bully, dialogueBox, dialogueText, continueButton);

      return new Scene(root, 800, 600);
    }
}
