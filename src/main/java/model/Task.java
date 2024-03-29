package model;

import exceptions.GradeException;
import exceptions.TaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// todo - make setstatus method private or protected

public class Task {
    private final String title;
    private String description;
    private final LocalDateTime createdDate;
    private final LocalDateTime completionDate;
    private final Priority priority;
    private Status status;
    private Grade grade;

    public Task(String title, String description, LocalDateTime createdDate,
                LocalDateTime completionDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.completionDate = completionDate;
        this.createdDate = createdDate;
        this.priority = priority;
        this.grade = Grade.NOT_SET;
        setStatus(Status.NEW);
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void changeGrade(Grade grade) {
        try {
            this.grade.setGrade(this, grade.name());
            this.grade = grade;
        } catch (GradeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDescription(String newDescription) {
        try {
            status.changeDescription(this);
            setDescription(newDescription);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusToNew() {
        try {
            status.changeToNew(this);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusToInProgress() {
        try {
            status.changeToInProgress(this);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusToDone() {
        try {
            status.changeToDone(this);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteTask() {
        try {
            status.deleteTask();
            return true;
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdDate, task.createdDate) &&
                Objects.equals(completionDate, task.completionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, createdDate, completionDate);
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
        return String.format("%-20s", "title: ") + title + "\n" +
                String.format("%-20s", "description: ") + description + "\n" +
                String.format("%-20s", "created Date: ") + createdDate.format(formatter) + "\n" +
                String.format("%-20s", "completion Date: ") + completionDate.format(formatter) + "\n" +
                String.format("%-20s", "priority: ") + priority + "\n" +
                String.format("%-20s", "status: ") + status + "\n" +
                String.format("%-20s", "grade: ") + grade + "\n";
    }

}
