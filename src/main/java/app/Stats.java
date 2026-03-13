package app;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.Random;

public class Stats extends User {

    Random rand = new Random();

    public VBox getMenu() {

        VBox menuBox = new VBox();
        menuBox.setPrefSize(200, 200);

        AnchorPane.setTopAnchor(menuBox, 120.0);
        AnchorPane.setLeftAnchor(menuBox, 50.0);

        StackPane section1 = createSection("GAME STATS");
        StackPane section2 = createSection("Tasks");
        StackPane section3 = createBarSection("Health Bar");
        StackPane section4 = createBarSection("Social Standing");
        StackPane section5 = createSection("Time");

        menuBox.getChildren().addAll(section1, section2, section3, section4, section5);

        return menuBox;
    }

    public StackPane createSection(String text) {

        StackPane pane = new StackPane();
        pane.setPrefHeight(50);
        pane.setStyle("-fx-border-color: black;");

        Label label = new Label(text);

        pane.getChildren().add(label);

        return pane;
    }

    public StackPane createBarSection(String text) {

        StackPane pane = new StackPane();
        pane.setPrefHeight(50);
        pane.setStyle("-fx-border-color: black;");

        VBox content = new VBox();

        Label label = new Label(text);

        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(150);

        double randomValue = rand.nextDouble();
        bar.setProgress(randomValue);

        content.getChildren().addAll(label, bar);

        pane.getChildren().add(content);

        return pane;
    }
}