package potaton.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private LocalDate eventDate;

    public Events(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + getEventDate() + ")");
    }
}