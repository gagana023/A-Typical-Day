package app;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Represents a room scene in the game. */
public abstract class Room {

  /**
   * Builds and returns the root pane for this room.
   *
   * @param stage the main game stage
   * @return the root pane for the room
   */
  public abstract AnchorPane getRoot(Stage stage);
}
