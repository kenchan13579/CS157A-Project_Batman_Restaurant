package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	private SimpleIntegerProperty id;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty email;


	/**
	 * Default constructor
	 */
	public Person() {
		id = new SimpleIntegerProperty(0);
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
	 * @param id
     */


	// Getters and setters

	public Person(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int value) {
		this.id.set(value);

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
