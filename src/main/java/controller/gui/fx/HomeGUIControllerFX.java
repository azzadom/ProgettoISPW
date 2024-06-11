package controller.gui.fx;


import engineering.view.SessionManager;
import engineering.view.fx.FilesFXML;
import engineering.view.fx.PageManagerSingleton;
import exception.NotFoundException;
import exception.OperationFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Label message;

    @FXML
    Button searchButton;

    @FXML
    TextField searchBar;

    public void searchCity(){

        resetMsg(errorMsg, message);

        String city = searchBar.getText();
        if(city.isEmpty()){
            message.setText("Insert a city to search for events!");
            message.setVisible(true);
        }else{
            try{
            SessionManager.getSessionManager().getSessionFromId(currentSession).setCity(city);
            PageManagerSingleton.getInstance().goNext(FilesFXML.LIST_EVENTS.getPath(), currentSession);
            }catch (OperationFailedException e){
                setErrorMsg(e.getMessage());
            }catch (NotFoundException e){
                message.setText(e.getMessage());
                message.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(Integer session) {
        this.currentSession = session;
        resetMsg(errorMsg, message);
    }

}

