package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer extends Person{
	private SimpleStringProperty lastVisited;
	private SimpleIntegerProperty discount;

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
		lastVisited = new SimpleStringProperty("");
		discount = new SimpleIntegerProperty(0);

	}

	/**
	 * Customer constructor with new customer properties
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param lastVisited
     * @param discount
     */

	public Customer(String firstName, String lastName, String email, String lastVisited, int discount) {
		super(firstName, lastName, email);
		this.lastVisited = new SimpleStringProperty(lastVisited);
		this.discount = new SimpleIntegerProperty(discount);
	}

	//Getters and setters
	public String getLastVisited() {
		return lastVisited.get();
	}

	public void setLastVisited(String lastVisited) {
		this.lastVisited.set(lastVisited);
	}

	public int getDiscount() {
		return discount.get();
	}

	public void setDiscount(int discount) {
		this.discount.set(discount);
	}
}
