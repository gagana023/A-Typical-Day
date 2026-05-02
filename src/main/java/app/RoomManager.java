package app;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Handles switching from the hallway into each room scene.
 *
 * <p>This class keeps track of whether the player is already entering a room so the same room does
 * not open multiple times from repeated collision checks or repeated input.
 */
public class RoomManager {
  /** The main stage used to display room scenes. */
  private Stage stage;

  /** Tracks whether the player is currently entering the office. */
  private boolean enteringOffice = false;

  /** Tracks whether the player is currently entering the cafeteria. */
  private boolean enteringCafeteria = false;

  /** Tracks whether the player is currently entering the classroom. */
  private boolean enteringClassroom = false;

  /** Tracks whether the player is currently entering the library. */
  private boolean enteringLibrary = false;

  /**
   * Creates a room manager for the given stage.
   *
   * @param stage the main stage used to display rooms
   */
  public RoomManager(Stage stage) {
    this.stage = stage;
  }

  /** Resets room-entry flags so the player can enter rooms again. */
  public void resetEntries() {
    enteringOffice = false;
    enteringCafeteria = false;
    enteringClassroom = false;
    enteringLibrary = false;
  }

  /** Opens the office scene if the player is not already entering it. */
  public void enterOffice() {
    if (enteringOffice) {
      return;
    }

    enteringOffice = true;
    openRoom(new Office());
  }

  /** Opens the classroom scene if the player is not already entering it. */
  public void enterClassroom() {
    if (enteringClassroom) {
      return;
    }

    enteringClassroom = true;
    openRoom(new Classroom());
  }

  /** Opens the library scene if the player is not already entering it. */
  public void enterLibrary() {
    if (enteringLibrary) {
      return;
    }

    enteringLibrary = true;
    openRoom(new Library());
  }

  /** Opens the cafeteria scene if the player is not already entering it. */
  public void enterCafeteria() {
    if (enteringCafeteria) {
      return;
    }

    enteringCafeteria = true;
    openRoom(new Cafeteria());
  }

  /**
   * Creates a scene for the given room and displays it on the main stage.
   *
   * @param room the room to open
   */
  private void openRoom(Room room) {
    Scene roomScene = new Scene(room.getRoot(stage), 800, 600);
    stage.setScene(roomScene);
  }
}
