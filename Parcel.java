public class Parcel {
    private int parcelID;
    private String status;
    private double cost;
    private double weight;
    private double length;
    private double width;
    private double height;
    private int code;
    private int carID;
    private int departmentID;
    private int senderID;
    private int receiverID;

    public Parcel(){}

    public Parcel(int parcelID, String status, double cost, double weight,
    double length, double width, double height, int code, int carID, 
    int departmentID, int senderID, int receiverID ){
        this.parcelID = parcelID;
        this.status = status;
        this.cost = cost;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.code = code;
        this.carID = carID;
        this.departmentID = departmentID;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

	public int getParcelID() {
        return parcelID;
    }

    public void setParcelID(int parcelID) {
        this.parcelID = parcelID;
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

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }
}

