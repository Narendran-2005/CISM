package dao.impl;
import dao.interfaces.LogDAO;
import models.Log;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LogDAOImpl implements LogDAO{

    private static final String INSERT_LOG = "INSERT INTO logs(user_id, action, log_time) VALUES (?,?,?)";
    private static final String SELECT_LOG_BY_ID = "SELECT * FROM logs WHERE log_id = ?";
    private static final String SELECT_ALL_LOGS = "SELECT * FROM logs";

    public void addLog(Log log)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(INSERT_LOG)){
                
                pstmt.setInt(1, log.getUserId());
                pstmt.setString(2, log.getAction());
                pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                pstmt.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    public Log getLogById(int id)
    {
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_LOG_BY_ID))
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Log(rs.getInt("log_id"),
                rs.getInt("user_id"),
                rs.getString("action"),
                rs.getTimestamp("log_time"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Log> getAllLogs()
    {
        List<Log> log = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs= stmt.executeQuery(SELECT_ALL_LOGS))
        {
            while(rs.next()) {
                log.add(new Log(rs.getInt("log_id"),
                rs.getInt("user_id"),
                rs.getString("action"),
                rs.getTimestamp("log_time")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return log;
    }

    
}