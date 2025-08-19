package models;
import java.sql.Timestamp;
public class Log {
    private int logId;
    private int userId;
    private String action;
    private Timestamp timeStamp;

    public Log(int logId,int userId,String action, Timestamp timeStamp)
    {
        this.logId = logId;
        this.userId = userId;
        this.action = action;
        this.timeStamp = timeStamp;
    }
    public Log(int userId,String action,Timestamp timeStamp)
    {
        this.userId=userId;
        this.action=action;
        this.timeStamp=timeStamp;
    }
    public int getLogId(){return logId;}
    public int getUserId(){return userId;}
    public String getAction(){return action;}
    public Timestamp getTimeStamp(){return timeStamp;}

    public void setLogId(int logId) {this.logId = logId;}
    public void setUserId(int userId) {this.userId = userId;}
    public void setAction(String action) {this.action = action;}
    public void setTimeStamp(Timestamp timeStamp) {this.timeStamp = timeStamp;}
    
}
