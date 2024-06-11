package controller.gui.cli;

import bean.NotificationBean;
import bean.OrganizerBean;
import bean.UserBean;
import controller.app.NotificationsController;
import engineering.view.SessionManager;
import engineering.view.cli.ReturnigHome;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.cli.NotificationsView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationsGUIControllerCLI extends AbstractGUIControllerCLI {

    private List<NotificationBean> notifications;
    private final UserBean user;
    private final NotificationsView view = new NotificationsView();

    public NotificationsGUIControllerCLI(Integer session, ReturnigHome returningHome){
        this.currentSession = session;
        this.user = SessionManager.getSessionManager().getSessionFromId(session).getUser();
        this.returningHome = returningHome;
    }

    public void start(){

        try {
            NotificationsController notificationsController = new NotificationsController();
            notifications = notificationsController.getNotifications((OrganizerBean) user);

            int choice;
            choice = view.showMenu();

            switch (choice) {
                case 1 -> showNotifs();
                case 2 -> deleteNotifs();
                case 3 -> goOrgHome();
                case 4 -> logout();
                case 5 -> exit();
                default -> throw new IllegalArgumentException("Invalid case!");
            }
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
        } catch (NotFoundException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void showNotifs() {
        String[] notifStrings = new String[notifications.size()];
        for (int i = 0; i < notifications.size(); i++) {
            LocalDateTime dateAndTime = notifications.get(i).getDateAndTime().toLocalDateTime();
            String date = String.valueOf(dateAndTime.toLocalDate());
            String time = String.format("%s:%s",dateAndTime.getHour(), dateAndTime.getMinute());
            notifStrings[i] = String.format("%d - Type: %s, Event: %s, Booking Code: %s, DateTime: %s - %s%n", i+1, notifications.get(i).getType(),
                    notifications.get(i).getName(), notifications.get(i).getBooking(), date, time);
        }
        view.showNotifications(notifStrings);
        start();
    }

    private void deleteNotifs() {
        try {
            List<Integer> numDelete = view.deleteNotification();
            NotificationsController notificationsController = new NotificationsController();
            if (numDelete.contains(-1)) {
                notificationsController.deleteNotificationsByOrg((OrganizerBean) SessionManager.getSessionManager().getSessionFromId(currentSession).getUser());
                notifications.clear();
            } else {
                List<NotificationBean> notifsToDelete = new ArrayList<>();
                for (int i : numDelete) {
                    if (i < 0 || i > notifications.size()) {
                        view.showError("Invalid input!");
                        break;
                    } else {
                        notifsToDelete.add(notifications.get(i - 1));
                    }
                }
                notificationsController.deleteNotifications(notifsToDelete, (OrganizerBean) user);
                notifications.removeAll(notifsToDelete);
            }
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
        }
        start();
    }

    private void goOrgHome() {
        returningHome.setReturningHome(false);
    }


}
