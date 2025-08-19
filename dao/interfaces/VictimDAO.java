package dao.interfaces;

import models.Victim;
import java.util.List;

public interface VictimDAO {
    void addVictim(Victim victim);
    Victim getVictimById(int id);
    List<Victim> getAllVictims();
    void updateVictim(Victim victim);
    void updateColumnVictim(int id,String column,Object val,String datatype);
    void deleteVictim(int id);
}

