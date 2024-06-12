package com.privateevents.utils.view.fx;

public enum FilesFXML {
    HOME("/HomeView.fxml"),

    LIST_EVENTS("/ListEventsView.fxml"),

    EVENT("/EventDetailsView.fxml"),

    BOOKING("/BookingView.fxml"),

    LOGIN("/LoginAndSignupView.fxml"),

     NOTIFICATION("/NotificationsView.fxml"),

    ORGANIZER_HOME("/OrganizerHomeView.fxml"),;

    private final String path;

    FilesFXML(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}