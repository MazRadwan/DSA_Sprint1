public class TaskList {
    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;

    public TaskList() {
        this.head = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);

        if(head == null){
            head = newNode;
            return;
        }
        // add to the end of the list
        Node current = head;
        while(current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public boolean markTaskCompleted(String description) {
        Node current = head;
        while(current != null){
            if(current.task.getDescription().equals(description)){
                current.task.markCompleted();
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public void printTasks() {
        if (head == null) {
            System.out.println("No Tasks found");
            return;
        }
        Node current = head;
        int taskNum = 1;
        while(current != null) {
            System.out.println(taskNum + ". " + current.task);
            current = current.next;
            taskNum++;
        }
    }
    public boolean isEmpty() {
        return head == null;
    }
}

