package controller;


import  dao.interfaces.EvidenceDAO;
import  dao.impl.EvidenceDAOImpl;
import  models.Evidence;
import java.util.List;

public class EvidenceController {
    private EvidenceDAO EvidenceDAO;

    public EvidenceController() {
        this.EvidenceDAO = new EvidenceDAOImpl();
    }

    public void addEvidence(Evidence Evidence) {
        EvidenceDAO.addEvidence(Evidence);
    }

    public Evidence getEvidenceById(int id) {
        return EvidenceDAO.getEvidenceById(id);
    }

    public List<Evidence> getAllEvidences() {
        return EvidenceDAO.getAllEvidence();
    }

    public void updateEvidence(int id,Evidence Evidence) {
        EvidenceDAO.updateEvidence(id,Evidence);
    }

    public void deleteEvidence(int id) {
        EvidenceDAO.deleteEvidence(id);
    }

    public List<Evidence> getEvidencesByThreatId(int threatId)
    {
        return EvidenceDAO.getEvidencebyThreatId(threatId);
    }

    public List<Evidence> getEvidencesByIncidentId(int incId)
    {
        return EvidenceDAO.getEvidencebyIncidentID(incId);
    }
}
