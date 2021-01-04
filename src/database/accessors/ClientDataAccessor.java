package database.accessors;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.Client;


public class ClientDataAccessor {
    
    private Connection connection ;

    public ClientDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Client getClient(String query) throws SQLException {
        return this.getClientsList(query).get(0);
    }

    public List<Client> getClientsList(String query) throws SQLException {
        ClientAddressDataAccessor addrAccessor = new ClientAddressDataAccessor(connection);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<Client> clientsList = new ArrayList<>();

        while (result.next()) {
            Client client = new Client();

            String addrQuery = "select * from client_addresses " +
                "where client_addresses_id = " +
                "(select address_id from clients " +
                String.format("where clients_id = %d)", result.getInt("clients_id"));

            client.setClientID(result.getInt("clients_id"));
            client.setName(result.getString("name"));
            client.setSurname(result.getString("surname"));
            client.setEmail(result.getString("email"));
            client.setPhoneNumber(result.getString("phone_number"));
            client.setClientAddress(addrAccessor.getAddress(addrQuery));

            clientsList.add(client);
        }

        statement.close();
        return clientsList;
    }

    public void setClient(Client client) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, client.getClientID());
        prepStatement.setString(2, client.getName());
        prepStatement.setString(3, client.getSurname());
        prepStatement.setString(4, client.getEmail());
        prepStatement.setString(5, client.getPhoneNumber());
        prepStatement.setInt(6, client.getClientAddress().getAddressID());

        prepStatement.execute();
        prepStatement.close();
    }

}
