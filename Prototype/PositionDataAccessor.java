import java.sql.Connection ;
import java.sql.PreparedStatement;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

/**
 * This class handles connection to table "positions"
 * in our database.
 * 
 * How to use:
 *      Connection con = Database.getConnection("login", "password");
 *      
 *      PositionDataAccessor accessor = new PositionDataAccessor(con);
 *      // get data
 *      List<Position> pList = accessor.getPositionList();
 *      // save data
 *      Position newPosition = new Position(130, "position name", 2000, 3000);
 *      accessor.setPosition(newPosition);
 * 
 *      Database.closeConnection(con);
 */

public class PositionDataAccessor {

    private Connection connection ;

    public PositionDataAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<Position> getPositionsList() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from positions");
        
        List<Position> positionList = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("position_id");
            String name = result.getString("name");
            float minSalary = result.getFloat("min_salary");
            float maxSalary = result.getFloat("max_salary");
            
            Position position = new Position(id, name, minSalary, maxSalary);
            positionList.add(position);
        }

        statement.close();
        return positionList;
    }

    public void setPosition(Position position) throws SQLException {
        String insertSQL = "INSERT INTO positions VALUES(?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(insertSQL);

        prepStatement.setInt(1, position.getId());
        prepStatement.setString(2, position.getName());
        prepStatement.setFloat(3, position.getMinSalary());
        prepStatement.setFloat(4, position.getMaxSalary());

        prepStatement.execute();
        prepStatement.close();
    }
}