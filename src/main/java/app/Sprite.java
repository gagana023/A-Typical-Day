package app;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a sprite sheet composed of evenly sized frames arranged in a grid (rows × columns).
 * This class provides:
 *
 * <ul>
 *   <li>Automatic calculation of each frame's width and height
 *   <li>Rendering of any frame index to a GraphicsContext
 *   <li>Support for customizable animation sequences
 *   <li>Methods for advancing to the next frame in the sequence
 * </ul>
 *
 * Sprite indices are numbered from 0 → (spriteCount - 1), scanning across rows first
 * (left-to-right, then top-to-bottom).
 *
 * <p>This class does not perform timing; callers should advance frames using an animation loop such
 * as AnimationTimer or Timeline.
 */
public class Sprite {

  /** Full sprite sheet image containing all frames. */
  private final Image sheet;

  /** Number of columns in the sprite sheet. */
  private final int cols;

  /** Total number of valid frames in the sheet. */
  private final int spriteCount;

  /** Width of a single sprite frame in pixels. */
  private double frameWidth;

  /** Height of a single sprite frame in pixels. */
  private double frameHeight;

  /** Native size of a single sprite.. doesn't change */
  private final double spriteWidth;

  /** Original width of one sprite frame in the sprite sheet */
  private final double spriteHeight;

  /** Location of the sprite */
  private double x;

  private double y;

  /**
   * The ordered list of frame indices used for animation.
   *
   * <p>For example, a sequence like [0, 1, 2, 1] creates a smoother looping walk animation.
   */
  private List<Integer> animationSequence = new ArrayList<>();

  /** Current index inside the animation sequence. */
  private int sequenceIndex = 0;

  /**
   * Constructs a Sprite from a sprite sheet image.
   *
   * @param resourcePath the resource path (path after /src/main/resources/... ) use linux style
   *     paths for windows & linux
   * @param rows number of rows the sheet is divided into
   * @param cols number of columns the sheet is divided into
   * @param spriteCount total number of valid frames in the sheet (supports sheets where the last
   *     row is partially filled)
   * @throws IllegalArgumentException if parameters are invalid
   */
  public Sprite(String resourcePath, int rows, int cols, int spriteCount) {
    this(new Image(Sprite.class.getResourceAsStream(resourcePath)), rows, cols, spriteCount);
  }

  /**
   * Constructs a Sprite from a sprite sheet image.
   *
   * @param sheet the full sprite-sheet image (must not be null)
   * @param rows number of rows the sheet is divided into
   * @param cols number of columns the sheet is divided into
   * @param spriteCount total number of valid frames in the sheet (supports sheets where the last
   *     row is partially filled)
   * @throws IllegalArgumentException if parameters are invalid
   */
  public Sprite(Image sheet, int rows, int cols, int spriteCount) {
    if (sheet == null) {
      throw new IllegalArgumentException("Sprite sheet image must not be null");
    }
    if (rows <= 0 || cols <= 0 || spriteCount <= 0) {
      throw new IllegalArgumentException("rows, cols, and spriteCount must be positive");
    }

    this.sheet = sheet;
    this.cols = cols;
    this.spriteCount = spriteCount;

    // Set default size of render
    this.frameWidth = this.spriteWidth = sheet.getWidth() / cols;
    this.frameHeight = this.spriteHeight = sheet.getHeight() / rows;

    // Default animation sequence: play all frames in order.
    for (int i = 0; i < spriteCount; i++) {
      animationSequence.add(i);
    }
  }

  /**
   * @return the total number of frames in the sprite sheet
   */
  public int getSpriteCount() {
    return spriteCount;
  }

  /**
   * Sets the size used when drawing each sprite frame.
   *
   * @param width the width to draw each frame
   * @param height the height to draw each frame
   */
  public void setFrameSize(double width, double height) {
    this.frameWidth = width;
    this.frameHeight = height;
  }

  /**
   * @return the width of a single frame - default to native size
   */
  public double getFrameWidth() {
    return frameWidth;
  }

  /**
   * @return the height of a single frame - defaults to native size
   */
  public double getFrameHeight() {
    return frameHeight;
  }

  /**
   * Replace the current animation sequence with a custom one. All frame indices must be between 0
   * and spriteCount-1.
   *
   * @param sequence ordered list of frame indices
   * @throws IllegalArgumentException if invalid or empty
   */
  public void setAnimationSequence(List<Integer> sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException("Animation sequence must not be null or empty");
    }
    for (int index : sequence) {
      if (index < 0 || index >= spriteCount) {
        throw new IllegalArgumentException("Invalid frame index in sequence: " + index);
      }
    }
    this.animationSequence = new ArrayList<>(sequence);
    this.sequenceIndex = 0;
  }

  /**
   * Advances to the next frame in the animation sequence.
   *
   * <p>The sequence loops back to the beginning after the final frame.
   */
  public void nextFrame() {
    if (animationSequence.isEmpty()) return;
    sequenceIndex = (sequenceIndex + 1) % animationSequence.size();
  }

  /**
   * Move the location of the sprite to new position
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * X location of sprite
   *
   * @return X location of sprite
   */
  public double getXPosition() {
    return x;
  }

  /**
   * Y location of sprite
   *
   * @return Y location of sprite
   */
  public double getYPosition() {
    return y;
  }

  /**
   * Renders the current frame in the animation sequence.
   *
   * @param gc the GraphicsContext to draw into
   */
  public void renderCurrent(GraphicsContext gc) {
    if (animationSequence.isEmpty()) return;
    int spriteIndex = animationSequence.get(sequenceIndex);
    render(gc, spriteIndex, this.x, this.y, getFrameWidth(), getFrameHeight());
  }

  /**
   * Custom render a specific frame index from the sprite sheet.
   *
   * @param gc the GraphicsContext to draw into
   * @param spriteIndex which frame to render (0-based)
   * @param x destination X coordinate on the canvas
   * @param y destination Y coordinate on the canvas
   * @param destWidth width to draw the frame on screen
   * @param destHeight height to draw the frame on screen
   * @throws IllegalArgumentException if spriteIndex is invalid
   */
  public void render(
      GraphicsContext gc,
      int spriteIndex,
      double x,
      double y,
      double destWidth,
      double destHeight) {
    if (spriteIndex < 0 || spriteIndex >= spriteCount) {
      throw new IllegalArgumentException("Invalid sprite index: " + spriteIndex);
    }

    int col = spriteIndex % cols;
    int row = spriteIndex / cols;

    double sx = col * spriteWidth;
    double sy = row * spriteHeight;

    gc.drawImage(
        sheet,
        sx,
        sy,
        spriteWidth,
        spriteHeight, // source rectangle
        x,
        y,
        destWidth,
        destHeight // destination rectangle
        );
  }
}
