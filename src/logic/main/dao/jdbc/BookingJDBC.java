package dao.jdbc;

import dao.BookingDAO;
import dao.jdbc.queries.BookingQueries;
import exception.dao.DAOException;
import model.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static exception.dao.TypeDAOException.*;

public class BookingJDBC implements BookingDAO {
    @Override
    public Booking addBooking(Integer idEvent, Booking booking) throws DAOException {
        Statement stmt;
        int id;
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = BookingQueries.countBookings(stmt, idEvent);
            if (rs.next()) {
                id = stmt.getUpdateCount() + 1;
                booking.setIdAndCodeBooking(id);
            }
            BookingQueries.insertBooking(stmt, booking.getCodeBooking(),booking.getLastname(), booking.getFirstname(), booking.getAge(),
                    booking.getEmail(), booking.getTelephone(), String.valueOf(booking.getGender()), booking.getTicketType(),
                    (booking.getOnlinePayment() ? 1 : 0), idEvent);
            rs.close();
            stmt.close();
            return booking;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062) {
                throw new DAOException("Booking already exists", DUPLICATE);
            }
            throw new DAOException("Error adding booking: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public List<Booking> selectBooking(Integer idEvent) throws DAOException {
        Statement stmt;
        List<Booking> bookings = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = BookingQueries.selectBookingByEvent(stmt, idEvent);
            while (rs.next()) {
                Booking booking = new Booking(rs.getString("LastName"), rs.getString("FirstName"), rs.getInt("Age"), rs.getString("Gender").charAt(0), rs.getString("Email"),
                        rs.getString("Telephone"), rs.getString("TicketType"), rs.getBoolean("OnlinePayment"));
                booking.setIdAndCodeBooking(rs.getString("CodeBooking"));
                bookings.add(booking);
            }
            rs.close();
            stmt.close();
            return bookings;
        }catch (SQLException e) {
            throw new DAOException("Error selecting booking: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }
}
