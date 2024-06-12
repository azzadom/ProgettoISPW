package com.privateevents.controller.gui.cli;

import com.privateevents.bean.OrganizerBean;
import com.privateevents.bean.UserBean;
import com.privateevents.controller.app.LoginController;
import com.privateevents.utils.view.SessionManager;
import com.privateevents.utils.view.cli.ReturnigHome;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import com.privateevents.view.cli.LoginAndRegisterView;

public class LoginAndRegisterGUIControllerCLI extends AbstractGUIControllerCLI {

    private final LoginAndRegisterView view = new LoginAndRegisterView();

    public LoginAndRegisterGUIControllerCLI(Integer session, ReturnigHome returningHome){
        this.currentSession = session;
        this.returningHome = returningHome;
    }

    public void start(){
        int choice;
        choice = view.showMenu();

        switch(choice) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> goHome();
            case 4 -> exit();
            default -> throw new IllegalArgumentException("Invalid case!");
        }
    }

    private void login() {
        try {
            String [] loginInfo = view.login();
            if(loginInfo[0].isEmpty() || loginInfo[1].isEmpty()) {
                view.showMessage("Please fill in all fields!");
            }
            UserBean user = new UserBean();
            user.setUsername(loginInfo[0]);
            user.setPassword(loginInfo[1]);
            LoginController loginController = new LoginController();
            user = loginController.login(user);
            SessionManager.getSessionManager().getSessionFromId(currentSession).setUser(user);
            OrganizerHomeGUIControllerCLI organizerHomeGUIController = new OrganizerHomeGUIControllerCLI(currentSession,returningHome);
            organizerHomeGUIController.start();
        } catch (IncorrectDataException | NotFoundException e) {
            view.showMessage(e.getMessage());
        } catch (OperationFailedException e) {
            view.showError(e.getMessage());
        }
        if (Boolean.FALSE.equals(returningHome.getReturningHome())) {
            start();
        }
    }

    private void register() {
        try {
            String [] registerInfo = view.register();
            if(registerInfo[0].isEmpty() || registerInfo[1].isEmpty() || registerInfo[2].isEmpty() || registerInfo[3].isEmpty() || registerInfo[4].isEmpty() || registerInfo[5].isEmpty()) {
                view.showMessage("Please fill in all fields!");
            }
            OrganizerBean org = new OrganizerBean();
            org.setFirstName(registerInfo[0]);
            org.setLastName(registerInfo[1]);
            org.setEmail(registerInfo[2]);
            org.setInfoPayPal(registerInfo[3]);
            org.setUsername(registerInfo[4]);
            org.setPassword(registerInfo[5]);
            LoginController loginController = new LoginController();
            UserBean user = loginController.register(org);
            SessionManager.getSessionManager().getSessionFromId(currentSession).setUser(user);
            OrganizerHomeGUIControllerCLI organizerHomeGUIController = new OrganizerHomeGUIControllerCLI(currentSession, returningHome);
            organizerHomeGUIController.start();
        } catch (IncorrectDataException e) {
            view.showMessage(e.getMessage());
        } catch (OperationFailedException | DuplicateEntryException e) {
            view.showError(e.getMessage());
        }
        if (Boolean.FALSE.equals(returningHome.getReturningHome())) {
            start();
        }
    }

}
