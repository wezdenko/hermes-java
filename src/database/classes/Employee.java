package database.classes;

import java.sql.Date;

public class Employee {
    private int employeeID;
    private String name;
    private String surname;
    private long pesel;
    private int salary;
    private Date employmentDate;
    private String login;
    private String password;
    private int carID;
    private int positionID;
    private int managerID;
    private int departmentID;

    public Employee() {
    }

    public Employee(int employeeID, String name, String surname, long pesel, int salary, Date employmentDate, String login, String password, int carID, int positionID, int managerID, int departmentID) {
        this.employeeID = employeeID;
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

    public int getCarID() {
        return this.carID;
    }

    public String getCarID_S() {
        return Converter.IntToString(this.carID);
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getPositionID() {
        return this.positionID;
    }

    public String getPositionID_S() {
        return Converter.IntToString(this.positionID);
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getManagerID() {
        return this.managerID;
    }

    public String getManagerID_S() {
        return Converter.IntToString(this.managerID);
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPesel() {
        return this.pesel;
    }

    public String getPesel_S() {
        return Converter.LongToString(this.pesel);
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getSalary_S() {
        return Converter.IntToString(this.salary);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEmploymentDate() {
        return this.employmentDate;
    }

    public String getEmploymentDate_S() {
        return Converter.DateToString(this.employmentDate);
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
