package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
	private SimpleStringProperty itemName;
	private SimpleDoubleProperty price;
	private SimpleStringProperty type;
	private SimpleStringProperty description;
	private SimpleIntegerProperty quantityAvailable;

	public Item() {
		itemName = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
		type = new SimpleStringProperty();
		description = new SimpleStringProperty();
		quantityAvailable = new SimpleIntegerProperty();
	};

	public Item(String itemName, double price, String type, String description, int quantityAvailable) {
		this.itemName = new SimpleStringProperty(itemName);
		this.price = new SimpleDoubleProperty(price);
		this.type = new SimpleStringProperty(type);
		this.description = new SimpleStringProperty(description);
		this.quantityAvailable = new SimpleIntegerProperty(quantityAvailable);
	}


	public Item(String itemName, double price, String description) {
		this.itemName = new SimpleStringProperty(itemName);
		this.price = new SimpleDoubleProperty(price);
		this.description = new SimpleStringProperty(description);
		this.quantityAvailable = new SimpleIntegerProperty(0);
		this.type = new SimpleStringProperty("");
	}

	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(String itemName) {
		this.itemName.set(itemName);
	}

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public int getQuantityAvailable() {
		return quantityAvailable.get();
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable.set(quantityAvailable);
	}

	public String toString(){
		return getItemName() +" | "+getDescription() +" | $" + getPrice() ; 
	}

}
