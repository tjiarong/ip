package potaton.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDate() + ")";
    }
}
