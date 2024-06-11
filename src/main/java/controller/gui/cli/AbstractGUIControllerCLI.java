package controller.gui.cli;

import engineering.view.SessionManager;
import engineering.view.cli.ReturnigHome;

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
