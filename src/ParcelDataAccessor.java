import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ParcelDataAccessor {
    
    private Connection connection ;

    public ParcelDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Parcel getParcel(String query) throws SQLException {
        Parcel parcel = new Parcel();
        ClientDataAccessor clientAccessor = new ClientDataAccessor(connection);

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery(query);

        String senderQuery = "select c.* from clients c, parcels p" +
            "where p.sender_id = c.clients_id" +
            String.format("and p.parcels_id = %d", result.getInt("sender_id"));

        String receiverQuery = "select c.* from clients c, parcels p" +
            "where p.receiver_id = c.clients_id" +
            String.format("and p.parcels_id = %d", result.getInt("receiver_id"));

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

        statement.close();
        return parcel;
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
