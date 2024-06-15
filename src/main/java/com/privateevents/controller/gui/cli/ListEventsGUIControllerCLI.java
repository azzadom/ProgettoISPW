package com.privateevents.controller.gui.cli;

import com.privateevents.bean.EventBean;
import com.privateevents.controller.app.BookTicketController;
import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import com.privateevents.view.cli.ListEventsView;

import java.util.List;

public class ListEventsGUIControllerCLI extends AbstractGUIControllerCLI {

    private final ListEventsView listEventsView = new ListEventsView();
    private List<EventBean> events;
    private final String city;

    ListEventsGUIControllerCLI(Integer session, ReturnigHome returningHome){
        this.currentSession = session;
        this.city = SessionManager.getSessionManager().getSessionFromId(session).getCity();
        this.returningHome = returningHome;
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
                SessionManager.getSessionManager().getSessionFromId(currentSession).setEvent(event);
                EventDetailsGUIControllerCLI eventDetailsGUIController = new EventDetailsGUIControllerCLI(currentSession, returningHome);
                eventDetailsGUIController.start();
            }
            SessionManager.getSessionManager().getSessionFromId(currentSession).resetEvent();
            if (Boolean.FALSE.equals(returningHome.getReturningHome())) {
                start();
            }
        }
    }

}
