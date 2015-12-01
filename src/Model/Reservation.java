package Model;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class Reservation {

    private int tID;
    private int cID;
    private int partySize;
    private String reservationDate;

    public Reservation(int tID) {
        this.tID = tID;
    }

    public Reservation(int tID, int cID, int partySize, String reservationDate) {
        this.tID = tID;
        this.cID = cID;
        this.partySize = partySize;
        this.reservationDate = reservationDate;
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
