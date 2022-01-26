import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "";
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
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

        while (!line.equals("bye")){
            line = in.nextLine();
            if (line.equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 1; i <= itemCount; i++){
                    System.out.println(i  + ". " + items[i - 1]);
                }
                System.out.println("____________________________________________________________\n");
            } else{
                items[itemCount] = line;
                itemCount++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + line + "\n" +
                        "____________________________________________________________\n"
                );
            }
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
                );
    }
}









