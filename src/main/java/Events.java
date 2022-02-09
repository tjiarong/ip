public class Events extends Task {
    private String period;

    public Events(String description, String period) {
        super(description);
        this.period = period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (at: " + getPeriod() + ")");
    }
}