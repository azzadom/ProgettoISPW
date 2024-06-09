package view.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerHomeView extends AbstractView{

    @Override
    public int showMenu() {
        printTitle("ORGANIZER HOME PAGE");
        System.out.println("1. View Events");
        System.out.println("2. View Notifications");
        System.out.println("3. View Settings");
        System.out.println("4. Log Out");
        System.out.println("5. Exit");

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
}
