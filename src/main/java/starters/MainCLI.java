package starters;

import controller.gui.two.HomeGUIController;
import engineering.Session;

public class MainCLI {

    void run() {
        Session currentSession = new Session();
        HomeGUIController controller = new HomeGUIController(currentSession);
        controller.start();
    }
}
