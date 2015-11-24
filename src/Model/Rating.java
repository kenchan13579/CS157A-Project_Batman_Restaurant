package Model;

/**
 * Created by phucnguyen on 11/24/15.
 */
public class Rating {
    private int cID;
    private int stars;
    private String feedback;

    public Rating(int cID, int stars, String feedback) {
        this.cID = cID;
        this.stars = stars;
        this.feedback = feedback;
    }

    public Rating(int cID) {
        this.cID = cID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
