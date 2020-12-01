package database.classes;

public class ClientAddress {
    private int clientAddressID;
    private String street;
    private int houseNumber;
    private int apartmentNumber;
    private String city;
    private String postalCode;
    private int countryID;

    public ClientAddress(){}

    public ClientAddress(int clientAddressID, String street, int houseNumber, int apartmentNumber,
    String city, String postalCode, int countryID){
        this.clientAddressID = clientAddressID;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.countryID = countryID;
    }
    public String getFullAddress(){
        if(getApartmentNumber() != -1){
            return getStreet() + " " + Integer.toString(getHouseNumber()) + "/" + Integer.toString(getApartmentNumber()) + ", " + getCity() + " " + getPostalCode();
        }else{
            return getStreet() + " " + Integer.toString(getHouseNumber()) + ", " + getCity() + " " + getPostalCode();
        }
    }
    
    public int getClientAddressID() {
        return clientAddressID;
    }

    public void setClientAddressID(int clientAddressID) {
        this.clientAddressID = clientAddressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
