package potaton.controller;

import potaton.task.Task;
import potaton.task.ToDos;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Check for existence of save file.
     * If not found, create one.
     * @param f
     */
    public static void checkFileExistence(File f) {
        try {
            boolean fileExist = f.createNewFile();
            if (fileExist) {
                UI.printText(UI.FILE_MISSING_MSG);
                UI.printText(UI.BORDER);
            } else {
                UI.printText(UI.FILE_FOUND_MSG);
                UI.printText(UI.BORDER);
            }
        } catch (IOException e) {
            UI.printText(UI.FAIl_FILE_CREATE);
        }
    }

    /**
     * Loads and parse line into an Event object
     * and add it to the task list
     * @param line
     * @param tasks
     */
    public static void loadEvent(String line, ArrayList<Task> tasks) {
        String[] arg = line.split("] ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            String event = arg[1].split(" \\(at: ", 2)[0];
            String parsedDate = arg[1].split(" \\(at: ", 2)[1].split("\\)")[0];
            LocalDate eventDate = LocalDate.parse(parsedDate, formatter);
            TaskList.addEventTask(event, eventDate, tasks, checkIfDone(arg[0]));
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_TODO_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1].split(" \\(at: ", 2)[0];
            LocalDate eventDate = LocalDate.now().plusMonths(1);
            TaskList.addEventTask(event, eventDate, tasks, checkIfDone(arg[0]));
        }
    }

    /**
     * Loads and parse line into a Deadline object
     * and add it to the task list
     * @param line
     * @param tasks
     */
    public static void loadDeadline(String line, ArrayList<Task> tasks) {
        String[] arg = line.split("] ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            String event = arg[1].split(" \\(by: ", 2)[0];
            String parsedDate = arg[1].split(" \\(by: ", 2)[1].split("\\)")[0];
            LocalDate dueDate = LocalDate.parse(parsedDate, formatter);
            TaskList.addDeadlineTask(event, dueDate, tasks, checkIfDone(arg[0]));
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_TODO_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1].split(" \\(by: ", 2)[0];
            LocalDate dueDate = LocalDate.now().plusMonths(1);
            TaskList.addDeadlineTask(event, dueDate, tasks, checkIfDone(arg[0]));
        }
    }

    /**
     * Loads and parse line into a ToDos object
     * and add it to the task list
     * @param line
     * @param tasks
     */
    public static void loadToDo(String line, ArrayList<Task> tasks) {
        String[] arg = line.split("] ", 2);
        try {
            Task t = new ToDos(arg[1]);
            boolean isDone = checkIfDone(arg[0]);
            if (isDone) {
                t.markAsDone();
            }
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
     * Check if given task is done by checking
     * for presence of 'X' char in string.
     * Returns true if found.
     * @param arg
     * @return
     */
    private static boolean checkIfDone(String arg) {
        if (arg.contains("X")){
            return true;
        }
        return false;
    }

    /**
     * Check for existence of save file and attempts
     * to parse content from it if available. The
     * parsed content is saved into the tasklist.
     * If no save file found, a new save file is created.
     * @param tasks
     */
    public static void loadFileContent(ArrayList<Task> tasks) {
        File f = new File(UI.INIT_FILE_PATH);
        Storage.checkFileExistence(f);
        parseFileLine(tasks, f);
    }

    /**
     * For each line in the save file, we attempt to
     * parse it into one of the 3 task type object:
     * Deadline, Event and Todo.
     * @param tasks
     * @param f
     */
    public static void parseFileLine(ArrayList<Task> tasks, File f) {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String taskType = Character.toString(line.charAt(1));
                switch (taskType) {
                case UI.LOAD_TODO:
                    Storage.loadToDo(line, tasks);
                    break;
                case UI.LOAD_DEADLINE:
                    Storage.loadDeadline(line, tasks);
                    break;
                case UI.LOAD_EVENT:
                    Storage.loadEvent(line, tasks);
                    break;
                default:
                    UI.printText(UI.UNKNOWN_TASK);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            UI.printText(UI.FILE_MISSING_MSG);
        }
    }

    /**
     * Saves task list contents into a save file.
     * @param tasks
     */
    public static void saveContentToFile(ArrayList<Task> tasks){
        try {
            FileWriter fw = new FileWriter(UI.INIT_FILE_PATH);
            for (Task task : tasks) {
                fw.write(String.valueOf(task));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            UI.printText(UI.FAIl_FILE_WRITE);
        }
    }
}
