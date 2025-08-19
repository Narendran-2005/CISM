package dao.interfaces;

import models.Mitigation;
import java.util.List;

public interface MitigationDAO {
    void addMitigation(Mitigation mitigation);
    Mitigation getMitigationById(int id);
    List<Mitigation> getAllMitigations();
    void updateMitigation(Mitigation mitigation);
    void updateColumnMitigation(int id,String column,Object val,String datatype);
    void deleteMitigation(int id);
}
