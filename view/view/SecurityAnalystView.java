package view;

import controller.ThreatController;
import controller.IncidentController;
import models.Threat;
import models.Incident;
import models.User;

import java.util.List;
import java.util.Scanner;

import models.enums.Enumeration.Severity;;

public class SecurityAnalystView {
    private final ThreatController threatController;
    private final IncidentController incidentController;
    private final Scanner scanner;

    public SecurityAnalystView() {
        this.threatController = new ThreatController();
        this.incidentController = new IncidentController();
        this.scanner = new Scanner(System.in);
    }

    public void showAnalystMenu(User user) {
        int choice=-1;
        do {
            System.out.println("\n=== Security Analyst Dashboard ===");
            System.out.println("1. View All Incidents");
            System.out.println("2. View All Threats");
            System.out.println("3. Update Threat Severity");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                            }

        } while (choice != 0);
    }
}
