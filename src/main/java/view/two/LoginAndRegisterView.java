package view.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginAndRegisterView extends AbstractView {

    public int showMenu() {
        printTitle("LOGIN PAGE");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Home");
        System.out.println("4. Exit");

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
        System.out.println("Enter your Username: ");
        credentials[0] = input.nextLine();
        System.out.println("Enter your Password: ");
        credentials[1] = input.nextLine();
        return credentials;
    }

    public String[] register() {

        printTitle("REGISTRATION PROCEDURE");
        String[] user = new String[6];
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Firstname: ");
        user[0] = input.nextLine();
        System.out.println("Enter your Lastname: ");
        user[1] = input.nextLine();
        System.out.println("Enter your Email: ");
        user[2] = input.nextLine();
        System.out.println("Enter your PayPal Email: ");
        user[3] = input.nextLine();
        System.out.println("Enter your Username: ");
        user[4] = input.nextLine();
        System.out.println("Enter your Password: ");
        user[5] = input.nextLine();

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
