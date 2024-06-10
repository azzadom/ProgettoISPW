package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EventDetailsView extends AbstractView{

    @Override
    public int showMenu() {
        printTitle("DETAILS PAGE");
        System.out.println("1. Show all info");
        System.out.println("2. Book ticket");
        System.out.println("3. Booking management");
        System.out.println("4. Back");
        System.out.println("5. Home");
        System.out.println("6. Exit");

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
