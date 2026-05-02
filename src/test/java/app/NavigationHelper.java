package app;

import javafx.stage.Stage;

public class NavigationHelper {
  /** Returns the player to the main hallway scene and refreshes task labels. */
  public static void returnToMainScene(Stage stage) {
    stage.setScene(HelloWorld.scene);
    HelloWorld.user.stop();
    HelloWorld.user.resume(HelloWorld.scene);
    HelloWorld.updateTasks(HelloWorld.task1, HelloWorld.task2, HelloWorld.task3);
  }
}
