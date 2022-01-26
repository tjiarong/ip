import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        final int MAX_TASK = 100;

        Scanner in = new Scanner(System.in);
        Task[] items = new Task[MAX_TASK];
        int itemCount = 0;
        int taskNum = 0;

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

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("_____________________________________________\n"
                        + "   Here are the tasks in your list:\n");
                for (int i = 1; i <= itemCount; i++) {
                    System.out.println(i + ".[" + items[i - 1].getStatusIcon() + "] "
                            + items[i - 1].description);
                }
                System.out.println("_____________________________________________\n");
            } else if (line.contains("unmark")) {
                taskNum = Integer.parseInt(line.substring(line.length() - 1));
                if (taskNum <= itemCount) {
                    System.out.println("_____________________________________________\n"
                            + "OK, I've marked this task as not done yet:\n"
                            + "[] " + items[taskNum - 1].description + "\n"
                            + "_____________________________________________\n"
                    );
                    items[taskNum - 1].markAsNotDone();
                }
            } else if (line.contains("mark")) {
                taskNum = Integer.parseInt(line.substring(line.length() - 1));
                if (taskNum <= itemCount) {
                    System.out.println("_____________________________________________\n"
                            + "Nice! I've marked this task as done:\n"
                            + "[X] " + items[taskNum - 1].description + "\n"
                            + "_____________________________________________\n"
                    );
                    items[taskNum - 1].markAsDone();
                }
            } else {
                Task t = new Task(line);
                items[itemCount] = t;
                itemCount++;
                System.out.println("_____________________________________________\n"
                        + "added: " + line + "\n"
                        + "_____________________________________________\n"
                );
            }
            line = in.nextLine();
        }

        System.out.println("_____________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_____________________________________________\n"
                );
    }
}









