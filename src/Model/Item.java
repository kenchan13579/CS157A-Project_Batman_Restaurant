package Model;

public class Item {
	private String itemName;
	private double price;
	private String type;
	private String description;
	private int quantityAvailable;
	public Item(String itemName, double price, String desc) {
		this.setItemName(itemName);
		this.setPrice(price);
		//this.setType(type);
		this.setDescription(desc);
		//this.setQuantityAvailable(q);
	}
	/*public int getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}*/
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}*/
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String toString(){
		return getItemName() +" | "+getDescription() +" | $" + getPrice() ; 
	}

}
