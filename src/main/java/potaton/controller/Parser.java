package potaton.controller;

import potaton.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    /**
     * Creates an array list of task. Attempts to
     * parse content from save file and start
     * taking in user input.
     */
    public static void parseUserCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        Storage.loadFileContent(tasks);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals(UI.EXIT)) {
            Command.executeCommand(tasks, line.trim());
            line = in.nextLine();
        }
        Storage.saveContentToFile(tasks);
    }

}
