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
import javafx.scene.input.KeyEvent;

public class HomeGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Label message;

    @FXML
    Button searchButton;

    @FXML
    TextField searchBar;

    @FXML
    public void searchCityEnter(KeyEvent event){
        if(event.getCode().toString().equals("ENTER")) {
            searchCity();
        }
    }

    @FXML
    public void searchCity(){

        resetMsg(errorMsg, message);

        String city = searchBar.getText();
        if(city.isEmpty()){
            setMsg(message,"Insert a city to search for events!");
        }else{
            try{
            SessionManager.getSessionManager().getSessionFromId(currentSession).setCity(city);
            PageManagerSingleton.getInstance().goNext(FilesFXML.LIST_EVENTS.getPath(), currentSession);
            }catch (OperationFailedException e){
                setMsg(errorMsg,e.getMessage());
            }catch (NotFoundException e){
                setMsg(message,e.getMessage());
            }
        }
    }

    @Override
    public void initialize(Integer session) {
        this.currentSession = session;
        resetMsg(errorMsg, message);
    }

}

