package app;

import javafx.stage.Stage;

/**
 * Provides helper methods for navigating between game scenes.
 *
 * <p>This class is used to return the player from a room or menu back to the main hallway scene.
 */
public class NavigationHelper {
  /**
   * Returns the player to the main hallway scene and refreshes task labels.
   *
   * <p>This method switches the stage back to the main game scene, stops and resumes the user so
   * movement is reset correctly, and updates the task display.
   *
   * @param stage the main stage used to display the main hallway scene
   */
  public static void returnToMainScene(Stage stage) {
    stage.setScene(HelloWorld.scene);
    HelloWorld.user.stop();
    HelloWorld.user.resume(HelloWorld.scene);
    HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3, HelloWorld.task4);
  }
}
