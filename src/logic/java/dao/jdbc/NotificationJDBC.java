package dao.jdbc;

import dao.NotificationDAO;
import dao.jdbc.queries.NotificationQueries;
import exception.dao.DAOException;
import model.*;

import static exception.dao.TypeDAOException.*;

import java.sql.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificationJDBC implements NotificationDAO {

    @Override
    public List<Notification> selectNotifications(String idOrganizer) throws DAOException {
        Statement stmt;
        List<Notification> notifications = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = NotificationQueries.selectNotificationsByOrganizer(stmt, idOrganizer);
            while (rs.next()) {
                Notification notification = new Notification(TypeNotif.valueOf(rs.getString("Type")),
                        rs.getTimestamp("DateTime").toLocalDateTime(),
                        rs.getString("EventName"),rs.getString("BookingCode"));
                notifications.add(notification);
            }
            rs.close();
            stmt.close();

            return notifications;

        } catch (SQLException e) {
            throw new DAOException("Error in selectNotifications: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public void addNotification(String idOrganizer, Notification notification) throws DAOException {
        Statement stmt;
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            Timestamp timestamp = Timestamp.valueOf(notification.getDateAndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            NotificationQueries.addNotification(stmt, timestamp,notification.getType().ordinal(),notification.getEventName(),
                    idOrganizer, notification.getBookingCode());
            stmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Notification already exists", DUPLICATE);
            }
            throw new DAOException("Error in addNotification: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public void deleteNotification(String idOrganizer, List<Notification> notification) throws DAOException {

        Statement stmt;
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            for (Notification notif : notification) {
                Timestamp timestamp = Timestamp.valueOf(notif.getDateAndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                NotificationQueries.deleteNotification(stmt, idOrganizer, notif.getEventName(),
                        notif.getBookingCode(), timestamp);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new DAOException("Error in deleteNotification: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }
}
