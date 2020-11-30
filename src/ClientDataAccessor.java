import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ClientDataAccessor {
    
    private Connection connection ;

    public ClientDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Client getClient(String query) throws SQLException {
        Client client = new Client();
        AddressDataAccessor addrAccessor = new AddressDataAccessor(connection);

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery(query);

        String addrQuery = "select * from client_addresses" +
            "where client_addresses_id =" +
            "(select address_id from clients" +
            String.format("where clients_id = %d)", client.getClientID());

        client.setClientID(result.getInt("clients_id"));
        client.setName(result.getString("name"));
        client.setSurname(result.getString("surname"));
        client.setEmail(result.getString("email"));
        client.setPhoneNumber(result.getString("phone_number"));
        client.setClientAddress(addrAccessor.getAddress(addrQuery));

        statement.close();
        return client;
    }

    public void setClient(Client client) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, client.getClientID());
        prepStatement.setString(2, client.getName());
        prepStatement.setString(3, client.getSurname());
        prepStatement.setString(4, client.getEmail());
        prepStatement.setString(5, client.getPhoneNumber());
        prepStatement.setInt(6, client.getClientAddress().getClientAddressID());

        prepStatement.execute();
        prepStatement.close();
    }

}
