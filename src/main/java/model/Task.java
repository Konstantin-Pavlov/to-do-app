package model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title; //название
    private String description; // описание
    @SerializedName("createdDate")
    private LocalDateTime createdDate; //дата создания
    @SerializedName("completionDate")
    private LocalDateTime completionDate; //дата завершения
    private Priority priority;
    private Status status;

    public Task() {
    }

    public Task(String title, String description, LocalDateTime createdDate,
                LocalDateTime completionDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;
        this.createdDate = createdDate;
        this.priority = priority;
        setStatus(Status.NEW);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        return  String.format("%-20s", "title: ")            + title + "\n" +
                String.format("%-20s", "description: ")      + description + "\n" +
                String.format("%-20s", "created Date: ")     + createdDate.format(formatter) + "\n" +
                String.format("%-20s", "completion Date: ")  + completionDate.format(formatter) + "\n" +
                String.format("%-20s", "priority: ")         + priority + "\n" +
                String.format("%-20s", "status: ")           + status + "\n";
    }


}
