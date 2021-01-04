package database.accessors;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import database.classes.Employee;


public class EmployeeDataAccessor {
    
    private Connection connection ;

    public EmployeeDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Employee getEmployee(String query) throws SQLException {
        return this.getEmployeeList(query).get(0);
    }

    public List<Employee> getEmployeeList(String query) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<Employee> employeeList = new ArrayList<>();

        while (result.next()) {
            Employee employee = new Employee();

            employee.setEmployeeID(result.getInt("EMPLOYEES_ID"));
            employee.setName(result.getString("NAME"));
            employee.setSurname(result.getString("SURNAME"));
            employee.setPesel(result.getLong("PESEL"));
            employee.setSalary(result.getInt("SALARY"));
            employee.setEmploymentDate(result.getDate("EMPLOYMENT_DATE"));
            employee.setLogin(result.getString("LOGIN"));
            employee.setPassword(result.getString("PASSWORD"));
            employee.setCarID(result.getInt("CAR_ID"));
            employee.setPositionID(result.getInt("POSITION_ID"));
            employee.setManagerID(result.getInt("MANAGER_ID"));
            employee.setDepartmentID(result.getInt("DEPARTMENT_ID"));

            employeeList.add(employee);
        }

        statement.close();
        return employeeList;
    }

    public void setDepartment(Employee employee) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, employee.getEmployeeID());
        prepStatement.setString(2, employee.getName());
        prepStatement.setString(3, employee.getSurname());
        prepStatement.setLong(4, employee.getPesel());
        prepStatement.setInt(5, employee.getSalary());
        prepStatement.setDate(6, employee.getEmploymentDate());
        prepStatement.setString(7, employee.getLogin());
        prepStatement.setString(8, employee.getPassword());
        prepStatement.setInt(9, employee.getCarID());
        prepStatement.setInt(10, employee.getPositionID());
        prepStatement.setInt(11, employee.getManagerID());
        prepStatement.setInt(12, employee.getDepartmentID());

        prepStatement.execute();
        prepStatement.close();
    }
}
