package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

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
  public boolean isColliding(Canvas player, Canvas target, double playerX, double playerY) {
    double playerW = player.getWidth();
    double playerH = player.getHeight();

    Double targetXObj = AnchorPane.getLeftAnchor(target);
    Double targetYObj = AnchorPane.getTopAnchor(target);

    double targetX = (targetXObj == null) ? 0 : targetXObj;
    double targetY = (targetYObj == null) ? 0 : targetYObj;

    double targetW = target.getWidth();
    double targetH = target.getHeight();

    return playerX < targetX + targetW
        && playerX + playerW > targetX
        && playerY < targetY + targetH
        && playerY + playerH > targetY;
  }
}
