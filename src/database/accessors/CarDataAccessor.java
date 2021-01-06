package database.accessors;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import database.classes.Car;


public class CarDataAccessor {
    
    private Connection connection ;

    public CarDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Car getCar(String query) throws SQLException {
        return this.getCarList(query).get(0);
    }

    public List<Car> getCarList(String query) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<Car> carList = new ArrayList<>();

        while (result.next()) {
            Car car = new Car();

            car.setCarsID(result.getInt("CARS_ID"));
            car.setPlates(result.getString("PLATES"));
            car.setModel(result.getString("MODEL"));
            car.setDepartmentID(result.getInt("DEPARTMENT_ID"));
            car.setEmployeeID(result.getInt("EMPLOYEE_ID"));

            carList.add(car);
        }

        statement.close();
        return carList;
    }

    public void setCar(Car car) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, car.getCarsID());
        prepStatement.setString(2, car.getPlates());
        prepStatement.setString(3, car.getModel());
        prepStatement.setInt(4, car.getDepartmentID());
        prepStatement.setInt(5, car.getEmployeeID());

        prepStatement.execute();
        prepStatement.close();
    }

    public void updateCar(Car car) throws SQLException {
        String query = "UPDATE cars SET plates = ?, model = ?, department_id = ?, employee_id = ? " + 
        "WHERE cars_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setString(1, car.getPlates());
        prepStatement.setString(2, car.getModel());
        prepStatement.setInt(3, car.getDepartmentID());
        prepStatement.setInt(4, car.getEmployeeID());
        prepStatement.setInt(5, car.getCarsID());

        prepStatement.execute();
        prepStatement.close();
    }
}