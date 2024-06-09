package controller.gui.two;

import bean.NotificationBean;
import bean.OrganizerBean;
import controller.app.NotificationsController;
import engineering.Session;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.two.OrganizerHomeView;

import java.util.List;

public class OrganizerHomeGUIController extends AbstractGUIController {

    private final OrganizerHomeView view = new OrganizerHomeView();

    public OrganizerHomeGUIController(Session session){
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

        try {
            NotificationsController notificationsController = new NotificationsController();
            List<NotificationBean> notifs = notificationsController.getNotifications((OrganizerBean) currentSession.getUser());
            NotificationsGUIController notificationsGUIController = new NotificationsGUIController(currentSession, notifs);
            notificationsGUIController.start();
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
            start();
        } catch (NotFoundException e) {
            view.showMessage(e.getMessage());
            start();
        }
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
    }

}
