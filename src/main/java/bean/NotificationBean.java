package bean;

import java.time.ZonedDateTime;

public class NotificationBean {

	private String type;

	private String eventName;

	private String bookingCode;

	private ZonedDateTime dateAndTime;

	public String getType() {
		return type;
	}

	public String getName() {
		return this.eventName;
	}

	public String getBooking() {
		return this.bookingCode;
	}

	public ZonedDateTime getDateAndTime() {
		return this.dateAndTime;
	}

	public void setDateAndTime(ZonedDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String eventName) {
		this.eventName = eventName;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

}