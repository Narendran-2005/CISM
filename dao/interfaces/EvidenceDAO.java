package dao.interfaces;

import models.Evidence;
import java.util.List;

public interface EvidenceDAO {
    void addEvidence(Evidence evidence);
    Evidence getEvidenceById(int id);
    List<Evidence> getAllEvidence();
    void updateEvidence(int id,Evidence evidence);
    void updateColumnEvidence(int id, String column, Object val,String datatype);
    void deleteEvidence(int id);
    List<Evidence> getEvidencebyThreatId(int threatId);
    List<Evidence> getEvidencebyIncidentID(int incId);
}
