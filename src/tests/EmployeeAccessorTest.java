package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.EmployeeDataAccessor;
import database.classes.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeAccessorTest {
    
    @Test
    public void getEmployeeTest() throws SQLException {

        Connection con = Database.getConnection();
        EmployeeDataAccessor employeeAccessor = new EmployeeDataAccessor(con);

        Employee employee = employeeAccessor.getEmployee("select * from employees where employees_id = 0");

        assertEquals(employee.getEmployeeID(), 0);
        assertEquals(employee.getName(), "Elmo");
        assertEquals(employee.getSurname(), "Hagenes");
        assertEquals(employee.getPesel(), 176831181048L);
        assertEquals(employee.getSalary(), 3286, 1);
        assertEquals(employee.getEmploymentDate(), new Date(67, 4, 10));
        assertEquals(employee.getLogin(), "manager");
        assertEquals(employee.getPassword(), "manager");
        assertEquals(employee.getCarID(), 0);
        assertEquals(employee.getPositionID(), 0);
        assertEquals(employee.getManagerID(), 0);
        assertEquals(employee.getDepartmentID(), 3);

        Database.closeConnection(con);
    }
}
