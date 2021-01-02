package database.classes;

public class Parcel {
    private int ID;
    private String status;
    private double cost;
    private double weight;
    private double length;
    private double width;
    private double height;
    private int code;
    private int carID;
    private int collectionPointID;
    private int departmentID;
    private Client sender;
    private Client receiver;

    public Parcel(){}

    public Parcel(int ID, String status, double cost, Client sender, Client receiver,
    double weight,double length, double width, double height, int code, int carID, 
    int collectionPointID, int departmentID){
        this.ID = ID;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
        this.cost = cost;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.code = code;
        this.carID = carID;
        this.collectionPointID = collectionPointID;
        this.departmentID = departmentID;
    }


	public int getID() {
        return ID;
    }

    public String getID_S() {
        return Converter.IntToString(this.ID);
    }

    public void setID(int parcelID) {
        this.ID = parcelID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCost_S() {
        return Converter.DoubleToString(this.cost);
    }
    
    public double getWeight() {
        return weight;
    }

    public String getWeight_S() {
        return Converter.DoubleToString(this.weight);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public String getLength_S() {
        return Converter.DoubleToString(this.length);
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public String getWidth_S() {
        return Converter.DoubleToString(this.width);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public String getHeight_S() {
        return Converter.DoubleToString(this.height);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCode() {
        return code;
    }

    public String getCode_S() {
        return Converter.IntToString(this.code);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCarID() {
        return carID;
    }

    public String getCarID_S() {
        return Converter.IntToString(this.carID);
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCollectionPointID() {
        return collectionPointID;
    }

    public String getCollectionPointID_S() {
        return Converter.IntToString(this.collectionPointID);
    }

    public void setCollectionPointID(int collectionPointID) {
        this.collectionPointID = collectionPointID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentID_S() {
        return Converter.IntToString(this.departmentID);
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public Client getReceiver() {
        return this.receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public Client getSender() {
        return this.sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public String getSName() {
        return this.sender.getName();
    }

    public void setSName(String name) {
        this.sender.setName(name);
    }

    public String getSSurname() {
        return this.sender.getSurname();
    }

    public String getSEmail() {
        return this.sender.getEmail();
    }

    public void setSEmail(String email) {
        this.sender.setEmail(email);
    }

    public String getSPhoneNumber() {
        return this.sender.getPhoneNumber();
    }

    public void setSPhoneNumber(String number) {
        this.sender.setPhoneNumber(number);
    }

    public void setSSurname(String surname) {
        this.sender.setSurname(surname);
    }

    public String getRName() {
        return this.receiver.getName();
    }

    public void setRName(String name) {
        this.receiver.setName(name);
    }

    public String getRSurname() {
        return this.receiver.getSurname();
    }

    public void setRSurname(String surname) {
        this.receiver.setSurname(surname);
    }

    public String getREmail() {
        return this.receiver.getEmail();
    }

    public void setREmail(String email) {
        this.receiver.setEmail(email);
    }

    public String getRPhoneNumber() {
        return this.receiver.getPhoneNumber();
    }

    public void setRPhoneNumber(String number) {
        this.receiver.setPhoneNumber(number);
    }

    public String getSenderAddress() {
        return this.sender.getClientAddress().getFullAddress();
    }

    public String getSAddress_street() {
        return this.sender.getClientAddress().getStreet();
    }

    public void setSAddress_street(String street) {
        this.sender.getClientAddress().setStreet(street);
    }

    public String getSAddress_houseNumber() {
        return Converter.IntToString(this.sender.getClientAddress().getHouseNumber());
    }

    public void setSAddress_houseNumber(int number) {
        this.sender.getClientAddress().setHouseNumber(number);
    }

    public String getSAddress_apartmentNumber() {
        return Converter.IntToString(this.sender.getClientAddress().getApartmentNumber());
    }

    public void setSAddress_apartmentNumber(int number) {
        this.sender.getClientAddress().setApartmentNumber(number);
    }

    public String getSAddress_city() {
        return this.sender.getClientAddress().getCity();
    }

    public void setSAddress_city(String city) {
        this.sender.getClientAddress().setCity(city);
    }

    public String getSAddress_postalCode() {
        return this.sender.getClientAddress().getPostalCode();
    }

    public void setSAddress_postalCode(String code) {
        this.sender.getClientAddress().setPostalCode(code);
    }

    public String getSAddress_countryID() {
        return Converter.IntToString(this.sender.getClientAddress().getCountryID());
    }

    public void setSAddress_countryID(int id) {
        this.sender.getClientAddress().setCountryID(id);
    }

    public String getReceiverAddress() {
        return this.receiver.getClientAddress().getFullAddress();
    }

    public String getRAddress_street() {
        return this.receiver.getClientAddress().getStreet();
    }

    public void setRAddress_street(String street) {
        this.receiver.getClientAddress().setStreet(street);
    }

    public String getRAddress_houseNumber() {
        return Converter.IntToString(this.receiver.getClientAddress().getHouseNumber());
    }

    public void setRAddress_houseNumber(int number) {
        this.receiver.getClientAddress().setHouseNumber(number);
    }

    public String getRAddress_apartmentNumber() {
        return Converter.IntToString(this.receiver.getClientAddress().getApartmentNumber());
    }

    public void setRAddress_apartmentNumber(int number) {
        this.receiver.getClientAddress().setApartmentNumber(number);
    }

    public String getRAddress_city() {
        return this.receiver.getClientAddress().getCity();
    }

    public void setRAddress_city(String city) {
        this.receiver.getClientAddress().setCity(city);
    }

    public String getRAddress_postalCode() {
        return this.receiver.getClientAddress().getPostalCode();
    }

    public void setRAddress_postalCode(String code) {
        this.receiver.getClientAddress().setPostalCode(code);
    }

    public String getRAddress_countryID() {
        return Converter.IntToString(this.receiver.getClientAddress().getCountryID());
    }

    public void setRAddress_countryID(int id) {
        this.receiver.getClientAddress().setCountryID(id);
    }

    public String getSenderFullName() {
        return this.sender.getName() + " " + this.sender.getSurname();
    }

    public String getReceiverFullName() {
        return this.receiver.getName() + " " + this.receiver.getSurname();
    }
}

