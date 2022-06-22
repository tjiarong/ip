package potaton;

import potaton.controller.Parser;
import potaton.controller.UI;

public class Potaton {

    /**
     * The chatbot itself, calls upon other classes to
     * function.
     * @param args
     */
    public static void main(String[] args){
        UI.printWelcomeMessage();
        Parser.parseUserCommand();
        UI.printByeMessage();
        System.out.println("Hello");
        System.out.println("Goodnight");
    }
}
