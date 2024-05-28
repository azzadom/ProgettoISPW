package model;

public class Ticket {

	public Ticket(String typeName, Double price, Integer minimumAge, String description, Integer maxTickets) {
		this.typeName = typeName;
		this.price = price;
		this.minimumAge = minimumAge;
		this.description = description;
		this.maxTickets = maxTickets;
	}

	private final String typeName;

	private final Double price;

	private final Integer minimumAge;

	private final String description;

	private final Integer maxTickets;

	public Integer restriction() {
		return this.minimumAge;
	}

	public String getType() {
		return this.typeName;
	}

	public Double getPrice() {
		return this.price;
	}

	public String getDescription() {
		return this.description;
	}

	public Integer getLimit() {
		return this.maxTickets;
	}


}