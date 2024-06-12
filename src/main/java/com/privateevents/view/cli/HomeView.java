package com.privateevents.view.cli;

import java.util.Scanner;

public class HomeView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("HOME PAGE", "Search Events", "Login or register", "Exit");
        return getInputMenu(3);
    }

    public String searchEvent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the city: ");
        return input.nextLine();
    }


}
