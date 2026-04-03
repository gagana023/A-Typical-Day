package app;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Office extends Room {

    public AnchorPane getRoot(Stage stage) {

        AnchorPane root = new AnchorPane();

        Label label = new Label("Welcome to the Office");
        Button back = new Button("Back");

        AnchorPane.setTopAnchor(label, 40.0);
        AnchorPane.setLeftAnchor(label, 320.0);

        AnchorPane.setTopAnchor(back, 20.0);
        AnchorPane.setLeftAnchor(back, 20.0);

        Rectangle desk = new Rectangle(400, 40);
        desk.setFill(Color.BURLYWOOD);

        AnchorPane.setTopAnchor(desk, 350.0);
        AnchorPane.setLeftAnchor(desk, 300.0);

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

        Rectangle monitor = new Rectangle(80, 50);
        monitor.setFill(Color.BLACK);

        AnchorPane.setTopAnchor(monitor, 280.0);
        AnchorPane.setLeftAnchor(monitor, 470.0);

        Rectangle stand = new Rectangle(10, 45);
        stand.setFill(Color.GRAY);

        AnchorPane.setTopAnchor(stand, 310.0);
        AnchorPane.setLeftAnchor(stand, 505.0);

        Rectangle base = new Rectangle(40, 8);
        base.setFill(Color.GRAY);

        AnchorPane.setTopAnchor(base, 345.0);
        AnchorPane.setLeftAnchor(base, 490.0);

        Label registrar = new Label("REGISTRAR");
        registrar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        AnchorPane.setTopAnchor(registrar, 360.0);
        AnchorPane.setLeftAnchor(registrar, 350.0);

        NPC npc = new NPC("/student_sprite.png");
        
        npc.setPosition(100,315);
        AnchorPane.setLeftAnchor(npc, 100.0);
        AnchorPane.setTopAnchor(npc, 315.0);

        List<String> options = DialogueEngine.getOptions(DialogueEngine.Room.OFFICE);

        Button option1 = new Button(options.get(0));
        Button option2 = new Button(options.get(1));
        Button option3 = new Button(options.get(2));
        root.getChildren().addAll(option1, option2, option3);

        FadeTransition fastFade = new FadeTransition(Duration.seconds(3.5), option1);
            fastFade.setToValue(0);
            fastFade.play();

        FadeTransition mediumFade = new FadeTransition(Duration.seconds(7), option2);
            mediumFade.setToValue(0);
            mediumFade.play();

        option1.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option2.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });

        option3.setOnAction(e -> {
            Tasks.completeOfficeTask();
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);        
        });
        AnchorPane.setTopAnchor(option1, 200.0);
        AnchorPane.setLeftAnchor(option1, 300.0);

        AnchorPane.setTopAnchor(option2, 250.0);
        AnchorPane.setLeftAnchor(option2, 300.0);

        AnchorPane.setTopAnchor(option3, 300.0);
        AnchorPane.setLeftAnchor(option3, 300.0);

        back.setOnAction(e ->{
            System.out.println("Back Clicked");
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
            HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        });
        root.getChildren().addAll(
                label,
                back,
                desk,
                leg1,
                leg2,
                leg3,
                leg4,
                monitor,
                stand,
                base,
                registrar,
                npc
        );

        return root;
    }
}