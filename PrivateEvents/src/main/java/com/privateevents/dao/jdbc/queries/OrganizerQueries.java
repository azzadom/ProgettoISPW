package com.privateevents.dao.jdbc.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizerQueries {

    private OrganizerQueries() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectOrganizer(Statement stmt, String idOrganizer) throws SQLException {
        String query = String.format("SELECT * FROM Organizer WHERE Username = '%s';", idOrganizer);
        return stmt.executeQuery(query);
    }

    public static ResultSet selectOrganizer(Statement stmt, String username, String password) throws SQLException {
        String query = String.format("SELECT * FROM Organizer WHERE Username = '%s' AND Password = '%s';", username, password);
        return stmt.executeQuery(query);
    }

    public static void insertOrganizer(Statement stmt, String username, String password, String firstName, String lastName, String email, String infoPayPal) throws SQLException {
        String query = String.format("INSERT INTO Organizer(`Username`, `Password`, `Email`, `LastName`, `FirstName`, `InfoPayPal`)" +
                " VALUES ('%s','%s','%s','%s', \"%s\", \"%s\");", username, password, email, lastName, firstName, infoPayPal);
        stmt.executeUpdate(query);
    }

}
