package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Receipt {

    private SimpleIntegerProperty rID;
    private SimpleIntegerProperty eID;
    private SimpleIntegerProperty cID;
    private SimpleDoubleProperty subtotal;
    private SimpleDoubleProperty gratuity;
    private SimpleStringProperty billDate;


    public Receipt(int rID, int eID, int cID, double subtotal, double gratuity, String billDate) {
        this.rID = new SimpleIntegerProperty(rID);
        this.eID = new SimpleIntegerProperty(eID);
        this.cID = new SimpleIntegerProperty(cID);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.gratuity = new SimpleDoubleProperty(gratuity);
        this.billDate = new SimpleStringProperty(billDate);
    }

    public Receipt(int rID) {
        this.rID = new SimpleIntegerProperty(rID);
    }

    public int getRID() {
        return rID.get();
    }

    public void setRID(int rID) {
        this.rID.set(rID);
    }

    public int getEID() {
        return eID.get();
    }

    public void setEID(int eID) {
        this.eID.set(eID);
    }

    public int getCID() {
        return cID.get();
    }

    public void setCID(int cID) {
        this.cID.set(cID);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public double getGratuity() {
        return gratuity.get();
    }

    public void setGratuity(double gratuity) {
        this.gratuity.set(gratuity);
    }

    public String getBillDate() {
        return billDate.get();
    }

    public void setBillDate(String billDate) {
        this.billDate.set(billDate);
    }
}
