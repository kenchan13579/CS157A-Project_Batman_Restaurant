package Model;

public class Person {
	private int ID;
	private String firstName;
	private String lastName;
	private String email;


	/**
	 * Default constructor
	 */
	public Person() {
		ID = 0;
		firstName = null;
		lastName = null;
		email = null;
	}


	/**
	 * Constructor for Person
	 * @param firstName
	 * @param lastName
	 * @param email
     */
	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	/**
	 * Constructor with a given ID, likely for use with getting a person out of database
	 * @param ID
     */


	// Getters and setters

	public Person(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
