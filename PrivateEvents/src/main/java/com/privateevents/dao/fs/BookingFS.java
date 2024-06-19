package com.privateevents.dao.fs;

import com.privateevents.dao.BookingDAO;
import com.privateevents.utils.dao.CSVHandler;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.privateevents.exception.dao.TypeDAOException.*;

public class BookingFS implements BookingDAO {

    private static final String FILE_PATH = "src/main/resources/data/Booking.csv";

    @Override
    public Booking addBooking(Integer idEvent, Booking booking) throws DAOException{
        try{
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            int idBooking;
            if(!(handler.find(uniquePredicate(String.valueOf(idEvent), booking.getEmail(), booking.getTelephone())).isEmpty())){
                throw new DAOException("Booking already exists", DUPLICATE);
            }
            List<Booking> bookings = this.selectBookingsByEvent(idEvent);
            if (bookings.isEmpty()) {
                idBooking = 1;
            } else {
                idBooking = bookings.getLast().getIdBooking() + 1;
            }
            booking.setIdAndCodeBooking(idBooking);
            bookings.add(booking);

            List<String[]> rs = new ArrayList<>();
            rs.add(toCsvRecord(idEvent, booking));
            handler.writeAll(rs);
            return booking;
        } catch (IOException e) {
            throw new DAOException("Error in addBooking: " + e.getMessage(), e, GENERIC);
        }
    }

    @Override
    public List<Booking> selectBookingsByEvent(Integer idEvent) throws DAOException {
        try {
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            List<String[]> found = handler.find(r -> r[9].equals(String.valueOf(idEvent)));
            return found.stream().map(this::fromCsvRecord).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new DAOException("Error in selectBookingsByEvent: " + e.getMessage(), e, GENERIC);
        }
    }

    private Predicate<String[]> uniquePredicate(String idEvent, String email, String telephone) {
        return r -> (r[9].equals(idEvent) && (r[4].equals(email) || r[5].equals(telephone)));
    }

    private Booking fromCsvRecord(String[] r) {
        Booking booking = new Booking(r[0], r[1], Integer.parseInt(r[2]), r[3].charAt(0), r[4], r[5], r[6], Boolean.parseBoolean(r[7]));
        booking.setIdAndCodeBooking(r[8]);
        return booking;
    }

    private String[] toCsvRecord(Integer idEvent, Booking booking) {
        return new String[]{booking.getLastname(), booking.getFirstname(), String.valueOf(booking.getAge()), String.valueOf(booking.getGender()),
                booking.getEmail(), booking.getTelephone(), booking.getTicketType(), String.valueOf(booking.getOnlinePayment()), booking.getCodeBooking(), String.valueOf(idEvent)};
    }

}
