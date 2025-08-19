package models;

import java.time.LocalDate;


public class Evidence {
    private int evidenceId;
    private int incidentId;
    private int threatId;
    private String evidenceType;
    private int collectedBy;
    private String description;
    private LocalDate collectedDate;

    public Evidence(int evidenceId, int incidentId, int threatId, String evidenceType, String description, int collectedBy, LocalDate collectedDate)
    {
        this.evidenceId = evidenceId;
        this.incidentId = incidentId;
        this.threatId = threatId;
        this.description = description;
        this.evidenceType = evidenceType;
        this.collectedBy = collectedBy;
        this.collectedDate = collectedDate;
    }
    public int getEvidenceId(){return evidenceId;}
    public int getIncidentId(){return incidentId;}
    public int getThreatId(){return threatId;}
    public String getEvidenceType(){return evidenceType;}
    public int getCollectedBy(){return collectedBy;}
    public String getDescription(){return description;}
    public LocalDate getCollectedDate(){return collectedDate;}
    
    public void setEvidenceId(int evidenceId) {this.evidenceId = evidenceId;}
    public void setIncidentId(int incidentId) {this.incidentId = incidentId;}
    public void setThreatId(int threatId) {this.threatId = threatId;}
    public void setEvidenceType(String evidenceType){this.evidenceType = evidenceType;}
    public void setCollectedBy(int collectedBy) {this.collectedBy = collectedBy;}
    public void setDescription(String description) {this.description = description;}
    public void setCollectedDate(LocalDate collectedDate) {this.collectedDate = collectedDate;}
   
}