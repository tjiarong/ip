package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String TASK_LIST = "list";
    public static final String TASK_TODO = "todo";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_MARK = "mark";
    public static final String TASK_UNMARK = "unmark";
    public static final String TASK_DELETE = "delete";
    public static final String EXIT = "bye";

    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        // Extract first part of command for action
        String command = line.split(" ")[0];
        while (!command.equals(EXIT)) {
            switch (command) {
            case TASK_LIST:
                printList(tasks);
                break;
            case TASK_UNMARK:
                unmarkTask(line, tasks);
                break;
            case TASK_MARK:
                markTask(line, tasks);
                break;
            case TASK_TODO:
                try {
                    addToDo(line, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Refactor this later
                    System.out.println("_____________________________________________\n"
                            + "Error: The description of a todo cannot be empty." + "\n"
                            + "_____________________________________________\n"
                    );
                }
                break;
            case TASK_DEADLINE:
                addDeadline(line, tasks);
                break;
            case TASK_EVENT:
                addEvent(line, tasks);
                break;
            case TASK_DELETE:
                deleteTask(line, tasks);
                break;
            default:
                printError();
                break;
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
        saveContentToFile(tasks);
        printBye();
    }

    private static void printWelcomeMessage() {
        String logo = " ______   _____   ________  _______ __________ _______ \n"
                    + "( ____ )(  ___  )\\__   __/(  ___  )\\__   __/(  ___  )( (    /|\n"
                    + "| (    )|| (   ) |   ) (   | (   ) |   ) (   | (   ) ||  \\  ( |\\\n"
                    + "| (____)|| |   | |   | |   | (___) |   | |   | |   | ||   \\ | |\n"
                    + "|  _____)| |   | |   | |   |  ___  |   | |   | |   | || (\\ \\) | \n"
                    + "| (      | |   | |   | |   | (   ) |   | |   | |   | || | \\   | \n"
                    + "| )      | (___) |   | |   | )   ( |   | |   | (___) || )  \\  | \n"
                    + "|/       (_______)   )_(   |/     \\|   )_(   (_______)|/    )_) \n";

        System.out.println("_____________________________________________\n"
                        + logo + "\n"
                        + "Hello! I'm Potaton\n"
                        + "What can I do for you?\n"
                        + "_____________________________________________\n"
                );
    }

    private static void addEvent(String line, ArrayList<Task> tasks) {
        String arg = line.split(" ", 2)[1];
        String event = arg.split("/at ", 2)[0];
        String eventDate = arg.split("/at ", 2)[1];
        Task t = new Events(event, eventDate);
        tasks.add(t);
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                +  t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        String arg = line.split(" ", 2)[1];
        String task = arg.split("/by ", 2)[0];
        String dueDate = arg.split("/by ", 2)[1];
        Task t = new Deadline(task, dueDate);
        tasks.add(t);
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                +  t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void addToDo(String line, ArrayList<Task> tasks) throws IndexOutOfBoundsException{
        String arg = line.split(" ", 2)[1];
        Task t = new ToDos(arg);
        tasks.add(t);
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void printList(ArrayList<Task> tasks) {
        System.out.println("_____________________________________________\n"
                + "   Here are the tasks in your list:\n");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("_____________________________________________\n");
    }

    private static void markTask(String line, ArrayList<Task> tasks) {
        String arg = line.split(" ")[1];
        int taskNum = Integer.parseInt(arg);
        if (taskNum <= tasks.size()) {
            tasks.get(taskNum - 1).markAsDone();
            System.out.println("_____________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + tasks.get(taskNum - 1) + "\n"
                    + "_____________________________________________\n"
            );
        } else {
            System.out.println("Invalid option");
        }
    }

    private static void unmarkTask(String line, ArrayList<Task> tasks) {
        String arg = line.split(" ")[1];
        int taskNum = Integer.parseInt(arg);
        tasks.get(taskNum - 1).markAsNotDone();
        if (taskNum <= tasks.size()) {
            System.out.println("_____________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + tasks.get(taskNum - 1) + "\n"
                    + "_____________________________________________\n"
            );
        } else {
            System.out.println("Invalid option");
        }
    }

    private static void deleteTask(String line, ArrayList<Task> tasks) {
        String arg = line.split(" ")[1];
        int taskNum = Integer.parseInt(arg);
        if (taskNum <= tasks.size()) {
            System.out.println("_____________________________________________\n"
                    + "Noted. I've removed this task:\n"
                    + tasks.get(taskNum - 1) + "\n"
                    + "Now you have " + (tasks.size() - 1) + " tasks in the list.\n"
                    + "_____________________________________________\n"
            );
            tasks.remove(taskNum - 1);
        } else {
            System.out.println("Invalid option");
        }
    }

    private static void saveContentToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        tasks.remove(0);
        for (Task task : tasks) {
            fw.write(String.valueOf(task));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private static void printError() {
        System.out.println("_____________________________________________\n"
                + "Error: Invalid command. Try again you nerd." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void printBye() {
        System.out.println("_____________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_____________________________________________\n"
        );
    }
}







