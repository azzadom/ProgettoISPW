package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EventDetailsView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("EVENT DETAILS PAGE", "Show all info", "Book ticket", "Booking management", "Back", "Home", "Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while(true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if(choice >= 1 && choice <= 6) {
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

    public void showInfo(String[] info) {
        printTitle("EVENT INFO");
        for (String s : info) {
            System.out.println(s);
        }
    }

}
