package controller.gui.cli;

import engineering.Session;
import view.cli.HomeView;

public class HomeGUIControllerCLI extends AbstractGUIControllerCLI {

    private final HomeView view = new HomeView();

    public HomeGUIControllerCLI(Session session){
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
        LoginAndRegisterGUIControllerCLI loginAndRegisterGUIController = new LoginAndRegisterGUIControllerCLI(currentSession);
        loginAndRegisterGUIController.start();
        currentSession.setReturningHome(false);
        start();
    }

    private void searchEvents(){
        String city = view.searchEvent();
        ListEventsGUIControllerCLI listEventsGUIController = new ListEventsGUIControllerCLI(currentSession, city);
        listEventsGUIController.start();
        currentSession.setReturningHome(false);
        start();
    }
}
