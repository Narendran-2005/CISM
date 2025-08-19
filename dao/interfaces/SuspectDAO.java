package dao.interfaces;

import models.Suspect;
import java.util.List;

public interface SuspectDAO {
    void addSuspect(Suspect suspect);
    Suspect getSuspectById(int id);
    List<Suspect> getAllSuspects();
    void updateSuspect(Suspect suspect);
    void updateColumnSuspect(int id,String column,Object val,String datatype);
    void deleteSuspect(int id);
}

