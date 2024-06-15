package com.privateevents.controller.gui.cli;

import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;

public abstract class AbstractGUIControllerCLI {

    protected Integer currentSession;

    protected ReturnigHome returningHome;

    public abstract void start();

    protected void exit(){
        SessionManager.getSessionManager().removeSession(currentSession);
        System.out.println("Exiting...Bye bye!");
        System.exit(0);
    }

    protected void goBack() {
        returningHome.setReturningHome(false);
    }

    protected void goHome() {
        SessionManager.getSessionManager().getSessionFromId(currentSession).softReset();
        returningHome.setReturningHome(true);
    }

    protected void logout() {
        SessionManager.getSessionManager().getSessionFromId(currentSession).reset();
        returningHome.setReturningHome(true);
    }

}
