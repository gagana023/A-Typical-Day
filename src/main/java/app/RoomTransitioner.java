package app;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Handles room transitions when the user collides with room entrance areas.
 *
 * <p>This class checks whether the player is colliding with one of the four room entrance canvases
 * and sends the player to the matching room scene.
 */
public class RoomTransitioner {
  /**
   * The canvas representing the top-left, top-right, bottom-left, and bottom-right room entrance.
   */
  private Canvas topL, topR, botL, botR;

  /** The room manager used to open room scenes. */
  private RoomManager roomManager;

  /** Tracks whether the user is currently allowed to enter a room. */
  private boolean canEnter = true;

  /** Checks collision between the player and room entrance canvases. */
  private CollisionChecker collisionChecker = new CollisionChecker();

  /**
   * Creates a room transitioner using the four room entrance canvases and stage.
   *
   * @param topL the canvas for the top-left room entrance
   * @param topR the canvas for the top-right room entrance
   * @param botL the canvas for the bottom-left room entrance
   * @param botR the canvas for the bottom-right room entrance
   * @param stage the main stage used to display room scenes
   */
  public RoomTransitioner(Canvas topL, Canvas topR, Canvas botL, Canvas botR, Stage stage) {
    this.topL = topL;
    this.topR = topR;
    this.botL = botL;
    this.botR = botR;
    this.roomManager = new RoomManager(stage);
  }

  /**
   * Checks whether the user is colliding with a room entrance and opens that room.
   *
   * @param user the user/player being checked for room entrance collisions
   */
  public void check(User user) {
    if (!canEnter) return;

    if (isColliding(user, topL)) {
      enter("office");
    } else if (isColliding(user, topR)) {
      enter("library");
    } else if (isColliding(user, botL)) {
      enter("classroom");
    } else if (isColliding(user, botR)) {
      enter("cafeteria");
    }
  }

  /**
   * Checks whether the user is colliding with the given entrance canvas.
   *
   * @param user the user/player being checked
   * @param box the room entrance canvas being checked
   * @return true if the user is colliding with the canvas, false otherwise
   */
  private boolean isColliding(User user, Canvas box) {
    return collisionChecker.isColliding(
        user.getPlayer().getCanvas(),
        box,
        user.getPlayer().getPosition().getX(),
        user.getPlayer().getPosition().getY());
  }

  /**
   * Opens the room matching the given room name.
   *
   * @param roomName the name of the room to enter
   */
  private void enter(String roomName) {
    canEnter = false;

    switch (roomName) {
      case "office" -> roomManager.enterOffice();
      case "library" -> roomManager.enterLibrary();
      case "classroom" -> roomManager.enterClassroom();
      case "cafeteria" -> roomManager.enterCafeteria();
    }
  }

  /** Allows the user to enter another room again. */
  public void reset() {
    canEnter = true;
  }
}
