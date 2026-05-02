package app;

import javafx.scene.Scene;

/** Stores and updates the player's position. */
public class PlayerPosition {

  private double x;
  private double y;

  /** Creates a player position at the given coordinates. */
  public PlayerPosition(double startX, double startY) {
    x = startX;
    y = startY;
  }

  /** Moves the player by the given amount. */
  public void move(double dx, double dy, double speed) {
    x += dx * speed;
    y += dy * speed;
  }

  /** Keeps the player inside the scene boundaries. */
  public void stayInBoundaries(Scene scene, double canvasWidth, double canvasHeight) {
    double maxX = scene.getWidth() - canvasWidth;
    double maxY = scene.getHeight() - canvasHeight;

    x = Math.max(0, Math.min(x, maxX));
    y = Math.max(0, Math.min(y, maxY));
  }

  /** Sets the player position. */
  public void setCoordinates(double newX, double newY) {
    x = newX;
    y = newY;
  }

  /** Sets the x-coordinate. */
  public void setX(double newX) {
    x = newX;
  }

  /** Sets the y-coordinate. */
  public void setY(double newY) {
    y = newY;
  }

  /** Returns the x-coordinate. */
  public double getX() {
    return x;
  }

  /** Returns the y-coordinate. */
  public double getY() {
    return y;
  }
}
