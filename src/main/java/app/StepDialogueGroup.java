package app;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Groups together a sequence of {@link DialogueUI} screens that make up the multiple steps of a
 * single task.
 *
 * <p>Each time the player clicks the task's interaction area, this class shows the DialogueUI that
 * matches the task's current step Selecting any
 * option in that step's dialogue advances the task by calling {@link
 * HelloWorld#handleChoice(int, String, Stage)}, which calls {@link Tasks#completeTask(String)} to
 * move the task to its next step (or mark it fully done if it was the last step).
 */
public class StepDialogueGroup {

  private final String taskId;
  private final List<DialogueUI> steps = new ArrayList<>();

  /**
   * Creates a step dialogue group for a single task.
   *
   * @param taskId the id of the task these steps belong to (must match the id used in {@link
   *     Tasks})
   * @param stage the main stage, passed through to {@link HelloWorld#handleChoice}
   * @param userStepOptions the player dialogue options for each step, in order
   * @param npcStepOptions the NPC responses for each step, in the same order as userStepOptions
   */
  public StepDialogueGroup(String taskId, Stage stage, List<List<String>> userStepOptions,List<List<String>> npcStepOptions) {
    this.taskId = taskId;

    for (int i = 0; i < userStepOptions.size(); i++) {
      DialogueUI ui = new DialogueUI(userStepOptions.get(i), npcStepOptions.get(i));

      ui.setOption1Action(() -> HelloWorld.handleChoice(1, taskId, stage));
      ui.setOption2Action(() -> HelloWorld.handleChoice(2, taskId, stage));
      ui.setOption3Action(() -> HelloWorld.handleChoice(3, taskId, stage));

      steps.add(ui);
    }
  }

  /**
   * Shows the DialogueUI for whichever step the task is currently on. Does nothing if the task is
   * not active (not yet started, or already fully completed).
   */
  public void handleClick() {
    if (!Tasks.isTaskActive(taskId)) {
      return;
    }

    int step = Tasks.getCurrentStep(taskId);

    if (step >= 0 && step < steps.size()) {
      steps.get(step).showOptions();
    }
  }

  /**
   * Adds every step's dialogue UI to the given root so they are ready to be shown.
   *
   * @param root the AnchorPane that receives the dialogue UI elements
   */
  public void addToRoot(AnchorPane root) {
    for (DialogueUI ui : steps) {
      ui.addToRoot(root);
    }
  }
}