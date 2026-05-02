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

  /** Opens the office scene if the player is not already entering it. */
  public void enterOffice(User user) {
    if (enteringOffice) {
      return;
    }

    enteringOffice = true;
    user.stop();

    Office office = new Office();
    Scene officeScene = new Scene(office.getRoot(stage), 800, 600);
    stage.setScene(officeScene);
  }

  /** Opens the classroom scene if the player is not already entering it. */
  public void enterClassroom(User user) {
    if (enteringClassroom) {
      return;
    }

    enteringClassroom = true;
    user.stop();

    Classroom classroom = new Classroom();
    Scene classroomScene = new Scene(classroom.getRoot(stage), 800, 600);
    stage.setScene(classroomScene);
  }

  /** Opens the library scene if the player is not already entering it. */
  public void enterLibrary(User user) {
    if (enteringLibrary) {
      return;
    }

    enteringLibrary = true;
    user.stop();

    Library library = new Library();
    stage.setScene(library.buildPrototypeLibrary(stage));
  }

  /** Opens the cafeteria scene if the player is not already entering it. */
  public void enterCafeteria(User user) {
    if (enteringCafeteria) {
      return;
    }

    enteringCafeteria = true;
    user.stop();

    Cafeteria cafeteria = new Cafeteria();
    stage.setScene(cafeteria.buildPrototypeCafeteria(stage));
  }
}
