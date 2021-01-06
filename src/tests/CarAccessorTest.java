package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.CarDataAccessor;
import database.classes.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

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

    @Test
    public void updateCar() throws SQLException {

        Connection con = Database.getConnection();
        CarDataAccessor carAccessor = new CarDataAccessor(con);

        Car car = carAccessor.getCar("select * from cars where cars_id = 5");

        // generating random data
        String plates[] = {"ABC-1234", "BDF-5678", "JKL-9876"};
        String models[] = {"Chevy Silverado", "Honda Odyssey", "BMW 328i"};
        int randomId[] = {1, 2, 3};

        int rnd = new Random().nextInt(3);

        // setting random data
        car.setPlates(plates[rnd]);
        car.setModel(models[rnd]);
        car.setDepartmentID(randomId[rnd]);

        // tested function
        carAccessor.updateCar(car);

        // tests
        assertEquals(car.getPlates(), plates[rnd]);
        assertEquals(car.getModel(), models[rnd]);
        assertEquals(car.getDepartmentID(), randomId[rnd]);

        Database.closeConnection(con);
    }
}
