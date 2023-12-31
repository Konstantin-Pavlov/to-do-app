import exceptions.TaskException;
import model.Priority;
import model.Status;
import model.Task;
import util.JsonReader;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Task[] tasks = JsonReader.loadJsonArray("data/tasks.json", Task[].class);

        assert tasks != null;
        Arrays.stream(tasks).forEach(System.out::println);


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