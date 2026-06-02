package app;

import javafx.scene.canvas.Canvas;

/**
 * Checks collision between the player canvas and another canvas object.
 *
 * <p>This class compares the position and size of two canvas objects to determine whether they
 * overlap on the screen.
 */
public class CollisionChecker {

  /**
   * Determines whether the player canvas is overlapping a target canvas.
   *
   * @param player the canvas representing the player
   * @param target the canvas object being checked for collision
   * @param playerX the current x-coordinate of the player
   * @param playerY the current y-coordinate of the player
   * @return true if the player overlaps the target canvas, false otherwise
   */
  public boolean isColliding(Canvas a, Canvas b) {
    return a.getBoundsInParent().intersects(b.getBoundsInParent());
  }

  public boolean isCollidingWithPadding(Canvas a, Canvas b, double xPadding, double yPadding) {
    double aMinX = a.getBoundsInParent().getMinX() + xPadding;
    double aMaxX = a.getBoundsInParent().getMaxX() - xPadding;
    double aMinY = a.getBoundsInParent().getMinY() + yPadding;
    double aMaxY = a.getBoundsInParent().getMaxY() - yPadding;

    double bMinX = b.getBoundsInParent().getMinX() + xPadding;
    double bMaxX = b.getBoundsInParent().getMaxX() - xPadding;
    double bMinY = b.getBoundsInParent().getMinY() + yPadding;
    double bMaxY = b.getBoundsInParent().getMaxY() - yPadding;

    return aMinX < bMaxX && aMaxX > bMinX && aMinY < bMaxY && aMaxY > bMinY;
  }
}
