package app;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.List;

public class Classroom extends Room {
    

    public AnchorPane getRoot(Stage stage) {

        AnchorPane root = new AnchorPane();

        Label label = new Label("Welcome to the Classroom");
        Button back = new Button("Back");

        AnchorPane.setTopAnchor(label, 40.0);
        AnchorPane.setLeftAnchor(label, 320.0);

        AnchorPane.setTopAnchor(back, 20.0);
        AnchorPane.setLeftAnchor(back, 20.0);

        // BLACKBOARD
        Rectangle blackBoard = new Rectangle(700, 200);
        blackBoard.setFill(Color.DARKGREEN);

        AnchorPane.setTopAnchor(blackBoard, 80.0);
        AnchorPane.setLeftAnchor(blackBoard, 150.0);

        // Math problem on board
        Label mathProblem = new Label("Solve: 3x² + 5x - 2 = 0");
        mathProblem.setTextFill(Color.WHITE);
        mathProblem.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        AnchorPane.setTopAnchor(mathProblem, 160.0);
        AnchorPane.setLeftAnchor(mathProblem, 350.0);

        // DESK (table top)
        Rectangle desk = new Rectangle(400, 40);
        desk.setFill(Color.BURLYWOOD);

        AnchorPane.setTopAnchor(desk, 350.0);
        AnchorPane.setLeftAnchor(desk, 300.0);

        // TABLE LEGS
        Rectangle leg1 = new Rectangle(20, 100, Color.SADDLEBROWN);
        Rectangle leg2 = new Rectangle(20, 100, Color.SADDLEBROWN);
        Rectangle leg3 = new Rectangle(20, 100, Color.SADDLEBROWN);
        Rectangle leg4 = new Rectangle(20, 100, Color.SADDLEBROWN);

        AnchorPane.setTopAnchor(leg1, 390.0);
        AnchorPane.setLeftAnchor(leg1, 300.0);

        AnchorPane.setTopAnchor(leg2, 390.0);
        AnchorPane.setLeftAnchor(leg2, 680.0);

        AnchorPane.setTopAnchor(leg3, 390.0);
        AnchorPane.setLeftAnchor(leg3, 420.0);

        AnchorPane.setTopAnchor(leg4, 390.0);
        AnchorPane.setLeftAnchor(leg4, 560.0);

        NPC classmate = new NPC("/student_sprite.png");
        classmate.setPosition(100, 380);

        AnchorPane.setLeftAnchor(classmate, 100.0);
        AnchorPane.setTopAnchor(classmate, 380.0);

        NPC teacher = new NPC("/teacher_sprite.png");
        teacher.setPosition(175, 250);

        AnchorPane.setLeftAnchor(teacher, 175.0);
        AnchorPane.setTopAnchor(teacher, 250.0);

        List<String> options = UserDialogueEngine.getOptions(UserDialogueEngine.Room.CLASSROOM);
        List<String> npcOptions = NPCDialogue.getOptions(NPCDialogue.Room.CLASSROOM);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));
        //root.getChildren().addAll(option1, option2);
        Text op1Text = new Text(npcOptions.get(0));
        Text op2Text = new Text(npcOptions.get(1));
        Text op3Text = new Text(npcOptions.get(2));

        // Wrap text so it stays inside box
        op1Text.setWrappingWidth(240);
        op2Text.setWrappingWidth(240);
        op3Text.setWrappingWidth(240);

        // Background boxes
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

        // Stack text on top of box
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
            Tasks.completeClassroomTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            op2.setVisible(true);
            Tasks.completeClassroomTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
            op3.setVisible(true);
            Tasks.completeClassroomTask();
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

        root.getChildren().addAll(
                label,
                back,
                blackBoard,
                mathProblem,
                desk,
                leg1,
                leg2,
                leg3,
                leg4,
                classmate,
                teacher,
                option1,
                option2, 
                option3, op1, op2, op3
        );

        return root;
    }
}