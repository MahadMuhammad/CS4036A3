package test;

import main.LoginService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginAppTest {

    private LoginService loginService;

    @Before
    public void setUp() {
        // Initialize the service class instead of the GUI class
        loginService = new LoginService();
    }

    @Test
    public void testSuccessfulLogin() {
        // Test with a valid email and correct password
        String userName = loginService.authenticateUser("johndoe@example.com", "password123");
        assertEquals("John Doe", userName);
    }

    @Test
    public void testInvalidEmail() {
        // Test with an email that doesn't exist in the database
        String userName = loginService.authenticateUser("invalidemail@example.com", "password123");
        assertNull(userName);
    }

    @Test
    public void testIncorrectPassword() {
        // Test with a valid email but incorrect password
        String userName = loginService.authenticateUser("johndoe@example.com", "wrongpassword");
        assertNull(userName);
    }

    @Test
    public void testEmptyFields() {
        // Test with empty email and password fields
        String userName = loginService.authenticateUser("", "");
        assertNull(userName);
    }

    @Test
    public void testSqlInjectionAttempt() {
        // Test for SQL injection attack
        String userName = loginService.authenticateUser("johndoe@example.com", "' OR '1'='1");
        assertNull(userName);
    }
}
