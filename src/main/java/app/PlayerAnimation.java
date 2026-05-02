package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/** Handles the player's sprite animation and rendering. */
public class PlayerAnimation {

  private static final int FRAME_DELAY = 8;

  private Sprite sprite;
  private Canvas canvas;
  private int frameTick = 0;

  /** Creates a player animation using the given sprite sheet information. */
  public PlayerAnimation(String spritePath, int rows, int columns, int frameCount) {
    sprite = new Sprite(spritePath, rows, columns, frameCount);
    canvas = new Canvas(sprite.getFrameWidth(), sprite.getFrameHeight());
    renderFrame();
  }

  /** Returns the canvas that displays the player sprite. */
  public Canvas getCanvas() {
    return canvas;
  }

  /** Updates the sprite animation frame when the player is moving. */
  public void updateAnimation(boolean moving) {
    if (moving) {
      frameTick++;

      if (frameTick >= FRAME_DELAY) {
        sprite.nextFrame();
        frameTick = 0;
      }
    } else {
      frameTick = 0;
    }
  }

  /** Draws the current sprite frame on the canvas. */
  public void renderFrame() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    sprite.setPosition(0, 0);
    sprite.renderCurrent(gc);
  }
}
