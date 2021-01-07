package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.EmployeeDataAccessor;
import database.classes.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
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

    @Test
    public void updateEmployeeTest() throws SQLException{
    
        Connection con = Database.getConnection();
        EmployeeDataAccessor employeeAccessor = new EmployeeDataAccessor(con);

        Employee employee = employeeAccessor.getEmployee("select * from employees where employees_id = 5");

        // generating random data
        String names[] = {"Julia", "Antonina", "Allen"};
        String surnames[] = {"Fadel", "Nicolas", "Wolff"};
        long pesels[] = {123831181048L, 321831181048L, 567831181048L};
        int salaries[] = {3500, 4500, 5000};
        String logins[] = {"julia.fadel", "antonina.nicolas", "allen.wolff"};
        String passwords[] = {"password1", "password2", "password3"};
        int randomId[] = {1, 2, 3};

        int rnd = new Random().nextInt(3);

        // setting random data
        employee.setName(names[rnd]);
        employee.setSurname(surnames[rnd]);
        employee.setPesel(pesels[rnd]);
        employee.setSalary(salaries[rnd]);
        //employee.setEmploymentDate(0);
        employee.setLogin(logins[rnd]);
        employee.setPassword(passwords[rnd]);
        //employee.setCarID(carID);
        //employee.setPositionID(positionID);
        //employee.setManagerID(managerID);
        employee.setDepartmentID(randomId[rnd]);

        // tested function
        employeeAccessor.updateEmployee(employee);

        employee = employeeAccessor.getEmployee("select * from employees where employees_id = 5");

        // tests
        assertEquals(employee.getEmployeeID(), 5);
        assertEquals(employee.getName(), names[rnd]);
        assertEquals(employee.getSurname(), surnames[rnd]);
        assertEquals(employee.getPesel(), pesels[rnd]);
        assertEquals(employee.getSalary(), salaries[rnd]);
        //assertEquals(employee.getEmploymentDate(), new Date(67, 4, 10));
        assertEquals(employee.getLogin(), logins[rnd]);
        assertEquals(employee.getPassword(), passwords[rnd]);
        //assertEquals(employee.getCarID(), 0);
        //assertEquals(employee.getPositionID(), 0);
        //assertEquals(employee.getManagerID(), 0);
        assertEquals(employee.getDepartmentID(), randomId[rnd]);

        Database.closeConnection(con);
    }
}
