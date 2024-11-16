public class LoginAppTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mahad";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mahadstassignment";

    public static void main(String[] args) throws ClassNotFoundException {
        testValidUserLogin();
        testInvalidUserLogin();
        testEmptyEmailField();
        testEmptyPasswordField();
        testSQLInjectionAttempt();
    }

    private static void testValidUserLogin() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "password123");
        assert result != null && result.equals("John Doe") : "Test Valid User Login Failed";
        System.out.println("Test Valid User Login Passed");
    }

    private static void testInvalidUserLogin() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("invalid@example.com", "invalidpassword");
        assert result == null : "Test Invalid User Login Failed";
        System.out.println("Test Invalid User Login Passed");
    }

    private static void testEmptyEmailField() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("", "password123");
        assert result == null : "Test Empty Email Field Failed";
        System.out.println("Test Empty Email Field Passed");
    }

    private static void testEmptyPasswordField() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "");
        assert result == null : "Test Empty Password Field Failed";
        System.out.println("Test Empty Password Field Passed");
    }

    private static void testSQLInjectionAttempt() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "' OR '1'='1");
        assert result == null : "Test SQL Injection Attempt Failed";
        System.out.println("Test SQL Injection Attempt Passed");
    }
}
