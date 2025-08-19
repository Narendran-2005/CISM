package models;
public class Person {
    protected int personId;
    protected String name;
    protected String contactNo;
    protected int addressId;
    protected int incidentId;

    public Person(int personId, String name, String contactNo, int addressId, int incidentId)
    {
        this.personId = personId;
        this.name = name;
        this.contactNo = contactNo;
        this.addressId = addressId;
        this.incidentId = incidentId;
    }
    public int getPersonId(){return personId;}
    public String getName(){return name;}
    public String getContactNo(){return contactNo;}
    public int getAddress(){return addressId;}
    public int getIncidentId(){return incidentId;}
    
    public void setPersonId(int personId){this.personId = personId;}
    public void setName(String name){this.name = name;}
    public void setContactNo(String contactNo){this.contactNo = contactNo;}
    public void setAddress(int addressId){this.addressId = addressId;}
}
