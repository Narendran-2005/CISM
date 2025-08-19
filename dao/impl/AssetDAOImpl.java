package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.AssetDAO;
import models.Assets;
import models.enums.Enumeration.AssetStatus;
import util.DatabaseConnection;

public class AssetDAOImpl implements AssetDAO{

   private static final String INSERT_ASSET = "INSERT INTO assets( asset_id, incident_id, threat_id, asset_name, asset_type, owner, status) VALUES (?,?,?,?,?,?,?)";
   private static final String SELECT_ASSET_BY_ID = "SELECT * FROM assets WHERE asset_id = ?";
   private static final String SELECT_ALL_ASSETS = "SELECT * FROM assets";
   private static final String UPDATE_ALLCOLUMNS_ASSET = "UPDATE threats SET asset_id = ?, incident_id = ?, threat_id = ?, asset_name = ?, asset_type = ?, owner = ?, status = ? WHERE asset_id = ?";
   private static final String DELETE_ASSET = "DELETE FROM assets WHERE asset_id = ?";

   @Override
   public void addAsset(Assets asset)
   {
      try(Connection conn = DatabaseConnection.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(INSERT_ASSET))
      {
         pstmt.setInt(1, asset.getAssetId());
         pstmt.setInt(2, asset.getIncidentId());
         pstmt.setInt(3, asset.getThreatId());
         pstmt.setString(4,asset.getAssetName());
         pstmt.setString(5,asset.getType());
         pstmt.setInt(7, asset.getOwner());
         pstmt.setString(6,asset.getStatus().name());  
      }
      catch (SQLException e)
      {
          e.printStackTrace();
      }
   }
   
   @Override
   public Assets getAssetById(int id)
   {
      try(Connection conn = DatabaseConnection.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(SELECT_ASSET_BY_ID))
      {
         pstmt.setInt(1,id);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next())
         {
            return new Assets(rs.getInt("asset_id"),
            rs.getInt("incident_id"),
            rs.getInt("threat_id"),
            rs.getString("asset_name"),
            rs.getString("asset_type"),
            rs.getInt("owner"),
            AssetStatus.valueOf(rs.getString("status")));
         }
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public List<Assets> getAllAssets()
   {
      List<Assets> assets = new ArrayList<>();
      try(Connection conn = DatabaseConnection.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(SELECT_ALL_ASSETS))
      {
         while(rs.next())
         {
            assets.add(new Assets(rs.getInt("asset_id"),
            rs.getInt("incident_id"),
            rs.getInt("threat_id"),
            rs.getString("asset_name"),
            rs.getString("asset_type"),
            rs.getInt("owner"),
            AssetStatus.valueOf(rs.getString("status"))));
         }
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public void updateAsset(Assets asset)
   {
      try(Connection conn = DatabaseConnection.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALLCOLUMNS_ASSET))
      {
         pstmt.setInt(1, asset.getAssetId());
         pstmt.setInt(2, asset.getIncidentId());
         pstmt.setInt(3, asset.getThreatId());
         pstmt.setString(4,asset.getAssetName());
         pstmt.setString(5,asset.getType());
         pstmt.setInt(7, asset.getOwner());
         pstmt.setString(6,asset.getStatus().name());  
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void deleteAsset(int id)
   {
       try(Connection conn  = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(DELETE_ASSET)) 
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
         
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
    
}
/*
 *     private int assetId;
    private int threatId;
    private int incidentId;
    private String assetName;
    private String assetType;
    private int owner;
    private String status;
    
    void addAsset(Assets asset);
    Assets getAssetById(int id);
    List<Assets> getAllAssets();
    void updateAsset(Assets asset);
    void deleteAsset(int id);

     asset_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    threat_id INT,
    asset_name VARCHAR(100) NOT NULL,
    asset_type VARCHAR(100),
    owner VARCHAR(100)NOT NULL,
}
 */