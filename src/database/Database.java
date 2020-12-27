package database;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;

/**
 * This class has hardcoded static attribute "url".
 * Don't change that for now.
 * 
 * How to use:
 *      Connection con = Database.getConnection("login", "password");
 *      // do smth in database
 *      Database.closeConnection(con);
 */
public class Database {

    private static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";

    public static Connection getConnection(String login, String password) throws SQLException {
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