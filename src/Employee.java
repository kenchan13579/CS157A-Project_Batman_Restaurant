import javafx.beans.property.SimpleStringProperty;

public class Employee extends Person{
	private SimpleStringProperty position;
	private SimpleStringProperty lastWorked;


	public Employee() {
		super();
		position = new SimpleStringProperty();
		lastWorked = new SimpleStringProperty();
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
		this.position = new SimpleStringProperty(position);
		this.lastWorked = new SimpleStringProperty(lastWorked);
	}

	//Setters and getters

	public String getPosition() {
		return position.get();
	}

	public void setPosition(String position) {
		this.position.set(position);
	}

	public String getLastWorked() {
		return lastWorked.get();
	}

	public void setLastWorked(String lastWorked) {
		this.lastWorked.set(lastWorked);
	}
}
