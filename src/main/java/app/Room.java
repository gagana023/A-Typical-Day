package app;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a room scene in the game.
 *
 * <p>Each room class must build and return its own root layout so it can be displayed as a scene in
 * the main game window.
 */
public abstract class Room {

  /**
   * Builds and returns the root pane for this room.
   *
   * @param stage the main stage used to display the room
   * @return the root AnchorPane containing the room's visual elements and interactions
   */
  public abstract AnchorPane getRoot(Stage stage);
}
