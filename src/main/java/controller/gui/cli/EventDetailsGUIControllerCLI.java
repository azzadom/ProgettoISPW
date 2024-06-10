package controller.gui.cli;

import bean.EventBean;
import controller.app.BookTicketController;
import engineering.Session;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.cli.EventDetailsView;

public class EventDetailsGUIControllerCLI extends AbstractGUIControllerCLI {

    private final EventDetailsView eventDetailsView = new EventDetailsView();
    private EventBean event;

    public EventDetailsGUIControllerCLI(Session session, EventBean event) {
        this.currentSession = session;
        this.event = event;
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
        BookingGUIControllerCLI bookingGUIController = new BookingGUIControllerCLI(currentSession, event);
        bookingGUIController.start();
        if(Boolean.FALSE.equals(currentSession.getReturningHome())){
            start();
        }
    }

    private void bookingManagement() {
        eventDetailsView.showError("Booking management is not implemented yet!");
        start();
    }

}
