package database.accessors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class LoginAccessor {

    public static final int LoginError = 20010;
    public static final int PasswordError = 20011;

    private Connection connection;

    public LoginAccessor(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int login(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select login(?, ?) from dual");
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();
        int employeeID = -1;

        while (result.next()) {
            employeeID = result.getInt(1);
        }

        result.close();
        statement.close();
        return employeeID;
    }
}
