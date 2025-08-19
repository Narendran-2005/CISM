package controller;

import dao.interfaces.MitigationDAO;
import dao.impl.MitigationDAOImpl;
import models.Mitigation;
import java.util.List;

public class MitigationController {
    private MitigationDAO mitigationDAO;

    public MitigationController() {
        this.mitigationDAO = new MitigationDAOImpl();
    }

    public void addMitigation(Mitigation mitigation) {
        mitigationDAO.addMitigation(mitigation);
    }

    public Mitigation getMitigationById(int id) {
        return mitigationDAO.getMitigationById(id);
    }

    public List<Mitigation> getAllMitigations() {
        return mitigationDAO.getAllMitigations();
    }

    public void updateMitigation(Mitigation mitigation) {
        mitigationDAO.updateMitigation(mitigation);
    }

    public void deleteMitigation(int id) {
        mitigationDAO.deleteMitigation(id);
    }
}
