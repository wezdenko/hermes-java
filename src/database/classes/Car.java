package database.classes;

public class Car {
    private int carsID;
    private String plates;
    private String model;
    private int departmentID;
    private int employeeID;


    public Car() {
    }

    public Car(int cars_id, String plates, String model, int departmentID, int employeeID) {
        this.carsID = cars_id;
        this.plates = plates;
        this.model = model;
        this.departmentID = departmentID;
        this.employeeID = employeeID;
    }

    public int getCarsID() {
        return this.carsID;
    }

    public String getCarsID_S() {
        return Converter.IntToString(this.carsID);
    }

    public void setCarsID(int cars_id) {
        this.carsID = cars_id;
    }

    public String getPlates() {
        return this.plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDepartmentID() {
        return this.departmentID;
    }

    public String getDepartmentID_S() {
        return Converter.IntToString(this.departmentID);
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public String getEmployeeID_S() {
        return Converter.IntToString(this.employeeID);
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}

