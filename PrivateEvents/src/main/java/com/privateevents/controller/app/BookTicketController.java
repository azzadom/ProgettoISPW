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
                EventBean eventBean = ToBeanConverter.fromEventToEventBean(event);
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

    public List<TicketBean> getEventTickets(EventBean eventBean) throws OperationFailedException, NotFoundException {
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

            List<TicketBean> ticketBeans = new ArrayList<>();
            for (Ticket t: tickets){
                ticketBeans.add(ToBeanConverter.fromTicketToTicketBean(t));
                eventBean.setTicketsAvailability(t.getType(), event.getTicketAvailability(t.getType()));
            }
            return ticketBeans;
        } catch (DAOException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        } catch (IncorrectDataException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public void sendReservation(EventBean eventBean, BookingBean bookingBean, TicketBean ticketBean) throws OperationFailedException, DuplicateEntryException {

        try {

            checkBookingValid(bookingBean, eventBean, ticketBean);

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
                Double amount = ticketBean.getPrice();
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

            bookingBean.setCodeBooking(booking.getCodeBooking());
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

    private void checkBookingValid(BookingBean bookingBean, EventBean eventBean, TicketBean ticketBean) throws OperationFailedException {
        if (Boolean.TRUE.equals(eventBean.getClosed())){
            throw new OperationFailedException("Bookings are closed.");
        }

        if (ticketBean.getTypeName().equals(bookingBean.getTicketType())) {
            if (bookingBean.getAge() < ticketBean.getMinimumAge()) {
                throw new OperationFailedException("Invalid age!");
            }
        }else {
            String msg = "Inconsistent data. Ticket type does not match the booking type.";
            Logger.getGlobal().log(Level.SEVERE, msg);
            throw new OperationFailedException();
        }

    }
}
