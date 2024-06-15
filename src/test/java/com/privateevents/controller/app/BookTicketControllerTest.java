package com.privateevents.controller.app;

import com.privateevents.bean.BookingBean;
import com.privateevents.bean.EventBean;
import com.privateevents.bean.TicketBean;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTicketControllerTest {

    @Test
    void findCityEvents() throws OperationFailedException, NotFoundException {
        System.setProperty("DAO_TYPE", "JDBC");
        BookTicketController bookTicketController = new BookTicketController();
        List<EventBean> eventBeans = null;
        eventBeans = bookTicketController.findCityEvents("Milan");
        assertNotEquals(new ArrayList<>(),eventBeans);
    }

    @Test
    void getEventTickets() throws OperationFailedException, NotFoundException {
        System.setProperty("DAO_TYPE", "JDBC");
        BookTicketController bookTicketController = new BookTicketController();
        List<EventBean> eventBeans = bookTicketController.findCityEvents("Milan");
        List<TicketBean> ticketBeans = bookTicketController.getEventTickets(eventBeans.getFirst());
        assertFalse(ticketBeans.isEmpty());
    }

    @Test
    void sendReservation() throws OperationFailedException, IncorrectDataException, DuplicateEntryException, NotFoundException {
        System.setProperty("DAO_TYPE", "JDBC");
        BookTicketController bookTicketController = new BookTicketController();
        EventBean eventBean = bookTicketController.findCityEvents("Milan").getFirst();
        List<TicketBean> ticketBeans = bookTicketController.getEventTickets(eventBean);
        TicketBean ticketChoosen = ticketBeans.get(0);
        BookingBean bookingBean = new BookingBean();
        bookingBean.setIdEvent(eventBean.getIdEvent());
        bookingBean.setEmail("bianchi.mimmo@gmail.com");
        bookingBean.setTelephone("+393899903040");
        bookingBean.setTicketType(ticketChoosen.getTypeName());
        bookingBean.setFirstName("Mimmo");
        bookingBean.setLastName("Bianchi");
        bookingBean.setAge(25);
        bookingBean.setGender('M');
        bookingBean.setOnlinePayment(false);
        bookTicketController.sendReservation(eventBean, bookingBean,ticketChoosen);
        assertNotNull(bookingBean.getCodeBooking());
    }
}