package dao.impl;

import dao.interfaces.SuspectDAO;
import models.Suspect;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuspectDAOImpl implements SuspectDAO {

    private static final String INSERT_SUSPECT = "INSERT INTO suspects (person_id, name, contact_no, address_id, incident_id, criminal_records, interrogation_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_CASE = "INSERT INTO suspect_cases (person_id, case_reference) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM suspects WHERE person_id = ?";
    private static final String SELECT_CASES = "SELECT case_reference FROM suspect_cases WHERE person_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM suspects";
    private static final String DELETE_CASES = "DELETE FROM suspect_cases WHERE person_id = ?";
    private static final String DELETE_SUSPECT = "DELETE FROM suspects WHERE person_id = ?";
    private static final String UPDATE_SUSPECT = "UPDATE suspects SET name = ?, contact_no = ?, address_id = ?, incident_id = ?, criminal_records = ?, interrogation_status = ? WHERE person_id = ?";

    private void insertAssociatedCases(int personId, List<String> cases, Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_CASE)) {
            for (String c : cases) {
                stmt.setInt(1, personId);
                stmt.setString(2, c);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private List<String> getAssociatedCases(int personId, Connection conn) throws SQLException {
        List<String> cases = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_CASES)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cases.add(rs.getString("case_reference"));
            }
        }
        return cases;
    }

    @Override
    public void addSuspect(Suspect s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(INSERT_SUSPECT)) {
                stmt.setInt(1, s.getPersonId());
                stmt.setString(2, s.getName());
                stmt.setString(3, s.getContactNo());
                stmt.setInt(4, s.getAddress());
                stmt.setInt(5, s.getIncidentId());
                stmt.setInt(6, s.getCriminalRecords());
                stmt.setString(7, s.getinterrogationStatus());
                stmt.executeUpdate();
            }

            insertAssociatedCases(s.getPersonId(), s.getAssociatedCases(), conn);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Suspect getSuspectById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<String> cases = getAssociatedCases(id, conn);
                return new Suspect(
                        rs.getInt("person_id"),
                        rs.getString("name"),
                        rs.getString("contact_no"),
                        rs.getInt("address_id"),
                        rs.getInt("incident_id"),
                        rs.getInt("criminal_records"),
                        new ArrayList<>(cases),
                        rs.getString("interrogation_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Suspect> getAllSuspects() {
        List<Suspect> suspects = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                int id = rs.getInt("person_id");
                List<String> cases = getAssociatedCases(id, conn);
                suspects.add(new Suspect(
                        id,
                        rs.getString("name"),
                        rs.getString("contact_no"),
                        rs.getInt("address_id"),
                        rs.getInt("incident_id"),
                        rs.getInt("criminal_records"),
                        new ArrayList<>(cases),
                        rs.getString("interrogation_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suspects;
    }

    @Override
    public void updateSuspect(Suspect s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SUSPECT)) {
                stmt.setString(1, s.getName());
                stmt.setString(2, s.getContactNo());
                stmt.setInt(3, s.getAddress());
                stmt.setInt(4, s.getIncidentId());
                stmt.setInt(5, s.getCriminalRecords());
                stmt.setString(6, s.getinterrogationStatus());
                stmt.setInt(7, s.getPersonId());
                stmt.executeUpdate();
            }

            // Delete old cases and insert new ones
            try (PreparedStatement deleteStmt = conn.prepareStatement(DELETE_CASES)) {
                deleteStmt.setInt(1, s.getPersonId());
                deleteStmt.executeUpdate();
            }

            insertAssociatedCases(s.getPersonId(), s.getAssociatedCases(), conn);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateColumnSuspect(int id, String column, Object val, String datatype) {
        final String query = "UPDATE suspects SET " + column + " = ? WHERE person_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            switch (datatype.toLowerCase()) {
                case "int": stmt.setInt(1, (Integer) val); break;
                case "string": stmt.setString(1, (String) val); break;
                default: throw new IllegalArgumentException("Unsupported datatype");
            }
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSuspect(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SUSPECT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
