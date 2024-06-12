package com.privateevents.controller.gui.cli;

import com.privateevents.bean.BookingBean;
import com.privateevents.bean.EventBean;
import com.privateevents.bean.TicketBean;
import com.privateevents.controller.app.BookTicketController;
import com.privateevents.utils.view.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.OperationFailedException;
import com.privateevents.view.cli.BookingView;

import java.util.List;

public class BookingGUIControllerCLI extends AbstractGUIControllerCLI {

    private final BookingView bookingView = new BookingView();
    private final EventBean event;
    private final List<TicketBean> tickets;

    public BookingGUIControllerCLI(Integer session, ReturnigHome returningHome) {
        this.currentSession = session;
        this.event = SessionManager.getSessionManager().getSessionFromId(session).getEvent();
        this.tickets = event.getTickets();
        this.returningHome = returningHome;
    }

    @Override
    public void start() {
        int choice;
        choice = bookingView.showMenu();

        switch (choice) {
            case 1 -> showTickets();
            case 2 -> bookTicket();
            case 3 -> goBack();
            case 4 -> goHome();
            case 5 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
        }
    }

    private void showTickets() {
        String[] ts = new String[event.getTickets().size()];
        int i = 0;
        for (TicketBean t : tickets) {
            if (event.getTicketsAvailability(t.getTypeName()) == 0) {
                ts[i] = String.format("%d - Ticket type: %s, Ticket price: %s$,Ticket Description: [SOLD OUT]%n", i + 1, t.getTypeName(), t.getPrice());
                i++;
            } else {
                ts[i] = String.format("%d - Ticket type: %s, Ticket price: %s$, Ticket Description: %s (Minimum Age: %d)%n", i + 1, t.getTypeName(), t.getPrice(), t.getDescription(), t.getMinimumAge());
                i++;
            }
        }
        bookingView.showTickets(ts);
        start();
    }

    private void bookTicket() {
        try {
            String[] data = bookingView.insertData();
            BookingBean booking = new BookingBean();

            if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty() || data[3].isEmpty() || data[4].isEmpty() || data[5].isEmpty() || data[6].isEmpty()){
                throw new IncorrectDataException("All fields are required!");
            }
            if((Integer.parseInt(data[6]) - 1) < 0 || (Integer.parseInt(data[6]) - 1) >= tickets.size()){
                throw new IncorrectDataException("Invalid ticket type!");
            }

            if (Integer.parseInt(data[2]) <  tickets.get(Integer.parseInt(data[6]) - 1).getMinimumAge()){
                throw new IncorrectDataException("Invalid age!");
            }

            booking.setTicketType(tickets.get(Integer.parseInt(data[6]) - 1).getTypeName());
            if (event.getTicketsAvailability(booking.getTicketType()) == 0) {
                throw new OperationFailedException("No tickets available for this type.");
            }
            booking.setFirstName(data[0]);
            booking.setLastName(data[1]);
            booking.setAge(Integer.parseInt(data[2]));
            booking.setGender(data[3].charAt(0));
            booking.setEmail(data[4]);
            booking.setTelephone(data[5]);
            booking.setOnlinePayment(Boolean.valueOf(data[7]));
            booking.setTicketType(tickets.get(Integer.parseInt(data[6]) - 1).getTypeName());

            BookTicketController controller = new BookTicketController();
            String bookingCode = controller.sendReservation(event, booking);
            bookingView.showMessage("Booking successful! Your booking code is: " + bookingCode);
        } catch (OperationFailedException | DuplicateEntryException e) {
            bookingView.showError(e.getMessage());
        }catch (IncorrectDataException e){
            bookingView.showMessage(e.getMessage());
        }
        start();
    }
}
