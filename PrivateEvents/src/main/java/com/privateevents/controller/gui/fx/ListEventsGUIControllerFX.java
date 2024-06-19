package com.privateevents.controller.gui.fx;

import com.privateevents.bean.EventBean;
import com.privateevents.controller.app.BookTicketController;
import com.privateevents.utils.SessionManager;
import com.privateevents.utils.view.fx.FilesFXML;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListEventsGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Pagination numberPage;

    @FXML
    VBox eventCard1;

    @FXML
    VBox eventCard2;

    @FXML
    VBox eventCard3;

    @FXML
    VBox eventCard4;

    @FXML
    VBox eventCard5;

    @FXML
    VBox eventCard6;

    VBox[] eventCards;

    List<EventBean> events;

    @FXML
    public void selectEvent(ActionEvent event) {
        Button button = (Button) event.getSource();
        String eventName = button.getText();
        EventBean eventBean = searchEvent(eventName);
        SessionManager.getSessionManager().getSessionFromId(currentSession).setEvent(eventBean);
        goNext(FilesFXML.EVENT.getPath());
    }

    public void initialize(Integer session) throws OperationFailedException, NotFoundException{
        this.currentSession = session;
        eventCards = new VBox[]{eventCard1, eventCard2, eventCard3, eventCard4, eventCard5, eventCard6};
        int maxCards = 6;

        BookTicketController bookTicketController = new BookTicketController();
        events = bookTicketController.findCityEvents(SessionManager.getSessionManager().getSessionFromId(session).getCity());

        int numPages = (events.size() / maxCards);
        if(events.size() % maxCards != 0){
            numPages++;
        }
        numberPage.setPageCount(numPages);
        numberPage.setMaxPageIndicatorCount(Math.min(numPages/2, 10));
        numberPage.currentPageIndexProperty().addListener(((obs, oldIndex, newIndex) -> createPage(newIndex.intValue(), maxCards)));
        createPage(0, maxCards);
    }

    private void createPage(Integer pageIndex, Integer maxEvents){
        resetMsg(errorMsg);

        eventCard1.setVisible(false);
        eventCard2.setVisible(false);
        eventCard3.setVisible(false);
        eventCard4.setVisible(false);
        eventCard5.setVisible(false);
        eventCard6.setVisible(false);

        int max = Math.min(maxEvents, events.size() - pageIndex * maxEvents);

        for (int i = 0; i < max; i++) {
            eventCards[i].setVisible(true);
            ObservableList<Node> elements = eventCards[i].getChildren();
            EventBean event = events.get(pageIndex * maxEvents + i);
            ((Button) elements.get(0)).setText(event.getName());
            ((Label) elements.get(1)).setText("Date: " + event.getDate());
            ((Label) elements.get(2)).setText("Organizer: " + event.getOrgName());
        }
    }

    private EventBean searchEvent(String eventName){
        for(EventBean event : events){
            if(event.getName().equals(eventName)){
                return event;
            }
        }
        return null;
    }
}

