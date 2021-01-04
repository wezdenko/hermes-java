package database;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

/**
 * This class has hardcoded static attribute "url".
 * Don't change that for now.
 * 
 * How to use:
 *      Connection con = Database.getConnection();
 *      // do smth in database
 *      Database.closeConnection(con);
 */
public class Database {

    private static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    private static String login = "BD1_Z15";
    private static String password = "twheas";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    public static void closeConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {}
    }
}