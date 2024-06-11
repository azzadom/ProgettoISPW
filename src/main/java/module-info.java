module PrivateEvents {
    requires java.logging;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;

    opens controller.gui.fx;
    opens engineering.view;
    opens engineering.view.fx;
    opens bean;
    opens engineering.payment;
    opens engineering.dao;

    exports controller.gui.fx;
    exports bean;
    exports engineering;
    exports engineering.view.fx;
    exports engineering.dao;
    exports engineering.payment;
    exports starters;
    exports exception;
}