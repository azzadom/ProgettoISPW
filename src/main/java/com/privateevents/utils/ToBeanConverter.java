package com.privateevents.utils;

import com.privateevents.bean.*;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.model.*;


import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ToBeanConverter {

    private ToBeanConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static EventBean fromEventToEventBeanWithoutBook(Event event) throws IncorrectDataException {
        EventBean eventBean = new EventBean();
        eventBean.setIdEvent(event.getIdEvent());
        eventBean.setName(event.getName());
        eventBean.setCity(event.getCity());
        eventBean.setAddress(event.getAddress());
        eventBean.setDate(event.getDate().toString());
        eventBean.setTime(event.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        eventBean.setDescription(event.getDescription());
        eventBean.setLocationName(event.getLocation());
        eventBean.setOrgName(event.getOrgUsername());
        eventBean.setClosed(event.getBookingClosed());

        List<Ticket> tickets = event.getTickets();
        if(tickets != null) {
            List<TicketBean> ticketBeans = new ArrayList<>();
            for (Ticket ticket : tickets) {
                ticketBeans.add(fromTicketToTicketBean(ticket));
                eventBean.setTicketsAvailability(ticket.getType(), event.getTicketAvailability(ticket.getType()));
            }
            eventBean.setTickets(ticketBeans);
        }

        return eventBean;
    }

    public static NotificationBean fromNotificationToNotificationBean(Notification notification) {
        NotificationBean notificationBean = new NotificationBean();
        notificationBean.setType(notification.getType().toString());
        notificationBean.setName(notification.getEventName());
        notificationBean.setBookingCode(notification.getBookingCode());
        notificationBean.setDateAndTime(notification.getDateAndTime().atZone(ZoneId.systemDefault()));
        return notificationBean;
    }

    public static BookingBean fromBookingToBookingBean(Booking booking) throws IncorrectDataException {
        BookingBean bookingBean = new BookingBean();
        bookingBean.setLastName(booking.getLastname());
        bookingBean.setFirstName(booking.getFirstname());
        bookingBean.setAge(booking.getAge());
        bookingBean.setGender(booking.getGender());
        bookingBean.setEmail(booking.getEmail());
        bookingBean.setTelephone(booking.getTelephone());
        bookingBean.setTicketType(booking.getTicketType());
        bookingBean.setOnlinePayment(booking.getOnlinePayment());
        return bookingBean;
    }

    public static TicketBean fromTicketToTicketBean(Ticket ticket) throws IncorrectDataException {
        TicketBean ticketBean = new TicketBean();
        ticketBean.setTypeName(ticket.getType());
        ticketBean.setPrice(ticket.getPrice());
        ticketBean.setMinimumAge(ticket.restriction());
        ticketBean.setDescription(ticket.getDescription());
        return ticketBean;
    }

    public static OrganizerBean fromOrganizerToOrganizerBean(Organizer organizer) throws IncorrectDataException {
        OrganizerBean organizerBean = new OrganizerBean();
        organizerBean.setUsername(organizer.getUsername());
        organizerBean.setFirstName(organizer.getFirstName());
        organizerBean.setLastName(organizer.getLastName());
        organizerBean.setEmail(organizer.getEmail());
        organizerBean.setInfoPayPal(organizer.getInfoPayPal());

        List<Event> events = organizer.getEvents();
        if(events != null) {
            List<EventBean> eventBeans = new ArrayList<>();
            for (Event event : events) {
                eventBeans.add(fromEventToEventBeanWithoutBook(event));
            }
            organizerBean.setEvents(eventBeans);

            List<Notification> notifications = organizer.getNewNotif();
            List<NotificationBean> notificationBeans = new ArrayList<>();
            for (Notification notification : notifications) {
                notificationBeans.add(fromNotificationToNotificationBean(notification));
            }
            organizerBean.setNotifs(notificationBeans);
        }

        return organizerBean;
    }

}
