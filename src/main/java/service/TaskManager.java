package service;

import model.Priority;
import model.Task;
import util.JsonReader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private final Scanner scanner;
    private final File file;
    private static final String path = "data/tasks1.json";

    public TaskManager() {
        scanner = new Scanner(System.in);
        file = new File(path);
        initTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printAllTasks() {
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
        this.tasks.forEach(System.out::println);
    }

    public void addTask() {
        System.out.println("task added");
        tasks.add(new Task(
                "shopping",
                "visit the mall",
                LocalDateTime.of(2023, 12, 28, 20, 0),
                LocalDateTime.of(2023, 12, 28, 21, 0),
                Priority.MEDIUM)
        );
    }

    public void saveTasksListToJson(){
        JsonReader.writeFile(tasks, path);
    }

    public void close(){
        scanner.close();
    }

    private void createJsonFile(){
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("failed to create new file" + e.getMessage());
            return;
        }
        System.out.println("file created");
    }

    private void initTasks() {
        String answer;
        if (file.exists()) {
            tasks = JsonReader.loadJsonArray(path);
            if (tasks == null ) {
                tasks = new ArrayList<>();
                System.out.println("task list is empty");
                System.out.print("create new task? y/n ");
                answer = scanner.nextLine();
                if(answer.equals("y")){
                    addTask();
                }
            }
        } else {
            System.out.println("file doesn't exist");
            System.out.print("create new file? y/n ");
            answer = scanner.nextLine();
            if(answer.equals("y")){
                createJsonFile();
            }
        }
    }

}
