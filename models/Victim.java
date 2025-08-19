package models;
public class Victim extends Person
{
    private boolean recievedSupport;
    private String writtenStatement;
    public Victim(int personId, String name, String contactInfo, int addressId, int incidentId, String writtenStatement, boolean recievedSupport)
    {
        super(personId, name, contactInfo, addressId, incidentId);
        this.writtenStatement = writtenStatement; 
        this.recievedSupport = recievedSupport;
    }
    public boolean hasRecievedSupport(){return recievedSupport;}
    public String givenWrittenStatement(){return writtenStatement;}
    
    public void setRecievedSupport(boolean recievedSupport){this.recievedSupport = recievedSupport;} 
    public void setWriitenStatement(String writtenStatement){this.writtenStatement = writtenStatement;} 
    
}
