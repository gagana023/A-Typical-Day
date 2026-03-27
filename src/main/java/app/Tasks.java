package app;

public class Tasks {
    public static boolean officeTaskDone = false;
    public static boolean classroomTaskDone = false;

    public static boolean completeOfficeTask() {
        officeTaskDone = true;
        return officeTaskDone;
    }

    public static boolean completeClassroomTask() {
        classroomTaskDone = true;
        return classroomTaskDone;
    }
}
