package database.classes;

public class Employee {
    private int ID;
    private String name;
    private String surname;
    private int pesel;
    private float salary;
    private String employmentDate; // do zmiany na obiekt Date
    private String login;
    private String password;
    private int carID;
    private int positionID;
    private int managerID;
    private int departmentID;

    public Employee() {
    }

    public Employee(int iD, String name, String surname, int pesel, float salary, String employmentDate, String login,
            String password, int carID, int positionID, int managerID, int departmentID) {
        this.ID = iD;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.login = login;
        this.password = password;
        this.carID = carID;
        this.positionID = positionID;
        this.managerID = managerID;
        this.departmentID = departmentID;
    }

    public int getID() {
        return ID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if (salary < 0) {
            throw new IllegalArgumentException(String.format("salary cannot be negative, given salary = %f", salary));
        }
        this.salary = salary;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        if (pesel <= 0) {
            throw new IllegalArgumentException(String.format("pesel cannot be negative, given pesel = %f", pesel));
        }
        this.pesel = pesel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int iD) {
        this.ID = iD;
    }
}
