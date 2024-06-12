package view.cli;


public class EventDetailsView extends AbstractView{

    @Override
    public int showMenu() {
        printMenu("EVENT DETAILS PAGE", "Show all info", "Book ticket", "Booking management", "Back", "Home", "Exit");
        return getInputMenu(6);
    }

    public void showInfo(String[] info) {
        printTitle("EVENT INFO");
        for (String s : info) {
            System.out.println(s);
        }
    }

}
