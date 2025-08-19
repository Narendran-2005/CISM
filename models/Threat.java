package models;
import java.time.LocalDate;
import models.enums.Enumeration.Severity;;
public class Threat 
{
    private int threatId; 
    private String threatName;
    private String threatType;
    private Severity severity;
    private String threatDescription;
    private LocalDate detectedDate;
    private int relatedIncident;
    
    public Threat(int threatId,String threatName,String threatType,Severity severity,String threatDescription,LocalDate detectedDate,int relatedIncident)
    {
        this.threatId = threatId ; 
        this.threatName = threatName;
        this.threatType = threatType;
        this.severity = severity;
        this.threatDescription = threatDescription;
        this.detectedDate = detectedDate;
        this.relatedIncident = relatedIncident;
    }
    public Threat(int threatId2, String name, String type, Severity severity2, String description, String string,
            int incidentId) {
        
    }
    public int getThreatId(){return threatId;}
    public String getName(){return threatName;}
    public String getType(){return threatType;}
    public Severity getSeverity(){return severity;}
    public String getDescription(){return threatDescription;}
    public LocalDate getDetectedDate(){return detectedDate;}
    public int getRelatedIncident(){return relatedIncident;}

    public void setThreatId(int threatId) {this.threatId = threatId;}
    public void setThreatName(String threatName) {this.threatName = threatName;}
    public void setThreatType(String threatType) {this.threatType = threatType;}
    public void setSeverity(Severity severity) {this.severity = severity;}
    public void setThreatDescription(String threatDescription) {this.threatDescription = threatDescription;}
    public void setDetectedDate(LocalDate detectedDate) {this.detectedDate = detectedDate;}
    public void setRelatedIncident(int relatedIncident) {this.relatedIncident = relatedIncident;}
}
