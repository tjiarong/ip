package potaton.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate period;

    public Events(String description, LocalDate period) {
        super(description);
        this.period = period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public String getPeriod() {
        return period.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + getPeriod() + ")");
    }
}