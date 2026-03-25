package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public static Scene scene;
    public static User user;

    @Override
    public void start(Stage stage) {

        AnchorPane root = new AnchorPane();
        user = new User();

        //Rectangle topL = new Rectangle(size, size + 30, Color.BURLYWOOD);
        NPC topL = new NPC("/door.png");
        NPC topR = new NPC("/door.png");
        NPC botL = new NPC("/door.png");
        NPC botR = new NPC("/door.png");
        //Rectangle topR = new Rectangle(size, size + 30, Color.BURLYWOOD);
        //Rectangle botL = new Rectangle(size, size + 30, Color.BURLYWOOD);
        //Rectangle botR = new Rectangle(size, size + 30, Color.BURLYWOOD);
        user.setRooms(topL, botL, topR, botR, stage);
        Button stats = new Button("Stats");
        Button help = new Button("Help");
        stats.setLayoutX(20);
        stats.setLayoutY(20);
        help.setLayoutX(20);
        help.setLayoutX(45);

        AnchorPane.setTopAnchor(topL, 100.0);
        AnchorPane.setLeftAnchor(topL, 100.0);

        AnchorPane.setLeftAnchor(topR, 550.0);
        AnchorPane.setTopAnchor(topR, 100.0);

        AnchorPane.setLeftAnchor(botL, 100.0);
        AnchorPane.setTopAnchor(botL, 400.0);

        AnchorPane.setLeftAnchor(botR, 550.0);
        AnchorPane.setTopAnchor(botR, 400.0);

        AnchorPane.setTopAnchor(stats, 20.0);
        AnchorPane.setRightAnchor(stats, 20.0);

        AnchorPane.setTopAnchor(help, 40.0);
        AnchorPane.setRightAnchor(help, 20.0);

        root.getChildren().addAll(topL, topR, botL, botR, stats, user.getInAddAllForm());

        scene = new Scene(root, 800, 600);

        user.connect(scene);

        topL.setOnMouseClicked(e -> {
            Office office = new Office();
            Scene officeScene = new Scene(office.getRoot(stage), 800, 600);
            stage.setScene(officeScene);
        });

        // doesn't work when inside setOnMouseClicked
        Library library = new Library();

        topR.setOnMouseClicked(e ->
                stage.setScene(library.buildPrototypeLibrary(stage))
        );

        botL.setOnMouseClicked(e -> {
            Classroom classroom = new Classroom();
            Scene classroomScene = new Scene(classroom.getRoot(stage), 800, 600);
            stage.setScene(classroomScene);
        });
        
        // doesn't work when inside setOnMouseClicked
        Cafeteria cafeteria = new Cafeteria();

        botR.setOnMouseClicked(e ->
                stage.setScene(cafeteria.buildPrototypeCafeteria(stage))
        );

        Label title = new Label("My App");

        Button start = new Button("Start");

        Stats s = new Stats();
        Help h = new Help();

        VBox titleRoot = new VBox(20, title, start, stats, help);
        titleRoot.setStyle("-fx-alignment: center;");

        Scene titleScene = new Scene(titleRoot, 800, 600);

        start.setOnAction(e -> {
            stage.setScene(scene);
            user.start();
            scene.getRoot().requestFocus();
        });

        stats.setOnAction(e -> {
            Scene statsScene = s.buildPrototypeHomeAndStats(stage);
            user.stop();
            stage.setScene(statsScene);
        });

        help.setOnAction(e -> {
            user.stop();
            stage.setScene(h.getHelp(stage));
        });

        stage.setScene(titleScene);
        stage.setTitle("Starting Position");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}