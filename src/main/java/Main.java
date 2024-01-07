import interactive.UserInteraction;
import model.Priority;
import model.Task;
import service.TaskManager;
import util.JsonReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserInteraction userInteraction = new UserInteraction();
        userInteraction.showFileInDirectory();
//        TaskManager taskManager = new TaskManager();
//        List<Task> tasks = taskManager.getTasks();
//
//
//        taskManager.saveTasksListToJson();
//
//        taskManager.printAllTasks();
//
//        taskManager.close();

//        System.out.println();
//
//        taskManager.addTask();
//
//        taskManager.printAllTasks();


//        Task task = tasks.get(0);
//        task.setPriority(Priority.LOW);
//        JsonReader.writeFile(tasks);
//
//        taskManager.printAllTasks();




//        Task task = new Task("task", "description",
//                LocalDateTime.now(), LocalDateTime.now().plusHours(1), Priority.MEDIUM);
//
//
//        try {
//            task.getStatus().changeToDone(task);
//        } catch (TaskException e) {
//            System.out.println(e.getMessage());
//        }

    }
}