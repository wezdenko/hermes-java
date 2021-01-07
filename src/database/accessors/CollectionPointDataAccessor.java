package database.accessors;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.CollectionPoint;


public class CollectionPointDataAccessor {
    
    private Connection connection ;

    public CollectionPointDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public CollectionPoint getCollectionPoint(String query) throws SQLException {
        return this.getCollectionPointList(query).get(0);
    }

    public List<CollectionPoint> getCollectionPointList(String query) throws SQLException {
        CompanyAddressDataAccessor addrAccessor = new CompanyAddressDataAccessor(connection);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<CollectionPoint> collectionPointList = new ArrayList<>();

        while (result.next()) {
            CollectionPoint collectionPoint = new CollectionPoint();

            String addrquery = "select * from company_addresses " +
                "where comp_addr_id = " +
                "(select ADDRESS_ID from COLLECTION_POINTS " +
                String.format("where COLLECTION_POINTS_ID = %d)", result.getInt("COLLECTION_POINTS_ID"));

            collectionPoint.setAddress(addrAccessor.getAddress(addrquery));
            collectionPoint.setName(result.getString("name"));
            collectionPoint.setCollectionPointID(result.getInt("COLLECTION_POINTS_ID"));
            
            collectionPointList.add(collectionPoint);
        }

        statement.close();
        return collectionPointList;
    }

    public void setCollectionPoint(CollectionPoint collectionPoint) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, collectionPoint.getCollectionPointID());
        prepStatement.setString(2, collectionPoint.getName());
        prepStatement.setInt(4, collectionPoint.getAddress().getAddressID());

        prepStatement.execute();
        prepStatement.close();
    }

    public void updateCollectionPoint(CollectionPoint collectionPoint) throws SQLException {
        String query = "UDPATE colletion_points SET name = ?, address_id = ? " +
        "WHERE collection_points_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setString(1, collectionPoint.getName());
        prepStatement.setInt(2, collectionPoint.getAddress().getAddressID());
        prepStatement.setInt(3, collectionPoint.getCollectionPointID());

        CompanyAddressDataAccessor addressAccessor = new CompanyAddressDataAccessor(connection);
        addressAccessor.updateAddress(collectionPoint.getAddress());

        prepStatement.execute();
        prepStatement.close();
    }

    public void deleteCollectionPoint(CollectionPoint collectionPoint) throws SQLException {
        String query = "DELETE FROM collection_points WHERE collection_points_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, collectionPoint.getCollectionPointID());

        prepStatement.execute();
        prepStatement.close();
    }
}
