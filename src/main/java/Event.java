import java.time.LocalDate;

public class Event {

    String name;
    String description;
    LocalDate date;
    boolean isDone;

    public Event(String name, String description, LocalDate date, boolean isDone) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
