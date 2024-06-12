package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingView extends AbstractView {

    @Override
    public int showMenu() {
        printTitle("BOOKING TICKET PAGE");
        System.out.println("1. Show all tickets");
        System.out.println("2. New booking");
        System.out.println("3. Back");
        System.out.println("4. Home");
        System.out.println("5. Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.next();
            }
        }
    }

    public void showTickets(String[] tickets) {
        printTitle("TICKETS LIST");
        for (String s : tickets) {
            System.out.println(s);
        }
    }

    public String[] insertData() {
        printTitle("BOOKING PROCEDURE");
        String[] data = new String[8];
        Scanner input = new Scanner(System.in);
        getInput(input, data, 0, "Enter your Firstname: ");
        getInput(input, data, 1, "Enter your Age: ");
        getInput(input, data, 2, "Enter your Gender (M/F): ");
        getInput(input, data, 3, "Enter your Email: ");
        getInput(input, data, 4, "Enter your Telephone Number (with national prefix): ");
        getInput(input, data, 5, "Enter the Ticket Number: ");
        getInput(input, data, 6, "Do you want to pay online using PayPal? (Y/N): ");

        System.out.println("DATA INSERTED CHECK");
        System.out.println("Firstname: " + data[0]);
        System.out.println("Lastname: " + data[1]);
        System.out.println("Age: " + data[2]);
        System.out.println("Gender: " + data[3]);
        System.out.println("Email: " + data[4]);
        System.out.println("Telephone Number: " + data[5]);
        System.out.println("Ticket Number: " + data[6]);
        System.out.println("PayPal: " + data[7]);

        if(data[7].equalsIgnoreCase("Y")){
            data[7] = "true";
        } else {
            data[7] = "false";
        }

        int choice;
        while (true) {
            try {
                System.out.println("Press 0 to confirm or 1 to cancel: ");
                choice = input.nextInt();
                if (choice >= 0 && choice <= 1) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
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
