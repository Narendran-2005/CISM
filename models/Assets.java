package models;

import models.enums.Enumeration.AssetStatus;

public class Assets {
    private int assetId;
    private int threatId;
    private int incidentId;
    private String assetName;
    private String assetType;
    private int owner;
    private AssetStatus status;
    
    public Assets(int assetId, int threatId, int incidentId, String assetName, String assetType, int owner, AssetStatus status)
    {
        this.assetId = assetId;
        this.threatId = threatId;
        this.incidentId = incidentId;
        this.assetName = assetName;
        this.assetType = assetType;
        this.owner = owner;
        this.status = status;
    }

    public int getAssetId() {return assetId;}
    public int getThreatId() {return threatId;}
    public int getIncidentId() {return incidentId;}
    public String getAssetName() {return assetName;}
    public String getType() {return assetType;}
    public int getOwner() {return owner;}
    public AssetStatus getStatus() {return status;}

    public void setAssetId(int assetId) {this.assetId = assetId;}
    public void setThreatId(int threatId) {this.threatId = threatId;}
    public void setIncidentId(int incidentId) {this.incidentId = incidentId;}
    public void setAssetName(String assetName) {this.assetName = assetName;}
    public void setAssetType(String assetType) {this.assetType = assetType;}
    public void setOwner(int owner) {this.owner = owner;}
    public void setStatus(AssetStatus status) {this.status = status;}

}
