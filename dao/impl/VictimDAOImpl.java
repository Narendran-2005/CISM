package dao.impl;

import dao.interfaces.VictimDAO;
import models.Victim;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VictimDAOImpl implements VictimDAO {

    private static final String INSERT_VICTIM = "INSERT INTO victims (person_id, name, contact_no, address_id, incident_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM victims WHERE person_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM victims";
    private static final String UPDATE_VICTIM = "UPDATE victims SET name = ?, contact_no = ?, address_id = ?, incident_id = ? WHERE person_id = ?";
    private static final String DELETE_VICTIM = "DELETE FROM victims WHERE person_id = ?";

    public void addVictim(Victim victim) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_VICTIM)) {

            pstmt.setInt(1, victim.getPersonId());
            pstmt.setString(2, victim.getName());
            pstmt.setString(3, victim.getContactNo());
            pstmt.setInt(4, victim.getAddress());
            pstmt.setInt(5, victim.getIncidentId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Victim getVictimById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Victim(
                        rs.getInt("personId"),
                        rs.getString("name"),
                        rs.getString("contactInfo"),
                        rs.getInt("addressId"),
                        rs.getInt("incidentId"),
                        rs.getString("writtenStatement"),
                        rs.getBoolean("recievedSupport")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Victim> getAllVictims() {
        List<Victim> victims = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                victims.add(new Victim(
                        rs.getInt("personId"),
                        rs.getString("name"),
                        rs.getString("contactInfo"),
                        rs.getInt("addressId"),
                        rs.getInt("incidentId"),
                        rs.getString("writtenStatement"),
                        rs.getBoolean("recievedSupport")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return victims;
    }

    public void updateVictim(Victim victim) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_VICTIM)) {

            pstmt.setString(1, victim.getName());
            pstmt.setString(2, victim.getContactNo());
            pstmt.setInt(3, victim.getAddress());
            pstmt.setInt(4, victim.getIncidentId());
            pstmt.setInt(5, victim.getPersonId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateColumnVictim(int id, String column, Object val, String datatype) {
        final String UPDATE = "UPDATE victims SET " + column + " = ? WHERE person_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            switch (datatype.toLowerCase()) {
                case "int":
                    pstmt.setInt(1, (Integer) val);
                    break;
                case "string":
                    pstmt.setString(1, (String) val);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported data type");
            }
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVictim(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_VICTIM)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

