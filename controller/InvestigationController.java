package controller;

import dao.interfaces.InvestigationDAO;
import dao.impl.InvestigationDAOImpl;
import models.Investigation;
import java.util.List;

public class InvestigationController {
    private InvestigationDAO InvestigationDAO;

    public InvestigationController() {
        this.InvestigationDAO = new InvestigationDAOImpl();
    }

    public void addInvestigation(Investigation Investigation) {
        InvestigationDAO.addInvestigation(Investigation);
    }

    public Investigation getInvestigationById(int id) {
        return InvestigationDAO.getInvestigationById(id);
    }

    public List<Investigation> getAllInvestigations() {
        return InvestigationDAO.getAllInvestigations();
    }

    public void updateInvestigation(Investigation Investigation) {
        InvestigationDAO.updateInvestigation(Investigation);
    }

    public void deleteInvestigation(int id) {
        InvestigationDAO.deleteInvestigation(id);
    }
}
