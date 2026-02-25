package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
        Scene scene = new Scene(new StackPane(l), 400, 200);
        
        stage.setScene(scene);
        stage.setTitle("Hello World!");
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
