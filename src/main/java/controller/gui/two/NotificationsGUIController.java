package controller.gui.two;

import bean.NotificationBean;
import bean.OrganizerBean;
import controller.app.NotificationsController;
import engineering.Session;
import exception.OperationFailedException;
import view.two.NotificationsView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsGUIController extends AbstractGUIController{

    private final List<NotificationBean> notifs;
    private final NotificationsView view = new NotificationsView();

    public NotificationsGUIController(Session session, List<NotificationBean> notifications){
        this.currentSession = session;
        this.notifs = notifications;
    }

    public void start(){
        int choice;
        choice = view.showMenu();

        switch(choice) {
            case 1 -> showNotifs();
            case 2 -> deleteNotifs();
            case 3 -> goOrgHome();
            case 4 -> logout();
            case 5 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
        }
    }

    private void showNotifs() {
        String[] notifStrings = new String[notifs.size()];
        for (int i = 0; i < notifs.size(); i++) {
            String dateAndTime = String.valueOf(notifs.get(i).getDateAndTime().toLocalDateTime());
            notifStrings[i] = String.format("%d - Type: %s, Event: %s, Booking Code: %s, DateTime: %s%n", i+1, notifs.get(i).getType(), notifs.get(i).getName(), notifs.get(i).getBooking(), dateAndTime);
        }
        view.showNotifications(notifStrings);
        start();
    }

    private void deleteNotifs() {
        try {
            List<Integer> numDelete = view.deleteNotification();
            NotificationsController notificationsController = new NotificationsController();
            if (numDelete.contains(-1)) {
                notificationsController.deleteNotificationsByOrg((OrganizerBean) currentSession.getUser());
                notifs.clear();
            } else {
                List<NotificationBean> notifsToDelete = new ArrayList<>();
                for (int i : numDelete) {
                    if (i < 0 || i > notifs.size()) {
                        view.showError("Invalid input!");
                        break;
                    } else {
                        notifsToDelete.add(notifs.get(i - 1));
                    }
                }
                notificationsController.deleteNotifications(notifsToDelete, (OrganizerBean) currentSession.getUser());
                notifs.removeAll(notifsToDelete);
            }
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
            start();
        }
        start();
    }

    private void goOrgHome() {
        currentSession.setReturningHome(false);
    }

    private void logout() {
        currentSession.setReturningHome(true);
        currentSession.setUser(null);
    }

}
