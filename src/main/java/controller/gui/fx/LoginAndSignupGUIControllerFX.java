package controller.gui.fx;

import bean.OrganizerBean;
import bean.UserBean;
import controller.app.LoginController;
import engineering.view.SessionManager;
import engineering.view.fx.FilesFXML;
import engineering.view.fx.PageManagerSingleton;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.NotFoundException;
import exception.OperationFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginAndSignupGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Label loginMessage;

    @FXML
    Label signupMessage;

    @FXML
    TextField userLogin;

    @FXML
    PasswordField passLogin;

    @FXML
    TextField user;

    @FXML
    PasswordField password;

    @FXML
    TextField firstname;

    @FXML
    TextField lastname;

    @FXML
    TextField email;

    @FXML
    TextField paypal;

    @FXML
    public void loginAction() {
        resetMsg(errorMsg, loginMessage, signupMessage);

        String [] loginInfo = {userLogin.getText(), passLogin.getText()};
        if(loginInfo[0].isEmpty() || loginInfo[1].isEmpty()) {
            setMsg(loginMessage, "Please fill in all fields!");
            return;
        }
        try{
            UserBean userBean = new UserBean();
            userBean.setUsername(loginInfo[0]);
            userBean.setPassword(loginInfo[1]);
            LoginController loginController = new LoginController();
            userBean = loginController.login(userBean);
            SessionManager.getSessionManager().getSessionFromId(currentSession).setUser(userBean);
            PageManagerSingleton.getInstance().setHome(FilesFXML.ORGANIZER_HOME.getPath(), currentSession);
        } catch (IncorrectDataException | NotFoundException e) {
            setMsg(loginMessage, e.getMessage());
        } catch (OperationFailedException e) {
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void signupAction(){
        resetMsg(errorMsg, loginMessage, signupMessage);
        
        String [] strings = {firstname.getText(), lastname.getText(), email.getText(), paypal.getText(), user.getText(), password.getText()};
        if(strings[0].isEmpty() || strings[1].isEmpty() || strings[2].isEmpty() || strings[3].isEmpty() || strings[4].isEmpty() || strings[5].isEmpty()) {
            setMsg(signupMessage, "Please fill in all fields!");
            return;
        }
        try{
            OrganizerBean organizerBean = new OrganizerBean();
            organizerBean.setFirstName(strings[0]);
            organizerBean.setLastName(strings[1]);
            organizerBean.setEmail(strings[2]);
            organizerBean.setInfoPayPal(strings[3]);
            organizerBean.setUsername(strings[4]);
            organizerBean.setPassword(strings[5]);
            LoginController loginController = new LoginController();
            UserBean userBean = loginController.register(organizerBean);
            SessionManager.getSessionManager().getSessionFromId(currentSession).setUser(userBean);
            PageManagerSingleton.getInstance().setHome(FilesFXML.ORGANIZER_HOME.getPath(), currentSession);
        } catch (IncorrectDataException | DuplicateEntryException e) {
            setMsg(signupMessage, e.getMessage());
        } catch (OperationFailedException | NotFoundException e) {
            setMsg(errorMsg,e.getMessage());
        }
    }


    @Override
    public void initialize(Integer session) throws OperationFailedException, NotFoundException {
        resetMsg(errorMsg, loginMessage, signupMessage);
        currentSession = session;
    }
}
