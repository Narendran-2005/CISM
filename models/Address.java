package models;

public class Address {
    private int id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;

   

    public Address(int id, String street, String city, String state, String country, String pinCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }
    
    public int getId() { return id; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPostalCode() { return pinCode; }
    

    public void setId(int id) { this.id = id; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setCountry(String country) { this.country = country; }
    public void setPostalCode(String pinCode) { this.pinCode = pinCode; }
}
