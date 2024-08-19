package quiz_application_fp_1303;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class UnitTestScore {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/1303_quiz_application";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Initialize the database connection before each test
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @Test
    public void testDatabaseInsertionPerformance() {
        long startTime = System.nanoTime();

        try {
            String query = "INSERT INTO quiz (name, score) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            // Randomly generated data for testing
            String randomName = "TestUser" + new Random().nextInt(1000);
            int randomScore = new Random().nextInt(100);

            ps.setString(1, randomName);
            ps.setInt(2, randomScore);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database insertion failed");
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Database insertion duration: " + duration + " ns");

        // Assert that the operation is completed within a reasonable time (e.g., 500 ms)
        assertTrue(duration < 500_000_000); // 500 ms in nanoseconds
    }

    @Test
    public void testUIInitializationPerformance() {
        long startTime = System.nanoTime();

        // Initialize the Score UI
        new Score("Test User", 0, 8);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("UI initialization duration: " + duration + " ns");

        // Assert that the operation is completed within a reasonable time (e.g., 1 second)
        assertTrue(duration < 1_000_000_000); // 1 second in nanoseconds
    }

    
    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
