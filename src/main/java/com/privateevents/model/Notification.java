package com.privateevents.model;

import java.time.LocalDateTime;


public class Notification {

	public Notification(TypeNotif type, LocalDateTime dateTime, String eventName, String bookingCode) {
		this.type = type;
		this.dateAndTime = dateTime;
		this.eventName = eventName;
		this.bookingCode = bookingCode;
	}

	private final LocalDateTime dateAndTime;

	private final String bookingCode;

	private final String eventName;

	private final TypeNotif type;

	public TypeNotif getType() {
		return this.type;
	}

	public LocalDateTime getDateAndTime() {
		return this.dateAndTime;
	}

	public String getBookingCode() {
		return this.bookingCode;
	}

	public String getEventName() {
		return this.eventName;
	}

}