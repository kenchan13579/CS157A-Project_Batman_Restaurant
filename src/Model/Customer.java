package Model;

public class Customer extends Person{
	private String lastVisited;
	private int discount;

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
		lastVisited = null;
		discount = 0;
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
		this.lastVisited = lastVisited;
		this.discount = discount;
	}

	//Getters and setters
	public String getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(String lastVisited) {
		this.lastVisited = lastVisited;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
