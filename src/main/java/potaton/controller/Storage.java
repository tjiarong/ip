package potaton.controller;

import potaton.task.Task;
import potaton.task.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

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

    public static void loadEvent(String line, ArrayList<Task> tasks) {
        String[] arg = line.split("] ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            String event = arg[1].split(" \\(at: ", 2)[0];
            String parsedDate = arg[1].split(" \\(at: ", 2)[1].split("\\)")[0];
            LocalDate eventDate = LocalDate.parse(parsedDate, formatter);
            Command.addEventTask(event, eventDate, tasks, checkIfDone(arg[0]));
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_TODO_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1].split(" \\(at: ", 2)[0];
            LocalDate eventDate = LocalDate.now().plusMonths(1);
            Command.addEventTask(event, eventDate, tasks, checkIfDone(arg[0]));
        }
    }

    public static void loadDeadline(String line, ArrayList<Task> tasks) {
        String[] arg = line.split("] ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            String event = arg[1].split(" \\(by: ", 2)[0];
            String parsedDate = arg[1].split(" \\(by: ", 2)[1].split("\\)")[0];
            LocalDate dueDate = LocalDate.parse(parsedDate, formatter);
            Command.addDeadlineTask(event, dueDate, tasks, checkIfDone(arg[0]));
        } catch (IndexOutOfBoundsException e) {
            UI.printText(UI.INVALID_TODO_FORMAT);
        } catch (DateTimeParseException e) {
            UI.printText(UI.INVALID_DATE_FORMAT);
            String event = arg[1].split(" \\(by: ", 2)[0];
            LocalDate dueDate = LocalDate.now().plusMonths(1);
            Command.addDeadlineTask(event, dueDate, tasks, checkIfDone(arg[0]));
        }
    }

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

    public static boolean checkIfDone(String arg) {
        if (arg.contains("X")){
            return true;
        }
        return false;
    }

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
