package model;

import exceptions.TaskException;

public enum Status {
    NEW {
        @Override
        void changeToNew(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на New, статус уже установлен на New");
        }

        @Override
        void deleteTask() {
            System.out.println("Задача успешно удаленаю");
        }

        @Override
        void changeToInProgress(Task task) {
            task.setStatus(Status.IN_PROGRESS);
            System.out.println("Успешно изменили статус на in_progress");
        }

        @Override
        void changeToDone(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на done");
        }

        @Override
        void changeDescription(Task task) {
            task.setDescription("new");//новые описания условия задачи.
            System.out.println(" Описание задачи успешно изменено");
        }
    }, IN_PROGRESS {
        @Override
        void changeToNew(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на New, статус уже установлен на New");

        }

        @Override
        void deleteTask() throws TaskException {
            throw new TaskException("Нельзя удалить задачу когда она в состоянии in-progress");

        }

        @Override
        void changeToInProgress(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на in_progress");

        }

        @Override
        void changeToDone(Task task) {
            task.setStatus(Status.DONE);
            System.out.println("Успешно изменили статус на Done");

        }

        @Override
        void changeDescription(Task task) throws TaskException {
            throw new TaskException("Нельзя менять описание задачи");

        }
    }, DONE {
        @Override
        void changeToNew(Task task) throws TaskException {
            throw new TaskException("изменить статус на Done");

        }

        @Override
        void deleteTask() throws TaskException {
            throw new TaskException("Нельзя удалить задачу когда она в состоянии Done");

        }

        @Override
        void changeToInProgress(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на in_progress");

        }

        @Override
        void changeToDone(Task task) throws TaskException {
            throw new TaskException("Нельзя изменить статус на done");

        }

        @Override
        void changeDescription(Task task) throws TaskException {
            throw new TaskException("Нельзя изменять описание задачи");

        }
    };
    abstract void changeToNew(Task task) throws TaskException;
    abstract void deleteTask() throws TaskException;
    abstract void changeToInProgress(Task task) throws TaskException;
    abstract void changeToDone(Task task) throws TaskException;
    abstract void changeDescription(Task task) throws TaskException;

}
