package dao.interfaces;

import models.Incident;
import java.util.List;

public interface IncidentDAO {
    void addIncident(Incident incident);
    Incident getIncidentById(int id);
    List<Incident> getAllIncidents();
    void updateIncident(Incident incident,int id);
    void updateColumnIncident(int id, String column, Object val);
    void deleteIncident(int id);
    List<Incident> searchIncident(String keyword);
    List<Incident> getIncidentsByUser(int userid);
    void assignIncidents(int incId,int userId);
    List<Incident> viewUnassignedIncidents();
}
