package controller;


import dao.interfaces.AssetDAO;
import dao.impl.AssetDAOImpl;
import models.Assets;
import java.util.List;

public class AssetController {
    private AssetDAO assetDAO;

    public AssetController() {
        this.assetDAO = new AssetDAOImpl();
    }

    public void addAssets(Assets asset) {
        assetDAO.addAsset(asset);
    }

    public Assets getassetById(int id) {
        return assetDAO.getAssetById(id);
    }

    public List<Assets> getAllAssets() {
        return assetDAO.getAllAssets();
    }

    public void updateAsset(Assets asset) {
        assetDAO.updateAsset(asset);
    }

    public void deleteAsset(int id) {
        assetDAO.deleteAsset(id);
    }
}
