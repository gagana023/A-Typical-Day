package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * A simple JavaFX application that displays a "Hello World" message
 * with the current Java and JavaFX versions.
 */
public class HelloWorld extends Application {

    /**
     * Default constructor for the HelloWorld application.
     */
    public HelloWorld() {
        // Default constructor
    }

    /**
     * Starts the JavaFX application by creating and showing a stage with a label.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        
        Label l = new Label("Hello from Java " + javaVersion + " with JavaFX " + javafxVersion);

        AnchorPane root = new AnchorPane();
        double size = 100;

        // Create 4 squares
        Rectangle topL = new Rectangle(size, size+30, Color.BURLYWOOD);
        Rectangle topR = new Rectangle(size, size+30, Color.BURLYWOOD);
        Rectangle botL = new Rectangle(size, size+30, Color.BURLYWOOD);
        Rectangle botR = new Rectangle(size, size+30, Color.BURLYWOOD);

        // Position in corners using Anchors [7]
        AnchorPane.setTopAnchor(topL, 75.0);
        AnchorPane.setLeftAnchor(topL, 75.0);

        AnchorPane.setTopAnchor(topR, 75.0);
        AnchorPane.setRightAnchor(topR, 75.0);

        AnchorPane.setBottomAnchor(botL, 75.0);
        AnchorPane.setLeftAnchor(botL, 75.0);

        AnchorPane.setBottomAnchor(botR, 75.0);
        AnchorPane.setRightAnchor(botR, 75.0);

        topL.setOnMouseClicked(e -> stage.setScene(new Scene(new Label("Top Left"), 800, 600)));
        topR.setOnMouseClicked(e -> stage.setScene(new Scene(new Label("Top Right"), 800, 600)));
        botL.setOnMouseClicked(e -> stage.setScene(new Scene(new Label("Bottom Left"), 800, 600)));
        botR.setOnMouseClicked(e -> stage.setScene(new Scene(new Label("Bottom Right"), 800, 600)));

        root.getChildren().addAll(topL, topR, botL, botR);
        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setTitle("Starting Position");
        stage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        launch();
    }
}