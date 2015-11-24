package Model;

public class Item {
	private String itemName;
	private double price;
	private String type;
	private String description;
	private int quantityAvailable;

	public Item(String itemName, double price, String type, String description, int quantityAvailable) {
		this.itemName = itemName;
		this.price = price;
		this.type = type;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
	}


	public Item(String itemName, double price, String description) {
		this.itemName = itemName;
		this.price = price;
		this.description = description;
		this.quantityAvailable = 0;
		this.type = "";
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String toString(){
		return getItemName() +" | "+getDescription() +" | $" + getPrice() ; 
	}

}
