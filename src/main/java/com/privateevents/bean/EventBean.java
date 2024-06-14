package com.privateevents.bean;

import com.privateevents.exception.IncorrectDataException;

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

	private Boolean closed;

	private List<TicketBean> tickets = new ArrayList<>();

	private Map<String, Integer> ticketsAvailability = new HashMap<>();

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public void setTicketsAvailability(String type, Integer available){
		if (type == null || type.isEmpty() || available < 0) {
			return;
		}

		this.ticketsAvailability.put(type, available);
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Boolean getClosed() {
		return closed;
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
		}else if(orgName.length() > 45) {
			throw new IncorrectDataException("Organizer Username is too long (max 45 characters)");
		}else {
			this.orgName = orgName;
		}
	}

	public void setDescription(String desc) throws IncorrectDataException {
		if(desc == null || desc.isEmpty()) {
			this.description = "No description available.";
		}else if(desc.length() > 1000) {
			throw new IncorrectDataException("Description is too long (max 1000 characters)");
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
		}else if(locationName.length() > 45) {
			throw new IncorrectDataException("Location is too long (max 45 characters)");
		}else {
			this.locationName = locationName;
		}
	}

	public void setAddress(String addr) throws IncorrectDataException {
		//Non impongo un pattern in quanto l'indirizzo può essere di qualsiasi tipo a seconda del paese
		//In una reale iplementazione si potrebbe usare un API come quella di google che assoccia a qualsiasi indirizzo un ID univoco
		if (addr == null || addr.isEmpty()) {
			throw new IncorrectDataException("Address is not valid.");
		}else if(addr.length() > 45) {
			throw new IncorrectDataException("Address is too long (max 45 characters)");
		} else {
			this.address = addr;
		}
	}

	public void setCity(String city) throws IncorrectDataException {
		String cityPattern = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*+$";
		boolean match = Pattern.matches(cityPattern, city);
		if (!match) {
			throw new IncorrectDataException("City is not valid.");
		}else if(city.length() > 45) {
			throw new IncorrectDataException("City is too long (max 45 characters)");
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

}