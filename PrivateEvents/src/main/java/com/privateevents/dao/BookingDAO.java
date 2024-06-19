package com.privateevents.dao;

import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Booking;

import java.util.List;

public interface BookingDAO {

    public Booking addBooking(Integer idEvent, Booking booking) throws DAOException;

    public List<Booking> selectBookingsByEvent(Integer idEvent) throws DAOException;
}
