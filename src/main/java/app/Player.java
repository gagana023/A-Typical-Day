package app;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

/**
 * Represents the player in the game.
 *
 * <p>The player has a canvas for displaying the character, a position object for tracking movement,
 * and a stats object for tracking player values.
 */
public class Player {
  /** Stores the player's current position. */
  private PlayerPosition position;

  /** Stores the player's stats. */
  private PlayerStats stats;

  /** The canvas used to display the player on the screen. */
  private Canvas canvas;

  /**
   * Creates a player using the given canvas.
   *
   * <p>The player starts at the default position of x = 350 and y = 300, and receives a new
   * PlayerStats object.
   *
   * @param canvas the canvas used to display the player
   */
  public Player(Canvas canvas) {
    this.canvas = canvas;
    this.position = new PlayerPosition(350, 300);
    this.stats = new PlayerStats();
  }

  /** Updates the player's canvas location to match the player's stored position. */
  public void updateCanvasPosition() {
    AnchorPane.setLeftAnchor(canvas, position.getX());
    AnchorPane.setTopAnchor(canvas, position.getY());
  }

  /**
   * Returns the player's position object.
   *
   * @return the player's current position
   */
  public PlayerPosition getPosition() {
    return position;
  }

  /**
   * Returns the canvas used to display the player.
   *
   * @return the player's canvas
   */
  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Returns the player's stats object.
   *
   * @return the player's stats
   */
  public PlayerStats getStats() {
    return stats;
  }

  /**
   * Sets the player's position coordinates.
   *
   * @param x the new x-coordinate
   * @param y the new y-coordinate
   */
  public void setCoordinates(double x, double y) {
    position.setCoordinates(x, y);
  }

  /**
   * Keeps the player inside the boundaries of the scene.
   *
   * @param scene the scene used to determine the movement boundaries
   */
  public void stayInBoundaries(Scene scene) {
    position.stayInBoundaries(scene, canvas.getWidth(), canvas.getHeight());
  }
}
