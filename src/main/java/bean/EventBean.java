package bean;

import exception.IncorrectDataException;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.regex.Pattern;

public class EventBean {

	private Integer idEvent;

	private String name;

	private String orgName;

	private String description;

	private Date date;

	private Time time;

	private String locationName;

	private String address;

	private String city;

	private List<TicketBean> tickets = new ArrayList<>();

	private Map<String, Integer> ticketsAvailability = new HashMap<>();

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public void setTicketsAvailability(String type, Integer available) throws IncorrectDataException {
		if (type == null || type.isEmpty()) {
			throw new IncorrectDataException("Type is not valid.");
		}
		if (available < 0) {
			throw new IncorrectDataException("Available tickets cannot be negative.");
		}
		this.ticketsAvailability.put(type, available);
	}

	public void setName(String name) throws IncorrectDataException {
		if(name == null || name.isEmpty()) {
			throw new IncorrectDataException("Name cannot be empty");
		}else {
			this.name = name;
		}
	}

	public void setOrgName(String orgName) throws IncorrectDataException {
		if(orgName == null || orgName.isEmpty()) {
			throw new IncorrectDataException("Organizer Username cannot be empty");
		}else {
			this.orgName = orgName;
		}
	}

	public void setDescription(String desc) {
		if(desc == null || desc.isEmpty()) {
			this.description = "No description available.";
		}else {
			this.description = desc;
		}
	}

	public void setDate(String date) throws IncorrectDataException {
		String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		boolean match = Pattern.matches(datePattern, date);
		if (!match) {
			throw new IncorrectDataException("Date is not valid.");
		} else {
			this.date = Date.valueOf(date);
		}
	}

	public void setTime(String time) throws IncorrectDataException {
		String timePattern = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
		boolean match = Pattern.matches(timePattern, time);
		if (!match) {
			throw new IncorrectDataException("Time is not valid.");
		} else {
			this.time = Time.valueOf(time);
		}
	}

	public void setLocationName(String locationName) throws IncorrectDataException {
		if(locationName == null || locationName.isEmpty()) {
			throw new IncorrectDataException("Location Name cannot be empty");
		}else {
			this.locationName = locationName;
		}
	}

	public void setAddress(String addr) throws IncorrectDataException {
		//Non impongo un pattern in quanto l'indirizzo può essere di qualsiasi tipo a seconda del paese
		//In una reale iplementazione si potrebbe usare un API come quella di google che assoccia a qualsiasi indirizzo un ID univoco
		if (addr == null || addr.isEmpty()) {
			throw new IncorrectDataException("Address is not valid.");
		} else {
			this.address = addr;
		}
	}

	public void setCity(String city) throws IncorrectDataException {
		String cityPattern = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*+$";
		boolean match = Pattern.matches(cityPattern, city);
		if (!match) {
			throw new IncorrectDataException("City is not valid.");
		} else {
			this.city = city;
		}
	}

	public void setTickets(List<TicketBean> tickets) {
		this.tickets = tickets;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public String getName() {
		return name;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date.toString();
	}

	public String getLocationName() {
		return locationName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public Integer getTicketsAvailability(String type) {
		return ticketsAvailability.get(type);
	}

	public List<TicketBean> getTickets() {
		return tickets;
	}

	public String getTime() {
		return time.toString();
	}

	public Map<String, Integer> getTicketAvailability() {
		return this.ticketsAvailability;
	}
}