package potaton.controller;

import potaton.exception.PotatonException;
import potaton.task.Deadline;
import potaton.task.Events;
import potaton.task.Task;
import potaton.task.ToDos;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {

    /**
     * Parse user input and attempts to execute as command
     * @param tasks - list of task
     * @param line - user input
     */
    public static void executeCommand(ArrayList<Task> tasks, String line) {
        String command = line.split(" ")[0];
        switch (command) {
        case UI.TASK_LIST:
            printList(tasks);
            break;
        case UI.TASK_UNMARK:
            unmarkTask(line, tasks);
            break;
        case UI.TASK_MARK:
            markTask(line, tasks);
            break;
        case UI.TASK_TODO:
            addToDo(line, tasks);
            break;
        case UI.TASK_DEADLINE:
            addDeadline(line, tasks);
            break;
        case UI.TASK_EVENT:
            addEvent(line, tasks);
            break;
        case UI.TASK_DELETE:
            deleteTask(line, tasks);
            break;
        case UI.TASK_FIND:
            findTask(line, tasks);
            break;
        default:
            UI.printText(UI.INVALID_INPUT);
            break;
        }
    }

    /**
     * Print all tasks in task list with index
     * @param tasks - list of task
     */
    private static void printList(ArrayList<Task> tasks) {
        UI.printText(UI.BORDER);
        UI.printText(UI.TASK_LIST_MSG);
        for (int i = 0; i < tasks.size(); i++) {
            UI.printTextSameLine((i + 1) + ". ");
            UI.printText(String.valueOf(tasks.get(i)));
        }
        UI.printText(UI.BORDER);
    }

    /**
     * Parse and add Event to task list
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     * @throws IndexOutOfBoundsException for missing parameters
     */
    private static void addEvent(String line, ArrayList<Task> tasks) {
        String[] arg = line.split(" ", 3);
        try {
            String event = arg[1];
            String parsedDate = arg[2].split("/at ", 2)[1];
            LocalDate eventDate = LocalDate.parse(parsedDate);
            addEventTask(event, eventDate, tasks, false);
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_EVENT_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1];
            LocalDate eventDate = LocalDate.now().plusMonths(1);
            addEventTask(event, eventDate, tasks, false);
        }
    }

    /**
     * Creates an Event Task object and add it to task list
     * @param event - Event description
     * @param eventDate - Date of event
     * @param tasks - list of task
     * @param isDone - Indicates whether task is done
     */
    public static void addEventTask(String event, LocalDate eventDate, ArrayList<Task> tasks, boolean isDone) {
        Task t = new Events(event, eventDate);
        if (isDone) {
            t.markAsDone();
        }
        tasks.add(t);
        UI.printText(UI.BORDER);
        UI.printText(UI.TASK_ADDED_MSG);
        UI.printText(String.valueOf(t));
        UI.printText(UI.BORDER);
    }

    /**
     * Parse and add Deadline to task list
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     * @throws IndexOutOfBoundsException for missing parameters
     */
    private static void addDeadline(String line, ArrayList<Task> tasks) {
        String[] arg = line.split(" ", 3);
        try {
            String event = arg[1];
            String parsedDate = arg[2].split("/by ", 2)[1];
            LocalDate dueDate = LocalDate.parse(parsedDate);
            addDeadlineTask(event, dueDate, tasks, false);
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_DEADLINE_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1];
            LocalDate dueDate = LocalDate.now().plusMonths(1);
            addDeadlineTask(event, dueDate, tasks, false);
        }
    }

    /**
     * Creates a Deadline Task object and add it to task list
     * @param event - Event description
     * @param dueDate - Deadline due date
     * @param tasks - list of task
     * @param isDone - Indicates whether task is done
     */
    public static void addDeadlineTask(String event, LocalDate dueDate, ArrayList<Task> tasks, boolean isDone) {
        Task t = new Deadline(event, dueDate);
        if (isDone) {
            t.markAsDone();
        }
        tasks.add(t);
        UI.printText(UI.BORDER);
        UI.printText(UI.TASK_ADDED_MSG);
        UI.printText(String.valueOf(t));
        UI.printText(UI.BORDER);
    }

    /**
     * Creates an ToDos Task object and add it to task list
     * @param line User's input to be parsed
     * @param tasks list of tasks
     */
    public static void addToDo(String line, ArrayList<Task> tasks) {
        try {
            String arg = line.split(" ", 2)[1];
            Task t = new ToDos(arg);
            tasks.add(t);
            UI.printText(UI.BORDER);
            UI.printText(UI.TASK_ADDED_MSG);
            UI.printText(String.valueOf(t));
            UI.printText(UI.BORDER);
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_TODO_FORMAT);
        }
    }

    /**
     * Search for a task number indicated by user
     * and mark it as done
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     */
    private static void markTask(String line, ArrayList<Task> tasks) {
        try {
            String[] arg = line.split(" ");
            int taskNum = Integer.parseInt(arg[1]);
            if (taskNum >= 1 & taskNum <= tasks.size()) {
                tasks.get(taskNum - 1).markAsDone();
                UI.printText(UI.BORDER);
                UI.printText(UI.TASK_MARKED_MSG);
                UI.printText(String.valueOf(tasks.get(taskNum - 1)));
                UI.printText(UI.BORDER);
            } else {
                throw new NumberFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.MISSING_TASK_DETAILS);
        } catch (NumberFormatException e) {
            UI.printText(UI.INVALID_TASKNUM_MSG);
        }
    }

    /**
     * Search for a task number indicated by user
     * and mark it as not done
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     */
    public static void unmarkTask(String line, ArrayList<Task> tasks) {
        try {
            String[] arg = line.split(" ");
            int taskNum = Integer.parseInt(arg[1]);
            if (taskNum >= 1 & taskNum <= tasks.size()) {
                tasks.get(taskNum - 1).markAsNotDone();
                UI.printText(UI.BORDER);
                UI.printText(UI.TASK_UNMARKED_MSG);
                UI.printText(String.valueOf(tasks.get(taskNum - 1)));
                UI.printText(UI.BORDER);
            } else {
                throw new NumberFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.MISSING_TASK_DETAILS);
        } catch (NumberFormatException e) {
            UI.printText(UI.INVALID_TASKNUM_MSG);
        }
    }

    /**
     * Search for a task number indicated by user
     * and delete it from task list
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     */
    public static void deleteTask(String line, ArrayList<Task> tasks) {
        try {
            String[] arg = line.split(" ");
            int taskNum = Integer.parseInt(arg[1]);
            if (taskNum >= 1 & taskNum <= tasks.size()) {
                UI.printText(UI.BORDER);
                UI.printText(UI.TASK_DELETED_MSG);
                UI.printText(String.valueOf(tasks.get(taskNum - 1)));
                UI.printText(UI.BORDER);
                tasks.remove(taskNum - 1);
            } else {
                throw new NumberFormatException();
            }
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.MISSING_TASK_DETAILS);
        } catch (NumberFormatException e) {
            UI.printText(UI.INVALID_TASKNUM_MSG);
        }
    }

    /**
     * Given a string from the user, parse the string
     * and search through the task list for a match
     * and prints matching task to output
     * @param line - User's input to be parsed
     * @param tasks - list of tasks
     */
    public static void findTask(String line, ArrayList<Task> tasks) {
        try {
            String[] arg = line.split(" ", 2);
            String matchString = arg[1];

            if (matchString.isEmpty()){
                throw new PotatonException();
            }
            UI.printText(UI.BORDER);
            UI.printText(UI.TASK_FIND_MSG);
            findTaskWithString(tasks, matchString);
            UI.printText(UI.BORDER);
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_FIND_FORMAT);
        } catch (PotatonException e) {
            UI.printText(UI.EMPTY_FIND_STRING);
        }
    }

    /**
     * Search through task list given a string
     * @param tasks - list of tasks
     * @param matchString - String to match task with
     */
    private static void findTaskWithString(ArrayList<Task> tasks, String matchString) {
        for (Task task: tasks) {
            if (task.getDescription().contains(matchString)){
                UI.printText(String.valueOf(task));
            }
        }
    }

}
