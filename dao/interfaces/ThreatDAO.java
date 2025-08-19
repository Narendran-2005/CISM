package dao.interfaces;

import models.Threat;
import java.util.List;

public interface ThreatDAO {
    void addThreat(Threat threat);
    Threat getThreatById(int id);
    List<Threat> getAllThreats();
    void updateThreat(Threat threat,int id);
    void updateColumnThreat(int id,String column,Object val,String datatype);
    void deleteThreat(int id);
}
