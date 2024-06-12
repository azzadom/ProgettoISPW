package view.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("HOME PAGE", "Search Events", "Login or register", "Exit");

        Scanner input = new Scanner(System.in);
        int choice;

        while(true) {
            try {
                System.out.println("Choose an option: ");
                choice = input.nextInt();
                if(choice >= 1 && choice <= 3) {
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

    public String searchEvent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the city: ");
        return input.nextLine();
    }


}
