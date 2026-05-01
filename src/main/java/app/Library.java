package app;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.List;


public class Library extends JoinGroupHere{
   public Scene buildPrototypeLibrary(Stage stage) {
      AnchorPane root = new AnchorPane();

      Image cafeteriaImg = new Image(getClass().getResource("/library.jpg").toExternalForm());
        ImageView background = new ImageView(cafeteriaImg);

        background.setFitWidth(800);
        background.setFitHeight(600);
        background.setPreserveRatio(false);

        root.getChildren().add(background);



      stage.setTitle("Library");
      Line l1 = new Line(0, 100, 300, 150);
      Line l2 = new Line(800, 100, 300, 150);
      Line l3 = new Line(0, 500, 300, 450);
      Line l4 = new Line(800, 500, 300, 450);
      Line l5 = new Line(300, 150, 300, 450);
      Ellipse table = new Ellipse(250, 450, 100, 50);
      Rectangle tableLegs = new Rectangle(225, 450, 50, 75);
      Button back = new Button("Back");
      back.setLayoutX(20);
      back.setLayoutY(20);

      NPC npc = new NPC("/student_sprite.png");
      // Random rand = new Random();
      //   double minX = 100;
      //   double maxX = 700;
      //   double minY = 150;
      //   double maxY = 500;

      //   double randomX = minX + rand.nextDouble() * (maxX - minX);
      //   double randomY = minY + rand.nextDouble() * (maxY - minY);
      //   npc.setPosition(randomX,randomY);
      //   AnchorPane.setLeftAnchor(npc, randomX);
      //   AnchorPane.setTopAnchor(npc, randomY);

      npc.setPosition(320, 310);
        AnchorPane.setLeftAnchor(npc, 320.0);
        AnchorPane.setTopAnchor(npc, 310.0);

      List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY);
      List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));
        //root.getChildren().addAll(option1, option2, option3);
        Text op1Text = new Text(npcOptions.get(0));
        Text op2Text = new Text(npcOptions.get(1));
        Text op3Text = new Text(npcOptions.get(2));

        op1Text.setWrappingWidth(240);
        op2Text.setWrappingWidth(240);
        op3Text.setWrappingWidth(240);

        Rectangle box1 = new Rectangle(260, 70);
        box1.setFill(Color.WHITE);
        box1.setStroke(Color.BLACK);
        box1.setArcWidth(15); // rounded corners
        box1.setArcHeight(15);

        Rectangle box2 = new Rectangle(260, 70);
        box2.setFill(Color.WHITE);
        box2.setStroke(Color.BLACK);
        box2.setArcWidth(15);
        box2.setArcHeight(15);

        Rectangle box3 = new Rectangle(260, 70);
        box3.setFill(Color.WHITE);
        box3.setStroke(Color.BLACK);
        box3.setArcWidth(15);
        box3.setArcHeight(15);

        StackPane op1 = new StackPane(box1, op1Text);
        StackPane op2 = new StackPane(box2, op2Text);
        StackPane op3 = new StackPane(box3, op3Text);

        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        
        AnchorPane.setTopAnchor(op1, 100.0);
        AnchorPane.setLeftAnchor(op1, 250.0);

        AnchorPane.setTopAnchor(op2, 100.0);
        AnchorPane.setLeftAnchor(op2, 250.0);

        AnchorPane.setTopAnchor(op3, 100.0);
        AnchorPane.setLeftAnchor(op3, 250.0);

        FadeTransition fastFade = new FadeTransition(Duration.seconds(3.5), option1);
            fastFade.setToValue(0);
            fastFade.play();

        FadeTransition mediumFade = new FadeTransition(Duration.seconds(7), option2);
            mediumFade.setToValue(0);
            mediumFade.play();

        option1.setOnAction(e -> {
            op1.setVisible(true);
            Tasks.completeTask("library_study");
HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            op2.setVisible(true);
            Tasks.completeTask("library_study");
HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
            op3.setVisible(true);
            Tasks.completeTask("library_study");
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        AnchorPane.setTopAnchor(option1, 200.0);
        AnchorPane.setLeftAnchor(option1, 300.0);

        AnchorPane.setTopAnchor(option2, 250.0);
        AnchorPane.setLeftAnchor(option2, 300.0);

        AnchorPane.setTopAnchor(option3, 300.0);
        AnchorPane.setLeftAnchor(option3, 300.0);
        

      back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
      });
        
    //   root.getChildren().addAll(l1, l2, l3, l4, l5, table, tableLegs, back, npc, option1, option2, option3, op1, op2, op3);
      root.getChildren().addAll(back, npc, option1, option2, option3, op1, op2, op3);
      
      return new Scene(root, 800, 600);
   }
}
