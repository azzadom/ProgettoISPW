package com.privateevents.dao.jdbc.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EventQueries {

    private EventQueries() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectEventsByCity(Statement stmt ,String city) throws SQLException {
        String query = String.format("SELECT * FROM Event WHERE City = '%s' AND Date >= CURDATE() ORDER BY Date ASC;", city);
        return stmt.executeQuery(query);
    }

    public static ResultSet selectEventsByOrganizer(Statement stmt,String idOrganizer) throws SQLException {
        String query = String.format("SELECT * FROM Event WHERE Organizer = '%s';", idOrganizer);
        return stmt.executeQuery(query);
    }

    public static ResultSet selectEvent(Statement stmt, Integer idEvent) throws SQLException {
        String query = String.format("SELECT * FROM Event WHERE idEvent = '%d';", idEvent);
        return stmt.executeQuery(query);

    }
}
