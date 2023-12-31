package service;

import model.Task;
import util.JsonReader;

import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        initTasks();
    }

    private void initTasks(){
        tasks = JsonReader.loadJsonArray();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
