package models;

import java.util.Date;
import models.enums.Enumeration.InvestigationStatus;

public class Investigation {
    private int investigationId;
    private int incidentId;
    private int investigatorId;
    private Date stdate;
    private Date endate;
    private InvestigationStatus status;
    private String findings;

    public Investigation(int investigationId, int incidentId, int investigatorId, Date stdate, Date endate, InvestigationStatus status, String findings)
    {
        this.investigationId = investigationId;
        this.incidentId = incidentId;
        this.investigatorId = investigatorId;
        this.stdate = stdate;
        this.endate = endate;
        this.status = status;
        this.findings = findings;
    }

    public int getInvestigationId() { return investigationId; }
    public int getIncidentId() { return incidentId; }
    public int getInvestigatorId() { return investigatorId; }
    public Date getStartDate() { return stdate; }
    public Date getEndDate() { return endate; }
    public InvestigationStatus getStatus() { return status; }
    public String getFindings() { return findings; } 
    
    public void setInvestigationId(int investigationId) {this.investigationId = investigationId;}
    public void setIncidentId(int incidentId) {this.incidentId = incidentId;}
    public void setInvestigatorId(int investigatorId) {this.investigatorId = investigatorId;}
    public void setStartDate(Date stdate) { this.stdate = stdate; }
    public void setEndDate(Date endate) { this.endate = endate; }
    public void setStatus(InvestigationStatus status) {this.status = status;}
    public void setFindings(String findings) {this.findings = findings;}
}
