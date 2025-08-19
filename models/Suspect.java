package models;
import java.util.ArrayList;
public class Suspect extends Person
{
    private int criminalRecords;
    private ArrayList<String> associatedCases;
    private String interrogationStatus;

    public Suspect(int personId, String name, String contactInfo, int addressId, int incidentId, int criminalRecords,ArrayList<String> associatedCases, String interrogationStatus)
    {
        super(personId, name, contactInfo, addressId, incidentId);
        this.criminalRecords = criminalRecords;
        this.associatedCases = associatedCases;
        this.interrogationStatus = interrogationStatus;
    }
    public int getCriminalRecords(){return criminalRecords;}
    public ArrayList<String> getAssociatedCases(){return associatedCases;}
    public String getinterrogationStatus(){return interrogationStatus;}

    public void setCriminalRecords(int criminalRecords){this.criminalRecords = criminalRecords;}
    public void setAssociatedCases(ArrayList<String> associatedCases){this.associatedCases = associatedCases;}
    public void setInterrogationStatus(String interrogationStatus){this.interrogationStatus = interrogationStatus;}
}
