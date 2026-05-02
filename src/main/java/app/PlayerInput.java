package app;

import javafx.scene.Scene;

/** Tracks WASD keyboard input for player movement. */
public class PlayerInput {

  private boolean wPressed;
  private boolean aPressed;
  private boolean sPressed;
  private boolean dPressed;

  /** Connects key press and key release controls to the scene. */
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

  /** Returns horizontal movement direction. */
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

  /** Returns vertical movement direction. */
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
