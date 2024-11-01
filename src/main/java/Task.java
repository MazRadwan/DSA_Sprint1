public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

//    public boolean IsCompleted() {
//        return isCompleted;
//    }
    public void markCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return description + "[" + (isCompleted ? "Completed" : "Pending") + "]";
    }
}
