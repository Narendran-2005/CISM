package view;

import controller.UserController;
import models.User;
import util.PasswordUtil;
import models.enums.Enumeration.Role;

import java.util.Scanner;

public class UserView {
    private final UserController userController;
    private final Scanner scanner;

    public UserView() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }

    public void showLoginMenu() {
        System.out.println("\n===== User Login =====");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        User user = userController.login(username, password);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
            RoleRouter.routeUser(user);
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public void showRegistrationMenu() {
        System.out.println("\n===== Register New User =====");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Select role using enum
        Role role = null;
        while (role == null) {
            System.out.println("Select Role:");
            Role[] roles = Role.values();
            for (int i = 0; i < roles.length; i++) {
                System.out.println((i + 1) + ". " + roles[i]);
            }
            System.out.print("Enter choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= roles.length) {
                    role = roles[choice - 1];
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }

        // Hash the password before saving
        String hashedPassword = PasswordUtil.hashPassword(password);

        boolean registered = userController.registerUser(new User(0, username, hashedPassword, role, email));

        if (registered) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists. Try a different one.");
        }
    }

    public void showUserProfile(String username) {
        User user = userController.getUserByUsername(username);
        if (user != null) {
            System.out.println("\n===== User Profile =====");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email   : " + user.getEmail());
            System.out.println("Role    : " + user.getRole());
        } else {
            System.out.println("User not found.");
        }
    }
}
