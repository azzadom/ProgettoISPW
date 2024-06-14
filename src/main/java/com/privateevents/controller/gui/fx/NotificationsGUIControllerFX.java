package com.privateevents.controller.gui.fx;

import com.privateevents.bean.NotificationBean;
import com.privateevents.bean.OrganizerBean;
import com.privateevents.controller.app.NotificationsController;
import com.privateevents.utils.view.SessionManager;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationsGUIControllerFX extends AbstractGUIControllerFX{

    @FXML
    Button all;

    @FXML
    Button selected;

    @FXML
    TableView<NotificationBean> table;

    @FXML
    TableColumn<NotificationBean, String> type;

    @FXML
    TableColumn<NotificationBean, String> event;

    @FXML
    TableColumn<NotificationBean, String> code;

    @FXML
    TableColumn<NotificationBean, ZonedDateTime> time;

    OrganizerBean organizerBean;

    List<NotificationBean> notifications;

    ObservableList<NotificationBean> showNotifications;

    @FXML
    public void deleteAll(){
        resetMsg(errorMsg);
        try {
            NotificationsController notificationsController = new NotificationsController();
            notificationsController.deleteAllNotifications(organizerBean);
            showNotifications.clear();
        } catch (OperationFailedException e) {
            setMsg(errorMsg, e.getMessage());
        }
    }

    @FXML
    public void deleteSelected() {
        resetMsg(errorMsg);

        ObservableList<NotificationBean> selectedItems = table.getSelectionModel().getSelectedItems();
        try {
            if (selectedItems.isEmpty()) {
                setMsg(errorMsg, "No items selected");
            } else {
                NotificationsController notificationsController = new NotificationsController();
                notificationsController.deleteNotifications(selectedItems, organizerBean);
                showNotifications.removeAll(selectedItems);
            }
        } catch (OperationFailedException e) {
            setMsg(errorMsg, e.getMessage());
        }

    }

    public void initialize(Integer session) throws OperationFailedException, NotFoundException {
        resetMsg(errorMsg);
        this.currentSession = session;

        organizerBean = (OrganizerBean) SessionManager.getSessionManager().getSessionFromId(currentSession).getUser();
        NotificationsController notificationsController = new NotificationsController();
        notifications = notificationsController.getNotifications(organizerBean);
        for (NotificationBean notification : notifications) {
            notification.setDateAndTime(notification.getDateAndTime().withZoneSameInstant(ZoneId.systemDefault()));
        }

        showNotifications = FXCollections.observableArrayList(notifications);

        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        event.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        code.setCellValueFactory(new PropertyValueFactory<>("bookingCode"));
        time.setCellValueFactory(new PropertyValueFactory<>("dateAndTime"));
        time.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(ZonedDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm");
                    setText(item.format(formatter));
                }
            }
        });

        table.editableProperty().setValue(false);
        table.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        table.setItems(showNotifications);

    }
}
