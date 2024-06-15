package com.privateevents.controller.gui.fx;

import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.fx.FilesFXML;
import com.privateevents.utils.view.fx.PageManagerSingleton;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class AbstractGUIControllerFX {

    protected Integer currentSession;

    @FXML
    Label errorMsg;

    @FXML
    Button home;

    @FXML
    Button login;

    public abstract void initialize(Integer session)  throws OperationFailedException, NotFoundException;

    public void setMsg(Label label, String msg){
        label.setText(msg);
        label.setVisible(true);
    }

    public void resetMsg(Label ... labels){
        for(Label label: labels){
            label.setVisible(false);
        }
    }

    public void goNext(String path){
        resetMsg(errorMsg);
        try{
            PageManagerSingleton.getInstance().goNext(path, currentSession);
        }catch (OperationFailedException | NotFoundException e){
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void goHome(){
        resetMsg(errorMsg);
        SessionManager.getSessionManager().getSessionFromId(currentSession).softReset();
        try{
            PageManagerSingleton.getInstance().goHome(currentSession);
        }catch (OperationFailedException | NotFoundException e){
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void logout(){
        resetMsg(errorMsg);
        SessionManager.getSessionManager().getSessionFromId(currentSession).reset();
        try{
            PageManagerSingleton.getInstance().setHome(FilesFXML.HOME.getPath(), currentSession);
        }catch (OperationFailedException | NotFoundException e){
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void login(){
        goNext(FilesFXML.LOGIN.getPath());
    }
}
