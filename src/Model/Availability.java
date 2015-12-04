package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by David on 12/4/2015.
 */
public class Availability {
    private SimpleIntegerProperty tablesAvailable;
    private SimpleStringProperty date;

    public Availability(int tablesAvailable, String date) {
        this.tablesAvailable = new SimpleIntegerProperty(tablesAvailable);
        this.date = new SimpleStringProperty(date);
    }

    public int getTablesAvailable() {
        return tablesAvailable.get();
    }

    public void setTablesAvailable(int tID) {
        this.tablesAvailable.set(tID);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String firstName) {
        this.date.set(firstName);
    }
}
