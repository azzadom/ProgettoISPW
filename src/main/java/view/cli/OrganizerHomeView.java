package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerHomeView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("ORGANIZER HOME PAGE", "View Events", "View Notifications", "View Settings", "Log Out", "Exit");

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
}
