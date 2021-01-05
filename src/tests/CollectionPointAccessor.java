package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.CollectionPointDataAccessor;
import database.classes.CollectionPoint;

import java.sql.Connection;
import java.sql.SQLException;

public class CollectionPointAccessor {

    @Test
    public void getCollectionPointTest() throws SQLException {

        Connection con = Database.getConnection();
        CollectionPointDataAccessor collectionPointAccessor = new CollectionPointDataAccessor(con);

        CollectionPoint collectionPoint = collectionPointAccessor
                .getCollectionPoint("select * from collection_points where collection_points_id = 0");

        assertEquals(collectionPoint.getCollectionPointID(), 0);
        assertEquals(collectionPoint.getName(), "PACZKOMAX 3000");
        assertEquals(collectionPoint.getAddress().getAddressID(), 0);

        Database.closeConnection(con);
    }
}
