package app;

import javafx.scene.Scene;
import javafx.stage.Stage;

/** Handles switching from the hallway into each room scene. */
public class RoomManager {

  private Stage stage;

  private boolean enteringOffice = false;
  private boolean enteringCafeteria = false;
  private boolean enteringClassroom = false;
  private boolean enteringLibrary = false;

  /** Creates a room manager for the given stage. */
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

  public void enterOffice(User user) {
    if (enteringOffice) {
      return;
    }

    enteringOffice = true;
    user.stop();
    openRoom(new Office());
  }

  public void enterClassroom(User user) {
    if (enteringClassroom) {
      return;
    }

    enteringClassroom = true;
    user.stop();
    openRoom(new Classroom());
  }

  public void enterLibrary(User user) {
    if (enteringLibrary) {
      return;
    }

    enteringLibrary = true;
    user.stop();
    openRoom(new Library());
  }

  public void enterCafeteria(User user) {
    if (enteringCafeteria) {
      return;
    }

    enteringCafeteria = true;
    user.stop();
    openRoom(new Cafeteria());
  }

  private void openRoom(Room room) {
    Scene roomScene = new Scene(room.getRoot(stage), 800, 600);
    stage.setScene(roomScene);
  }
}
