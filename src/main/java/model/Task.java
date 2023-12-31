package model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", completionDate=" + completionDate +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
