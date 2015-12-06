import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class Reservation {

    private SimpleIntegerProperty tID;
    private SimpleIntegerProperty cID;
    private SimpleIntegerProperty partySize;
    private SimpleStringProperty reservationDate;

    public Reservation(int tID) {
        this.tID = new SimpleIntegerProperty(tID);
    }

    public Reservation(int tID, int cID, int partySize, String reservationDate) {
        this.tID = new SimpleIntegerProperty(tID);
        this.cID = new SimpleIntegerProperty(cID);
        this.partySize = new SimpleIntegerProperty(partySize);
        this.reservationDate = new SimpleStringProperty(reservationDate);
    }

    public int getTID() {
        return tID.get();
    }

    public void setTID(int tID) {
        this.tID.set(tID);
    }

    public int getCID() {
        return cID.get();
    }

    public void setCID(int cID) {
        this.cID.set(cID);
    }

    public int getPartySize() {
        return partySize.get();
    }

    public void setPartySize(int partySize) {
        this.partySize.set(partySize);
    }

    public String getReservationDate() {
        return reservationDate.get();
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate.set(reservationDate);
    }
}
