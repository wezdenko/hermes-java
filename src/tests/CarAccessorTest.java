package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.CarDataAccessor;
import database.classes.Car;

import java.sql.Connection;
import java.sql.SQLException;

public class CarAccessorTest {
    
    @Test
    public void getCar() throws SQLException {

        Connection con = Database.getConnection();
        CarDataAccessor carAccessor = new CarDataAccessor(con);

        Car car = carAccessor.getCar("select * from cars where cars_id = 0");

        assertEquals(car.getCarsID(), 0);
        assertEquals(car.getPlates(), "QEX-6865");
        assertEquals(car.getModel(), "Chevy Silverado");
        assertEquals(car.getDepartmentID(), 4);
        assertEquals(car.getEmployeeID(), 4);

        Database.closeConnection(con);
    }
}
