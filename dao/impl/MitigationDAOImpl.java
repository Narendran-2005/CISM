package dao.impl;

import dao.interfaces.MitigationDAO;
import models.Mitigation;
import models.enums.Enumeration.Effectiveness;
import models.enums.Enumeration.MitigationStatus;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MitigationDAOImpl implements MitigationDAO{
     private static final String INSERT_MITIGATION = "INSERT INTO  mitigation(mitigation_id, threat_id, action_taken, implemented_by, implementation_date, mitigation_status, effectiveness) VALUES (?,?,?,?,?,?,?)";
     private static final String SELECT_MITIGATION_BY_ID = "SELECT * FROM mitigation WHERE mitigation_id = ?";
     private static final String SELECT_ALL_MITIGATIONS = "SELECT * FROM mitigation";
     private static final String UPDATE_ALLCOLUMNS_MITIGATION = "UPDATE mitigation SET threat_id = ?, action_taken = ?, implemented_by = ?, implementation_date = ?, mitigation_status = ?, effectiveness = ? WHERE mitigation_id = ?";
    private static final String DELETE_MITIGATION = "DELETE FROM mitigation WHERE mitigation_id = ?";
    
    public void addMitigation(Mitigation mitigation)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_MITIGATION)){
            pstmt.setInt(1, mitigation.getMitigationId());
            pstmt.setInt(2, mitigation.getThreatId());
            pstmt.setString(3, mitigation.getActionTaken());
            pstmt.setInt(4, mitigation.getImplementedBy());
            pstmt.setDate(5, Date.valueOf(mitigation.getImplementedDate()));
            pstmt.setString(6, mitigation.getMitigationStatus().name());
            pstmt.setString(7, mitigation.getEffectiveness().name());
            pstmt.executeUpdate(); 
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
    
    public void updateColumnMitigation(int id, String column, Object val, String datatype)
    {
        final String UPDATE_MITIGATION ="UPDATE mitigation SET " + column + " = ? WHERE mitigation_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_MITIGATION))
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

    public Mitigation getMitigationById(int id)
    {
         try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_MITIGATION_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Mitigation(rs.getInt("mitigation_id"),
                rs.getInt("threat_id"), 
                rs.getString("action_taken"), 
                rs.getInt("implemented_by"), 
                rs.getDate("implementation_date").toLocalDate(), 
                MitigationStatus.valueOf(rs.getString("mitigation_status")),
                Effectiveness.valueOf(rs.getString("effectiveness")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Mitigation> getAllMitigations() {
        List<Mitigation> mitigations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_MITIGATIONS)) {
            while (rs.next()) {
                mitigations.add(new Mitigation(rs.getInt("mitigation_id"),
                rs.getInt("threat_id"), 
                rs.getString("action_taken"), 
                rs.getInt("implemented_by"), 
                rs.getDate("implementation_date").toLocalDate(), 
                MitigationStatus.valueOf(rs.getString("mitigation_status")),
                Effectiveness.valueOf(rs.getString("effectiveness"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mitigations;
    }

    public void updateMitigation(Mitigation mitigation)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_MITIGATION))
        {
            pstmt.setInt(2, mitigation.getThreatId());
            pstmt.setString(3, mitigation.getActionTaken());
            pstmt.setInt(4, mitigation.getImplementedBy());
            pstmt.setDate(5, Date.valueOf(mitigation.getImplementedDate()));
            pstmt.setString(6, mitigation.getMitigationStatus().name());
            pstmt.setString(7, mitigation.getEffectiveness().name());
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteMitigation(int id)
    {
        try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_MITIGATION)) 
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
 * CREATE TABLE mitigation (
    mitigation_id INT AUTO_INCREMENT PRIMARY KEY,
    threat_id INT,
    action_taken TEXT NOT NULL,
    implemented_by INT,
    implementation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    mitigation_status ENUM('Implemented','Yet to implement'),
    effectiveness ENUM('Effective', 'Partially Effective', 'Not Effective') DEFAULT NULL,
    FOREIGN KEY (threat_id) REFERENCES threats(threat_id) ON DELETE CASCADE,
    FOREIGN KEY (implemented_by) REFERENCES users(user_id) ON DELETE SET NULL
);
 private int mitigationId;
    private int threatId;
    private String actionTaken;
    private int implementedBy;
    private Date implementedDate;
    private String mitigationStatus;
    private String effectiveness;

    void addMitigation(Mitigation mitigation);
    Mitigation getMitigationById(int id);
    List<Mitigation> getAllMitigations();
    void updateMitigation(Mitigation mitigation);
    void deleteMitigation(int id);
*/