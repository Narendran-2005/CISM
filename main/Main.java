
package main;

import view.UserView;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();

        int choice=-1;
        do {
            System.out.println("\n=== Welcome to Cybercrime Incident Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try
            {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice)
            {
                case 1:
                    userView.showLoginMenu();
                    break;
                case 2:
                    userView.showRegistrationMenu();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println(" Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }
}

