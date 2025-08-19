package view;

import controller.UserController;
import controller.LogController;
import controller.IncidentController;
import models.User;

import java.util.List;
import java.util.Scanner;
import models.enums.Enumeration.Role;

public class AdminView {

    private final UserController userController;
    private final LogController logController;
    private final IncidentController incidentController;
    private final Scanner scanner;

    public AdminView()
    {
        this.userController = new UserController();
        this.logController =new LogController();
        this.incidentController =new IncidentController();
        this.scanner=new Scanner(System.in);
    }

    public void showAdminMenu(User user)
    {
        int ch=-1;
        
        do{
            System.out.println("\n=== Admin Dashboard ===");
            System.out.println("1. View All Users");
            System.out.println("2. Add New User");
            System.out.println("3. Delete existing User");
            System.out.println("4. Update existing User");
            System.out.println("5. View Logs");
            System.out.println("6. View User Profile");
            System.out.println("7. Assign Incidents");
            System.out.println("8. View Unassigned Incidents");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
             
            try 
            {
                    ch = Integer.parseInt(scanner.nextLine());
            } 
            catch (Exception e) {
                    System.out.println("Invalid input.");
            }

            switch(ch)
            {
                case 1:
                    List<User> users = userController.getAllUsers();
                    users.forEach(u ->
                        System.out.println("ID: " + u.getUserId() + " | " + u.getUsername() + " (" + u.getRole() + ")")
                    );
                    break;

                case 2:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Role role = null;
                    while (role == null) {
                        System.out.println("Select Role:");
                        Role[] roles = Role.values();
                        for(int i = 0; i < roles.length; i++)
                        {
                            System.out.println((i + 1) + ". " + roles[i]);
                        }
                        System.out.print("Enter choice: ");
                        try
                        {
                            int choice = Integer.parseInt(scanner.nextLine());
                            if (choice >= 1 && choice <= roles.length)
                            {
                                role = roles[choice - 1];
                            }
                            else
                            {
                                System.out.println("Invalid selection.");
                            }
                        } 
                        catch (NumberFormatException e)
                        {
                            System.out.println("Please enter a number.");
                        }
                    }
                    User newUser = new User(0, username, password, role, email);
                    if (userController.registerUser(newUser)) {
                        System.out.println("User added.");
                    } else {
                        System.out.println("Username already exists.");
                    }
                    break;

                case 3:
                    System.out.print("Enter User ID to delete: ");
                    int delId = Integer.parseInt(scanner.nextLine());
                    userController.deleteUser(delId);
                    System.out.println("User deleted.");
                    break;

                case 4:
                    System.out.print("Enter User ID to update: ");
                    int updId = Integer.parseInt(scanner.nextLine());

                    User existing = userController.getUserById(updId);
                    if (existing == null) 
                    {
                        System.out.println("User not found.");
                        break;
                    }

                    System.out.println("Enter a choice to Update:");
                    System.out.println("1. All Details of the User");
                    System.out.println("2. Specific Detail of the User");

                    int cho = -1;
                    while (cho != 1 && cho != 2) 
                    {
                        try 
                        {
                            cho = Integer.parseInt(scanner.nextLine());
                            if (cho != 1 && cho != 2) 
                            {
                                System.out.println("Invalid choice. Enter 1 or 2.");
                            }
                        } 
                        catch (NumberFormatException e) 
                        {
                            System.out.println("Please enter a number.");
                        }
                    }

                    if (cho == 1) 
                    {
                        System.out.print("New Username: ");
                        existing.setUsername(scanner.nextLine());

                        System.out.print("New Password: ");
                        existing.setPasswordHash(scanner.nextLine());

                        System.out.print("New Email: ");
                        existing.setEmail(scanner.nextLine());

                        System.out.println("New Role:");
                        Role[] roles = Role.values();
                        for (int i = 0; i < roles.length; i++) 
                        {
                            System.out.println((i + 1) + ". " + roles[i]);
                        }
                        int roleChoice = Integer.parseInt(scanner.nextLine());
                        existing.setRole(roles[roleChoice - 1]);

                        userController.updateUser(existing, updId);
                        System.out.println("User updated.");

                    } 
                    else if (cho == 2) 
                    {
                        System.out.println("Select Column that has to be Updated:");
                        System.out.println("1. Username");
                        System.out.println("2. Email");
                        System.out.println("3. Role");

                        int ucho = -1;
                        while (ucho < 1 || ucho > 3)
                        {
                            try
                            {
                                ucho = Integer.parseInt(scanner.nextLine());
                                if (ucho < 1 || ucho > 3)
                                {
                                    System.out.println("Invalid number, Try again.");
                                }
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Please enter a number.");
                            }
                        }

                        String col = "";
                        Object val = null;

                        if (ucho == 1) 
                        {
                            col = "username";
                            System.out.print("Enter new Username: ");
                            val = scanner.nextLine();
                        } 
                        else if (ucho == 2) 
                        {
                            col = "email";
                            System.out.print("Enter new Email: ");
                            val = scanner.nextLine();
                        } 
                        else if (ucho == 3) 
                        {
                            col = "role";
                            Role[] roles = Role.values();
                            for (int i = 0; i < roles.length; i++) {
                                System.out.println((i + 1) + ". " + roles[i]);
                            }
                            int choice = Integer.parseInt(scanner.nextLine());
                            val = roles[choice - 1].name(); 
                        }

                        userController.updateColumnUser(updId, col, val);
                        System.out.println("User updated successfully.");
                    }
                    break;

                case 5:
                    logController.getAllLogs().forEach(log ->
                        System.out.println("{ ID: "+log.getLogId()+" User : "+userController.getUsernameById(log.getUserId())+" Action: "+log.getAction()+"("+log.getTimeStamp()+") }")
                    );
                    break;

                case 6:
                    System.out.print("Enter username: ");
                    String us=scanner.nextLine();
                    UserView u=new UserView();
                    u.showUserProfile(us);
                    break;

                case 7:
                    System.out.print("Enter Incident ID: ");
                    int incId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter User ID: ");
                    int usrId = Integer.parseInt(scanner.nextLine());
                    incidentController.assignIncidents(incId,usrId);
                    break;

                case 8:
                    
                    break;

                case 0:
                    System.out.println("Logging out...");
                    userController.logout(user);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }while(ch!=0);
    }
    
}
