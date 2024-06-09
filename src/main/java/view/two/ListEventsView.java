package view.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ListEventsView extends AbstractView{

    private static final String INPUT_ERROR = "Invalid input!";

    @Override
    public int showMenu() {
        printTitle("EVENTS PAGE");
        System.out.println("1. Show all events");
        System.out.println("2. Select event");
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
                System.out.println(INPUT_ERROR);
                input.next();
            }
        }
    }

    public Integer selectEvent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of the event: ");
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(INPUT_ERROR);
                input.next();
            }
        }
    }

    public void showEvents(String[] events) {
        printTitle("EVENTS LIST");
        int lengthToPrint = events.length;

        while(lengthToPrint > 0) {
            int max = Math.min(5, lengthToPrint);
            for (int i = 0; i < max; i++) {
                System.out.println(events[i]);
            }
            lengthToPrint -= max;
            if (lengthToPrint < 0) {
                break;
            }
            System.out.println("Insert 0 to show more events or 1 to show menu: ");
            Scanner input = new Scanner(System.in);
            while (true) {
                try {
                    int choice = input.nextInt();
                    switch (choice) {
                        case 0:
                            break;
                        case 1:
                            return;
                        default:
                            throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println(INPUT_ERROR);
                    input.next();
                }
            }
        }
    }

}
