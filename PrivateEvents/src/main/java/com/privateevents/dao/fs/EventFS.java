package com.privateevents.dao.fs;

import com.privateevents.dao.EventDAO;
import com.privateevents.model.Booking;
import com.privateevents.model.Ticket;
import com.privateevents.utils.dao.ObjectSerializationHandler;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Event;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class EventFS implements EventDAO {
    private static final String FILE_PATH = "src/main/resources/data/Event.ser";

    @Override
    public List<Event> selectEventsByCity(String city) throws DAOException {
        try {
            ObjectSerializationHandler<Event> hanlder = new ObjectSerializationHandler<>(FILE_PATH);
            List <Event> events = hanlder.findObject(event -> event.getCity().equalsIgnoreCase(city) &&
                    event.getDate().isAfter(LocalDate.now()));
            for (Event event : events) {
                event.setTransientParams();
                addBookingAndTickets(event);
            }
            return events;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEventsByCity: " + e.getMessage(), e, GENERIC);
        }

    }

    public Event selectEvent(Integer idEvent) throws DAOException{
        try {
            ObjectSerializationHandler<Event> handler = new ObjectSerializationHandler<>(FILE_PATH);
            List<Event> events = handler.findObject(event -> event.getIdEvent().equals(idEvent));
            if (events.isEmpty()) {
                return null;
            }
            Event event = events.getFirst();
            event.setTransientParams();
            addBookingAndTickets(event);
            return event;
        } catch (IOException | NoSuchElementException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEvent: " + e.getMessage(), e, GENERIC);
        }
    }

    @Override
    public List<Event> selectEventsByOrganizer(String idOrganizer) throws DAOException {
        try {
            ObjectSerializationHandler<Event> hanlder = new ObjectSerializationHandler<>(FILE_PATH);
            List<Event> events = hanlder.findObject(event -> event.getOrgUsername().equals(idOrganizer));
            for (Event event : events) {
                event.setTransientParams();
            }
            return events;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEventsByOrganizer: " + e.getMessage(), e, GENERIC);
        }
    }

    public void insertEvent(Event event) throws DAOException {
        try {
            ObjectSerializationHandler<Event> handler = new ObjectSerializationHandler<>(FILE_PATH);
            if(!handler.findObject(uniquePredicate(event.getCity(), event.getName())).isEmpty()){
                throw new DAOException("Event already exists", DUPLICATE);
            }
            List<Event> events = handler.readObjects();
            if (events.isEmpty()) {
                event.setIdEvent(1);
            } else {
                event.setIdEvent(events.getLast().getIdEvent() + 1);
            }
            handler.writeObjects(event);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in insertEvent: " + e.getMessage(), e, GENERIC);
        }
    }

    Predicate<Event> uniquePredicate(String city, String nameEvent){
        return event -> event.getCity().equals(city) && event.getName().equals(nameEvent);
    }

    private void addBookingAndTickets(Event event) throws DAOException {
        if(event == null) {
            return;
        }
        BookingFS bookingFS = new BookingFS();
        TicketFS ticketFS = new TicketFS();
        List<Booking> bookings = bookingFS.selectBookingsByEvent(event.getIdEvent());
        List<Ticket> tickets = ticketFS.selectTickets(event.getIdEvent());
        event.setTicketsAndBookings(tickets, bookings);
    }

}
