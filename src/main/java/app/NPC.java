package app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a non-player character displayed on the screen.
 *
 * <p>An NPC uses a sprite image, sets its canvas size based on that sprite, and renders the sprite
 * onto the canvas.
 */
public class NPC extends Canvas {

  private Sprite sprite;

  /**
   * Creates an NPC using the given sprite image path.
   *
   * @param spritePath the file path for the sprite image used by the NPC
   */
  public NPC(String spritePath) {
    sprite = new Sprite(spritePath, 1, 1, 1);

    sprite.setFrameSize(100, 160);

    setWidth(sprite.getFrameWidth());
    setHeight(sprite.getFrameHeight());

    sprite.setPosition(0, 0);
    GraphicsContext gc = getGraphicsContext2D();
    sprite.renderCurrent(gc);
  }

  /**
   * Sets the position of the NPC on the screen.
   *
   * @param x the x-coordinate of the NPC
   * @param y the y-coordinate of the NPC
   */
  public void setPosition(double x, double y) {
    setLayoutX(x);
    setLayoutY(y);
  }
}
