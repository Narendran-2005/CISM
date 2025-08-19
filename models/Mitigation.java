package models;
import java.time.LocalDate;
import models.enums.Enumeration.Effectiveness;
import models.enums.Enumeration.MitigationStatus;
public class Mitigation {
    private int mitigationId;
    private int threatId;
    private String actionTaken;
    private int implementedBy;
    private LocalDate implementedDate;
    private MitigationStatus mitigationStatus;
    private Effectiveness effectiveness;

    
    public Mitigation(int mitigationId, int threatId, String actionTaken, int implementedBy,LocalDate implementedDate, MitigationStatus mitigationStatus, Effectiveness effectiveness)
    {
        this.mitigationId = mitigationId;
        this.threatId = threatId;
        this.actionTaken = actionTaken;
        this.implementedBy = implementedBy;
        this.implementedDate = implementedDate;
        this.mitigationStatus = mitigationStatus;
        this.effectiveness = effectiveness;
    }

    public int getMitigationId(){return mitigationId;}
    public int getThreatId(){return threatId;}
    public String getActionTaken(){return actionTaken;}
    public int getImplementedBy() {return implementedBy;}
    public LocalDate getImplementedDate() {return implementedDate;}
    public MitigationStatus getMitigationStatus(){return mitigationStatus;}
    public Effectiveness getEffectiveness() {return effectiveness;}
    
    public void setMitigationId(int mitigationId) {this.mitigationId = mitigationId;}
    public void setThreatId(int threatId) {this.threatId = threatId;}
    public void setActionTaken(String actionTaken){this.actionTaken = actionTaken;}
    public void setImplementedDate(LocalDate implementedDate) {this.implementedDate = implementedDate;}
    public void setImplementedBy(int implementedBy) {this.implementedBy = implementedBy;}
    public void setMitigationStatus(MitigationStatus mitigationStatus){this.mitigationStatus = mitigationStatus;}
    public void setEffectiveness(Effectiveness effectiveness) {this.effectiveness = effectiveness;}




}
