package com.privateevents.dao.jdbc.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingQueries {

     private BookingQueries() {
        throw new IllegalStateException("Utility class");
    }

    public static void insertBooking(Statement stmt, String codeBooking,String lastName, String firstName, Integer age, String email, String telephone, String gender, String ticketType, int onlinePayment, Integer idEvent) throws SQLException {
        String query = String.format("INSERT INTO Booking (CodeBooking, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment, Event) " +
                "VALUES ('%s','%s', '%s', '%d','%s', '%s', '%s', '%s', '%d', '%d')", codeBooking,lastName, firstName, age, email, telephone, gender, ticketType, onlinePayment, idEvent);
        stmt.executeUpdate(query);
    }

    public static ResultSet countBookings(Statement stmt, Integer idEvent) throws SQLException {
        String query = String.format("SELECT COUNT(*) FROM booking WHERE Event = '%d';", idEvent);
        return stmt.executeQuery(query);
    }

    public static ResultSet selectBookingByEvent(Statement stmt, Integer idEvent) throws SQLException {
        String query = String.format("SELECT * FROM Booking WHERE Event = '%d';", idEvent);
        return stmt.executeQuery(query);
    }
}
