import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "";
        Scanner in = new Scanner(System.in);
        Task[] items = new Task[100];
        int itemCount = 0;

        String logo = "_______  _______ _________ _______ _________ _______  _\n"
                    + "(  ____ )(  ___  )\\__   __/(  ___  )\\__   __/(  ___  )( (    /|\n"
                    + "| (    )|| (   ) |   ) (   | (   ) |   ) (   | (   ) ||  \\  ( |\\\n"
                    + "| (____)|| |   | |   | |   | (___) |   | |   | |   | ||   \\ | |\n"
                    + "|  _____)| |   | |   | |   |  ___  |   | |   | |   | || (\\ \\) | \n"
                    + "| (      | |   | |   | |   | (   ) |   | |   | |   | || | \\   | \n"
                    + "| )      | (___) |   | |   | )   ( |   | |   | (___) || )  \\  | \n"
                    + "|/       (_______)   )_(   |/     \\|   )_(   (_______)|/    )_) \n";

        System.out.println("____________________________________________________________\n" +
                logo + "\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n"
                );

        line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")) {
                System.out.println("____________________________________________________________\n" +
                        "   Here are the tasks in your list:\n");
                for (int i = 1; i <= itemCount; i++) {
                    System.out.println(i + ".[" + items[i - 1].getStatusIcon() + "] " + items[i - 1].description);
                }
                System.out.println("____________________________________________________________\n");
            } else if (line.contains("unmark")){
                int taskNum = Integer.parseInt(line.substring(line.length() - 1));
                if (taskNum <= itemCount){
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            "[] " + items[taskNum - 1].description + "\n" +
                            "____________________________________________________________\n"
                    );
                    items[taskNum - 1].markAsNotDone();
                }
            } else if (line.contains("mark")) {
                int taskNum = Integer.parseInt(line.substring(line.length() - 1));
                if (taskNum <= itemCount) {
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            "[X] " + items[taskNum - 1].description + "\n" +
                            "____________________________________________________________\n"
                    );
                    items[taskNum - 1].markAsDone();
                }
            } else{
                Task t = new Task(line);
                items[itemCount] = t;
                itemCount++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + line + "\n" +
                        "____________________________________________________________\n"
                );
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
                );
    }
}









