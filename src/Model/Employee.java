package Model;

public class Employee extends Person{
	private String position;
	private String lastWorked;


	public Employee() {
		super();
		position = null;
		lastWorked = null;
	}

	/**
	 * Employee constructor with additional properties for employee
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param position
     * @param lastWorked
     */
	public Employee(String firstName, String lastName, String email, String position, String lastWorked) {
		super(firstName, lastName, email);
		this.position = position;
		this.lastWorked = lastWorked;
	}

	//Setters and getters

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLastWorked() {
		return lastWorked;
	}

	public void setLastWorked(String lastWorked) {
		this.lastWorked = lastWorked;
	}
}
