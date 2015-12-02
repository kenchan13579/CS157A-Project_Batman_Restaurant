package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class ReceiptItem {
    private SimpleIntegerProperty rID;
    private SimpleIntegerProperty mID;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty price;

    public ReceiptItem(int rID) {
        this.rID = new SimpleIntegerProperty(rID);
    }

    public ReceiptItem(int rID, int mID, int quantity, double price) {
        this.rID = new SimpleIntegerProperty(rID);
        this.mID = new SimpleIntegerProperty(mID);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }


    public int getRID() {
        return rID.get();
    }

    public void setRID(int rID) {
        this.rID.set(rID);
    }

    public int getMID() {
        return mID.get();
    }

    public void setMID(int mID) {
        this.mID.set(mID);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
