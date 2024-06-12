package controller.app;

import bean.NotificationBean;
import bean.OrganizerBean;
import dao.NotificationDAO;
import engineering.ToBeanConverter;
import engineering.dao.factory.FactorySingletonDAO;
import exception.NotFoundException;
import exception.dao.DAOException;
import exception.OperationFailedException;
import model.Notification;
import model.Organizer;
import model.TypeNotif;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static exception.dao.TypeDAOException.DUPLICATE;

public class NotificationsController {

    protected void notifyOrganizer(Notification notif, Organizer organizer) throws OperationFailedException {
        try{
        NotificationDAO notifDAO = FactorySingletonDAO.getDefaultDAO().getNotificationDAO();
        notifDAO.addNotification(organizer.getUsername(), notif);
        } catch (DAOException e) {
            if (e.getTypeException().equals(DUPLICATE)) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            } else {
                Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            }
                throw new OperationFailedException();
        }
    }

    public void deleteNotifications(List<NotificationBean> notifBean, OrganizerBean organizerBean) throws OperationFailedException {
        try {
        List<Notification> notifs = new ArrayList<>();
        for (NotificationBean notif : notifBean) {
            notifs.add(new Notification(TypeNotif.valueOf(notif.getType()), notif.getDateAndTime().toLocalDateTime(),
                    notif.getEventName(),notif.getBookingCode()));
        }
        FactorySingletonDAO.getDefaultDAO().getNotificationDAO().deleteNotification(organizerBean.getUsername(), notifs);
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public void deleteNotificationsByOrg(OrganizerBean organizerBean) throws OperationFailedException {
        try {
            FactorySingletonDAO.getDefaultDAO().getNotificationDAO().deleteNotificationByOrg(organizerBean.getUsername());
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public List<NotificationBean> getNotifications(OrganizerBean org) throws OperationFailedException, NotFoundException {
        try {
            List<Notification> notifs = FactorySingletonDAO.getDefaultDAO().getNotificationDAO().selectNotifications(org.getUsername());
            if (notifs.isEmpty()) {
                throw new NotFoundException("No notifications found.");
            }
            List<NotificationBean> notifBean = new ArrayList<>();
            for (Notification notif : notifs) {
                notifBean.add(ToBeanConverter.fromNotificationToNotificationBean(notif));
            }

            return notifBean;
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }
}
