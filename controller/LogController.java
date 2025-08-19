package controller;

import dao.interfaces.LogDAO;
import dao.impl.LogDAOImpl;
import models.Log;
import java.util.List;

public class LogController {
    private LogDAO LogDAO;

    public LogController() {
        this.LogDAO = new LogDAOImpl();
    }

    public void addLog(Log Log) {
        LogDAO.addLog(Log);
    }

    public Log getLogById(int id) {
        return LogDAO.getLogById(id);
    }

    public List<Log> getAllLogs() {
        return LogDAO.getAllLogs();
    }
}
