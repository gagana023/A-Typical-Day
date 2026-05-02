package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

/** Checks whether the player canvas is colliding with another canvas object. */
public class CollisionChecker {

  /** Returns true when the player overlaps the target canvas. */
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
