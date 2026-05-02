package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tasks {

  public static class Task {
    private String id;
    private String description;
    private boolean done;

    public Task(String id, String description) {
      this.id = id;
      this.description = description;
      this.done = false;
    }

    public String getId() {
      return id;
    }

    public String getDescription() {
      return description;
    }

    public boolean isDone() {
      return done;
    }

    public void complete() {
      done = true;
    }
  }

  private static List<Task> allTasks = new ArrayList<>();
  private static List<Task> activeTasks = new ArrayList<>();

  static {
    allTasks.add(new Task("office_extension", "Go to Office and ask for an extension"));
    allTasks.add(new Task("classroom_homework", "Go to Classroom and turn in your late homework"));
    allTasks.add(new Task("library_study", "Go to Library and join a study group"));
    allTasks.add(new Task("classroom_problem", "Go to Classroom and solve the board problem"));
    allTasks.add(new Task("cafeteria_friend", "Go to Cafeteria and talk to a student"));
    allTasks.add(new Task("library_book", "Go to Library and find a book"));
  }

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

  private static boolean conflictsWithActiveTasks(Task newTask) {
    for (Task activeTask : activeTasks) {
      if (tasksConflict(newTask.getId(), activeTask.getId())) {
        return true;
      }
    }

    return false;
  }

  private static boolean tasksConflict(String taskA, String taskB) {
    return samePair(taskA, taskB, "library_book", "library_study")
        || samePair(taskA, taskB, "classroom_homework", "classroom_problem");
  }

  private static boolean samePair(String taskA, String taskB, String first, String second) {
    return (taskA.equals(first) && taskB.equals(second))
        || (taskA.equals(second) && taskB.equals(first));
  }

  public static List<Task> getActiveTasks() {
    return activeTasks;
  }

  public static void completeTask(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id)) {
        task.complete();
      }
    }
  }

  public static boolean isTaskActive(String id) {
    for (Task task : activeTasks) {
      if (task.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }
}
