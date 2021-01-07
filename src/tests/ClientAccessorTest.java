package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.ClientDataAccessor;
import database.classes.Client;
import database.classes.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class ClientAccessorTest {
    
    @Test
    public void getClientTest() throws SQLException {

        Connection con = Database.getConnection();
        ClientDataAccessor clientAccessor = new ClientDataAccessor(con);

        Client client = clientAccessor.getClient("select * from clients where clients_id = 0");

        assertEquals(client.getClientID(), 0);
        assertEquals(client.getName(), "Julia");
        assertEquals(client.getSurname(), "Bradtke");
        assertEquals(client.getEmail(), "julia.bradtke@email.com");
        assertEquals(client.getPhoneNumber(), "740937164");

        Address address = client.getClientAddress();

        assertEquals(address.getAddressID(), 3);
        assertEquals(address.getStreet(), "Senger Overpass");
        assertEquals(address.getHouseNumber(), 4513);
        assertEquals(address.getApartmentNumber(), 9026);
        assertEquals(address.getCity(), "Lake Sadiechester");
        assertEquals(address.getPostalCode(), "98190");
        assertEquals(address.getCountryID(), 36);

        Database.closeConnection(con);
    }

    @Test
    public void updateClientTest() throws SQLException{
    
        Connection con = Database.getConnection();
        ClientDataAccessor clientAccessor = new ClientDataAccessor(con);

        Client client = clientAccessor.getClient("select * from clients where clients_id = 5");

        // generating random data
        String names[] = {"Julia", "Antonina", "Allen"};
        String surnames[] = {"Fadel", "Nicolas", "Wolff"};
        String emails[] = {"julia.fadel@email.com", "antonina.nicolas@email.com", "allen.wolff@email.com"};
        String phoneNumbers[] = {"111222333", "444555666", "777888999"};

        int rnd = new Random().nextInt(3);

        // setting random data
        client.setName(names[rnd]);
        client.setSurname(surnames[rnd]);
        client.setEmail(emails[rnd]);
        client.setPhoneNumber(phoneNumbers[rnd]);

        // tested function
        clientAccessor.updateClient(client);

        // tests
        assertEquals(client.getClientID(), 5);
        assertEquals(client.getName(), names[rnd]);
        assertEquals(client.getSurname(), surnames[rnd]);
        assertEquals(client.getEmail(), emails[rnd]);
        assertEquals(client.getPhoneNumber(), phoneNumbers[rnd]);

        Database.closeConnection(con);
    }
}
