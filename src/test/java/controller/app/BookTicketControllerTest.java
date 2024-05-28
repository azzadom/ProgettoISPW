package controller.app;

import bean.BookingBean;
import bean.EventBean;
import bean.TicketBean;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.OperationFailedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTicketControllerTest {

    @Test
    void findCityEvents() throws OperationFailedException{
        BookTicketController bookTicketController = new BookTicketController();
        List<EventBean> eventBeans = null;
        eventBeans = bookTicketController.findCityEvents("Milan");
        assertNotNull(eventBeans);
    }

    @Test
    void eventDetails() throws OperationFailedException {
        BookTicketController bookTicketController = new BookTicketController();
        List<EventBean> eventBeans = bookTicketController.findCityEvents("Milan");
        EventBean eventBean = bookTicketController.eventDetails(eventBeans.getFirst());
        List<TicketBean> ticketBeans = eventBean.getTickets();
        assertFalse(ticketBeans.isEmpty());
    }

    @Test
    void sendReservation() throws OperationFailedException, IncorrectDataException, DuplicateEntryException {
        BookTicketController bookTicketController = new BookTicketController();
        EventBean eventBean = bookTicketController.findCityEvents("Milan").getFirst();
        eventBean = bookTicketController.eventDetails(eventBean);
        TicketBean ticketBean = eventBean.getTickets().getFirst();
        BookingBean bookingBean = new BookingBean();
        bookingBean.setIdEvent(eventBean.getIdEvent());
        bookingBean.setEmail("mimmo@gmail.com");
        bookingBean.setTelephone("+393895203040");
        bookingBean.setTicketType(ticketBean.getTypeName());
        bookingBean.setFirstName("Mimmo");
        bookingBean.setLastName("Bianchi");
        bookingBean.setAge(25);
        bookingBean.setGender('M');
        bookingBean.setOnlinePayment(false);
        String code = bookTicketController.sendReservation(eventBean, bookingBean);
        assertNotNull(code);
    }
}