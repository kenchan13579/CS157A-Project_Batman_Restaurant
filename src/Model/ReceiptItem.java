package Model;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class ReceiptItem {
    private int rID;
    private int mID;
    private int quantity;
    private double price;

    public ReceiptItem(int rID) {
        this.rID = rID;
    }

    public ReceiptItem(int rID, int mID, int quantity, double price) {
        this.rID = rID;
        this.mID = mID;
        this.quantity = quantity;
        this.price = price;
    }


    public int getrID() {
        return rID;
    }

    public void setrID(int rID) {
        this.rID = rID;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
