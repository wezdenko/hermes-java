package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import database.Database;
import database.accessors.LoginAccessor;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginAccessorTest {

    @Test
    public void getConnectionTest() {

        try {
            Connection con = Database.getConnection();
            LoginAccessor loginAccessor = new LoginAccessor(con);

            // checking if bad login or password throws exception
            assertThrows(SQLException.class, () -> {
                loginAccessor.login("courier", "badPassword");
            });

            assertThrows(SQLException.class, () -> {
                loginAccessor.login("badLogin", "courier");
            });

            assertThrows(SQLException.class, () -> {
                loginAccessor.login("badLogin", "badPassword");
            });

            // checking if exception code is good
            try {
                loginAccessor.login("courier", "badPassword");
            } catch (SQLException sqlException) {
                assertEquals(LoginAccessor.PasswordError, sqlException.getErrorCode());
            }

            try {
                loginAccessor.login("badLogin", "courier");
            } catch (SQLException sqlException) {
                assertEquals(LoginAccessor.LoginError, sqlException.getErrorCode());
            }

            try {
                loginAccessor.login("badLogin", "badPassword");
            } catch (SQLException sqlException) {
                assertEquals(LoginAccessor.LoginError, sqlException.getErrorCode());
            }

            Database.closeConnection(con);

        } catch (SQLException e) {
            System.out.println("Exception during LoginAccessorTest.getConnectionTest()");
            System.out.println(e.getMessage());
        }
    }
}
