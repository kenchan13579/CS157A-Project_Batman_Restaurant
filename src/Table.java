import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Table {
    private SimpleIntegerProperty tID;
    private SimpleIntegerProperty eID;
    private SimpleIntegerProperty seats;
    private SimpleBooleanProperty available;

    public Table() {
        tID = new SimpleIntegerProperty(0);
        eID = new SimpleIntegerProperty(0);
        seats = new SimpleIntegerProperty(0);
        available = new SimpleBooleanProperty(false);

    }

    public Table(int tID, int eID, int seats, boolean available) {
        this.tID = new SimpleIntegerProperty(tID);
        this.eID = new SimpleIntegerProperty(eID);
        this.seats = new SimpleIntegerProperty(seats);
        this.available = new SimpleBooleanProperty(available);
    }

    public int getTID() {
        return tID.get();
    }

    public void setTID(int tID) {
        this.tID.set(tID);
    }

    public int getEID() {
        return eID.get();
    }

    public void setEID(int eID) {
        this.eID.set(eID);
    }

    public int getSeats() {
        return seats.get();
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }

    public boolean isAvailable() {
        return available.get();
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }
}
