package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores, selects, tracks, and completes game tasks.
 *
 * <p>This class keeps a list of all possible tasks and randomly chooses active tasks for the player
 * to complete. It also prevents conflicting tasks from being active at the same time.
 */
public class Tasks {
  /**
   * Represents one task that the player can complete.
   *
   * <p>Each task has an id, a description, and a completion status.
   */
  public static class Task {
    /** The unique id used to identify the task. */
    private String id;

    /** The text shown to the player for this task. */
    private String description;

    /** Tracks whether this task has been completed. */
    private boolean done;

    /**
     * Creates a task with the given id and description.
     *
     * @param id the unique id for the task
     * @param description the text shown to the player
     */
    public Task(String id, String description) {
      this.id = id;
      this.description = description;
      this.done = false;
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
     * Returns the task description shown to the player.
     *
     * @return the task description
     */
    public String getDescription() {
      return description;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() {
      return done;
    }

    /** Marks this task as completed. */
    public void complete() {
      done = true;
    }
  }

  /** Stores every possible task that can appear in the game. */
  private static List<Task> allTasks = new ArrayList<>();

  /** Stores the tasks currently assigned to the player. */
  private static List<Task> activeTasks = new ArrayList<>();

  /** Adds all possible tasks to the task list when the class loads. */
  static {
    allTasks.add(new Task("office_extension", "Go to Office and ask to see counselor"));
    allTasks.add(new Task("classroom_homework", "Go to Classroom and turn in your late homework"));
    allTasks.add(new Task("library_study", "Go to Library and join a study group"));
    allTasks.add(new Task("classroom_problem", "Go to Classroom and solve the board problem"));
    allTasks.add(
        new Task("cafeteria_order", "Go to Cafeteria and order based on diet restrictions"));
    allTasks.add(new Task("library_book", "Go to Library and checkout a book"));
    allTasks.add(new Task("cafeteria_join_table", "Go to Cafeteria and ask to join a table"));
    allTasks.add(new Task("office_form", "Go to Office and pick up a counselor form"));
    allTasks.add(new Task("office_schedule", "Go to Office and ask about your schedule"));
    allTasks.add(new Task("library_return", "Go to Library and return a book"));
  }

  /**
   * Randomly chooses a given number of active tasks for the player.
   *
   * <p>This method clears the current active tasks, copies and shuffles all possible tasks, and
   * then adds tasks that do not conflict with already selected tasks.
   *
   * @param amount the number of active tasks to choose
   */
  public static void chooseRandomTasks(int amount) {
    activeTasks.clear();

    List<Task> shuffledTasks = new ArrayList<>();

    for (Task task : allTasks) {
      shuffledTasks.add(new Task(task.getId(), task.getDescription()));
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
   * Checks whether a task with the given id is currently active.
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

  public static boolean areAllActiveTasksDone() {
    for (Task task : activeTasks) {
      if (!task.isDone()) {
        return false;
      }
    }

    return true;
  }

  public static boolean completeTask(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id) && !task.isDone()) {
        task.complete();
        return true;
      }
    }

    return false;
  }
}
