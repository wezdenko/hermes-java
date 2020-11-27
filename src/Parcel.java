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
    private int departmentID;
    private Client sender;
    private Client receiver;
    private String receiverAddress;
    private String senderAddress;

    public Parcel(){}

    public Parcel(int ID, String status, double cost, Client sender, Client receiver,
    double weight,double length, double width, double height, int code, int carID, 
    int departmentID){
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
        this.departmentID = departmentID;
        this.receiverAddress = receiver.getClientAddress().getAddress();
        this.senderAddress = sender.getClientAddress().getAddress();
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

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getReceiver() {
        return this.receiver.getName();
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return this.sender.getName();
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getSenderAddress() {
        return senderAddress;
    }



}

