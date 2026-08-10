package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores, selects, tracks, and completes game tasks.
 *
 * <p>Each task is now made up of one or more ordered steps. Completing a step advances the task to
 * the next step (updating the description shown in the task bar); completing the final step marks
 * the whole task as done.
 */
public class Tasks {
  /**
   * Represents one multi-step task that the player can complete.
   *
   * <p>Each task has an id, an ordered list of step descriptions, a pointer to the current step, and
   * a completion status.
   */
  public static class Task {
    /** The unique id used to identify the task. */
    private String id;

    /** The ordered list of step descriptions for this task. */
    private List<String> stepDescriptions;

    /** The index of the step the player is currently on. */
    private int currentStep;

    /** Tracks whether this task has been fully completed. */
    private boolean done;

    /**
     * Creates a task with the given id and ordered step descriptions.
     *
     * @param id the unique id for the task
     * @param stepDescriptions the ordered descriptions shown to the player, one per step
     */
    public Task(String id, List<String> stepDescriptions) {
      this.id = id;
      this.stepDescriptions = new ArrayList<>(stepDescriptions);
      this.currentStep = 0;
      this.done = false;
    }

    /**
     * Creates a task with the given id and ordered step descriptions.
     *
     * @param id the unique id for the task
     * @param steps the ordered descriptions shown to the player, one per step
     */
    public Task(String id, String... steps) {
      this(id, List.of(steps));
    }

    /**
     * Returns the task's unique id.
     *
     * @return the task id
     */
    public String getId() {
      return id;
    }

    /**
     * Returns the description of whichever step the player is currently on.
     *
     * @return the current step's description
     */
    public String getDescription() {
      return stepDescriptions.get(currentStep);
    }

    /**
     * Returns every step description for this task, in order.
     *
     * @return the ordered list of step descriptions
     */
    public List<String> getAllStepDescriptions() {
      return stepDescriptions;
    }

    /**
     * Returns the index of the step the player is currently on.
     *
     * @return the current step index, starting at 0
     */
    public int getCurrentStep() {
      return currentStep;
    }

    /**
     * Returns how many steps this task has.
     *
     * @return the total number of steps
     */
    public int getStepCount() {
      return stepDescriptions.size();
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return true if every step is completed, false otherwise
     */
    public boolean isDone() {
      return done;
    }

    /**
     * Advances the task to its next step. If the current step was the final step, the task is
     * marked done instead.
     */
    public void advance() {
      if (done) {
        return;
      }

      if (currentStep < stepDescriptions.size() - 1) {
        currentStep++;
      } else {
        done = true;
      }
    }
  }

  /** Stores every possible task that can appear in the game. */
  private static List<Task> allTasks = new ArrayList<>();

  /** Stores the tasks currently assigned to the player. */
  private static List<Task> activeTasks = new ArrayList<>();

  /** Adds all possible tasks (each with their steps) to the task list when the class loads. */
  static {
    allTasks.add(
        new Task(
            "office_extension",
            "Go to Office and ask to see counselor",
            "Explain why you need an extension",
            "Thank the counselor before you leave"));

    allTasks.add(
        new Task(
            "classroom_homework",
            "Go to Classroom and turn in your late homework",
            "Explain why it's late",
            "Ask if there's a penalty"));

    allTasks.add(
        new Task(
            "library_study",
            "Go to Library and join a study group",
            "Pull up a chair and sit with the group",
            "Ask the group a question about the material"));

    allTasks.add(
        new Task(
            "classroom_problem",
            "Go to Classroom and solve the board problem",
            "Tell the teacher if you can solve it",
            "Try the first step with the teacher's help"));

    allTasks.add(
        new Task(
            "cafeteria_order",
            "Go to Cafeteria and order based on diet restrictions",
            "Choose a meal that fits your diet",
            "Pay and say thank you"));

    allTasks.add(
        new Task(
            "library_book",
            "Go to Library and checkout a book",
            "Hand over your library card",
            "Confirm the due date before leaving"));

    allTasks.add(
        new Task(
            "cafeteria_join_table",
            "Go to Cafeteria and ask to join a table",
            "Sit down with the group",
            "Introduce yourself to the table"));

    allTasks.add(
        new Task(
            "office_form",
            "Go to Office and pick up a counselor form",
            "Fill out the form",
            "Return the completed form"));

    allTasks.add(
        new Task(
            "office_schedule",
            "Go to Office and ask about your schedule",
            "Point out what looks wrong",
            "Thank them for checking it"));

    allTasks.add(
        new Task(
            "library_return",
            "Go to Library and return a book",
            "Place it in the return area",
            "Confirm it was received"));
  }

  /**
   * Randomly chooses a given number of active tasks for the player.
   *
   * <p>This method clears the current active tasks, copies and shuffles all possible tasks (steps
   * included), and then adds tasks up to the requested amount.
   *
   * @param amount the number of active tasks to choose
   */
  public static void chooseRandomTasks(int amount) {
    activeTasks.clear();

    List<Task> shuffledTasks = new ArrayList<>();

    for (Task task : allTasks) {
      shuffledTasks.add(new Task(task.getId(), task.getAllStepDescriptions()));
    }

    Collections.shuffle(shuffledTasks);

    for (Task task : shuffledTasks) {
      if (activeTasks.size() >= amount) {
        break;
      }

      activeTasks.add(task);
    }
  }

  /**
   * Returns the list of currently active tasks.
   *
   * @return the active task list
   */
  public static List<Task> getActiveTasks() {
    return activeTasks;
  }

  /**
   * Checks whether a task with the given id is currently active (started, and not yet fully
   * completed).
   *
   * @param id the id of the task to check
   * @return true if the task is active, false otherwise
   */
  public static boolean isTaskActive(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id) && !task.isDone()) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns which step the given task is currently on.
   *
   * @param id the id of the task to check
   * @return the current step index, or 0 if the task isn't active
   */
  public static int getCurrentStep(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id)) {
        return task.getCurrentStep();
      }
    }

    return 0;
  }

  /**
   * Checks whether every active task has been fully completed.
   *
   * @return true if all active tasks are done, false otherwise
   */
  public static boolean areAllActiveTasksDone() {
    for (Task task : activeTasks) {
      if (!task.isDone()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Advances the task with the given id by one step.
   *
   * <p>This preserves the original method contract used throughout the codebase: it returns true
   * only when this was the task's final step and the task is now fully complete (which is exactly
   * when {@code HelloWorld.handleChoice} should show the "Task completed!" notification). It
   * returns false if the task simply moved to its next step, or if no matching active task was
   * found.
   *
   * @param id the id of the task to advance
   * @return true if the task is now fully completed, false otherwise
   */
  public static boolean completeTask(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id) && !task.isDone()) {
        task.advance();
        return task.isDone();
      }
    }

    return false;
  }
}