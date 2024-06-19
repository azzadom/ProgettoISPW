package com.privateevents.controller.gui.cli;

import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;
import com.privateevents.view.cli.HomeView;

public class HomeGUIControllerCLI extends AbstractGUIControllerCLI {

    private final HomeView view = new HomeView();

    public HomeGUIControllerCLI(Integer session, ReturnigHome returningHome){
        this.currentSession = session;
        this.returningHome = returningHome;
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
        LoginAndRegisterGUIControllerCLI loginAndRegisterGUIController = new LoginAndRegisterGUIControllerCLI(currentSession, returningHome);
        loginAndRegisterGUIController.start();
        returningHome.setReturningHome(false);
        start();
    }

    private void searchEvents(){
        String city = view.searchEvent();
        SessionManager.getSessionManager().getSessionFromId(currentSession).setCity(city);
        ListEventsGUIControllerCLI listEventsGUIController = new ListEventsGUIControllerCLI(currentSession, returningHome);
        listEventsGUIController.start();
        SessionManager.getSessionManager().getSessionFromId(currentSession).resetCity();
        returningHome.setReturningHome(false);
        start();
    }
}
