package bean;

import exception.IncorrectDataException;
import java.util.regex.Pattern;

public class BookingBean {

    private Integer idEvent;

    private String codeBooking;

    private String lastName;

    private String firstName;

    private String email;

    private String telephone;

    private Integer age;

    private char gender;

    private String ticketType;

    private Boolean onlinePayment;

    public void setCodeBooking(String code) {
        this.codeBooking = code;
    }

    public String getCodeBooking() {
        return this.codeBooking;
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

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) throws IncorrectDataException {
        String firstNamePattern = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)?$";
        boolean match = Pattern.matches(firstNamePattern, firstName);
        if (!match) {
            throw new IncorrectDataException("Firstname is not valid.");
        } else {
            this.firstName = firstName;
        }
    }

    public String getFirstName() {
        return firstName;
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

    public String getEmail() {
        return email;
    }

    public void setTelephone(String telephone) throws IncorrectDataException {
        String phonePattern = "^\\+\\d{1,3}\\d{10}$";
        boolean match = Pattern.matches(phonePattern, telephone);
        if (!match) {
            throw new IncorrectDataException("Telephone is not valid.");
        } else {
            this.telephone = telephone;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAge(Integer age) throws IncorrectDataException {
        if(age <= 0) {
            throw new IncorrectDataException("Age is not valid.");
        } else {
            this.age = age;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setGender(char gender) throws IncorrectDataException{
        if(gender != 'M' && gender != 'F'){
            throw new IncorrectDataException("Gender is not valid.");
        } else {
            this.gender = gender;
        }
    }

    public char getGender(){
        return this.gender;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setOnlinePayment(Boolean onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    public Boolean getOnlinePayment() {
        return onlinePayment;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

}
