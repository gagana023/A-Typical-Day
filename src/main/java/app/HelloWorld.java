package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public static Scene scene;
    public static User user;
    public static Label task1;
    public static Label task2;
    public static Label task3;
    // canvas.setId("gameCanvas");

    @Override
    public void start(Stage stage) {

        AnchorPane root = new AnchorPane();
        Canvas bgCanvas = new Canvas(800, 600);
        bgCanvas.setId("gameCanvas");
        root.setStyle("-fx-background-image: url('/hallway_background.png')");
        user = new User();

        //Rectangle topL = new Rectangle(size, size + 30, Color.BURLYWOOD);
        NPC topL = new NPC("/door.png");
        NPC topR = new NPC("/door.png");
        NPC botL = new NPC("/door.png");
        NPC botR = new NPC("/door.png");
        
        user.setRooms(topL, botL, topR, botR, stage);

        Button stats = new Button("Stats");
        Button help = new Button("Help");

        AnchorPane.setTopAnchor(topL, 100.0);
        AnchorPane.setLeftAnchor(topL, 100.0);
        topL.setWidth(100);
        topL.setHeight(160);

        AnchorPane.setLeftAnchor(topR, 550.0);
        AnchorPane.setTopAnchor(topR, 100.0);
        topR.setWidth(100);
        topR.setHeight(160);

        AnchorPane.setLeftAnchor(botL, 100.0);
        AnchorPane.setTopAnchor(botL, 400.0);
        botL.setWidth(100);
        botL.setHeight(160);

        AnchorPane.setLeftAnchor(botR, 550.0);
        AnchorPane.setTopAnchor(botR, 400.0);
        botR.setWidth(100);
        botR.setHeight(160);

        AnchorPane.setTopAnchor(stats, 500.0);
        AnchorPane.setRightAnchor(stats, 20.0);

        AnchorPane.setTopAnchor(help, 450.0);
        AnchorPane.setRightAnchor(help, 20.0);
        VBox taskBar = new VBox(5);
        taskBar.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-padding: 10;");
        Label title = new Label("Tasks");

        
        title.setTextFill(Color.WHITE);
        task1 = new Label();
        task2 = new Label();
        task3 = new Label();

        task1.setTextFill(Color.WHITE);
        task2.setTextFill(Color.WHITE);
        task3.setTextFill(Color.WHITE);

        updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
        Button toggle = new Button("▼");
        toggle.setOnAction(e -> {
            taskBar.setVisible(!taskBar.isVisible());
        });

        taskBar.getChildren().addAll(title, task1, task2, task3);

        AnchorPane.setTopAnchor(taskBar, 0.0);
        AnchorPane.setLeftAnchor(taskBar, 0.0);

        AnchorPane.setTopAnchor(toggle, 0.0);
        AnchorPane.setRightAnchor(toggle, 10.0);

        //stats.toFront();
        //help.toFront();
        
        scene = new Scene(root, 800, 600);

        user.connect(scene);

        topL.setOnMouseClicked(e -> {
            Office office = new Office();
            topL.setId("topLeft");
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

        Stats s = new Stats();

        VBox topStatsMenu = new VBox(5);
        topStatsMenu.getChildren().addAll(
            s.createBarSection("Health Bar"),
            s.createBarSection("Social Standing")
        );

        AnchorPane.setTopAnchor(topStatsMenu, 0.0);
        AnchorPane.setLeftAnchor(topStatsMenu, 315.0);
        topStatsMenu.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
        
        Help h = new Help(stage);

        stats.setOnAction(e -> {
            Scene statsScene = s.buildPrototypeHomeAndStats(stage);
            user.stop();
            stage.setScene(statsScene);
        });

        help.setOnAction(e -> {
            user.stop();
            stage.setScene(h.getHelp(stage));
        });

        root.getChildren().addAll(bgCanvas,
            topL, topR, botL, botR,
            user.getInAddAllForm(),
            stats, help,
            taskBar, toggle,
            topStatsMenu
        );

        StoryIntro intro = new StoryIntro();
        Scene introScene = intro.build(stage, scene, user);

        stage.setScene(introScene);
        stage.setTitle("ATypical Day");
        stage.show();
        intro.play();
        
        introScene.setOnMouseClicked(e ->
            {
                stage.setScene(scene);
                user.start();
            }
        );

    }

    /*

    */

    public static void main(String[] args) {
        launch();
    }

    public static void updateTasks(Label t1, Label t2) {
        t1.setText("Go to Office (top left room) and ask for extension");
        //t1.setStrikethrough(Tasks.officeTaskDone);
        t1.setStyle(Tasks.officeTaskDone ? "-fx-strikethrough: true;" : "");
    }

    public static void updateTasks(Label t1, Label t2, Label t3) {
        t1.setText("Go to Office and ask for extension");
        t1.setStyle(Tasks.officeTaskDone 
        ? "-fx-strikethrough: true; -fx-text-fill: gray;" 
        : "-fx-strikethrough: false; -fx-text-fill: white;");

        t2.setText("Go to Classroom and turn in homework");
        t2.setStyle(Tasks.classroomTaskDone 
        ? "-fx-strikethrough: true; -fx-text-fill: gray;" 
        : "-fx-strikethrough: false; -fx-text-fill: white;");

        if (Tasks.officeTaskDone) {
            t1.setTextFill(Color.GRAY);
        } else {
            t1.setTextFill(Color.WHITE);
        }

         if (Tasks.classroomTaskDone) {
             t2.setTextFill(Color.GRAY);
         } else {
             t2.setTextFill(Color.WHITE);
        }
    }
    
}