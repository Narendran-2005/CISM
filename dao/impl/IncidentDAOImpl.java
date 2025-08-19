package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import dao.interfaces.IncidentDAO;
import models.Incident;
import models.enums.Enumeration.IncidentStatus;
import util.DatabaseConnection;

public class IncidentDAOImpl implements IncidentDAO{

    
    private static final String INSERT_INCIDENT= "INSERT INTO incidents(title, description, incident_date, reported_by, status, assigned_to) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_INCIDENT_BY_ID = "SELECT * FROM incidents WHERE incident_id = ?";
    private static final String SELECT_ALL_INCIDENTS = "SELECT * FROM incidents";
    private static final String UPDATE_ALLCOLUMNS_INCIDENT = "UPDATE incidents SET title = ?, description = ?, incident_date = ?, reported_by = ?, status = ?, assigned_to = ? WHERE incident_id = ?";
    private static final String DELETE_INCIDENT = "DELETE FROM incidents WHERE incident_id = ?";

    public void addIncident(Incident Incident)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_INCIDENT))
        {
            pstmt.setString(1,Incident.getTitle());
            pstmt.setString(2,Incident.getDescription());
            pstmt.setDate(3,Date.valueOf(Incident.getDateReported()));
            pstmt.setInt(4,Incident.getReportedBy());
            pstmt.setString(5,Incident.getStatus().name());
            pstmt.setInt(6,Incident.getAssignedTo());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Incident getIncidentById(int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_INCIDENT_BY_ID))
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new Incident(rs.getInt("incident_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("incident_date").toLocalDate(),
                rs.getInt("reported_by"),
                IncidentStatus.valueOf(rs.getString("status")),
                rs.getInt("assigned_to"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Incident> getAllIncidents()
    {
        List<Incident> incidents = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_INCIDENTS))
        {
            while(rs.next())
            {
                incidents.add(new Incident(rs.getInt("incident_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("incident_date").toLocalDate(),
                rs.getInt("reported_by"),
                IncidentStatus.valueOf(rs.getString("status")),
                rs.getInt("assigned_to")));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateIncident(Incident incident, int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_INCIDENT))
        {
            pstmt.setString(1,incident.getTitle());
            pstmt.setString(2,incident.getDescription());
            pstmt.setDate(3,Date.valueOf(incident.getDateReported()));
            pstmt.setInt(4,incident.getReportedBy());
            pstmt.setString(5,incident.getStatus().name());
            pstmt.setInt(6,incident.getAssignedTo());
            pstmt.setInt(7,id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateColumnIncident(int id, String column, Object val) 
    {
        final String UPDATE_INCIDENT = "UPDATE incidents SET " + column + " = ? WHERE incident_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_INCIDENT))
        {

            if (val instanceof Integer) 
            {
                pstmt.setInt(1, (Integer) val);
            }
            else if (val instanceof Enum<?>) 
            {
                pstmt.setString(1, ((Enum<?>) val).name()); 
            }
            else if (val instanceof java.time.LocalDate) 
            {
                pstmt.setDate(1, java.sql.Date.valueOf((java.time.LocalDate) val));
            }
            else if (val instanceof java.sql.Timestamp) 
            {
                pstmt.setTimestamp(1, (java.sql.Timestamp) val);
            }
            else 
            {
                pstmt.setObject(1, val); 
            }

            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Incident column updated successfully!");

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }


    public void deleteIncident(int id)
    {
        try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_INCIDENT)) 
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Incident> searchIncident(String keyword)
    {
        List<Incident> in=new ArrayList<>();
        final String SEARCH_INCIDENT ="SELECT * FROM incidents WHERE title LIKE ? OR description LIKE ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SEARCH_INCIDENT))
        {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                in.add(new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("date_reported").toLocalDate(),
                    rs.getInt("reported_by"),
                    IncidentStatus.valueOf(rs.getString("status")),
                    rs.getInt("assigned_to")        
                ));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return in;
    }

    public List<Incident> getIncidentsByUser(int userid)
    {
        List<Incident> in = new ArrayList<>();
        final String INCIDENTS_BY_USER = "SELECT * FROM incidents WHERE assigned_to = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INCIDENTS_BY_USER))
        {
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                in.add(new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("date_reported").toLocalDate(),
                    rs.getInt("reported_by"),
                    IncidentStatus.valueOf(rs.getString("status")),
                    rs.getInt("assigned_to")
                ));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return in;
    }

    public void assignIncidents(int incId, int usrId) {
        String ASSIGN_INCIDENTS = "UPDATE incidents SET assigned_to = ? WHERE incident_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(ASSIGN_INCIDENTS)) {
            pstmt.setInt(1, usrId);
            pstmt.setInt(2, incId);
            pstmt.executeUpdate();
            System.out.println("Incident assigned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Incident> viewUnassignedIncidents()
    {
        List<Incident> in = new ArrayList<>();
        String VIEW_UNASSIGNED_INCIDENTS = "SELECT * FROM incidents WHERE assigned_to = -1";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(VIEW_UNASSIGNED_INCIDENTS))
            {
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    in.add(new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("date_reported").toLocalDate(),
                    rs.getInt("reported_by"),
                    IncidentStatus.valueOf(rs.getString("status")),
                    rs.getInt("assigned_to")));
                }
            }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return in;
    }
}

