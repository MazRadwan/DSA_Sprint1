import java.util.Scanner;

public class Main {
    private static final int MAX_USERS = 10;
    private static User[] users = new User[MAX_USERS];
    private static int userCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    markTaskCompleted();
                    break;
                case 4:
                    viewAllUsersTasks();
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Todo List Manager ===");
        System.out.println("1. Create New User");
        System.out.println("2. Add Task to User");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. View All User's Tasks");
        System.out.println("5. View User List");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid choice. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void createUser() {
        if (userCount == MAX_USERS) {
            System.out.println("Maximum number of users reached");
            return;
        }
        scanner.nextLine(); // clear buffer
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        // Check for duplicate names
        if (findUser(name) != null) {
            System.out.println("User already exists");
            return;
        }
        users[userCount] = new User(name);
        userCount++;
        System.out.println("User created successfully");
    }

    private static void addTask() {
        User user = selectUser();
        if (user == null) return;

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        user.addTask(description);
        System.out.println("Task added successfully!");
    }

    private static void markTaskCompleted() {
        User user = selectUser();
        if (user == null) return;

        // First check if user has any tasks
        if (!user.hasTasks()) {
            System.out.println("No tasks found for this user.");
            return;
        }

        // Show current tasks
        System.out.println("\nCurrent tasks for " + user.getName() + ":");
        user.printTasks();

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter task description to mark as completed: ");
        String description = scanner.nextLine();

        user.markTaskCompleted(description);
    }

    private static void viewAllUsers() {
        if (userCount == 0) {
            System.out.println("No users found");
            return;
        }
        System.out.println("\nUsers:");
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].getName());
        }
    }

    private static User selectUser() {
        if (userCount == 0) {
            System.out.println("No users found. Please create a user first.");
            return null;
        }

        viewAllUsers();
        System.out.print("Enter user number: ");  // Changed println to print
        int userNum = getUserChoice();

        if (userNum < 1 || userNum > userCount) {
            System.out.println("Invalid user number");
            return null;
        }
        return users[userNum - 1];
    }

    private static User findUser(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    private static void viewAllUsersTasks() {
        if (userCount == 0) {
            System.out.println("No users found in the system.");
            return;
        }

        System.out.println("\n=== All Users' Tasks ===");
        boolean hasAnyTasks = false;

        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            System.out.println("\n--- " + user.getName() + "'s Tasks ---");
            if (user.hasTasks()) {
                user.printTasks();
                hasAnyTasks = true;
            } else {
                System.out.println("No tasks.");
            }
        }

        if (!hasAnyTasks) {
            System.out.println("\nNo tasks found for any user.");
        }
    }
}