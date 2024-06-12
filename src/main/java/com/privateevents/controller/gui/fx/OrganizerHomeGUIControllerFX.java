package com.privateevents.controller.gui.fx;

import com.privateevents.utils.view.fx.FilesFXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OrganizerHomeGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Button notifications;

    @FXML
    Button events;

    @FXML
    Button settings;

    @FXML
    public void viewNotifications(){
        goNext(FilesFXML.NOTIFICATION.getPath());
    }

    @FXML
    public void viewEvents(){
        setMsg(errorMsg,"Not implemented yet.");
    }

    @FXML
    public void viewSettings(){
        setMsg(errorMsg,"Not implemented yet.");
    }

    public void initialize(Integer session) {
        resetMsg(errorMsg);
        this.currentSession = session;
    }
}
