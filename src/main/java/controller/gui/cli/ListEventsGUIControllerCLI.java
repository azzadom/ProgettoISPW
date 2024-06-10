package controller.gui.cli;

import bean.EventBean;
import controller.app.BookTicketController;
import engineering.Session;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.cli.ListEventsView;

import java.util.List;

public class ListEventsGUIControllerCLI extends AbstractGUIControllerCLI {

    private final ListEventsView listEventsView = new ListEventsView();
    private List<EventBean> events;
    private final String city;

    ListEventsGUIControllerCLI(Session session, String city){
        this.currentSession = session;
        this.city = city;
    }

    @Override
    public void start() {
        BookTicketController bookTicketController = new BookTicketController();

        try {
            events = bookTicketController.findCityEvents(city);

            int choice;
            choice = listEventsView.showMenu();

            switch(choice) {
                case 1 -> showEvents();
                case 2 -> selectEvent();
                case 3 -> goHome();
                case 4 -> exit();
                default -> throw new IllegalArgumentException("Invalid case!");
            }

        } catch (OperationFailedException e) {
            listEventsView.showError(e.getMessage());
        } catch (NotFoundException e) {
            listEventsView.showMessage(e.getMessage());
        }
    }

    private void showEvents() {
        String[] evs = new String[this.events.size()];
        int i = 0;
        for (EventBean event : this.events) {
            evs[i] = String.format("%d - Event name: %s, Event date: %s, Event organizer: %s%n",i+1, event.getName(), event.getDate(), event.getOrgName());
            i++;
        }
        listEventsView.showEvents(evs);
        start();
    }

    private void selectEvent() {
        int num = listEventsView.selectEvent();
        if (num < 1 || num > events.size()){
            listEventsView.showMessage("Event not found!");
            start();
        } else {
            EventBean event = events.get(num - 1);
            if (event != null) {
                EventDetailsGUIControllerCLI eventDetailsGUIController = new EventDetailsGUIControllerCLI(currentSession, event);
                eventDetailsGUIController.start();
            }
            if (Boolean.FALSE.equals(currentSession.getReturningHome())) {
                start();
            }
        }
    }

}
