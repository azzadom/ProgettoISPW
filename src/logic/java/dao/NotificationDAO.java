package dao;

import exception.dao.DAOException;
import model.Notification;

import java.util.List;

public interface NotificationDAO {

    public List<Notification> selectNotifications(String idOrganizer) throws DAOException;

    public void addNotification(String idOrganizer, Notification notification) throws DAOException;

    public void deleteNotification(String idOrganizer, List<Notification> notification) throws DAOException;

}
