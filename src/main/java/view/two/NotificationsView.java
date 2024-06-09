package view.two;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NotificationsView extends AbstractView{

    @Override
    public int showMenu() {
        printTitle("NOTIFICATIONS PAGE");
        System.out.println("1. View Notifications");
        System.out.println("2. Delete");
        System.out.println("3. Home");
        System.out.println("4. Log Out");
        System.out.println("5. Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while(true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if(choice >= 1 && choice <= 5) {
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

    public void showNotifications(String[] notifications) {
        printTitle("NOTIFICATIONS");
        for (String s : notifications) {
            System.out.println(s);
        }
    }

    public List<Integer> deleteNotification() {

        List<Integer> notifs = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to delete all notification? (Y/N)");
        String choice = input.nextLine();
        if (choice.equals("Y")) {
            notifs.add(-1);
            return notifs;
        }

        int inputNumber;

        while (true){
            try {
                System.out.println("Enter the number of the notification you want to delete: ");
                inputNumber = Integer.parseInt(input.nextLine());
                if (inputNumber <= 0) {
                    throw new InputMismatchException();
                }

                notifs.add(inputNumber);

                System.out.println("Do you want to delete another notification? (Y/N): ");
                choice = input.nextLine();
                if (choice.equals("N")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.next();
            }
        }
        return notifs;
    }
}
