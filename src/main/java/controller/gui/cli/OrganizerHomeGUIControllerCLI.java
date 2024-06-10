package controller.gui.cli;

import engineering.Session;
import view.cli.OrganizerHomeView;

public class OrganizerHomeGUIControllerCLI extends AbstractGUIControllerCLI {

    private final OrganizerHomeView view = new OrganizerHomeView();

    public OrganizerHomeGUIControllerCLI(Session session){
        this.currentSession = session;
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
        start();
    }

    private void notif() {
        NotificationsGUIControllerCLI notificationsGUIController = new NotificationsGUIControllerCLI(currentSession);
        notificationsGUIController.start();
        if(Boolean.FALSE.equals(currentSession.getReturningHome())) {
            start();
        }
    }

    private void setting() {
        view.showError("View Settings not implemented yet!");
        start();
    }

    private void logout() {
        currentSession.setUser(null);
        goHome();
    }

}
