package potaton.controller;

import potaton.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void parseUserCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        parseFileContent(tasks);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals(UI.EXIT)) {
            Command.executeCommand(tasks, line.trim());
            line = in.nextLine();
        }
        Storage.saveContentToFile(tasks);
    }

    public static void parseFileContent(ArrayList<Task> tasks) {
        File f = new File(UI.INIT_FILE_PATH);
        Storage.checkFileExistence(f);
        parseFileLine(tasks, f);
    }

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
}
