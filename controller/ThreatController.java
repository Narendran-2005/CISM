package controller;

import dao.interfaces.ThreatDAO;
import dao.impl.ThreatDAOImpl;
import models.Threat;
import java.util.List;

public class ThreatController {
    private ThreatDAO ThreatDAO;

    public ThreatController() {
        this.ThreatDAO = new ThreatDAOImpl();
    }

    public void addThreat(Threat Threat) {
        ThreatDAO.addThreat(Threat);
    }

    public Threat getThreatById(int id) {
        return ThreatDAO.getThreatById(id);
    }

    public List<Threat> getAllThreats() {
        return ThreatDAO.getAllThreats();
    }

    public void updateThreat(Threat Threat,int id) {
        ThreatDAO.updateThreat(Threat,id);
    }

    public void updateColumnThreat(int id,String column,Object val,String datatype)
    {
        ThreatDAO.updateColumnThreat(id, column, val, datatype);
    }
    public void deleteThreat(int id) {
        ThreatDAO.deleteThreat(id);
    }
}

