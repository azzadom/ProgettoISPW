package controller.gui.cli;

import engineering.view.SessionManager;
import engineering.view.cli.ReturnigHome;
import view.cli.OrganizerHomeView;

public class OrganizerHomeGUIControllerCLI extends AbstractGUIControllerCLI {

    private final OrganizerHomeView view = new OrganizerHomeView();

    public OrganizerHomeGUIControllerCLI(Integer session, ReturnigHome returningHome){
        this.currentSession = session;
        this.returningHome = returningHome;
    }

    public void start(){
        int choice;
        choice = view.showMenu();

        switch(choice) {
            case 1 -> event();
            case 2 -> notif();
            case 3 -> setting();
            case 4 -> logout();
            case 5 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
        }
    }

    private void event() {
        view.showError("View Events not implemented yet!");
        if(Boolean.FALSE.equals(returningHome.getReturningHome())) {
            start();
        }
    }

    private void notif() {
        NotificationsGUIControllerCLI notificationsGUIController = new NotificationsGUIControllerCLI(currentSession, returningHome);
        notificationsGUIController.start();
        if(Boolean.FALSE.equals(returningHome.getReturningHome())) {
            start();
        }
    }

    private void setting() {
        view.showError("View Settings not implemented yet!");
        if(Boolean.FALSE.equals(returningHome.getReturningHome())) {
            start();
        }
    }

}
