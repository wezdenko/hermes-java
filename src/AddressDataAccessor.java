import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;


public class AddressDataAccessor {
    
    private Connection connection ;

    public AddressDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public ClientAddress getAddress(String query) throws SQLException {
        return this.getAddressesList(query).get(0);
    }

    public List<ClientAddress> getAddressesList(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery(query);

        List<ClientAddress> addrList = new ArrayList<>();

        while (result.next()) {
            ClientAddress addr = new ClientAddress();

            addr.setClientAddressID(result.getInt("client_addresses_id"));
            addr.setStreet(result.getString("street"));
            addr.setHouseNumber(result.getInt("house_number"));
            addr.setApartmentNumber(result.getInt("apartment_number"));
            addr.setCity(result.getString("city"));
            addr.setPostalCode(result.getString("postal_code"));
            addr.setCountryID(result.getInt("country_id"));

            addrList.add(addr);
        }

        statement.close();
        return addrList;
    }

    public void setAddress(ClientAddress addr) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, addr.getClientAddressID());
        prepStatement.setString(2, addr.getStreet());
        prepStatement.setInt(3, addr.getHouseNumber());
        prepStatement.setInt(4, addr.getApartmentNumber());
        prepStatement.setString(5, addr.getCity());
        prepStatement.setString(6, addr.getPostalCode());
        prepStatement.setInt(7, addr.getCountryID());

        prepStatement.execute();
        prepStatement.close();
    }
}
