package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.ParcelDataAccessor;
import database.classes.Parcel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

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

    @Test
    public void updateParcelTest() throws SQLException{
    
        Connection con = Database.getConnection();
        ParcelDataAccessor parcelAccessor = new ParcelDataAccessor(con);

        Parcel parcel = parcelAccessor.getParcel("select * from parcels where parcels_id = 5");

        // generating random data
        int randNum[] = {11, 22, 33};
        String status[] = {"IN_WAREHOUSE", "IN_C_POINT", "ON_ROAD"};
        int randCode[] = {119123326, 321749326, 119749123};
        int randId[] = {1, 2, 3};

        int rnd = new Random().nextInt(3);

        // setting random data
        parcel.setStatus(status[rnd]);
        parcel.setWeight(randNum[rnd]);
        parcel.setLength(randNum[rnd]);
        parcel.setWidth(randNum[rnd]);
        parcel.setHeight(randNum[rnd]);
        parcel.setCode(randCode[rnd]);
        parcel.setCarID(randId[rnd]);
        parcel.setCollectionPointID(0);
        parcel.setDepartmentID(0);

        // tested function
        parcelAccessor.updateParcel(parcel);

        // tests
        assertEquals(parcel.getID(), 5);
        assertEquals(parcel.getStatus(), status[rnd]);
        assertEquals(parcel.getWeight(), randNum[rnd], 1);
        assertEquals(parcel.getLength(), randNum[rnd], 1);
        assertEquals(parcel.getWidth(), randNum[rnd], 1);
        assertEquals(parcel.getHeight(), randNum[rnd], 1);
        assertEquals(parcel.getCode(), randCode[rnd]);
        assertEquals(parcel.getCarID(), randId[rnd]);
        assertEquals(parcel.getCollectionPointID(), 0);
        assertEquals(parcel.getDepartmentID(), 0);
        assertEquals(parcel.getSender().getClientID(), 30);
        assertEquals(parcel.getReceiver().getClientID(), 45);

        Database.closeConnection(con);
    }
}
