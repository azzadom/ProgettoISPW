package controller.gui.two;

import bean.EventBean;
import engineering.Session;
import view.two.EventDetailsView;

public class EventDetailsGUIController extends AbstractGUIController{

    private final EventDetailsView eventDetailsView = new EventDetailsView();
    private final EventBean event;

    public EventDetailsGUIController(Session session, EventBean event) {
        this.currentSession = session;
        this.event = event;
    }

    @Override
    public void start() {
        int choice;
        choice = eventDetailsView.showMenu();

        switch(choice) {
            case 1 -> showInfo();
            case 2 -> bookTicket();
            case 3 -> bookingManagement();
            case 4 -> goBack();
            case 5 -> goHome();
            case 6 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
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
        BookingGUIController bookingGUIController = new BookingGUIController(currentSession, event);
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
