/**
 * Created by Murzynas on 2017-04-28.
 */
public class Post {
    private int id;
    private String text;
    private double sentiment;

    public Post() {}

    public Post(int id, String text) {
        this.id = id;
        this.text = text;
        this.sentiment = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }
}
