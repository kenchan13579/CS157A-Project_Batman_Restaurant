package Model;

public class Table {
    private int tID;
    private int eID;
    private int seats;
    private boolean available;

    public Table() {
        tID = 0;
        eID = 0;
        seats = 0;
        available = false;
    }

    public Table(int tID, int eID, int seats, boolean available) {
        this.tID = tID;
        this.eID = eID;
        this.seats = seats;
        this.available = available;
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
