package dao.interfaces;

import models.Investigation;
import java.util.List;

public interface InvestigationDAO {
    void addInvestigation(Investigation investigation);
    Investigation getInvestigationById(int id);
    List<Investigation> getAllInvestigations();
    void updateInvestigation(Investigation investigation);
    void deleteInvestigation(int id);
}
