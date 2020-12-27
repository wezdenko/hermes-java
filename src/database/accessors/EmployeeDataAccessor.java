package database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.Employee;

public class EmployeeDataAccessor {
    private Connection connection ;

    public EmployeeDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Employee getEmployee(String query) throws SQLException {
        return this.getEmployeesList(query).get(0);
    }

    public List<Employee> getEmployeesList(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery(query);

        List<Employee> empList = new ArrayList<>();

        while (result.next()) {
            Employee emp = new Employee();

            emp.setID(result.getInt("employees_id"));
            emp.setName(result.getString("name"));
            emp.setSurname(result.getString("surname"));
            emp.setPesel(result.getInt("pesel"));
            emp.setEmploymentDate("employment_date");
            emp.setLogin(result.getString("login"));
            emp.setPassword(result.getString("password"));
            emp.setCarID(result.getInt("car_id"));
            emp.setPositionID(result.getInt("position_id"));
            emp.setManagerID(result.getInt("manager_id"));
            emp.setDepartmentID(result.getInt("department_id"));

            empList.add(emp);
        }

        result.close();
        statement.close();
        return empList;
    }
/*
    public void setempess(Employee emp) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, emp.getClientempessID());
        prepStatement.setString(2, emp.getStreet());
        prepStatement.setInt(3, emp.getHouseNumber());
        prepStatement.setInt(4, emp.getApartmentNumber());
        prepStatement.setString(5, emp.getCity());
        prepStatement.setString(6, emp.getPostalCode());
        prepStatement.setInt(7, emp.getCountryID());

        prepStatement.execute();
        prepStatement.close();
    }
*/
}
