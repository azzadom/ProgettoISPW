package com.privateevents.controller.app;

import com.privateevents.bean.BookingBean;
import com.privateevents.bean.EventBean;
import com.privateevents.bean.TicketBean;
import com.privateevents.dao.BookingDAO;
import com.privateevents.dao.EventDAO;
import com.privateevents.dao.OrganizerDAO;
import com.privateevents.dao.TicketDAO;
import com.privateevents.utils.ToBeanConverter;
import com.privateevents.utils.dao.factory.FactorySingletonDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.privateevents.model.*;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.OperationFailedException;

import static com.privateevents.exception.dao.TypeDAOException.*;

public class BookTicketController {

    private final BookingDAO bookingDAO;
    private final EventDAO eventDAO;
    private final OrganizerDAO organizerDAO;
    private final TicketDAO ticketDAO;


    public BookTicketController() {
        this.bookingDAO = FactorySingletonDAO.getDefaultDAO().getBookingDAO();
        this.eventDAO = FactorySingletonDAO.getDefaultDAO().getEventDAO();
        this.organizerDAO = FactorySingletonDAO.getDefaultDAO().getOrganizerDAO();
        this.ticketDAO = FactorySingletonDAO.getDefaultDAO().getTicketDAO();
    }

    public List<EventBean> findCityEvents(String city) throws OperationFailedException, NotFoundException {
        try {
            List<Event> events = eventDAO.selectEventsByCity(city);
            if (events.isEmpty()) {
                throw new NotFoundException("No events found in the city: " + city);
            }
            List<EventBean> eventBeans = new ArrayList<>();
            for (Event event : events) {
                EventBean eventBean = ToBeanConverter.fromEventToEventBeanWithoutBook(event);
                eventBeans.add(eventBean);
            }
            return eventBeans;
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        } catch (IncorrectDataException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public EventBean eventDetails(EventBean eventBean) throws OperationFailedException, NotFoundException {
        try {
            Event event = eventDAO.selectEvent(eventBean.getIdEvent());
            if (event == null) {
                throw new NotFoundException("Event not found.");
            }
            List<Ticket> tickets = ticketDAO.selectTickets(eventBean.getIdEvent());
            if (tickets.isEmpty()){
                String msg = "Inconsistent data. No tickets found for event: " + eventBean.getIdEvent();
                Logger.getGlobal().log(Level.SEVERE, msg);
                throw new OperationFailedException();
            }
            List<Booking> bookings = bookingDAO.selectBookingsByEvent(eventBean.getIdEvent());
            event.setTicketsAndBookings(tickets, bookings);
            eventBean = ToBeanConverter.fromEventToEventBeanWithoutBook(event);
            return eventBean;
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        } catch (IncorrectDataException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public String sendReservation(EventBean eventBean, BookingBean bookingBean) throws OperationFailedException, DuplicateEntryException {

        try {

            checkBookingValid(bookingBean, eventBean);

            Organizer organizer = organizerDAO.selectOrganizer(eventBean.getOrgName());
            if (organizer == null) {
                String msg = "Inconsistent data. Organizer not found for event: " + eventBean.getIdEvent();
                Logger.getGlobal().log(Level.SEVERE, msg);
                throw new OperationFailedException();
            }

            Booking booking = new Booking(bookingBean.getLastName(), bookingBean.getFirstName(), bookingBean.getAge(),
                    bookingBean.getGender(), bookingBean.getEmail(), bookingBean.getTelephone(),
                    bookingBean.getTicketType(), bookingBean.getOnlinePayment());

            if (bookingBean.getOnlinePayment().equals(true)) {
                Double amount = 0.0;
                for (TicketBean t : eventBean.getTickets()) {
                    if (t.getTypeName().equals(booking.getTicketType())) {
                        amount = t.getPrice();
                        break;
                    }
                }
                OnlinePaymentController onlinePaymentController = new OnlinePaymentController();
                boolean response = onlinePaymentController.payPayPal(organizer, amount,
                        "Booking for event: " + eventBean.getName());
                if (!response) {
                    throw new OperationFailedException("Payment failed.");
                }
            }

            booking = bookingDAO.addBooking(eventBean.getIdEvent(), booking);

            NotificationsController notificationsController = new NotificationsController();
            notificationsController.notifyOrganizer(new Notification(TypeNotif.NEW, LocalDateTime.now(), eventBean.getName(), booking.getCodeBooking()), organizer);

            eventBean.setTicketsAvailability(bookingBean.getTicketType(),eventBean.getTicketsAvailability(booking.getTicketType()) - 1);

            return booking.getCodeBooking();
        } catch (DAOException e) {
            if (e.getTypeException().equals(DUPLICATE)) {
                throw new DuplicateEntryException(e.getMessage() + ", if you have already paid, contact support.");
            } else if (e.getTypeException().equals(LIMIT_REACHED)) {
                throw new OperationFailedException("No tickets available for this type. If you have already paid, contact support.");
            }else {
                Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
                throw new OperationFailedException("Unable to complete the operation. If you have already paid, contact support.");
            }
        }
    }

    private void checkBookingValid(BookingBean bookingBean, EventBean eventBean) throws OperationFailedException {
        if (Boolean.TRUE.equals(eventBean.getClosed())){
            throw new OperationFailedException("Bookings are closed.");
        }

        List<TicketBean> tickets = eventBean.getTickets();
        boolean found = false;
        for (TicketBean t : tickets) {
            if (t.getTypeName().equals(bookingBean.getTicketType())) {
                found = true;
                if (bookingBean.getAge() < t.getMinimumAge()) {
                    throw new OperationFailedException("Invalid age!");
                }
                break;
            }
        }
        if (!found) {
            throw new OperationFailedException("Invalid ticket type.");
        }
    }
}
