import java.io.Serializable;

/**
 * Created By Sagun Pandey
 */
public class Event implements Serializable {

    private int month;

    private int date;

    private String description;

    public Event(int month, int date, String description) {
        this.month = month;
        this.date = date;
        this.description = description;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event[month=" + month + ", " +
                "date=" + date + ", " +
                "description='" + description + "']";
    }
}
