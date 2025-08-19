package view;

import controller.MitigationController;
import controller.AssetController;
import controller.ThreatController;
import models.Assets;
import models.Mitigation;
import models.Threat;
import models.User;
import models.enums.Enumeration.Effectiveness;
import models.enums.Enumeration.MitigationStatus;

import java.util.List;
import java.util.Scanner;

public class SecurityEngineerView {
    private final MitigationController mitigationController;
    private final AssetController assetController;
    private final ThreatController threatController;
    private final Scanner scanner;

    public SecurityEngineerView() {
        this.mitigationController = new MitigationController();
        this.assetController = new AssetController();
        this.threatController = new ThreatController();
        this.scanner = new Scanner(System.in);
    }

    public void showEngineerMenu(User user) {
        int choice=-1;
        do {
            System.out.println("\n=== Security Engineer Dashboard ===");
            System.out.println("1. View All Threats");
            System.out.println("2. View Affected Assets");
            System.out.println("3. Apply Mitigation");
            System.out.println("4. View Applied Mitigations");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                
            }

        } while (choice != 0);
    }
}
