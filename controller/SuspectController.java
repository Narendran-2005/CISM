package controller;

import dao.impl.SuspectDAOImpl;
import dao.interfaces.SuspectDAO;
import models.Suspect;

import java.util.List;

public class SuspectController {
    private final SuspectDAO suspectDAO;

    public SuspectController() {
        this.suspectDAO = new SuspectDAOImpl();
    }

    public void addSuspect(Suspect suspect) {
        suspectDAO.addSuspect(suspect);
    }

    public Suspect getSuspectById(int id) {
        return suspectDAO.getSuspectById(id);
    }

    public List<Suspect> getAllSuspects() {
        return suspectDAO.getAllSuspects();
    }

    public void updateSuspect(Suspect suspect) {
        suspectDAO.updateSuspect(suspect);
    }

    public void deleteSuspect(int id) {
        suspectDAO.deleteSuspect(id);
    }

    public void updateSuspectField(int id, String column, Object value, String type) {
        suspectDAO.updateColumnSuspect(id, column, value, type);
    }
}
