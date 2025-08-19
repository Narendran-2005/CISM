package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.InvestigationDAO;
import models.Investigation;
import models.enums.Enumeration.InvestigationStatus;
import util.DatabaseConnection;

public class InvestigationDAOImpl implements InvestigationDAO{

    private static final String INSERT_INVESTIGATION = "INSERT INTO investigations( investigation_id, incident_id, investigator_id, start_date, end_date, status, findings) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_INVESTIGATION_BY_ID = "SELECT * FROM investigations WHERE investigation_id = ?";
    private static final String SELECT_ALL_INVESTIGATIONS = "SELECT * FROM investigations";
    private static final String UPDATE_ALLCOLUMNS_INVESTIGATION = "UPDATE invesigations SET investigation_id = ?, incident_id = ?, investigator_id = ?, start_date = ?, end_date = ?, status = ?, findings = ? WHERE investigation_id = ?";
    private static final String DELETE_INVESTIGATION = "DELETE FROM investigations WHERE investigation_id = ?";
    public void addInvestigation(Investigation investigation)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_INVESTIGATION))
        {
            pstmt.setInt(1,investigation.getInvestigationId());
            pstmt.setInt(2,investigation.getIncidentId());
            pstmt.setInt(3,investigation.getInvestigatorId());
            pstmt.setDate(4,new java.sql.Date(investigation.getStartDate().getTime()));
            pstmt.setDate(5,new java.sql.Date(investigation.getEndDate().getTime()));
            pstmt.setString(6,investigation.getStatus().name());
            pstmt.setString(7,investigation.getFindings());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Investigation getInvestigationById(int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_INVESTIGATION_BY_ID))
        {
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new Investigation(
                    rs.getInt("investigation_id"),
                    rs.getInt("incident_id"),
                    rs.getInt("investigator_id"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    InvestigationStatus.valueOf(rs.getString("status")),
                    rs.getString("findings")
                    );
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public List<Investigation> getAllInvestigations()
        {
            List<Investigation> investigations = new ArrayList<>();
            try(Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(SELECT_ALL_INVESTIGATIONS))
            {
                while(rs.next())
                {
                    investigations.add(new Investigation(rs.getInt("investigation_id"),
                    rs.getInt("incident_id"),
                    rs.getInt("investigator_id"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    InvestigationStatus.valueOf(rs.getString("status")),
                    rs.getString("findings")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateInvestigation(Investigation investigation)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_INVESTIGATION))
        {
            pstmt.setInt(1,investigation.getInvestigationId());
            pstmt.setInt(2,investigation.getIncidentId());
            pstmt.setInt(3,investigation.getInvestigatorId());
            pstmt.setDate(4,new java.sql.Date(investigation.getStartDate().getTime()));
            pstmt.setDate(5,new java.sql.Date(investigation.getEndDate().getTime()));
            pstmt.setString(6,investigation.getStatus().name());
            pstmt.setString(7,investigation.getFindings());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateColumnInvestigation(int id, String column, String val)
    {
        final String UPDATE_INVESTIGATION ="UPDATE investigations SET " + column + " = ? WHERE investigation_id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_INVESTIGATION))
        {
            pstmt.setString(1, val);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteInvestigation(int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_INVESTIGATION))
        {
            pstmt.setInt(1,id);
            pstmt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*void addInvestigation(Investigation investigation);
    Investigation getInvestigationById(int id);
    List<Investigation> getAllInvestigations();
    void updateInvestigation(Investigation investigation);
    void deleteInvestigation(int id);
    
    private int investigationId;
    private int incidentId;
    private int investigatorId;
    private Date stdate;
    private Date endate;
    private String status;
    private String findings;

    investigation_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    investigator_id INT,
    start_date DATE NOT NULL,
    end_date DATE,
    status ENUM('Ongoing', 'Completed') DEFAULT 'Ongoing',
    findings TEXT,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE,
    FOREIGN KEY (investigator_id) REFERENCES users(user_id) ON DELETE SET NULL
    */