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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCode() {
        return code;
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

    public void setCollectionPointID(int collectionPointID) {
        this.collectionPointID = collectionPointID;
    }

    public int getDepartmentID() {
        return departmentID;
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

    public String getSenderName() {
        return this.sender.getName();
    }
    public String getReceiverName() {
        return this.receiver.getName();
    }

    public void setSenderName(String name) {
        this.sender.setName(name);
    }

    public void setReceiverName(String name) {
        this.receiver.setName(name);
    }

    public String getReceiverAddress() {
        return this.receiver.getClientAddress().getFullAddress();
    }

    public String getSenderAddress() {
        return this.sender.getClientAddress().getFullAddress();
    }

    public String getSenderFullName() {
        return this.sender.getName() + " " + this.sender.getSurname();
    }

    public String getReceiverFullName() {
        return this.receiver.getName() + " " + this.receiver.getSurname();
    }
}

