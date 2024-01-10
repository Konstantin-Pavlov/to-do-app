import model.Priority;
import model.Task;
import service.TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        LocalDateTime creationDate = LocalDateTime.parse("2023:12:25 16:00", formatter);

        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.getTasks();

        System.out.println("#".repeat(42) + "search by priority" + "#".repeat(42));
        tasks.stream().filter(task -> task.getPriority() == Priority.MEDIUM).forEach(System.out::println); // by priority
        System.out.println();

        System.out.println("#".repeat(42) + "search by creation date" + "#".repeat(42));
        tasks.stream().filter(task -> task.getCreatedDate().equals(creationDate)).forEach(System.out::println); // by creation date
        System.out.println();

        System.out.println("#".repeat(42) + "search by title key word" + "#".repeat(42));
        tasks.stream().filter(task -> task.getTitle().contains("relax")).forEach(System.out::println); // by title key word
//        taskManager.start();
//        taskManager.close();

//        tasks.forEach(Task::changeStatusToInProgress);
//        tasks.forEach(Task::changeStatusToDone);
//        tasks.forEach(task -> task.changeGrade(Grade.GOOD));
//        tasks.forEach(task -> task.changeGrade(Grade.EXCELLENT));
//        tasks.forEach(System.out::println);


//        Task task = new Task("learning",
//                "study hard!",
//                LocalDateTime.parse("2023:12:25 16:00", formatter),
//                LocalDateTime.parse("2023:12:25 18:00", formatter),
//                LOW
//        );
//        task.changeGrade(Grade.AVERAGE);
//        task.changeStatusToInProgress();
//        task.changeGrade(Grade.FAIL);
//        task.changeStatusToDone();
//        task.changeGrade(Grade.GOOD);
//        task.changeGrade(Grade.EXCELLENT);
//
//        System.out.println(task);
    }
}