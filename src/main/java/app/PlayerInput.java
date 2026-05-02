package app;

import javafx.scene.Scene;

/**
 * Tracks WASD keyboard input for player movement.
 *
 * <p>This class listens for key press and key release events, stores which movement keys are
 * currently pressed, and provides horizontal and vertical movement values.
 */
public class PlayerInput {
  /** Tracks whether the W key is currently pressed. */
  private boolean wPressed;

  /** Tracks whether the A key is currently pressed. */
  private boolean aPressed;

  /** Tracks whether the S key is currently pressed. */
  private boolean sPressed;

  /** Tracks whether the D key is currently pressed. */
  private boolean dPressed;

  /**
   * Connects key press and key release controls to the scene.
   *
   * @param scene the scene that listens for keyboard input
   */
  public void connect(Scene scene) {
    scene.setOnKeyPressed(
        e -> {
          switch (e.getCode()) {
            case W -> wPressed = true;
            case A -> aPressed = true;
            case S -> sPressed = true;
            case D -> dPressed = true;
            default -> {}
          }
        });

    scene.setOnKeyReleased(
        e -> {
          switch (e.getCode()) {
            case W -> wPressed = false;
            case A -> aPressed = false;
            case S -> sPressed = false;
            case D -> dPressed = false;
            default -> {}
          }
        });
  }

  /** Stops all movement input. */
  public void stopMovement() {
    wPressed = false;
    aPressed = false;
    sPressed = false;
    dPressed = false;
  }

  /**
   * Returns the player's horizontal movement direction.
   *
   * @return -1 when moving left, 1 when moving right, or 0 when not moving horizontally
   */
  public double getHorizontalMovement() {
    double dx = 0;

    if (aPressed) {
      dx -= 1;
    }

    if (dPressed) {
      dx += 1;
    }

    return dx;
  }

  /**
   * Returns the player's vertical movement direction.
   *
   * @return -1 when moving up, 1 when moving down, or 0 when not moving vertically
   */
  public double getVerticalMovement() {
    double dy = 0;

    if (wPressed) {
      dy -= 1;
    }

    if (sPressed) {
      dy += 1;
    }

    return dy;
  }
}
