public class User {
    private String name;
    private TaskList todoList;

    public User(String name) {
        this.name = name;
        this.todoList = new TaskList();
    }

    public String getName() {
        return name;
    }

    public boolean hasTasks() {
        return !todoList.isEmpty();
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        todoList.addTask(newTask);
    }

    public void markTaskCompleted(String description) {
        if (todoList.markTaskCompleted(description)) {
            System.out.println("Task marked as completed successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void printTasks() {
        todoList.printTasks();
    }
}