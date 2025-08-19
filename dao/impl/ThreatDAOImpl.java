package dao.impl;

import dao.interfaces.ThreatDAO;
import models.Threat;
import models.enums.Enumeration.Severity;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ThreatDAOImpl implements ThreatDAO{

    private static final String INSERT_THREAT = "INSERT INTO threats (threat_id, threat_name, threat_type, severity, description, detected_date, Threat_id) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_THREAT_BY_ID = "SELECT * FROM threats WHERE threat_id = ?";
    private static final String SELECT_ALL_THREATS = "SELECT * FROM threats";
    private static final String UPDATE_ALLCOLUMNS_THREAT = "UPDATE threats SET threat_name = ?, threat_type = ?, severity = ?, description = ?, detected_date = ?,Threat_id = ? WHERE threat_id = ?";
    private static final String DELETE_THREAT = "DELETE FROM threats WHERE threat_id = ?";
    
    public void addThreat(Threat threat)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_THREAT)){
                pstmt.setInt(1, threat.getThreatId());
                pstmt.setString(2, threat.getName());
                pstmt.setString(3, threat.getType());
                pstmt.setString(4, threat.getSeverity().name());
                pstmt.setString(5, threat.getDescription());
                pstmt.setDate(6, Date.valueOf(threat.getDetectedDate()));
                pstmt.setInt(7, threat.getRelatedIncident());
                pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

     public Threat getThreatById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_THREAT_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Threat(rs.getInt("threat_id"), 
                rs.getString("threat_name"), 
                rs.getString("threat_type"), 
                Severity.valueOf(rs.getString("severity")), 
                rs.getString("description"), 
                rs.getDate("detected_date").toLocalDate(), 
                rs.getInt("incident_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Threat> getAllThreats()
    {
        List<Threat> threats=new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_THREATS)) {
            while(rs.next())
            {
                threats.add(new Threat(rs.getInt("threat_id"),
                rs.getString("threat_name"), 
                rs.getString("threat_type"), 
                Severity.valueOf(rs.getString("severity")), 
                rs.getString("description"), 
                rs.getDate("detected_date").toLocalDate(), 
                rs.getInt("incident_id")));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return threats;
    }
    
    public void updateThreat(Threat threat,int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_THREAT))
        {
            pstmt.setString(1, threat.getName());
            pstmt.setString(2, threat.getType());
            pstmt.setString(3, threat.getSeverity().name());
            pstmt.setString(4, threat.getDescription());
            pstmt.setDate(5, Date.valueOf(threat.getDetectedDate()));
            pstmt.setInt(6, threat.getRelatedIncident());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateColumnThreat(int id, String column, Object val,String datatype)
    {
        final String UPDATE_Threat ="UPDATE threats SET " + column + " = ? WHERE threat_id = ?";
        
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_Threat))
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

    public void deleteThreat(int id)
    {
        try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_THREAT)) 
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}