package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.ParcelDataAccessor;
import database.classes.Parcel;

import java.sql.Connection;
import java.sql.SQLException;

public class ParcelAccessorTest {
    
    @Test
    public void getParcel() throws SQLException {

        Connection con = Database.getConnection();
        ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(con);

        Parcel parcel = parcelAccessor.getParcel("select * from parcels where parcels_id = 0");

        assertEquals(parcel.getID(), 0);
        assertEquals(parcel.getStatus(), "DELIVERED");
        assertEquals(parcel.getWeight(), 4258, 1);
        assertEquals(parcel.getLength(), 41, 1);
        assertEquals(parcel.getWidth(), 28, 1);
        assertEquals(parcel.getHeight(), 73, 1);
        assertEquals(parcel.getCode(), 727103254);
        assertEquals(parcel.getCarID(), 0);
        assertEquals(parcel.getCollectionPointID(), 0);
        assertEquals(parcel.getDepartmentID(), 0);
        assertEquals(parcel.getSender().getClientID(), 39);
        assertEquals(parcel.getReceiver().getClientID(), 13);

        Database.closeConnection(con);
    }
}
