package model;

import exceptions.GradeException;

import static util.ConsoleColors.*;

public enum Grade {
    NOT_SET,
    EXCELLENT,
    GOOD,
    AVERAGE,
    BELOW_AVERAGE,
    FAIL;

    public void setGrade(Task task, String gradeName) throws GradeException {
        if (task.getStatus() != Status.DONE) {
            throw new GradeException(ANSI_RED + "an attempt to set a task grade when the task status is not 'done'" + ANSI_RESET);
        }
        if (task.getGrade() != Grade.NOT_SET) {
            throw new GradeException(ANSI_RED + "an attempt to rate the task for the second time" + ANSI_RESET);
        }
        System.out.println(ANSI_GREEN_BACKGROUND + "Успешно изменили оценку на " + gradeName + ANSI_RESET);
    }
}
