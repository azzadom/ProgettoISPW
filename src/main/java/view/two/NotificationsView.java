package view.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NotificationsView extends AbstractView{

    @Override
    public int showMenu() {
        printTitle("NOTIFICATIONS PAGE");
        System.out.println("1. View Notifications");
        System.out.println("2. Delete");
        System.out.println("4. Home");
        System.out.println("5. Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while(true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if(choice >= 1 && choice <= 4) {
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

    public void showNotifications(String[] notifications) {
        printTitle("NOTIFICATIONS");
        for (String s : notifications) {
            System.out.println(s);
        }
    }

    public int deleteNotification() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to delete all notification? (yes/no)");
        String choice = input.nextLine();
        if (choice.equals("yes")) {
            return -1;
        }

        int inputNumber;
        while (true){
            try {
                System.out.println("Enter the number of the notification you want to delete: ");
                inputNumber = input.nextInt();
                if (inputNumber < 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.next();
            }
        }
        return inputNumber;
    }
}
