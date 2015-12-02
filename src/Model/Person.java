package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	private SimpleIntegerProperty ID;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty email;


	/**
	 * Default constructor
	 */
	public Person() {
		ID = new SimpleIntegerProperty(0);
		firstName = new SimpleStringProperty("");
		lastName = new SimpleStringProperty("");
		email = new SimpleStringProperty("");
	}


	/**
	 * Constructor for Person
	 * @param firstName
	 * @param lastName
	 * @param email
     */
	public Person(String firstName, String lastName, String email) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
	}


	/**
	 * Constructor with a given ID, likely for use with getting a person out of database
	 * @param ID
     */


	// Getters and setters

	public Person(int ID) {
		this.ID = new SimpleIntegerProperty(ID);
	}

	public int getID() {
		return ID.get();
	}

	public void setID(int ID) {
		this.ID.set(ID);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
}
