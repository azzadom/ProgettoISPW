package controller.gui.two;

import bean.EventBean;
import controller.app.BookTicketController;
import engineering.Session;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.two.ListEventsView;

import java.util.List;

public class ListEventsGUIController extends AbstractGUIController {

    private final ListEventsView listEventsView = new ListEventsView();
    private final List<EventBean> events;

    ListEventsGUIController(Session session,List<EventBean> events){
        this.currentSession = session;
        this.events = events;
    }

    @Override
    public void start() {
            int choice;
            choice = listEventsView.showMenu();

            switch(choice) {
                case 1 -> showEvents();
                case 2 -> selectEvent();
                case 3 -> goHome();
                case 4 -> exit();
                default -> throw new IllegalArgumentException("Invalid case!");
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
        }

        EventBean event = events.get(num-1);
        if (event != null) {
            BookTicketController controller = new BookTicketController();
            try {
                event = controller.eventDetails(event);
            } catch (OperationFailedException e) {
                listEventsView.showError(e.getMessage());
                start();
            } catch (NotFoundException e) {
                listEventsView.showMessage(e.getMessage());
                start();
            }
            EventDetailsGUIController eventDetailsGUIController = new EventDetailsGUIController(currentSession, event);
            eventDetailsGUIController.start();
        }
        if(Boolean.FALSE.equals(currentSession.getReturningHome())){
            start();
        }
    }

}
