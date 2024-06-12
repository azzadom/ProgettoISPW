package com.privateevents.view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ListEventsView extends AbstractView{

    private static final String INPUT_ERROR = "Invalid input!";

    @Override
    public int showMenu() {
        printMenu("EVENTS PAGE", "Show all events", "Select event", "Home", "Exit");
        return getInputMenu(4);
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

        while (lengthToPrint > 0) {
            int max = Math.min(5, lengthToPrint);
            for (int i = 0; i < max; i++) {
                System.out.println(events[i]);
            }
            lengthToPrint -= max;
            if (lengthToPrint > 0) {
                System.out.println("Insert 0 to show more events or 1 to show menu: ");
                Scanner input = new Scanner(System.in);
                int choice;
                while (true){
                    choice = getIntChoice(input);
                    switch (choice) {
                        case 0:
                            break;
                        case 1:
                            return;
                        default:
                            System.out.println(INPUT_ERROR);
                    }
                }
            }
        }
    }

    private int getIntChoice(Scanner input) {
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            input.next();
            return -1;
        }
    }

}
