package database.accessors;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.Department;


public class DepartmentsDataAccessor {
    
    private Connection connection ;

    public DepartmentsDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Department getDepartment(String query) throws SQLException {
        return this.getDepartmentList(query).get(0);
    }

    public List<Department> getDepartmentList(String query) throws SQLException {
        CompanyAddressDataAccessor addrAccessor = new CompanyAddressDataAccessor(connection);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<Department> departmentList = new ArrayList<>();

        while (result.next()) {
            Department department = new Department();

            String addrquery = "select * from company_addresses " +
                "where comp_addr_id = " +
                "(select address_id from departments " +
                String.format("where departments_id = %d)", result.getInt("departments_id"));

            department.setAddress(addrAccessor.getAddress(addrquery));
            department.setName(result.getString("name"));
            department.setDepartmentID(result.getInt("departments_id"));
            department.setCreationDate(result.getDate("creation_date"));
            
            departmentList.add(department);
        }

        statement.close();
        return departmentList;
    }

    public void setDepartment(Department department) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, department.getDepartmentID());
        prepStatement.setString(2, department.getName());
        prepStatement.setDate(3, department.getCreationDate());
        prepStatement.setInt(4, department.getAddress().getAddressID());

        prepStatement.execute();
        prepStatement.close();
    }

    public void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE departments SET name = ?, addresses_id = ? " +
        "WHERE departments_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setString(1, department.getName());
        prepStatement.setInt(2, department.getAddress().getAddressID());
        prepStatement.setInt(3, department.getDepartmentID());

        prepStatement.execute();
        prepStatement.close();
    }

    public void deleteDepartment(Department department) throws SQLException {
        String query = "DELETE FROM departments WHERE departments_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, department.getDepartmentID());

        prepStatement.execute();
        prepStatement.close();
    }
}
