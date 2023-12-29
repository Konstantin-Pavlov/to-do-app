package model;

import exceptions.TaskException;

public enum Status {
    NEW {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на New, статус уже установлен на New");
        }

        @Override
        public void deleteTask() {
            System.out.println("Задача успешно удаленаю");
        }

        @Override
        public void changeToInProgress(Task task) {
            task.setStatus(Status.IN_PROGRESS);
            System.out.println("Успешно изменили статус на in_progress");
        }

        @Override
        public void changeToDone(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на done");
        }

        @Override
        public void changeDescription(Task task) {
            task.setDescription("new");//новые описания условия задачи.
            System.out.println(" Описание задачи успешно изменено");
        }
    }, IN_PROGRESS {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на New, статус уже установлен на New");

        }

        @Override
        public void deleteTask() throws TaskException {
            throw new TaskException("Нельзя удалить задачу когда она в состоянии in-progress");

        }

        @Override
        public void changeToInProgress(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на in_progress");

        }

        @Override
        public void changeToDone(Task task) {
            task.setStatus(Status.DONE);
            System.out.println("Успешно изменили статус на Done");

        }

        @Override
        public void changeDescription(Task task) throws TaskException {
            throw new TaskException("Нельзя менять описание задачи");

        }
    }, DONE {
        @Override
        public void changeToNew(Task task) throws TaskException {
            throw new TaskException("изменить статус на Done");

        }

        @Override
        public void deleteTask() throws TaskException {
            throw new TaskException("Нельзя удалить задачу когда она в состоянии Done");

        }

        @Override
        public void changeToInProgress(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на in_progress");

        }

        @Override
        public void changeToDone(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на done");

        }

        @Override
        public void changeDescription(Task task) throws TaskException {
            throw new TaskException("Нельзя изменять описание задачи");

        }
    };

    public abstract void changeToNew(Task task) throws TaskException;

    public abstract void deleteTask() throws TaskException;

    public abstract void changeToInProgress(Task task) throws TaskException;

    public abstract void changeToDone(Task task) throws TaskException;

    public abstract void changeDescription(Task task) throws TaskException;

}
