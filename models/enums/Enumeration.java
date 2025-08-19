package models.enums;

public class Enumeration {
    
    public enum Role
    {
        ADMIN,
        SECURITY_ANALYST,
        SECURITY_ENGINEER,
        INCIDENT_RESPONDER,
        MALWARE_ANALYST,
        PENTESTER,
        DF_EXAMINER;
    }

    public enum IncidentStatus
    {
        OPEN,
        UNDER_INVESTIGATION,
        RESOLVED,
        CLOSED;
    }

    public enum InvestigationStatus
    {
        ONGOING,
        COMPLETED;
    }

    public enum Severity
    {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL;
    }

    public enum AssetStatus
    {
        COMPROMISED,
        SAFE,
        UNDER_INVESTIGATION;
    }

    public enum Gender
    {
        MALE,
        FEMALE,
        OTHER;
    }

    public enum RecievedSupport
    {
        YES,
        NO;
    }

    public enum MitigationStatus {
        IMPLEMENTED,
        YET_TO_IMPLEMENT;
    }

    public enum Effectiveness {
        EFFECTIVE,
        PARTIALLY_EFFECTIVE,
        NOT_EFFECTIVE;
    }

    public enum RiskLevel
    {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL;
    }
}
