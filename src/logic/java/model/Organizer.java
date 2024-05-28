package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Organizer extends User{

	@Serial
	private static final long serialVersionUID = 1L;

	public Organizer(String username, String password, String email, String firstName, String lastName, String fiscalCode, String infoPayPal) {
		super(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.email = email;
		this.infoPayPal = infoPayPal;
	}

	private final String firstName;

	private final String lastName;

	private final String fiscalCode;

	private String email;

	private final String infoPayPal;

	private transient List<Event> events = new ArrayList<>();

	private transient List<Notification> notifs = new ArrayList<>();

	public void addEvent(Event event) {
		this.events.add(new Event(event.getIdEvent(), event.getName(), event.getDescription(), event.getLocation(), event.getAddress(), event.getCity(), event.getDate(), event.getTime(), event.getBookingClosed(), event.getOrgUsername()));
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void modifyEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFiscalCode() {
		return this.fiscalCode;
	}

	public String getInfoPayPal() {
		return this.infoPayPal;
	}

	public void addNotif(Notification notif) {
		this.notifs.add(new Notification(notif.getType(), notif.getDateAndTime(), notif.getEventName(), notif.getBookingCode()));
	}

	public void removeNotif(Notification notif) {
		this.notifs.removeIf(n -> n.getType().equals(notif.getType())
				&& n.getDateAndTime().equals(notif.getDateAndTime())
				&& n.getEventName().equals(notif.getEventName())
				&& n.getBookingCode().equals(notif.getBookingCode()));
	}

	public List<Notification> getNewNotif() {
		return this.notifs;
	}

	public void addNotif(List<Notification> notifs) {
		for (Notification notif : notifs) {
			this.addNotif(notif);
		}
	}

	public void removeNotif(List<Notification> notifs) {
		for (Notification notif : notifs) {
			this.removeNotif(notif);
		}
	}

	public void addEvent(List<Event> events) {
		for (Event event : events) {
			this.addEvent(event);
		}
	}

	public void setTransientParams() {
		this.events = new ArrayList<>();
		this.notifs = new ArrayList<>();
	}

}