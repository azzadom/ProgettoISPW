package com.privateevents.controller.gui.cli;

import com.privateevents.bean.EventBean;
import com.privateevents.controller.app.BookTicketController;
import com.privateevents.utils.view.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import com.privateevents.view.cli.EventDetailsView;

public class EventDetailsGUIControllerCLI extends AbstractGUIControllerCLI {

    private final EventDetailsView eventDetailsView = new EventDetailsView();
    private EventBean event;

    public EventDetailsGUIControllerCLI(Integer session, ReturnigHome returnigHome) {
        this.currentSession = session;
        this.event = SessionManager.getSessionManager().getSessionFromId(session).getEvent();
        this.returningHome = returnigHome;
    }

    @Override
    public void start() {
        try {
            BookTicketController controller = new BookTicketController();
            event = controller.eventDetails(event);

            int choice;
            choice = eventDetailsView.showMenu();

            switch (choice) {
                case 1 -> showInfo();
                case 2 -> bookTicket();
                case 3 -> bookingManagement();
                case 4 -> goBack();
                case 5 -> goHome();
                case 6 -> exit();
                default -> throw new IllegalArgumentException("Invalid case!");

            }
        }catch(OperationFailedException e){
            eventDetailsView.showError(e.getMessage());
            start();
        } catch(NotFoundException e){
            eventDetailsView.showMessage(e.getMessage());
            start();
        }
    }

    private void showInfo() {
        String[] info = {
            "Event Name: " + event.getName(),
            "Event Date: " + event.getDate(),
            "Event Time: " + event.getTime() + " (Local Time)",
            "Event Location: " + event.getLocationName(),
            "Event Description\n" + event.getDescription(),
        };
        eventDetailsView.showInfo(info);
        start();
    }

    private void bookTicket() {
        BookingGUIControllerCLI bookingGUIController = new BookingGUIControllerCLI(currentSession, returningHome);
        bookingGUIController.start();
        if(Boolean.FALSE.equals(returningHome.getReturningHome())){
            start();
        }
    }

    private void bookingManagement() {
        eventDetailsView.showError("Booking management is not implemented yet!");
        start();
    }

}