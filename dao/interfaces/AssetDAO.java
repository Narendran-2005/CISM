package dao.interfaces;

import models.Assets;
import java.util.List;

public interface AssetDAO {
    void addAsset(Assets asset);
    Assets getAssetById(int id);
    List<Assets> getAllAssets();
    void updateAsset(Assets asset);
    void deleteAsset(int id);
}
