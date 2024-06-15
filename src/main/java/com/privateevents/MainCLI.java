package com.privateevents;

import com.privateevents.controller.gui.cli.HomeGUIControllerCLI;
import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;

public class MainCLI {

    private MainCLI() {
        throw new IllegalStateException("Starter class");
    }

    static void run() {
        Integer currentSession = SessionManager.getSessionManager().createSession();
        HomeGUIControllerCLI controller = new HomeGUIControllerCLI(currentSession, new ReturnigHome());
        controller.start();
    }
}
