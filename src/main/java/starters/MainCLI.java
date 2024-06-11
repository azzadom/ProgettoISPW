package starters;

import controller.gui.cli.HomeGUIControllerCLI;
import engineering.view.SessionManager;
import engineering.view.cli.ReturnigHome;

public class MainCLI {

    static void run() {
        Integer currentSession = SessionManager.getSessionManager().createSession();
        HomeGUIControllerCLI controller = new HomeGUIControllerCLI(currentSession, new ReturnigHome());
        controller.start();
    }
}
