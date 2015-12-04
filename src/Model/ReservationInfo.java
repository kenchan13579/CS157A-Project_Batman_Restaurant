package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by David on 12/4/2015.
 */
public class ReservationInfo {

    private SimpleIntegerProperty tID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleIntegerProperty partySize;
    private SimpleIntegerProperty seats;
    private SimpleStringProperty reservationDate;

    public ReservationInfo(int tID,
                           String firstName,
                           String lastName,
                           int partySize,
                           int seats,
                           String reservationDate) {
        this.tID = new SimpleIntegerProperty(tID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.partySize = new SimpleIntegerProperty(partySize);
        this.seats = new SimpleIntegerProperty(seats);
        this.reservationDate = new SimpleStringProperty(reservationDate);

    }

    public int getTID() {
        return tID.get();
    }

    public void setTID(int tID) {
        this.tID.set(tID);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastNAme(String lastName) {
        this.lastName.set(lastName);
    }

    public int getPartySize() {
        return partySize.get();
    }

    public void setPartySize(int partySize) {
        this.partySize.set(partySize);
    }

    public int getSeats() {
        return seats.get();
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }

    public String getReservationDate() {
        return reservationDate.get();
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate.set(reservationDate);
    }
}
