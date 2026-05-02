package app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class Player {

    private PlayerPosition position;
    private PlayerStats stats;
    private Canvas canvas;

    public Player(Canvas canvas) {
        this.canvas = canvas;
        this.position = new PlayerPosition(350, 300);
        this.stats = new PlayerStats();
    }

    public void updateCanvasPosition() {
        AnchorPane.setLeftAnchor(canvas, position.getX());
        AnchorPane.setTopAnchor(canvas, position.getY());
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setCoordinates(double x, double y) {
      position.setCoordinates(x, y);
    }

    public void stayInBoundaries(Scene scene) { 
      position.stayInBoundaries(scene, canvas.getWidth(), canvas.getHeight()); 
    }
}