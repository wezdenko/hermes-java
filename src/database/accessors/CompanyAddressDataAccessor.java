package database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.Address;


public class CompanyAddressDataAccessor {
    
    private Connection connection ;

    public CompanyAddressDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Address getAddress(String query) throws SQLException {
        return this.getAddressesList(query).get(0);
    }

    public List<Address> getAddressesList(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery(query);

        List<Address> addrList = new ArrayList<>();

        while (result.next()) {
            Address addr = new Address();

            addr.setAddressID(result.getInt("comp_addr_id"));
            addr.setStreet(result.getString("street"));
            addr.setHouseNumber(result.getInt("house_number"));
            addr.setApartmentNumber(result.getInt("apartment_number"));
            addr.setCity(result.getString("city"));
            addr.setPostalCode(result.getString("postal_code"));
            addr.setCountryID(result.getInt("countries_id"));

            addrList.add(addr);
        }

        statement.close();
        return addrList;
    }

    public void setAddress(Address addr) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, addr.getAddressID());
        prepStatement.setString(2, addr.getStreet());
        prepStatement.setInt(3, addr.getHouseNumber());
        prepStatement.setInt(4, addr.getApartmentNumber());
        prepStatement.setString(5, addr.getCity());
        prepStatement.setString(6, addr.getPostalCode());
        prepStatement.setInt(7, addr.getCountryID());

        prepStatement.execute();
        prepStatement.close();
    }

    public void updateAdrress(Address addr) throws SQLException {
        String query = "UPDATE company_addresses SET street = ?, house_number = ?, " +
        "apartment_number = ?, city = ?, postal_code = ?, countries_id = ?" +
        "WHERE comp_addr_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setString(1, addr.getStreet());
        prepStatement.setInt(2, addr.getHouseNumber());
        prepStatement.setInt(3, addr.getApartmentNumber());
        prepStatement.setString(4, addr.getCity());
        prepStatement.setString(5, addr.getPostalCode());
        prepStatement.setInt(6, addr.getCountryID());
        prepStatement.setInt(7, addr.getAddressID());

        prepStatement.execute();
        prepStatement.close();
    }
}

