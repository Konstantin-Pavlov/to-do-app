package service;

import model.Priority;
import model.Task;
import util.ConsoleColors;
import util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager {
    private List<Task> tasks;
    private final Scanner scanner;
    private final File file;
    private static final String DEFAULT_PATH = "data/tasks.json";

    public TaskManager() {
        scanner = new Scanner(System.in);
        file = new File(DEFAULT_PATH);
        initTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void start() {
        boolean session = true;
        int choice;
        while (session) {
            System.out.print(
                    """
                            меню задач:
                            0  выход
                            1  отобразить все задачи
                            2  добавить новую задачу
                            3  изменить описание задачи
                            4  пометить как (“новая”, “в работе”, “сделано” )
                            5  посмотреть есть ли у задачи дубликат // реализовать
                            6  найти задачу по описанию // реализовать
                            7  удалить задачу
                            8  сохранить в файл
                            9  сортировать по приоритету и распечатать задачи
                            10 сортировать по дате создания и распечатать задачи
                            11 сортировать по длине описания и распечатать задачи
                            """
            );
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    session = false;
                    System.out.println("вышли из меню задач");
                    break;
                case 1:
                case 7:
                    printAllTasksSortedByPriority();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    System.out.println("изменить описание задачи");
                    changeTaskDescription();
                    break;
                case 4:
                    System.out.println("пометить как (“новая”, “в работе”, “сделано” ");
                    changeTaskStatus();
                    break;
                case 5:
                    System.out.println("удалить задачу");
                    deleteTask();
                    break;
                case 6:
                    saveTasksListToJson();
                    break;
                case 8:
                    printAllTasksSortedByCreationDate();
                    break;
                case 9:
                    printAllTasksSortedByDescriptionLength();
                    break;
                default:
                    System.out.println("нет такого пункта в меню.");
                    break;
            }
        }
    }

    public void close() {
        scanner.close();
    }

    private void printAllTasksSortedByCreationDate() {
        tasks.sort(Comparator.comparing(Task::getCreatedDate).reversed());
        printAllTasks();
    }

    private void printAllTasksSortedByDescriptionLength() {
        tasks.sort(Comparator.comparing((Task task) -> task.getDescription().length()).reversed());
        printAllTasks();
    }

    private void printAllTasksSortedByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
        printAllTasks();
    }

    private void printAllTasks() {
        AtomicInteger id = new AtomicInteger(1);
        this.tasks.forEach(task -> {
            System.out.println("id: " + id.getAndIncrement());
            System.out.println(task);
        });
    }

    private void addTask() {
        scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String title;
        String description;
        String createdDate;
        String completionDate;
        int priorityNumber;

        System.out.println("adding new task:");
        System.out.print("enter task title: ");
        title = scanner.nextLine();

        System.out.print("enter task description: ");
        description = scanner.nextLine();

        System.out.print("enter create task date in format yyyy:MM:dd HH:mm: ");
        createdDate = scanner.nextLine();

        System.out.print("enter completion task date in format yyyy:MM:dd HH:mm: ");
        completionDate = scanner.nextLine();

        System.out.print("choose task priority LOW(1), MEDIUM(2), HIGH(3), enter number: ");
        priorityNumber = scanner.nextInt();

        try {
            tasks.add(
                    new Task(
                            title,
                            description,
                            LocalDateTime.parse(createdDate, formatter),
                            LocalDateTime.parse(completionDate, formatter),
                            Priority.values()[priorityNumber - 1]
                    )
            );
        } catch (DateTimeParseException e) {
            System.out.println("fail to create a task, wrong date format: " + e.getMessage());
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("fail to create a task, wrong priority number: " + e.getMessage());
            return;
        }
        System.out.println("task added");
    }

    private void saveTasksListToJson() {
        try {
            FileUtil.writeFile(tasks, DEFAULT_PATH);
            System.out.println(ConsoleColors.ANSI_GREEN_BACKGROUND + "успешно сохранили в json файл" + ConsoleColors.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ConsoleColors.ANSI_RED +  e.getMessage() + ConsoleColors.ANSI_RESET);
        }
    }

    private void createDefaultJsonFile() {
        try {
            if (file.createNewFile()) {
                System.out.println("file created");
            } else {
                System.out.println("file was not created for some reasons");
            }
        } catch (IOException e) {
            System.out.println("failed to create new file" + e.getMessage());
        }
    }

    private void initTasks() {
        String answer;
        if (file.exists()) {
            tasks = FileUtil.loadJsonArray(DEFAULT_PATH);
            if (tasks == null) {
                tasks = new ArrayList<>();
                System.out.println("task list is empty");
                System.out.print("create new task? y/n ");
                answer = scanner.nextLine();
                if (answer.equals("y")) {
                    addTask();
                }
            }
        } else {
            System.out.println("file doesn't exist");
            System.out.print("create new file? y/n ");
            answer = scanner.nextLine();
            if (answer.equals("y")) {
                createDefaultJsonFile();
            }
        }
    }

    private Task getTaskById() {
        printAllTasksSortedByPriority();
        System.out.print("выберете номер задачи:  ");
        int taskNumber = scanner.nextInt();
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException("нет такой задачи");
        }
        return tasks.get(taskNumber - 1);
    }

    private void changeTaskDescription() { // изменить описание задачи
        Task task;
        try {
            task = getTaskById();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        scanner.nextLine();
        System.out.print("введите новое описание:  ");
        String newDescription = scanner.nextLine();
        task.changeDescription(newDescription);
    }

    private void changeTaskStatus() {
        Task task;
        try {
            task = getTaskById();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("""
                пометить как\s
                1 новая\s
                2 в работе\s
                3 сделано""");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("меняем статус на 'новую'... ");
                task.changeStatusToNew();
                break;
            case 2:
                System.out.print("меняем статус на 'в работе': ");
                task.changeStatusToInProgress();
                break;
            case 3:
                System.out.print("меняем статус на 'сделано' : ");
                task.changeStatusToDone();
                break;
            default:
                System.out.println("нет такого пункта в меню");
                break;

        }
    }

    private void deleteTask() {
        Task task;
        try {
            task = getTaskById();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (task.deleteTask()) {
            tasks.remove(task);
        }
    }

}