package database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List ;
import java.util.ArrayList ;

import database.classes.Parcel;


public class ParcelDataAccessor {
    
    private Connection connection ;

    public ParcelDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Parcel getParcel(String query) throws SQLException {
        return this.getParcelsList(query).get(0);
    }

    public List<Parcel> getParcelsList(String query) throws SQLException {
        ClientDataAccessor clientAccessor = new ClientDataAccessor(connection);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        List<Parcel> parcelList = new ArrayList<>();

        while (result.next()) {
            Parcel parcel = new Parcel();

            String senderQuery = "select * from clients " +
                String.format("where clients_id = %d", result.getInt("sender_id"));

            String receiverQuery = "select * from clients " +
                String.format("where clients_id = %d", result.getInt("receiver_id"));

            parcel.setID(result.getInt("parcels_id"));
            parcel.setStatus(result.getString("status"));
            parcel.setWeight(result.getInt("weight"));
            parcel.setLength(result.getInt("length"));
            parcel.setWidth(result.getInt("width"));
            parcel.setHeight(result.getInt("height"));
            parcel.setCode(result.getInt("code"));
            parcel.setCarID(result.getInt("car_id"));
            parcel.setDepartmentID(result.getInt("department_id"));
            parcel.setSender(clientAccessor.getClient(senderQuery));
            parcel.setReceiver(clientAccessor.getClient(receiverQuery));

            parcelList.add(parcel);
        }
        
        statement.close();
        return parcelList;
    }

    public void setParcel(Parcel parcel) throws SQLException {
        String query = "INSERT INTO positions VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(query);

        prepStatement.setInt(1, parcel.getID());
        prepStatement.setString(2, parcel.getStatus());
        prepStatement.setDouble(3, parcel.getWeight());
        prepStatement.setDouble(4, parcel.getLength());
        prepStatement.setDouble(5, parcel.getHeight());
        prepStatement.setInt(6, parcel.getCode());
        prepStatement.setInt(7, parcel.getCarID());
        prepStatement.setInt(8, parcel.getCollectionPointID());
        prepStatement.setInt(9, parcel.getDepartmentID());
        prepStatement.setInt(10, parcel.getSender().getClientID());
        prepStatement.setInt(11, parcel.getReceiver().getClientID());

        prepStatement.execute();
        prepStatement.close();
    }

}
