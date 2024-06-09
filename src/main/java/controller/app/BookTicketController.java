package controller.app;

import bean.BookingBean;
import bean.EventBean;
import bean.TicketBean;
import engineering.ToBeanConverter;
import engineering.dao.factory.FactorySingletonDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.*;
import exception.NotFoundException;
import exception.dao.DAOException;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.OperationFailedException;
import model.*;

import static exception.dao.TypeDAOException.*;

public class BookTicketController {

    public List<EventBean> findCityEvents(String city) throws OperationFailedException, NotFoundException {
        try {
            EventDAO eventDAO = FactorySingletonDAO.getDefaultDAO().getEventDAO();
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
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        } catch (IncorrectDataException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public EventBean eventDetails(EventBean eventBean) throws OperationFailedException, NotFoundException {
        try {
            Event event = FactorySingletonDAO.getDefaultDAO().getEventDAO().selectEvent(eventBean.getIdEvent());
            if (event == null) {
                throw new NotFoundException("Event not found.");
            }
            List<Ticket> tickets = FactorySingletonDAO.getDefaultDAO().getTicketDAO().selectTickets(eventBean.getIdEvent());
            if (tickets.isEmpty()){
                String msg = "Inconsistent data. No tickets found for event: " + eventBean.getIdEvent();
                Logger.getAnonymousLogger().log(Level.SEVERE, msg);
                throw new OperationFailedException();
            }
            List<Booking> bookings = FactorySingletonDAO.getDefaultDAO().getBookingDAO().selectBooking(eventBean.getIdEvent());
            event.setTicketsAndBookings(tickets, bookings);
            eventBean = ToBeanConverter.fromEventToEventBeanWithoutBook(event);
            return eventBean;
        } catch (DAOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        } catch (IncorrectDataException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw new OperationFailedException();
        }
    }

    public String sendReservation(EventBean eventBean, BookingBean bookingBean) throws OperationFailedException, DuplicateEntryException {

        try {

            Booking booking = new Booking(bookingBean.getLastName(), bookingBean.getFirstName(), bookingBean.getAge(),
                    bookingBean.getGender(), bookingBean.getEmail(), bookingBean.getTelephone(),
                    bookingBean.getTicketType(), bookingBean.getOnlinePayment());

            OrganizerDAO organizerDAO = FactorySingletonDAO.getDefaultDAO().getOrganizerDAO();
            Organizer organizer = organizerDAO.selectOrganizer(eventBean.getOrgName());
            if (organizer == null) {
                String msg = "Inconsistent data. Organizer not found for event: " + eventBean.getIdEvent();
                Logger.getAnonymousLogger().log(Level.SEVERE, msg);
                throw new OperationFailedException();
            }

            BookingDAO bookingDAO = FactorySingletonDAO.getDefaultDAO().getBookingDAO();

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
            notificationsController.notifyOrganizer(
                    new Notification(TypeNotif.NEW, LocalDateTime.now(),
                            eventBean.getName(), booking.getCodeBooking()), organizer);

            return booking.getCodeBooking();
        } catch (DAOException e) {
            if (e.getTypeException().equals(DUPLICATE)) {
                throw new DuplicateEntryException(e.getMessage());
            } else if (e.getTypeException().equals(LIMIT_REACHED)) {
                throw new OperationFailedException("No tickets available for this type.");
            }else {
                Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
                throw new OperationFailedException();
            }
        }
    }

}
