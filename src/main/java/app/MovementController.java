package app;

import javafx.scene.Scene;

/**
 * Controls player movement based on keyboard input.
 *
 * <p>This class reads movement values from a PlayerInput object, moves the player, keeps the player
 * inside the scene boundaries, and can stop movement when needed.
 */
public class MovementController {

  private final double SPEED = 5;
  private PlayerInput input;

  /**
   * Creates a movement controller using the given player input handler.
   *
   * @param input the input handler used to read the player's movement direction
   */
  public MovementController(PlayerInput input) {
    this.input = input;
  }

  /**
   * Updates the player's position based on the current movement input.
   *
   * <p>If the player is moving, this method moves the player by the set speed and keeps the player
   * within the scene boundaries.
   *
   * @param player the player whose position is updated
   * @param scene the scene used to check movement boundaries
   * @return true if the player is moving, false otherwise
   */
  public boolean update(Player player, Scene scene) {
    double dx = input.getHorizontalMovement();
    double dy = input.getVerticalMovement();

    boolean moving = dx != 0 || dy != 0;

    if (moving) {
      player.getPosition().move(dx, dy, SPEED);
      player
          .getPosition()
          .stayInBoundaries(scene, player.getCanvas().getWidth(), player.getCanvas().getHeight());
    }

    return moving;
  }

  /** Stops all current player movement input. */
  public void stop() {
    input.stopMovement();
  }
}
