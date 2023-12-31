
import model.Priority;
import model.Task;
import util.JsonReader;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = JsonReader.loadJsonArray();

        tasks.forEach(System.out::println);

        System.out.println();

        Task task = tasks.get(0);
        task.setPriority(Priority.MEDIUM);
        JsonReader.writeFile(tasks);

        tasks.forEach(System.out::println);




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