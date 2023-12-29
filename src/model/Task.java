package model;

import java.time.LocalDateTime;

public class Task {
    private String title; //название
    private String description; // описание
    private LocalDateTime completionDate; //дата завершения
    private LocalDateTime createdDate; //дата создания

    private Priority priority;
    private Status status;

}
