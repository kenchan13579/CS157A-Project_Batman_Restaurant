package Model;

public class Receipt {

    private int rID;
    private int eID;
    private int cID;
    private double subtotal;
    private double gratuity;
    private String billDate;


    public Receipt(int rID, int eID, int cID, double subtotal, double gratuity, String billDate) {
        this.rID = rID;
        this.eID = eID;
        this.cID = cID;
        this.subtotal = subtotal;
        this.gratuity = gratuity;
        this.billDate = billDate;
    }

    public Receipt(int rID) {
        this.rID = rID;
    }

    public int getrID() {
        return rID;
    }

    public void setrID(int rID) {
        this.rID = rID;
    }

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getGratuity() {
        return gratuity;
    }

    public void setGratuity(double gratuity) {
        this.gratuity = gratuity;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
