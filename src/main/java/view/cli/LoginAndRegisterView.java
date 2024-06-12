package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginAndRegisterView extends AbstractView {

    public int showMenu() {
        printMenu("LOGIN PAGE", "Login", "Register", "Home", "Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while(true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if(choice >= 1 && choice <= 4) {
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

    public String[] login() {

        printTitle("LOGIN PROCEDURE");
        String[] credentials = new String[2];
        Scanner input = new Scanner(System.in);
        getInput(input, credentials, 0, "Enter your Username: ");
        getInput(input, credentials, 1, "Enter your Password: ");
        return credentials;
    }

    public String[] register() {

        printTitle("REGISTRATION PROCEDURE");
        String[] user = new String[6];
        Scanner input = new Scanner(System.in);
        getInput(input, user, 0, "Enter your Firstname: ");
        getInput(input, user, 1, "Enter your Lastname: ");
        getInput(input, user, 2, "Enter your Email: ");
        getInput(input, user, 3, "Enter your PayPal Email: ");
        getInput(input, user, 4, "Enter your Username: ");
        getInput(input, user, 5, "Enter your Password: ");

        System.out.println("DATA INSERTED CHECK");
        System.out.println("Firstname: " + user[0]);
        System.out.println("Lastname: " + user[1]);
        System.out.println("Email: " + user[2]);
        System.out.println("PayPal Email: " + user[3]);
        System.out.println("Username: " + user[4]);
        System.out.println("Password: " + user[5]);

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
            return user;
        } else {
            return new String[0];
        }
    }
}
