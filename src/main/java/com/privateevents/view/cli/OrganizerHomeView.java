package com.privateevents.view.cli;

public class OrganizerHomeView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("ORGANIZER HOME PAGE", "View Events", "View Notifications", "View Settings", "Log Out", "Exit");
        return getInputMenu(5);
    }
}
