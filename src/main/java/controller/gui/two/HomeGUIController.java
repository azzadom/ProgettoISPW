package controller.gui.two;

import bean.EventBean;
import controller.app.BookTicketController;
import engineering.Session;
import exception.NotFoundException;
import exception.OperationFailedException;
import view.two.HomeView;

import java.util.List;

public class HomeGUIController extends AbstractGUIController {

    private final HomeView view = new HomeView();

    public HomeGUIController(Session session){
        this.currentSession = session;
    }

    public void start(){
        int choice;
        choice = view.showMenu();

        switch(choice) {
            case 1 -> searchEvents();
            case 2 -> loginPage();
            case 3 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
        }
    }

    private void loginPage(){
        LoginAndRegisterGUIController loginAndRegisterGUIController = new LoginAndRegisterGUIController(currentSession);
        loginAndRegisterGUIController.start();
        currentSession.setReturningHome(false);
        start();
    }

    private void searchEvents(){

        List<EventBean> events = null;

        String city = view.searchEvent();
        BookTicketController bookTicketController = new BookTicketController();
        try {
            events = bookTicketController.findCityEvents(city);
            ListEventsGUIController listEventsGUIController = new ListEventsGUIController(currentSession, events);
            listEventsGUIController.start();
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
        } catch (NotFoundException e) {
            view.showMessage(e.getMessage());
        }
        currentSession.setReturningHome(false);
        start();
    }
}
