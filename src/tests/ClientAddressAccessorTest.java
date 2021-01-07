package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.ClientAddressDataAccessor;
import database.classes.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

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

    @Test
    public void updateAdrressTest() throws SQLException{
    
        Connection con = Database.getConnection();
        ClientAddressDataAccessor addressAccessor = new ClientAddressDataAccessor(con);

        // generating random data
        int houseNumbers[] = {11, 22, 33};
        String cities[] = {"Gdansk", "Warszawa", "Krakow"};
        int randomIds[] = {1, 2, 3};
        String postalCodes[] = {"11222", "11333", "11444"};
        String streetNames[] = {"Gerard Square", "Jarvis Trail", "Mraz Divide"};

        int rnd = new Random().nextInt(3);

        // setting random data
        Address address = new Address();
        address.setAddressID(5);
        address.setStreet(streetNames[rnd]);
        address.setHouseNumber(houseNumbers[rnd]);
        address.setApartmentNumber(houseNumbers[rnd]);
        address.setCity(cities[rnd]);
        address.setPostalCode(postalCodes[rnd]);
        address.setCountryID(randomIds[rnd]);

        // tested function
        addressAccessor.updateAdrress(address);

        // tests
        assertEquals(address.getAddressID(), 5);
        assertEquals(address.getStreet(), streetNames[rnd]);
        assertEquals(address.getHouseNumber(), houseNumbers[rnd]);
        assertEquals(address.getApartmentNumber(), houseNumbers[rnd]);
        assertEquals(address.getCity(), cities[rnd]);
        assertEquals(address.getPostalCode(), postalCodes[rnd]);
        assertEquals(address.getCountryID(), randomIds[rnd]);

        Database.closeConnection(con);
    }
}
