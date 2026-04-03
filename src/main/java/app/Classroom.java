package app;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.List;
import java.util.Random;

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

        NPC npc = new NPC("/student_sprite.png");

        npc.setPosition(100, 380);
        AnchorPane.setLeftAnchor(npc, 100.0);
        AnchorPane.setTopAnchor(npc, 380.0);

        List<String> options = DialogueEngine.getOptions(DialogueEngine.Room.CLASSROOM);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));
        //root.getChildren().addAll(option1, option2);

        FadeTransition fastFade = new FadeTransition(Duration.seconds(3.5), option1);
            fastFade.setToValue(0);
            fastFade.play();

        FadeTransition mediumFade = new FadeTransition(Duration.seconds(7), option2);
            mediumFade.setToValue(0);
            mediumFade.play();

        option1.setOnAction(e -> {
            Tasks.completeClassroomTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            Tasks.completeClassroomTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
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
                npc,
                option1,
                option2, 
                option3
        );

        return root;
    }
}