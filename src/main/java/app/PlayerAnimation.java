package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Handles the player's sprite animation and rendering.
 *
 * <p>This class creates a sprite from a sprite sheet, displays it on a canvas, updates animation
 * frames when the player moves, and renders the current frame.
 */
public class PlayerAnimation {
  /** The number of update calls to wait before changing to the next animation frame. */
  private static final int FRAME_DELAY = 8;

  /** The sprite used for the player's animation. */
  private Sprite sprite;

  /** The canvas where the player sprite is drawn. */
  private Canvas canvas;

  /** Counts updates so the animation does not switch frames too quickly. */
  private int frameTick = 0;

  /**
   * Creates a player animation using the given sprite sheet information.
   *
   * @param spritePath the file path for the player sprite sheet
   * @param rows the number of rows in the sprite sheet
   * @param columns the number of columns in the sprite sheet
   * @param frameCount the total number of animation frames
   */
  public PlayerAnimation(String spritePath, int rows, int columns, int frameCount) {
    sprite = new Sprite(spritePath, rows, columns, frameCount);
    canvas = new Canvas(sprite.getFrameWidth(), sprite.getFrameHeight());
    renderFrame();
  }

  /**
   * Returns the canvas that displays the player sprite.
   *
   * @return the canvas containing the player animation
   */
  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Updates the sprite animation frame when the player is moving.
   *
   * <p>If the player is moving, the frame counter increases until it reaches the frame delay, then
   * the sprite advances to the next frame. If the player is not moving, the frame counter resets.
   *
   * @param moving true if the player is moving, false otherwise
   */
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

  /**
   * Draws the current sprite frame on the canvas.
   *
   * <p>This method clears the old frame, resets the sprite drawing position to the top-left corner
   * of the canvas, and renders the current sprite frame.
   */
  public void renderFrame() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    sprite.setPosition(0, 0);
    sprite.renderCurrent(gc);
  }
}
