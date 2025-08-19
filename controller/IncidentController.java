package controller;

import dao.interfaces.IncidentDAO;
import dao.impl.IncidentDAOImpl;
import models.Incident;
import java.util.List;

public class IncidentController {
    private IncidentDAO incidentDAO;

    public IncidentController() {
        this.incidentDAO = new IncidentDAOImpl();
    }

    public void addIncident(Incident Incident) {
        incidentDAO.addIncident(Incident);
    }

    public Incident getIncidentById(int id) {
        return incidentDAO.getIncidentById(id);
    }

    public List<Incident> getAllIncidents() {
        return incidentDAO.getAllIncidents();
    }

    public void updateIncident(Incident Incident,int id) {
        incidentDAO.updateIncident(Incident,id);
    }
    
    public void updateColumnIncident(int id, String column, Object val)
    {
        incidentDAO.updateColumnIncident(id, column, val);
    }

    public List<Incident> searchIncidents(String keyword)
    {
        return incidentDAO.searchIncident(keyword);
    }

    public List<Incident> getIncidentsByUser(int userid)
    {
        return incidentDAO.getIncidentsByUser(userid);
    }

    public void deleteIncident(int id) {
        incidentDAO.deleteIncident(id);
    }

    public void assignIncidents(int incId,int usrId)
    {
        incidentDAO.assignIncidents(incId, usrId);
    }

    public List<Incident> viewUnassignedIncidents()
    {
        return incidentDAO.viewUnassignedIncidents();
    }
}

