package database.classes;

import java.sql.Date;

public class Department {
    private String name;
    private Date creationDate;
    private Address address;
    private int departmentID;

    public Department(){}

    public Department(int departmentID, String name, Date creationDate, Address address){
        this.address = address;
        this.creationDate = creationDate;
        this.name = name;
        this.departmentID = departmentID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getCreationDate_S() {
        return Converter.DateToString(this.creationDate);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddress_street() {
        return this.address.getStreet();
    }

    public void setAddress_street(String street) {
        this.address.setStreet(street);
    }

    public String getAddress_houseNumber() {
        return Converter.IntToString(this.address.getHouseNumber());
    }

    public void setAddress_houseNumber(int number) {
        this.address.setHouseNumber(number);
    }

    public String getAddress_apartmentNumber() {
        return Converter.IntToString(this.address.getApartmentNumber());
    }

    public void setAddress_apartmentNumber(int number) {
        this.address.setApartmentNumber(number);
    }

    public String getAddress_city() {
        return this.address.getCity();
    }

    public void setAddress_city(String city) {
        this.address.setCity(city);
    }

    public String getAddress_postalCode() {
        return this.address.getPostalCode();
    }

    public void setAddress_postalCode(String code) {
        this.address.setPostalCode(code);
    }

    public String getAddress_countryID() {
        return Converter.IntToString(this.address.getCountryID());
    }

    public void setAddress_countryID(int id) {
        this.address.setCountryID(id);
    }

    public int getDepartmentID() {
        return this.departmentID;
    }

    public String getDepartmentID_S() {
        return Converter.IntToString(this.departmentID);
    }

    public void setDepartmentID(int dep_id) {
        this.departmentID = dep_id;
    }

}