package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.ClientAddressDataAccessor;
import database.classes.Address;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientAddressAccessorTest {
    
    @Test
    public void getAddressTest() {
        try {
            Connection con = Database.getConnection();
            ClientAddressDataAccessor addressAccessor = new ClientAddressDataAccessor(con);
            
            Address address = addressAccessor.getAddress("select * from client_addresses where client_addresses_id = 0");

            assertEquals(address.getAddressID(), 0);
            assertEquals(address.getStreet(), "Hackett Fords");
            assertEquals(address.getHouseNumber(), 1031);
            assertEquals(address.getApartmentNumber(), 2062);
            assertEquals(address.getCity(), "Newtonland");
            assertEquals(address.getPostalCode(), "22368");
            assertEquals(address.getCountryID(), 44);

            Database.closeConnection(con);

        } catch (SQLException e) {
            System.out.println("Exception during ClientAddressAccessorTest.getAddressTest()");
            System.out.println(e.getMessage());
            assertEquals(1, 0);
        }
    }
}
