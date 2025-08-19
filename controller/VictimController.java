package controller;

import dao.impl.VictimDAOImpl;
import dao.interfaces.VictimDAO;
import models.Victim;
import java.util.List;

public class VictimController {
    private VictimDAO victimDAO;

    public VictimController() {
        this.victimDAO = new VictimDAOImpl();
    }

    public void addVictim(Victim victim) {
        victimDAO.addVictim(victim);
    }

    public Victim getVictimById(int id) {
        return victimDAO.getVictimById(id);
    }

    public List<Victim> getAllVictims() {
        return victimDAO.getAllVictims();
    }

    public void updateVictim(Victim victim) {
        victimDAO.updateVictim(victim);
    }

    public void deleteVictim(int id) {
        victimDAO.deleteVictim(id);
    }

    public void updateVictimField(int id, String column, Object value, String type) {
        victimDAO.updateColumnVictim(id, column, value, type);
    }
}

