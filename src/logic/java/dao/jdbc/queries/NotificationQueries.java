package dao.jdbc.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class NotificationQueries {

    public static ResultSet selectNotificationsByOrganizer(Statement stmt, String idOrganizer) throws SQLException {
        String query = String.format("SELECT * FROM Notif WHERE Organizer = '%s';", idOrganizer);
        return stmt.executeQuery(query);
    }

    public static void addNotification(Statement stmt, Timestamp timestamp, Integer type, String eventName, String idOrganizer, String bookingCode) throws SQLException {
        String query = String.format("INSERT INTO Notif (DateTime,Type, EventName, Organizer, BookingCode) " +
                "VALUES ('%s', '%d', '%s', '%s', '%s')", timestamp, type, eventName, idOrganizer, bookingCode);
        stmt.executeUpdate(query);
    }

    public static void deleteNotification(Statement stmt, String idOrganizer, String nameEvent, String bookingCode, Timestamp timestamp) throws SQLException {
        String query = String.format("DELETE FROM Notif WHERE Organizer = '%s' AND EventName = '%s' AND BookingCode = '%s' AND DateTime = '%s'",
                idOrganizer, nameEvent, bookingCode, timestamp);
        stmt.executeUpdate(query);
    }



}
