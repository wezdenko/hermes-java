package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.DepartmentsDataAccessor;
import database.classes.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class DepartmentAccessorTest {

    @Test
    public void getCollectionPointTest() throws SQLException {

        Connection con = Database.getConnection();
        DepartmentsDataAccessor departmentsAccessor = new DepartmentsDataAccessor(con);

        Department department = departmentsAccessor.getDepartment("select * from departments where departments_id = 0");

        assertEquals(department.getDepartmentID(), 0);
        assertEquals(department.getName(), "Farming");
        assertEquals(department.getCreationDate(), new Date(90, 0, 13));
        assertEquals(department.getAddress().getAddressID(), 3);

        Database.closeConnection(con);
    }
}
