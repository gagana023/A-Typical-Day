package app;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
        // Random rand = new Random();
        // double minX = 100;
        // double maxX = 700;
        // double minY = 150;
        // double maxY = 500;

        // double randomX = minX + rand.nextDouble() * (maxX - minX);
        // double randomY = minY + rand.nextDouble() * (maxY - minY);

        // npc.setPosition(randomX, randomY);
        // AnchorPane.setLeftAnchor(npc, randomX);
        // AnchorPane.setTopAnchor(npc, randomY);

        npc.setPosition(100, 380);
        AnchorPane.setLeftAnchor(npc, 100.0);
        AnchorPane.setTopAnchor(npc, 380.0);
        back.setOnAction(e ->{
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
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
                npc
        );

        return root;
    }
}