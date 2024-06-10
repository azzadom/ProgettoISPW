package starters;

import controller.gui.cli.HomeGUIControllerCLI;
import engineering.Session;

public class MainCLI {

    void run() {
        Session currentSession = new Session();
        HomeGUIControllerCLI controller = new HomeGUIControllerCLI(currentSession);
        controller.start();
    }
}
