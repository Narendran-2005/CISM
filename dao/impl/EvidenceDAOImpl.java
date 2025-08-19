package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.interfaces.EvidenceDAO;
import models.Evidence;
import util.DatabaseConnection;

public class EvidenceDAOImpl implements EvidenceDAO{

    private static final String INSERT_EVIDENCE = "INSERT INTO evidence (evidence_id, incident_id, evidence_type, description, collected_by, collection_date) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_EVIDENCE_BY_ID = "SELECT * FROM evidence WHERE evidence_id = ?";
    private static final String SELECT_ALL_EVIDENCES = "SELECT * FROM evidence";
    private static final String UPDATE_ALLCOLUMNS_EVIDENCE = "UPDATE evidence SET incident_id = ?, evidence_type = ?, description = ?, collected_by = ?, collection_date = ? WHERE evidence_id = ?";
    private static final String DELETE_EVIDENCE = "DELETE FROM evidence WHERE evidence_id = ?";
    private static final String SELECT_EVIDENCE_BY_INCIDENT_ID = "SELECT * FROM evidence WHERE incident_id = ?";
    private static final String SELECT_EVIDENCE_BY_THREAT_ID = "SELECT DISTINCT e.* FROM evidence e JOIN threats t ON e.incident_id = t.incident_id WHERE t.threat_id = ? GROUP BY e.evidence_id";

    @Override
    public void addEvidence(Evidence evidence)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_EVIDENCE))
        {
            pstmt.setInt(1,evidence.getEvidenceId());
            pstmt.setInt(2,evidence.getIncidentId());
            pstmt.setString(3,evidence.getEvidenceType());
            pstmt.setString(4,evidence.getDescription());
            pstmt.setInt(5,evidence.getCollectedBy());
            pstmt.setDate(6,Date.valueOf(evidence.getCollectedDate()));
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public Evidence getEvidenceById(int id)
    {
       try(Connection conn = DatabaseConnection.getConnection();
       PreparedStatement pstmt = conn.prepareStatement(SELECT_EVIDENCE_BY_ID))
       {
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new Evidence(rs.getInt("evidence_id"),
                rs.getInt("incident_id"),
                rs.getInt("threat_id"),
                rs.getString("evidence_type"),
                rs.getString("description"),
                rs.getInt("collected_by"),
                rs.getDate("collected_date").toLocalDate());
            }
       }
       catch(SQLException e)
       {
            e.printStackTrace();
       }
       return null;
    }

    @Override
    public List<Evidence> getAllEvidence()
    {
        List<Evidence> evidences = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_EVIDENCES);)
        {    
            while(rs.next())
            {
                evidences.add(new Evidence(rs.getInt("evidence_id"),
                rs.getInt("incident_id"),
                rs.getInt("threat_id"),
                rs.getString("evidence_type"),
                rs.getString("description"),
                rs.getInt("collected_by"),
                rs.getDate("collected_date").toLocalDate()));
            }
       }
       catch(SQLException e)
       {
            e.printStackTrace();
       } 
       return null;
    }
    
    @Override
    public void updateEvidence(int id,Evidence evidence)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_EVIDENCE))
        {
            pstmt.setInt(1,evidence.getIncidentId());
            pstmt.setString(2,evidence.getEvidenceType());
            pstmt.setString(3,evidence.getDescription());
            pstmt.setInt(4,evidence.getCollectedBy());
            pstmt.setDate(5,Date.valueOf(evidence.getCollectedDate()));
            pstmt.setInt(6,id);
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateColumnEvidence(int id, String column, Object val,String datatype)
    {
        final String UPDATE_EVIDENCE ="UPDATE evidence SET " + column + " = ? WHERE evidence_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_EVIDENCE))
        {
            switch (datatype.toLowerCase()) 
            {
                case "int":
                pstmt.setInt(1, (Integer)val);
                break;

                case "enum":
                pstmt.setString(1, (String)val);
                break;

                case "date":
                pstmt.setDate(1, java.sql.Date.valueOf((LocalDate) val));
                break;

                default:
                throw new IllegalArgumentException("Unsupported data type");

            }
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteEvidence(int id)
    {
        try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_EVIDENCE)) 
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Evidence> getEvidencebyIncidentID(int incId)
    {
        List<Evidence> evi = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_EVIDENCE_BY_INCIDENT_ID))
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                evi.add(new Evidence(rs.getInt("evidence_id"),
                rs.getInt("incident_id"),
                rs.getInt("threat_id"),
                rs.getString("evidence_type"),
                rs.getString("description"),
                rs.getInt("collected_by"),
                rs.getDate("collected_date").toLocalDate()));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return evi;
    }

    @Override
    public List<Evidence> getEvidencebyThreatId(int threatId)
    {
        List<Evidence> evi = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_EVIDENCE_BY_THREAT_ID))
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                evi.add(new Evidence(rs.getInt("evidence_id"),
                rs.getInt("incident_id"),
                rs.getInt("threat_id"),
                rs.getString("evidence_type"),
                rs.getString("description"),
                rs.getInt("collected_by"),
                rs.getDate("collected_date").toLocalDate()));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return evi;
    }
}
/*
 * void addEvidence(Evidence evidence);
    Evidence getEvidenceById(int id);
    List<Evidence> getAllEvidence();
    void updateEvidence(Evidence evidence);
    void deleteEvidence(int id);

    private int evidenceId;
    private int incidentId;
    private int threatId;
    private String evidenceType;
    private int collectedBy;
    private String description;
    private Date collectedDate;
    
    evidence_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    evidence_type VARCHAR(100) NOT NULL,
    description TEXT,
    collected_by INT,
    collection_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE,
    FOREIGN KEY (collected_by) REFERENCES users(user_id) ON DELETE SET NULL
 */