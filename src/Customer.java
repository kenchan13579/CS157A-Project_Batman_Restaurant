import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer extends Person{
	private SimpleStringProperty updatedAt;
	private SimpleIntegerProperty discount;

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
		updatedAt = new SimpleStringProperty("");
		discount = new SimpleIntegerProperty(0);

	}

	/**
	 * Customer constructor with new customer properties
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param updatedAt
     * @param discount
     */

	public Customer(String firstName, String lastName, String email, String updatedAt, int discount) {
		super(firstName, lastName, email);
		this.updatedAt = new SimpleStringProperty(updatedAt);
		this.discount = new SimpleIntegerProperty(discount);
	}

	//Getters and setters
	public String getUpdatedAt() {
		return updatedAt.get();
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt.set(updatedAt);
	}

	public int getDiscount() {
		return discount.get();
	}

	public void setDiscount(int discount) {
		this.discount.set(discount);
	}
}
