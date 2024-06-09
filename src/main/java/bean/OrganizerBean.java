package bean;

import exception.IncorrectDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class OrganizerBean extends UserBean{

    private String firstName;

    private String lastName;

    private String email;

    private String infoPayPal;

    private List<NotificationBean> notifs = new ArrayList<>();

    private List<EventBean> events = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IncorrectDataException {
        String firstNamePattern = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)?$";
        boolean match = Pattern.matches(firstNamePattern, firstName);
        if (!match) {
            throw new IncorrectDataException("Lastname is not valid.");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IncorrectDataException {
        String lastNamePattern = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)?('[A-Z][a-zA-Z]*)?$";
        boolean match = Pattern.matches(lastNamePattern, lastName);
        if (!match) {
            throw new IncorrectDataException("Lastname is not valid.");
        } else {
            this.lastName = lastName;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IncorrectDataException {
        String emailPattern = "^[\\w-.]++@([\\w-]++\\.)++[\\w-]{2,3}$";
        boolean match = Pattern.matches(emailPattern, email);
        if (!match) {
            throw new IncorrectDataException("Email is not valid.");
        } else {
            this.email = email;
        }
    }

    public String getInfoPayPal() {
        return infoPayPal;
    }

    public void setInfoPayPal(String infoPayPal) throws IncorrectDataException {
        String infoPayPalPattern = "^[\\w-.]++@([\\w-]++\\.)++[\\w-]{2,3}$";
        boolean match = Pattern.matches(infoPayPalPattern, infoPayPal);
        if (!match) {
            throw new IncorrectDataException("InfoPayPal is not valid.");
        } else {
            this.infoPayPal = infoPayPal;
        }
    }

    public List<NotificationBean> getNotifs() {
        return notifs;
    }

    public void setNotifs(List<NotificationBean> notifs) {
        this.notifs = notifs;
    }

    public List<EventBean> getEvents() {
        return events;
    }

    public void setEvents(List<EventBean> events) {
        this.events = events;
    }

}
