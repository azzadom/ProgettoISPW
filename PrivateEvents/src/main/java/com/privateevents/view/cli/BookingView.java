package com.privateevents.view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingView extends AbstractView {

    @Override
    public int showMenu() {
        printMenu("BOOKING TICKET PAGE", "Show all tickets", "New booking", "Back", "Home", "Exit");
        return getInputMenu(5);
    }

    public void showTickets(String[] tickets) {
        printTitle("TICKETS LIST");
        for (String s : tickets) {
            showMessage(s);
        }
    }

    public String[] insertData() {
        printTitle("BOOKING PROCEDURE");
        String[] data = new String[8];
        Scanner input = new Scanner(System.in);
        getInput(input, data, 0, "Enter your Firstname: ");
        getInput(input, data, 1, "Enter your Lastname: ");
        getInput(input, data, 2, "Enter your Age: ");
        getInput(input, data, 3, "Enter your Gender (M/F): ");
        getInput(input, data, 4, "Enter your Email: ");
        getInput(input, data, 5, "Enter your Telephone Number (with national prefix): ");
        getInput(input, data, 6, "Enter the Ticket Number: ");
        getInput(input, data, 7, "Do you want to pay online using PayPal? (Y/N): ");

        showMessage("DATA INSERTED CHECK");
        showMessage("Firstname: " + data[0]);
        showMessage("Lastname: " + data[1]);
        showMessage("Age: " + data[2]);
        showMessage("Gender: " + data[3]);
        showMessage("Email: " + data[4]);
        showMessage("Telephone Number: " + data[5]);
        showMessage("Ticket Number: " + data[6]);
        showMessage("PayPal: " + data[7]);

        if(data[7].equalsIgnoreCase("Y")){
            data[7] = "true";
        } else {
            data[7] = "false";
        }

        int choice;
        while (true) {
            try {
                showMessage("Press 0 to confirm or 1 to cancel: ");
                choice = input.nextInt();
                if (choice >= 0 && choice <= 1) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                showMessage("Invalid input!");
                input.next();
            }
        }
        if (choice == 0) {
            return data;
        } else {
            return new String[0];
        }
    }
}
