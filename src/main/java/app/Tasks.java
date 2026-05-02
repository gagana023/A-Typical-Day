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
    allTasks.add(new Task("office_extension", "Go to Office and ask for an extension"));
    allTasks.add(new Task("classroom_homework", "Go to Classroom and turn in your late homework"));
    allTasks.add(new Task("library_study", "Go to Library and join a study group"));
    allTasks.add(new Task("classroom_problem", "Go to Classroom and solve the board problem"));
    allTasks.add(new Task("cafeteria_order", "Go to Cafeteria and order based on diet restrictions"));
    allTasks.add(new Task("library_book", "Go to Library and find a book"));
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

      if (conflictsWithActiveTasks(task)) {
        continue;
      }

      activeTasks.add(task);
    }
  }

  /**
   * Checks whether a new task conflicts with any currently active task.
   *
   * @param newTask the task being checked
   * @return true if the task conflicts with an active task, false otherwise
   */
  private static boolean conflictsWithActiveTasks(Task newTask) {
    for (Task activeTask : activeTasks) {
      if (tasksConflict(newTask.getId(), activeTask.getId())) {
        return true;
      }
    }

    return false;
  }

  /**
   * Checks whether two task ids refer to conflicting tasks.
   *
   * @param taskA the id of the first task
   * @param taskB the id of the second task
   * @return true if the two tasks conflict, false otherwise
   */
  private static boolean tasksConflict(String taskA, String taskB) {
    return samePair(taskA, taskB, "library_book", "library_study")
        || samePair(taskA, taskB, "classroom_homework", "classroom_problem");
  }

  /**
   * Checks whether two task ids match the same pair of task ids in either order.
   *
   * @param taskA the id of the first task being compared
   * @param taskB the id of the second task being compared
   * @param first the first id in the conflicting pair
   * @param second the second id in the conflicting pair
   * @return true if taskA and taskB match the pair in either order, false otherwise
   */
  private static boolean samePair(String taskA, String taskB, String first, String second) {
    return (taskA.equals(first) && taskB.equals(second))
        || (taskA.equals(second) && taskB.equals(first));
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
   * Marks the active task with the given id as completed.
   *
   * @param id the id of the task to complete
   */
  public static void completeTask(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id)) {
        task.complete();
      }
    }
  }

  /**
   * Checks whether a task with the given id is currently active.
   *
   * @param id the id of the task to check
   * @return true if the task is active, false otherwise
   */
  public static boolean isTaskActive(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }
}
