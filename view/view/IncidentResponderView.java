package view;

import controller.IncidentController;
import controller.EvidenceController;
import controller.LogController;
import models.Incident;
import models.Evidence;
import models.Log;
import models.User;
import models.enums.Enumeration.IncidentStatus;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class IncidentResponderView {
    private final IncidentController incidentController;
    private final EvidenceController evidenceController;
    private final LogController logController;
    private final Scanner scanner;

    public IncidentResponderView() {
        this.incidentController = new IncidentController();
        this.evidenceController = new EvidenceController();
        this.logController = new LogController();
        this.scanner = new Scanner(System.in);
    }

    public void showIncidentResponderMenu(User user) {
        int choice=-1;
        do {
            System.out.println("\n=== Incident Responder Dashboard ===");
            System.out.println("1. View All Incidents");
            System.out.println("2. Add New Incident");
            System.out.println("3. Add Evidence to Incident");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Search Incidents");
            System.out.println("6. View My Assigned Incidents");
            System.out.println("7. Generate Incident Report");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewAllIncidents(user);
                    break;
                case 2:
                    addIncident(user);
                    break;
                case 3:
                    addEvidence(user);
                    break;
                case 4:
                    updateIncidentStatus(user);
                    break;
                case 5:
                    searchIncidents(user);
                    break;
                case 6:
                    viewAssignedIncidents(user);
                    break;
                case 7:
                    generateIncidentReport(user);
                    break;
                case 0:
                    logController.addLog(new Log(user.getUserId(), "Logged out",new Timestamp(System.currentTimeMillis())));
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void viewAllIncidents(User user) {
        List<Incident> incidents = incidentController.getAllIncidents();
        incidents.forEach(i ->
            System.out.println("Incident #" + i.getIncidentId() + ": " + i.getTitle() + " [" + i.getStatus() + "]")
        );
        logController.addLog(new Log(user.getUserId(), "VIEWED ALL INCIDENTS",new Timestamp(System.currentTimeMillis())));
    }

    private void addIncident(User user) {
        System.out.print("Incident Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        Incident incident = new Incident(0,title, description, LocalDate.now(),user.getUserId(),IncidentStatus.OPEN,-1);
        incidentController.addIncident(incident);

        logController.addLog(new Log(user.getUserId(), "ADDED NEW INCIDENT: " + title,new Timestamp(System.currentTimeMillis())));
        System.out.println("Incident added successfully.");
    }

    private void addEvidence(User user) {
        System.out.print("Incident ID: ");
        int incidentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Threat ID: ");
        int threatId = Integer.parseInt(scanner.nextLine());

        System.out.print("Evidence Description: ");
        String desc = scanner.nextLine();

        System.out.print("Evidence Type (File/Log/Command/...): ");
        String type = scanner.nextLine();

        Evidence evidence = new Evidence(
            0,
            incidentId,
            threatId,
            type,
            desc,
            user.getUserId(),
            LocalDate.now()
        );
        evidenceController.addEvidence(evidence);
        logController.addLog(new Log(user.getUserId(), "ADDED EVIDENCE TO INCIDENT#" + incidentId,new Timestamp(System.currentTimeMillis())));

        System.out.println("Evidence logged.");
    }

    private void updateIncidentStatus(User user) {
        System.out.print("Incident ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        IncidentStatus ins = null;
        while (ins == null) {
            System.out.println("Select Incident Status:");
            IncidentStatus[] instat = IncidentStatus.values();
            for(int i = 0; i < instat.length; i++)
            {
                System.out.println((i + 1) + ". " + instat[i]);
            }
            System.out.print("Enter choice: ");
            try
            {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= instat.length)
                {
                   ins = instat[choice - 1];
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

        incidentController.updateColumnIncident(id, "status", ins);
        logController.addLog(new Log(user.getUserId(), "UPDATED STATUS OF INCIDENT#" + id + " TO " + ins,new Timestamp(System.currentTimeMillis())));

        System.out.println("Status updated.");
    }

    private void searchIncidents(User user) {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        List<Incident> results = incidentController.searchIncidents(keyword);
        if (results.isEmpty()) {
            System.out.println("No incidents found.");
        } else {
            results.forEach(i ->
                System.out.println("Incident #" + i.getIncidentId() + ": " + i.getTitle() + " [" + i.getStatus() + "]")
            );
        }
        logController.addLog(new Log(user.getUserId(), "SEARCHED INCIDENTS WITH KEYWORD: " + keyword,new Timestamp(System.currentTimeMillis())));
    }

    private void viewAssignedIncidents(User user) {
        List<Incident> incidents = incidentController.getIncidentsByUser(user.getUserId());
        if (incidents.isEmpty()) {
            System.out.println("No incidents assigned to you.");
        } else {
            incidents.forEach(i ->
                System.out.println("Incident #" + i.getIncidentId() + ": " + i.getTitle() + " [" + i.getStatus() + "]")
            );
        }
        logController.addLog(new Log(user.getUserId(), "VIEWED ASSIGNED INCIDENTS",new Timestamp(System.currentTimeMillis())));
    }

    private void generateIncidentReport(User user) {
        System.out.print("Incident ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Incident incident = incidentController.getIncidentById(id);
        if (incident == null) {
            System.out.println("Incident not found.");
            return;
        }

        System.out.println("\n=== Incident Report ===");
        System.out.println("ID: " + incident.getIncidentId());
        System.out.println("Title: " + incident.getTitle());
        System.out.println("Description: " + incident.getDescription());
        System.out.println("Status: " + incident.getStatus());
        System.out.println("Reported By: " + incident.getReportedBy());
        System.out.println("Date: " + incident.getDateReported());
        System.out.println("=======================");

        logController.addLog(new Log(user.getUserId(), "GENERATED REPORT FOR INCIDENT#" + id,new Timestamp(System.currentTimeMillis())));
    }
}
