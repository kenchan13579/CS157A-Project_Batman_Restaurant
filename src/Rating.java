import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class Rating {
    private SimpleIntegerProperty cID;
    private SimpleIntegerProperty stars;
    private SimpleStringProperty feedback;

    public Rating() {
        this.cID = new SimpleIntegerProperty();
        this.stars = new SimpleIntegerProperty();
        this.feedback = new SimpleStringProperty();
    }

    public Rating(int cID, int stars, String feedback) {
        this.cID = new SimpleIntegerProperty(cID);
        this.stars = new SimpleIntegerProperty(stars);
        this.feedback = new SimpleStringProperty(feedback);
    }

    public Rating(int cID) {
        this.cID = new SimpleIntegerProperty(cID);
    }

    public int getCID() {
        return cID.get();
    }

    public void setCID(int cID) {
        this.cID.set(cID);
    }

    public int getStars() {
        return stars.get();
    }

    public void setStars(int stars) {
        this.stars.set(stars);
    }

    public String getFeedback() {
        return feedback.get();
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }
}
