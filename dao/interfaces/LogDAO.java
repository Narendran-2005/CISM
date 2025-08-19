package dao.interfaces;

import models.Log;
import java.util.List;

public interface LogDAO {
    void addLog(Log log);
    Log getLogById(int id);
    List<Log> getAllLogs();
}
