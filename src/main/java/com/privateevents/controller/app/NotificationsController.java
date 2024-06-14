package com.privateevents.controller.app;

import com.privateevents.bean.NotificationBean;
import com.privateevents.bean.OrganizerBean;
import com.privateevents.dao.NotificationDAO;
import com.privateevents.utils.ToBeanConverter;
import com.privateevents.utils.dao.factory.FactorySingletonDAO;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.exception.OperationFailedException;
import com.privateevents.model.Notification;
import com.privateevents.model.Organizer;
import com.privateevents.model.TypeNotif;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.privateevents.exception.dao.TypeDAOException.DUPLICATE;

public class NotificationsController {

    private final NotificationDAO notificationDAO;

    public NotificationsController() {
        this.notificationDAO = FactorySingletonDAO.getDefaultDAO().getNotificationDAO();
    }

    protected void notifyOrganizer(Notification notif, Organizer organizer) {
        try{
            notificationDAO.addNotification(organizer.getUsername(), notif);
        } catch (DAOException e) {
            if (e.getTypeException().equals(DUPLICATE)) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            } else {
                Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            }
        }
    }

    public void deleteNotifications(List<NotificationBean> notifBean, OrganizerBean organizerBean) throws OperationFailedException {
        try {
            List<Notification> notifs = new ArrayList<>();
            for (NotificationBean notif : notifBean) {
                notifs.add(new Notification(TypeNotif.valueOf(notif.getType()), notif.getDateAndTime().toLocalDateTime(),
                        notif.getEventName(),notif.getBookingCode()));
            }
            notificationDAO.deleteNotification(organizerBean.getUsername(), notifs);
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public void deleteAllNotifications(OrganizerBean organizerBean) throws OperationFailedException {
        try {
            notificationDAO.deleteNotificationByOrg(organizerBean.getUsername());
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public List<NotificationBean> getNotifications(OrganizerBean org) throws OperationFailedException, NotFoundException {
        try {
            List<Notification> notifs = notificationDAO.selectNotifications(org.getUsername());
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
