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
        background.setPreserveRatio(false);// Invisible clickable bookshelf area: left half of the screen
Rectangle bookshelfClickArea = new Rectangle(400, 600);
bookshelfClickArea.setFill(Color.TRANSPARENT);
bookshelfClickArea.setStroke(Color.TRANSPARENT);

AnchorPane.setLeftAnchor(bookshelfClickArea, 0.0);
AnchorPane.setTopAnchor(bookshelfClickArea, 0.0);

// Checkout button starts hidden
Button checkoutButton = new Button("Checkout");
checkoutButton.setVisible(false);

AnchorPane.setLeftAnchor(checkoutButton, 430.0);
AnchorPane.setTopAnchor(checkoutButton, 280.0);

// When player clicks left half/bookshelf, show Checkout only if this task is active
bookshelfClickArea.setOnMouseClicked(e -> {
    if (Tasks.isTaskActive("library_book")) {
        checkoutButton.setVisible(true);
    }
});

// When player clicks Checkout, complete the task
checkoutButton.setOnAction(e -> {
    Tasks.completeTask("library_book");
    checkoutButton.setVisible(false);
    HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
});



        root.getChildren().addAll(background);



      stage.setTitle("Library");
      Button back = new Button("Back");
      back.setLayoutX(20);
      back.setLayoutY(20);

      NPC npc = new NPC("/student_sprite.png");

      npc.setPosition(320, 310);
        AnchorPane.setLeftAnchor(npc, 320.0);
        AnchorPane.setTopAnchor(npc, 310.0);

      List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.LIBRARY);
      List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.LIBRARY);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));

        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        Rectangle studyClickArea = new Rectangle(250, 180);
        studyClickArea.setFill(Color.TRANSPARENT);
        studyClickArea.setStroke(Color.TRANSPARENT);

        AnchorPane.setLeftAnchor(studyClickArea, 250.0);
        AnchorPane.setTopAnchor(studyClickArea, 340.0);

        studyClickArea.setOnMouseClicked(e -> {
            if (Tasks.isTaskActive("library_study")) {
                option1.setVisible(true);
                option2.setVisible(true);
                option3.setVisible(true);
            }
        });


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
            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            op2.setVisible(true);
            Tasks.completeTask("library_study");
            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
            op3.setVisible(true);
            Tasks.completeTask("library_study");
            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
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
      root.getChildren().addAll(bookshelfClickArea, studyClickArea, back, npc, option1, option2, option3, op1, op2, op3, checkoutButton);
      
      return new Scene(root, 800, 600);
   }
}
