package com.privateevents.dao.jdbc;

import com.privateevents.dao.BookingDAO;
import com.privateevents.dao.jdbc.queries.BookingQueries;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Booking;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingJDBC implements BookingDAO {

    private static final String COLUMN_LASTNAME = "LastName";
    private static final String COLUMN_FIRSTNAME = "FirstName";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_TELEPHONE = "Telephone";
    private static final String COLUMN_TICKET_TYPE = "TicketType";
    private static final String COLUMN_ONLINE_PAYMENT = "OnlinePayment";
    private static final String COLUMN_CODE_BOOKING = "CodeBooking";

    @Override
    public Booking addBooking(Integer idEvent, Booking booking) throws DAOException {
        int id;
        try (Statement stmt = SingletonConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet rs = BookingQueries.countBookings(stmt, idEvent);
            if (rs.next()) {
                id = stmt.getUpdateCount() + 1;
                booking.setIdAndCodeBooking(id);
            }
            BookingQueries.insertBooking(stmt, booking.getCodeBooking(),booking.getLastname(), booking.getFirstname(), booking.getAge(),
                    booking.getEmail(), booking.getTelephone(), String.valueOf(booking.getGender()), booking.getTicketType(),
                    (Boolean.TRUE.equals(booking.getOnlinePayment()) ? 1 : 0), idEvent);
            rs.close();
            return booking;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DAOException("Booking already exists", DUPLICATE);
            }else if (e.getSQLState().equals("45000")) {
                throw new DAOException("Tickets sold out", LIMIT_REACHED);
            }else {
                throw new DAOException("Error adding booking: " + e.getMessage(), e, GENERIC);
            }
        }
    }

    @Override
    public List<Booking> selectBookingsByEvent(Integer idEvent) throws DAOException {
        List<Booking> bookings = new ArrayList<>();
        try (Statement stmt = SingletonConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet rs = BookingQueries.selectBookingByEvent(stmt, idEvent);
            while (rs.next()) {
                Booking booking = fromResultSet(rs);
                bookings.add(booking);
            }
            rs.close();
            return bookings;
        }catch (SQLException e) {
            throw new DAOException("Error selecting booking: " + e.getMessage(), e, GENERIC);
        }
    }

    private Booking fromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking(rs.getString(COLUMN_LASTNAME), rs.getString(COLUMN_FIRSTNAME), rs.getInt(COLUMN_AGE), rs.getString(COLUMN_GENDER).charAt(0), rs.getString(COLUMN_EMAIL),
                rs.getString(COLUMN_TELEPHONE), rs.getString(COLUMN_TICKET_TYPE), rs.getBoolean(COLUMN_ONLINE_PAYMENT));
        booking.setIdAndCodeBooking(rs.getString(COLUMN_CODE_BOOKING));
        return booking;
    }
}
