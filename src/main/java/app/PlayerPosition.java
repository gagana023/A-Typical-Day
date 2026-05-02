package app;

import javafx.scene.Scene;

/**
 * Stores and updates the player's position.
 *
 * <p>This class tracks the player's x- and y-coordinates, moves the player based on input, and
 * keeps the player within the visible scene boundaries.
 */
public class PlayerPosition {
  /** The player's current x-coordinate. */
  private double x;

  /** The player's current y-coordinate. */
  private double y;

  /**
   * Creates a player position at the given coordinates.
   *
   * @param startX the starting x-coordinate
   * @param startY the starting y-coordinate
   */
  public PlayerPosition(double startX, double startY) {
    x = startX;
    y = startY;
  }

  /**
   * Moves the player by the given direction and speed.
   *
   * @param dx the horizontal movement direction
   * @param dy the vertical movement direction
   * @param speed the speed multiplier used for movement
   */
  public void move(double dx, double dy, double speed) {
    x += dx * speed;
    y += dy * speed;
  }

  /**
   * Keeps the player inside the scene boundaries.
   *
   * @param scene the scene used to calculate the visible boundaries
   * @param canvasWidth the width of the player's canvas
   * @param canvasHeight the height of the player's canvas
   */
  public void stayInBoundaries(Scene scene, double canvasWidth, double canvasHeight) {
    double maxX = scene.getWidth() - canvasWidth;
    double maxY = scene.getHeight() - canvasHeight;

    x = Math.max(0, Math.min(x, maxX));
    y = Math.max(0, Math.min(y, maxY));
  }

  /**
   * Sets the player's position to the given coordinates.
   *
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  public void setCoordinates(double newX, double newY) {
    x = newX;
    y = newY;
  }

  /**
   * Sets the player's x-coordinate.
   *
   * @param newX the new x-coordinate
   */
  public void setX(double newX) {
    x = newX;
  }

  /**
   * Sets the player's y-coordinate.
   *
   * @param newY the new y-coordinate
   */
  public void setY(double newY) {
    y = newY;
  }

  /**
   * Returns the player's x-coordinate.
   *
   * @return the current x-coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the player's y-coordinate.
   *
   * @return the current y-coordinate
   */
  public double getY() {
    return y;
  }
}
