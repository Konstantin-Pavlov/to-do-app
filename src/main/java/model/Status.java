package model;

import exceptions.TaskException;

import static util.ConsoleColors.*;

public enum Status {
    NEW {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на New, статус уже установлен на New" + ANSI_RESET);
        }

        @Override
        public void deleteTask() {
            System.out.println(ANSI_GREEN_BACKGROUND + "Задача успешно удалена" + ANSI_RESET);
        }

        @Override
        public void changeToInProgress(Task task) {
            task.setStatus(Status.IN_PROGRESS);
            System.out.println(ANSI_GREEN_BACKGROUND + "Успешно изменили статус на in_progress" + ANSI_RESET);
        }

        @Override
        public void changeToDone(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на done" + ANSI_RESET);
        }

        @Override
        public void changeDescription(Task task) {
            System.out.println(ANSI_GREEN_BACKGROUND + "Описание задачи успешно изменено" + ANSI_RESET);
        }
    },
    IN_PROGRESS {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на New, статус уже установлен на New" + ANSI_RESET);

        }

        @Override
        public void deleteTask() throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя удалить задачу когда она в состоянии in-progress" + ANSI_RESET);

        }

        @Override
        public void changeToInProgress(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на in_progress" + ANSI_RESET);

        }

        @Override
        public void changeToDone(Task task) {
            task.setStatus(Status.DONE);
            System.out.println(ANSI_GREEN_BACKGROUND + "Успешно изменили статус на Done" + ANSI_RESET);

        }

        @Override
        public void changeDescription(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя менять описание задачи" + ANSI_RESET);

        }
    },
    DONE {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "изменить статус на Done" + ANSI_RESET);

        }

        @Override
        public void deleteTask() throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя удалить задачу когда она в состоянии Done" + ANSI_RESET);

        }

        @Override
        public void changeToInProgress(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на in_progress" + ANSI_RESET);

        }

        @Override
        public void changeToDone(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменить статус на done" + ANSI_RESET);

        }

        @Override
        public void changeDescription(Task task) throws TaskException {
            throw new TaskException(ANSI_RED + "Нельзя изменять описание задачи" + ANSI_RESET);

        }
    };

    public abstract void changeToNew(Task task) throws TaskException;

    public abstract void deleteTask() throws TaskException;

    public abstract void changeToInProgress(Task task) throws TaskException;

    public abstract void changeToDone(Task task) throws TaskException;

    public abstract void changeDescription(Task task) throws TaskException;

}
