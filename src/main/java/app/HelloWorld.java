package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) {

        AnchorPane root = new AnchorPane();
        double size = 100;

        Rectangle topL = new Rectangle(size, size + 30, Color.BURLYWOOD);
        Rectangle topR = new Rectangle(size, size + 30, Color.BURLYWOOD);
        Rectangle botL = new Rectangle(size, size + 30, Color.BURLYWOOD);
        Rectangle botR = new Rectangle(size, size + 30, Color.BURLYWOOD);

        AnchorPane.setTopAnchor(topL, 75.0);
        AnchorPane.setLeftAnchor(topL, 75.0);

        AnchorPane.setTopAnchor(topR, 75.0);
        AnchorPane.setRightAnchor(topR, 75.0);

        AnchorPane.setBottomAnchor(botL, 75.0);
        AnchorPane.setLeftAnchor(botL, 75.0);

        AnchorPane.setBottomAnchor(botR, 75.0);
        AnchorPane.setRightAnchor(botR, 75.0);

        topL.setOnMouseClicked(e -> {
            Office office = new Office();
            Scene officeScene = new Scene(office.getRoot(stage), 800, 600);
            stage.setScene(officeScene);
        });

        // doesn't work when inside setOnMouseClicked
        Library library = new Library();

        topR.setOnMouseClicked(e ->
                stage.setScene(library.buildPrototypeLibrary())
        );

        botL.setOnMouseClicked(e -> {
            Classroom classroom = new Classroom();
            Scene classroomScene = new Scene(classroom.getRoot(stage), 800, 600);
            stage.setScene(classroomScene);
        });
        
        // doesn't work when inside setOnMouseClicked
        Cafeteria cafeteria = new Cafeteria();

        botR.setOnMouseClicked(e ->
                stage.setScene(cafeteria.buildPrototypeCafeteria())
        );

        root.getChildren().addAll(topL, topR, botL, botR);

        scene = new Scene(root, 800, 600);


        Label title = new Label("My App");

        Button start = new Button("Start");
        Button stats = new Button("Stats");

        Stats s = new Stats();

        VBox titleRoot = new VBox(20, title, start, stats);
        titleRoot.setStyle("-fx-alignment: center;");

        Scene titleScene = new Scene(titleRoot, 800, 600);

        start.setOnAction(e -> stage.setScene(scene));

        stats.setOnAction(e -> {
            Scene statsScene = new Scene(s.getMenu(), 800, 600);
            stage.setScene(statsScene);
        });

        stage.setScene(titleScene);
        stage.setTitle("Starting Position");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}