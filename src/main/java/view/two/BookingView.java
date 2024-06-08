package view.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingView extends AbstractView {

    @Override
    public int showMenu() {
        printTitle("BOOKING TICKET PAGE");
        System.out.println("1. Show all tickets");
        System.out.println("2. New booking");
        System.out.println("5. Back");
        System.out.println("6. Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.next();
            }
        }
        return choice;
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
        System.out.println("Enter your Firstname: ");
        data[0] = input.nextLine();
        System.out.println("Enter your Lastname: ");
        data[1] = input.nextLine();
        System.out.println("Enter your Age: ");
        data[2] = input.nextLine();
        System.out.println("Enter your Gender: ");
        data[3] = input.nextLine();
        System.out.println("Enter your Email: ");
        data[4] = input.nextLine();
        System.out.println("Enter your Telephone Number: ");
        data[5] = input.nextLine();
        System.out.println("Enter the Ticket Number: ");
        data[6] = input.nextLine();
        System.out.println("Do you want to pay online using PayPal? (Y/N): ");
        data[7] = input.nextLine();

        System.out.println("DATA INSERTED CHECK");
        System.out.println("Firstname: " + data[0]);
        System.out.println("Lastname: " + data[1]);
        System.out.println("Age: " + data[2]);
        System.out.println("Gender: " + data[3]);
        System.out.println("Email: " + data[4]);
        System.out.println("Telephone Number: " + data[5]);
        System.out.println("Ticket Number: " + data[6]);
        System.out.println("PayPal: " + data[7]);

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
