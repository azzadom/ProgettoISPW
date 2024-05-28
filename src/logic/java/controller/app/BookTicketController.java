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
import exception.dao.DAOException;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.OperationFailedException;
import model.*;

import static exception.dao.TypeDAOException.*;

public class BookTicketController {

    public List<EventBean> findCityEvents(String city) throws OperationFailedException {
        try {
            EventDAO eventDAO = FactorySingletonDAO.getDefaultDAO().getEventDAO();
            List<Event> events = eventDAO.selectEventsByCity(city);
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

    public EventBean eventDetails(EventBean eventBean) throws OperationFailedException {
        try {
            Event event = FactorySingletonDAO.getDefaultDAO().getEventDAO().selectEvent(eventBean.getIdEvent());
            List<Ticket> tickets = FactorySingletonDAO.getDefaultDAO().getTicketDAO().selectTickets(eventBean.getIdEvent());
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
            }else {
                Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
                throw new OperationFailedException();
            }
        }
    }

}
