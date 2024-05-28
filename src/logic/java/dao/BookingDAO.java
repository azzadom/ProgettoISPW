package dao;

import exception.dao.DAOException;
import model.Booking;

import java.util.List;

public interface BookingDAO {

    public Booking addBooking(Integer idEvent, Booking booking) throws DAOException;

    public List<Booking> selectBooking(Integer idEvent) throws DAOException;
}
