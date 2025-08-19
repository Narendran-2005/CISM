package models;
import models.enums.Enumeration.IncidentStatus;

import java.time.LocalDate;
public class Incident
 {
    private int incidentId;
    private int reportedBy;
    private String title;
    private String description;
    private IncidentStatus status;
    private LocalDate dateReported;
    private int assignedTo;

    public Incident(int incidentId, String title, String description, LocalDate dateReported, int reportedBy, IncidentStatus status,int assignedTo)
    {
        this.incidentId = incidentId;
        this.title = title;
        this.description = description;
        this.reportedBy = reportedBy;
        this.status = status;
        this.dateReported = dateReported;
        this.assignedTo = assignedTo;
    }

    public int getIncidentId() { return incidentId; }
    public int getReportedBy() { return reportedBy; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public IncidentStatus getStatus() { return status; }
    public LocalDate getDateReported() { return dateReported; }
    public int getAssignedTo() { return assignedTo; }
    
    public void setStatus(IncidentStatus Status) { this.status = Status; }
    public void setReportedBy(int reportedBy){this.reportedBy = reportedBy; }
    public void setIncidentId(int incidentId) {this.incidentId = incidentId; }
    public void setTitle(String title) {this.title = title; }
    public void setDescription(String description) {this.description = description; }
    public void setDateReported(LocalDate dateReported) {this.dateReported = dateReported; }
    public void setAssignedTo(int assignedTo){this.assignedTo = assignedTo; }
}