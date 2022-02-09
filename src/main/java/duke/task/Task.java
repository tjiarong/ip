package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public int getTaskCount(){
        return taskCount;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + getDescription());
    }

}
