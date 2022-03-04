package potaton;

import potaton.controller.Parser;
import potaton.controller.UI;

public class Potaton {
    public static void main(String[] args){
        UI.printWelcomeMessage();
        Parser.parseUserCommand();
        UI.printByeMessage();
    }
}
