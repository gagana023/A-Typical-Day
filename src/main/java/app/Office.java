package app;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;

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

        Rectangle npc = new Rectangle(40, 60, Color.BLUE);
        Random rand = new Random();
        double minX = 100;
        double maxX = 700;
        double minY = 150;
        double maxY = 500;

        double randomX = minX + rand.nextDouble() * (maxX - minX);
        double randomY = minY + rand.nextDouble() * (maxY - minY);

        AnchorPane.setLeftAnchor(npc, randomX);
        AnchorPane.setTopAnchor(npc, randomY);

        back.setOnAction(e ->{
            System.out.println("Back Clicked");
            stage.setScene(HelloWorld.scene);
            HelloWorld.user.stop();
            HelloWorld.user.resume(HelloWorld.scene);
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