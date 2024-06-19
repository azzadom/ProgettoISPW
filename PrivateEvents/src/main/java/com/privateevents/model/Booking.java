package com.privateevents.model;

import java.security.SecureRandom;
import java.util.Random;

public class Booking {

	private Integer idBooking = null;

	private String codeBooking= null;

	private final String lastName;

	private final String firstName;

	private final Integer age;

	private String email;

	private String telephone;

	private char gender;

	private String ticketType;

	private Boolean onlinePayment;

	public Booking(String lastName, String firstName, Integer age, char gender, String email, String telephone, String ticketType, Boolean onlinePayment){
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.telephone = telephone;
		this.ticketType = ticketType;
		this.onlinePayment = onlinePayment;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setIdAndCodeBooking(Integer idBooking) {
		this.idBooking = idBooking;
		this.codeBooking = generateCodeBooking(idBooking);
	}

	public void setIdAndCodeBooking(String codeBooking) {
		this.codeBooking = codeBooking;
		this.idBooking = reverseCodeBooking(codeBooking);
	}

	public Integer getIdBooking() {
		return this.idBooking;
	}

	public String getFirstname() {
		return this.firstName;
	}

	public String getLastname() {
		return this.lastName;
	}

	public Integer getAge() {
		return this.age;
	}

	public char getGender() {
		return this.gender;
	}

	public String getTicketType() {
		return this.ticketType;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public Boolean getOnlinePayment() {
		return this.onlinePayment;
	}

	public String getCodeBooking() {
		return this.codeBooking;
	}

	/**
	 * Generates a code booking string of length 10 using the given id booking.
	 *
	 * @param  idBooking  the id booking
	 * @return            the generated code booking
	 */
	private String generateCodeBooking(Integer idBooking) {
		String numStr = String.format("%04d", idBooking);

		char[] digits = new char[4];
		digits[0] = numStr.charAt(0);
		digits[1] = numStr.charAt(1);
		digits[2] = numStr.charAt(2);
		digits[3] = numStr.charAt(3);

		String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "0123456789";

		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(characterSet.length());
			sb.append(characterSet.charAt(index));
		}

		String randomString = sb.toString();

		char[] resultArray = randomString.toCharArray();

		//shuffle the digits in the code booking string
		resultArray[1] = digits[3];
		resultArray[3] = digits[0];
		resultArray[5] = digits[2];
		resultArray[7] = digits[1];

		return new String(resultArray);
	}

	/**
	 * Reverses the order of the characters in the given code booking string and converts it to an integer.
	 *
	 * @param  codeBooking  the code booking string to be reversed and converted to an integer
	 * @return              the reversed and converted code booking as an integer
	 */
	private Integer reverseCodeBooking(String codeBooking) {
		char[] resultArray = codeBooking.toCharArray();
		char[] digits = new char[4];

		digits[0] = resultArray[3];
		digits[1] = resultArray[1];
		digits[2] = resultArray[7];
		digits[3] = resultArray[5];

		String numStr = String.valueOf(digits);

		return Integer.parseInt(numStr);
	}




}
