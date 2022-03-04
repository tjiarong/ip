package potaton.controller;

import potaton.task.Task;

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
        executeUserCommand(tasks, in, line);
        Storage.saveContentToFile(tasks);
    }

    /**
     * Takes in user input and executes as command
     * until user enters exit command
     * @param tasks - list of tasks
     * @param in - Scanner object
     * @param line - User input to be parsed
     */
    private static void executeUserCommand(ArrayList<Task> tasks, Scanner in, String line) {
        while (!line.equals(UI.EXIT)) {
            TaskList.executeCommand(tasks, line.trim());
            line = in.nextLine();
        }
    }

}
