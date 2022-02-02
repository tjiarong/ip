import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final int MAX_TASK = 100;
        Task[] tasks = new Task[MAX_TASK];
        tasks[0] = new Task("Init");

        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String command = line.split(" ")[0];
        while (!command.equals("bye")) {
            switch (command) {
            case "list":
                printList(tasks);
                break;
            case "unmark":
                unmarkTask(line, tasks);
                break;
            case "mark":
                markTask(line, tasks);
                break;
            case "todo":
                addToDo(line, tasks);
                break;
            case "deadline":
                addDeadline(line, tasks);
                break;
            case "event":
                addEvent(line, tasks);
                break;
            default:
                addTask(line, tasks);
                break;
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }

        printBye();
    }

    private static void printWelcomeMessage() {
        String logo = "_______  _______ _________ _______ _________ _______  _\n"
                    + "(  ____ )(  ___  )\\__   __/(  ___  )\\__   __/(  ___  )( (    /|\n"
                    + "| (    )|| (   ) |   ) (   | (   ) |   ) (   | (   ) ||  \\  ( |\\\n"
                    + "| (____)|| |   | |   | |   | (___) |   | |   | |   | ||   \\ | |\n"
                    + "|  _____)| |   | |   | |   |  ___  |   | |   | |   | || (\\ \\) | \n"
                    + "| (      | |   | |   | |   | (   ) |   | |   | |   | || | \\   | \n"
                    + "| )      | (___) |   | |   | )   ( |   | |   | (___) || )  \\  | \n"
                    + "|/       (_______)   )_(   |/     \\|   )_(   (_______)|/    )_) \n";

        System.out.println("_____________________________________________\n"
                        + logo + "\n"
                        + "Hello! I'm Duke\n"
                        + "What can I do for you?\n"
                        + "_____________________________________________\n"
                );
    }

    private static void addEvent(String line, Task[] tasks) {
        String arg = line.split(" ", 2)[1];
        String event = arg.split("/at ", 2)[0];
        String eventDate = arg.split("/at ", 2)[1];
        Task t = new Events(event, eventDate);
        tasks[t.getTaskCount() - 1] = t;
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                +  t + "\n"
                + "Now you have " + (t.getTaskCount() - 1) + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void addDeadline(String line, Task[] tasks) {
        String arg = line.split(" ", 2)[1];
        String task = arg.split("/by ", 2)[0];
        String dueDate = arg.split("/by ", 2)[1];
        Task t = new Deadline(task, dueDate);
        tasks[t.getTaskCount() - 1] = t;
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                +  t + "\n"
                + "Now you have " + (t.getTaskCount() - 1) + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void addToDo(String line, Task[] tasks) {
        String arg = line.split(" ", 2)[1];
        Task t = new ToDos(arg);
        tasks[t.getTaskCount() - 1] = t;
        System.out.println("_____________________________________________\n"
                + "Got it. I've added this task: \n"
                + t + "\n"
                + "Now you have " + (t.getTaskCount() - 1) + " tasks in the list." + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void addTask(String line, Task[] tasks) {
        Task t = new Task(line);
        tasks[t.getTaskCount() - 1] = t;
        System.out.println("_____________________________________________\n"
                + "added: " + line + "\n"
                + "_____________________________________________\n"
        );
    }

    private static void printList(Task[] tasks) {
        System.out.println("_____________________________________________\n"
                + "   Here are the tasks in your list:\n");
        for (int i = 1; i < tasks[0].getTaskCount(); i++) {
            System.out.println(tasks[i]);
        }
        System.out.println("_____________________________________________\n");
    }

    private static void markTask(String line, Task[] tasks) {
        String arg = line.split(" ")[1];
        int taskNum = Integer.parseInt(arg);
        if (taskNum <= tasks[0].getTaskCount()) {
            tasks[taskNum].markAsDone();
            System.out.println("_____________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + tasks[taskNum] + "\n"
                    + "_____________________________________________\n"
            );
        }
    }

    private static void unmarkTask(String line, Task[] tasks) {
        String arg = line.split(" ")[1];
        int taskNum = Integer.parseInt(arg);
        tasks[taskNum].markAsNotDone();
        if (taskNum <= tasks[0].getTaskCount()) {
            System.out.println("_____________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + tasks[taskNum] + "\n"
                    + "_____________________________________________\n"
            );
        }
    }

    private static void printBye() {
        System.out.println("_____________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_____________________________________________\n"
        );
    }
}







