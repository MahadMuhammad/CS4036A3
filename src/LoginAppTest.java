public class LoginAppTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mahad";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mahadstassignment";

    public static void main(String[] args) throws ClassNotFoundException {
        testValidUserLogin();
        testInvalidUserLogin();
        testEmptyEmailField();
        testEmptyPasswordField();
        testNonExistentUser();
        testSQLInjectionAttempt();
        testEmailWithSpecialCharacters();
        testPasswordWithSpecialCharacters();
        testCaseSensitivityInEmail();
        testCaseSensitivityInPassword();
    }

    private static void testValidUserLogin() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        {
            String result = app.authenticateUser("johndoe@example.com", "password123");
            assert result != null : "Test (John Doe) Valid User Login Failed - User should be found";
            assert result.equals("John Doe") : "Test Valid User Login Failed - Incorrect user name";
            System.out.println("Test Valid User Login (John Doe) Passed");
        }


        {
            String result = app.authenticateUser("tomclark@example.com", "password202");
            assert result != null : "Test (Tom Clark) Valid User Login Failed - User (tomclark@example.com) should be found";
            assert result.equals("Tom Clark") : "Test Valid User Login Failed - Incorrect user name";
            System.out.println("Test Valid User (Tom Clark) Login Passed");
        }
    }

    private static void testInvalidUserLogin() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("invalid@example.com", "invalidpassword");
        assert result == null : "Test Invalid User Login Failed - User should not be found";
        System.out.println("Test Invalid User Login Passed");
    }

    private static void testEmptyEmailField() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("", "password123");
        assert result == null : "Test Empty Email Field Failed - User should not be found";
        System.out.println("Test Empty Email Field Passed");
    }

    private static void testEmptyPasswordField() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "");
        assert result == null : "Test Empty Password Field Failed - User should not be found";
        System.out.println("Test Empty Password Field Passed");
    }

    private static void testSQLInjectionAttempt() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "' OR '1'='1");
        assert result == null : "Test SQL Injection Attempt Failed - User should not be found";
        System.out.println("Test SQL Injection Attempt Passed");
    }

    private static void testEmailWithSpecialCharacters() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("john.doe+test@example.com", "password123");
        assert result == null : "Test Email With Special Characters Failed - User should not be found";
        System.out.println("Test Email With Special Characters Passed");
    }

    private static void testPasswordWithSpecialCharacters() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "p@ssw0rd!");
        assert result == null : "Test Password With Special Characters Failed - User should not be found";
        System.out.println("Test Password With Special Characters Passed");
    }

    private static void testNonExistentUser() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("nonexistent@example.com", "password123");
        assert result == null : "Test Non-Existent User Failed - User should not be found";
        System.out.println("Test Non-Existent User Passed");
    }

    private static void testCaseSensitivityInEmail() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("JohnDoe@Example.com", "password123");
        assert result == null : "Test Case Sensitivity In Email Failed - User should not be found";
        System.out.println("Test Case Sensitivity In Email Passed");
    }

    private static void testCaseSensitivityInPassword() throws ClassNotFoundException {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com", "Password123");
        assert result == null : "Test Case Sensitivity In Password Failed - User should not be found";
        System.out.println("Test Case Sensitivity In Password Passed");
    }
}