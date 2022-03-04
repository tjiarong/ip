package potaton.controller;

/**
 * Handles all UI functions such as printing of messages
 */
public class UI {

    public static final String INIT_FILE_PATH = "potaton.txt";

    public static final String BORDER = "_____________________________________________";
    public static final String TASK_LIST = "list";
    public static final String TASK_TODO = "todo";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_MARK = "mark";
    public static final String TASK_UNMARK = "unmark";
    public static final String TASK_DELETE = "delete";
    public static final String TASK_FIND = "find";
    public static final String EXIT = "bye";
    public static final String LOAD_TODO = "T";
    public static final String LOAD_EVENT = "E";
    public static final String LOAD_DEADLINE = "D";
    public static final String TASK_ADDED_MSG = "Got it. I've added this task";
    public static final String TASK_MARKED_MSG = "Nice! I've marked this task as done:";
    public static final String TASK_UNMARKED_MSG = "OK, I've marked this task as not done yet:";
    public static final String TASK_DELETED_MSG = "Noted. I've removed this task:";
    public static final String TASK_FIND_MSG = "Here are the matching tasks in your list:";
    public static final String TASK_LIST_MSG = "   Here are the tasks in your list:";
    public static final String MISSING_TASK_DETAILS = "Please fill in all details of task.";
    public static final String INVALID_INPUT = "Invalid input. Try again you nerd.";
    public static final String INVALID_EVENT_FORMAT = "Invalid event format. Please use: event " +
            "<description> /at <yyyy-mm-dd> ";
    public static final String INVALID_DEADLINE_FORMAT = "Invalid deadline format. Please use: " +
            "deadline <description> /by <yyyy-mm-dd>";
    public static final String INVALID_TODO_FORMAT = "Invalid todo format. Description of todo " +
            "cannot be empty";
    public static final String INVALID_DATE_FORMAT = "Unknown date entered. Defaulting to 1" +
            " month from now";
    public static final String INVALID_FIND_FORMAT = "Invalid find format. Please use: find <string>";
    public static final String EMPTY_FIND_STRING = "Search string cannot be empty";
    public static final String INVALID_TASKNUM_MSG = "Invalid task number: task does not exist";
    public static final String UNKNOWN_TASK = "Invalid task identified in file.";
    public static final String FILE_MISSING_MSG = "File does not exist";
    public static final String FILE_FOUND_MSG = "File found. Loading file into database.";
    public static final String FAIl_FILE_CREATE = "File creation failed.";
    public static final String FAIl_FILE_WRITE = "File writing failed.";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String WELCOME_MESSAGE =
            " ______   _____   ________  _______ __________ _______ \n"
            + "( ____ )(  ___  )\\__   __/(  ___  )\\__   __/(  ___  )( (    /|\n"
            + "| (    )|| (   ) |   ) (   | (   ) |   ) (   | (   ) ||  \\  ( |\\\n"
            + "| (____)|| |   | |   | |   | (___) |   | |   | |   | ||   \\ | |\n"
            + "|  _____)| |   | |   | |   |  ___  |   | |   | |   | || (\\ \\) | \n"
            + "| (      | |   | |   | |   | (   ) |   | |   | |   | || | \\   | \n"
            + "| )      | (___) |   | |   | )   ( |   | |   | (___) || )  \\  | \n"
            + "|/       (_______)   )_(   |/     \\|   )_(   (_______)|/    )_) \n \n"
            + "Hello! I'm Potaton\nWhat can I do for you?";

    public static void printText(String textToPrint) {
        System.out.println(textToPrint);
    }

    public static void printTextSameLine(String textToPrint) {
        System.out.print(textToPrint);
    }

    /**
     * Print goodbye message
     */
    public static void printByeMessage() {
        printText(BORDER);
        printText(GOODBYE_MESSAGE);
        printText(BORDER);
    }

    /**
     * Print welcome message
     */
    public static void printWelcomeMessage() {
        printText(BORDER);
        printText(WELCOME_MESSAGE);
        printText(BORDER);
    }

}
