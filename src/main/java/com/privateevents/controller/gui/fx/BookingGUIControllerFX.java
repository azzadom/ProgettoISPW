package com.privateevents.controller.gui.fx;

import com.privateevents.bean.BookingBean;
import com.privateevents.bean.EventBean;
import com.privateevents.bean.TicketBean;
import com.privateevents.controller.app.BookTicketController;
import com.privateevents.utils.view.SessionManager;
import com.privateevents.utils.view.fx.PageManagerSingleton;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class BookingGUIControllerFX extends AbstractGUIControllerFX {

    @FXML
    Label sold1;

    @FXML
    Label sold2;

    @FXML
    Label sold3;

    @FXML
    BorderPane ticket1;

    @FXML
    BorderPane ticket2;

    @FXML
    BorderPane ticket3;

    @FXML
    VBox type1;

    @FXML
    VBox type2;

    @FXML
    VBox type3;

    @FXML
    RadioButton radio1;

    @FXML
    RadioButton radio2;

    @FXML
    RadioButton radio3;

    @FXML
    Button back;

    @FXML
    Button confirm;

    @FXML
    TextField firstname;

    @FXML
    TextField lastname;

    @FXML
    TextField email;

    @FXML
    TextField telephone;

    @FXML
    TextField age;

    @FXML
    TextField gender;

    @FXML
    RadioButton paypalRadio;

    @FXML
    RadioButton onSiteRadio;

    @FXML
    Label message;

    ToggleGroup paymentMethod;

    ToggleGroup ticketSelection;

    EventBean event;

    List<TicketBean> tickets;

    @FXML
    public void goBack() {
        resetMsg(errorMsg, message);
        try {
            PageManagerSingleton.getInstance().goBack(currentSession);
        } catch (OperationFailedException | NotFoundException e) {
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void bookTicket() {
        resetMsg(errorMsg, message);

        TicketBean ticketChosen = null;
        RadioButton radio = (RadioButton) ticketSelection.getSelectedToggle();
        if(radio == radio1){
            ticketChosen = tickets.get(0);
        }else if(radio == radio2){
            ticketChosen = tickets.get(1);
        }else if(radio == radio3){
            ticketChosen = tickets.get(2);
        }

        try {
            BookingBean booking = getBooking(ticketChosen);
            BookTicketController bookTicketController = new BookTicketController();
            String code = bookTicketController.sendReservation(event, booking);
            setMsg(message, "Booking successful! Your booking code is: " + code);
        } catch (IncorrectDataException | DuplicateEntryException e) {
            setMsg(message, e.getMessage());
        } catch (OperationFailedException e) {
            setMsg(errorMsg,e.getMessage());
        }
    }

    private BookingBean getBooking(TicketBean ticketType) throws IncorrectDataException {
        try {
            String[] data = {firstname.getText(), lastname.getText(), email.getText(), telephone.getText(), age.getText(), gender.getText()};
            if (data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty() || data[3].isEmpty() || data[4].isEmpty() || data[5].isEmpty()) {
                throw new IncorrectDataException("All fields are required!");
            } else if (paymentMethod.getSelectedToggle() == null) {
                throw new IncorrectDataException("Please select a payment method!");
            } else if (ticketSelection.getSelectedToggle() == null) {
                throw new IncorrectDataException("Please select a ticket type!");
            }

            BookingBean booking = new BookingBean();
            booking.setFirstName(data[0]);
            booking.setLastName(data[1]);
            booking.setEmail(data[2]);
            booking.setTelephone(data[3]);
            booking.setAge(Integer.parseInt(data[4]));
            booking.setGender(data[5].charAt(0));
            booking.setOnlinePayment(paypalRadio.isSelected());
            booking.setTicketType(ticketType.getTypeName());
            this.setTickets();
            return booking;
        } catch (NumberFormatException e) {
            throw new IncorrectDataException("Invalid age!");
        }
    }

    public void initialize(Integer session) throws OperationFailedException {
        this.currentSession = session;
        resetMsg(errorMsg, message, sold1, sold2, sold3);
        ticket1.setVisible(false);
        ticket2.setVisible(false);
        ticket3.setVisible(false);

        event = SessionManager.getSessionManager().getSessionFromId(session).getEvent();
        tickets = event.getTickets();
        if(tickets.isEmpty()) {
            throw new OperationFailedException("No tickets available for this event.");
        } else if (tickets.size() > 3) {
            throw new OperationFailedException();
        } else {
            paymentMethod = new ToggleGroup();
            paypalRadio.setToggleGroup(paymentMethod);
            onSiteRadio.setToggleGroup(paymentMethod);
            ticketSelection = new ToggleGroup();
            radio1.setToggleGroup(ticketSelection);
            radio2.setToggleGroup(ticketSelection);
            radio3.setToggleGroup(ticketSelection);
            setTickets();
        }

    }

    private void setTickets() {
        for (int i = 0; i < tickets.size(); i++) {
            TicketBean ticket = tickets.get(i);
            if (i == 0) {
                setTicketLabels(ticket1, type1, ticket.getTypeName(), ticket.getPrice().toString(), ticket.getDescription(), ticket.getMinimumAge());
                if (event.getTicketsAvailability(ticket.getTypeName()) == 0) {
                    sold1.setVisible(true);
                    radio1.setVisible(false);
                }
            } else if (i == 1) {
                setTicketLabels(ticket2, type2, ticket.getTypeName(), ticket.getPrice().toString(), ticket.getDescription(), ticket.getMinimumAge());
                if (event.getTicketsAvailability(ticket.getTypeName()) == 0) {
                    sold2.setVisible(true);
                    radio2.setVisible(false);
                }
            } else if (i == 2) {
                setTicketLabels(ticket3, type3, ticket.getTypeName(), ticket.getPrice().toString(), ticket.getDescription(), ticket.getMinimumAge());
                if (event.getTicketsAvailability(ticket.getTypeName()) == 0) {
                    sold3.setVisible(true);
                    radio3.setVisible(false);
                }
            }
        }
    }

    private void setTicketLabels(BorderPane pane, VBox box, String type, String price, String description, int age) {
        pane.setVisible(true);
        ObservableList<Node> elements = box.getChildren();
        ((Label) elements.get(0)).setText(type);
        ((Label) elements.get(1)).setText("Price: " + price + "$\n" +
                "Description: " + description + "\n" + "Minimum age: " + age);
    }
}
