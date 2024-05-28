package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Event implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Integer idEvent;

	private String name;

	private String desription;

	private final String locationName;

	private final String address;

	private final String city;

	private final LocalDate date;

	private final LocalTime time;

	private Boolean bookingClosed;

	private String orgUsername;

	private transient HashMap<String, Integer> ticketsSold;

	private transient List<Ticket> tickets;

	private transient List<Booking> bookings;

	public Event(Integer id, String name, String description, String locationName, String address, String city,
				 LocalDate date, LocalTime time, Boolean bookingClosed, String orgUsername) {
		this.idEvent = id;
		this.name = name;
		this.desription = description;
		this.locationName = locationName;
		this.address = address;
		this.city = city;
		this.date = date;
		this.time = time;
		this.bookingClosed = bookingClosed;
		this.orgUsername = orgUsername;
		this.bookings = new ArrayList<>();
		this.tickets = new ArrayList<>();
		this.ticketsSold = new HashMap<>();
	}

	public void addBooking(Booking book) {
		this.bookings.add(new Booking(book.getLastname(), book.getFirstname(), book.getAge(), book.getGender(),book.getEmail(),book.getTelephone(),
				book.getTicketType(),book.getOnlinePayment()));
		this.ticketsSold.put(book.getTicketType(), this.ticketsSold.get(book.getTicketType()) + 1);
	}

	public void setTicketsAndBookings(List<Ticket> tickets, List<Booking> bookings) {
        for (Ticket ticket : tickets) {
			this.tickets.add(new Ticket(ticket.getType(), ticket.getPrice(), ticket.restriction(), ticket.getDescription(), ticket.getLimit()));
			this.ticketsSold.put(ticket.getType(), 0);
		}

		for (Booking booking : bookings) {
			addBooking(booking);
		}
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public void setOrgUsername(String orgUsername) {
		this.orgUsername = orgUsername;
	}

	public void setDescription(String desc) {
		this.desription = desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBookingClosed(Boolean bookingClosed) {
		this.bookingClosed = bookingClosed;
	}


	public String getName() {
		return this.name;
	}
	
	public String getCity() {
		return this.city;
	}

	public String getAddress() {
		return this.address;
	}

	public String getDescription() {
		return this.desription;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public LocalTime getTime() {
		return this.time;
	}

	public String getLocation() {
		return this.locationName;
	}

	public String getOrgUsername() {
		return this.orgUsername;
	}

	public Boolean getBookingClosed() {
		return this.bookingClosed;
	}

	public Integer getIdEvent() {
		return this.idEvent;}

	public Integer getLimitTicket(String type) {
		for (Ticket t : this.tickets) {
			if (t.getType().equals(type)) {
				return t.getLimit();
			}
		}
		return null;
	}

	public Integer getTicketAvailability(String type) {
		Integer availability = getLimitTicket(type) - ticketsSold.get(type);
		if(availability < 0) {
			throw new IllegalStateException("Impossible to have negative availability.");
		}
		return availability;
	}

	public Integer getBookedTickets(String type) {
		return ticketsSold.get(type);
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public List<Ticket> getTickets(){
		return this.tickets;
	}

	public void setTransientParams() {
		this.bookings = new ArrayList<>();
		this.tickets = new ArrayList<>();
		this.ticketsSold = new HashMap<>();
	}
}