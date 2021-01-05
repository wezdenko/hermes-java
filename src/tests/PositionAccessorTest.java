package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.PositionDataAccessor;
import database.classes.Position;

import java.sql.Connection;
import java.sql.SQLException;

public class PositionAccessorTest {
    
    @Test
    public void getPositionTest() throws SQLException {

        Connection con = Database.getConnection();
        PositionDataAccessor positionAccessor = new PositionDataAccessor(con);

        Position position = positionAccessor.getPosition("select * from position where position_id = 0");

        assertEquals(position.getId(), 0);
        assertEquals(position.getMaxSalary(), 2884, 1);
        assertEquals(position.getMinSalary(), 7123, 1);

        Database.closeConnection(con);
    }
}
