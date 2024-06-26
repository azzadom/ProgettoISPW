package com.privateevents.controller.gui.fx;

import com.privateevents.utils.view.fx.FilesFXML;
import com.privateevents.utils.view.fx.PageManagerSingleton;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.privateevents.bean.EventBean;
import com.privateevents.utils.SessionManager;


public class EventDetailsGUIControllerFX extends AbstractGUIControllerFX {

    @FXML
    Label title;

    @FXML
    Label info;

    @FXML
    Label details;

    @FXML
    Button back;

    @FXML
    Button book;

    @FXML
    Button management;

    EventBean event;

    @FXML
    public void goBack() {
        resetMsg(errorMsg);
        try {
            SessionManager.getSessionManager().getSessionFromId(currentSession).setEvent(null);
            PageManagerSingleton.getInstance().goBack(currentSession);
        } catch (OperationFailedException | NotFoundException e) {
            setMsg(errorMsg,e.getMessage());
        }
    }

    @FXML
    public void bookTicket() {
        EventBean eventBean = SessionManager.getSessionManager().getSessionFromId(currentSession).getEvent();
        if (Boolean.TRUE.equals(eventBean.getClosed())){
            setMsg(errorMsg,"Event is closed for booking.");
        }else {
            goNext(FilesFXML.BOOKING.getPath());
        }
    }

    @FXML
    public void bookManagement() {
        setMsg(errorMsg,"Not implemented yet.");
    }

    public void initialize(Integer session) {
        this.currentSession = session;
        resetMsg(errorMsg);

        event = SessionManager.getSessionManager().getSessionFromId(currentSession).getEvent();

        title.setText(event.getName());
        info.setText("Date: " + event.getDate() + " | " + "Start Time: " + event.getTime() + "\n"
                + "Location: " + event.getLocationName() + " (" + event.getAddress() + ")");
        details.setText(event.getDescription());
    }
}

