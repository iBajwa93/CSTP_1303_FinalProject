package quiz_application_fp_1303;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class UnitTestQuizGame {

    private QuizGamebyIan quizGame;

    @Before
    public void setUp() {
        // Initialize QuizGamebyIan before each test
        quizGame = new QuizGamebyIan();
        // Ensure the JFrame is visible for the tests
        quizGame.setVisible(true);
    }

    @Test
    public void testInitializationTime() {
        long startTime = System.nanoTime();
        // The initialization happens in the constructor of QuizGamebyIan
        quizGame = new QuizGamebyIan();
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Initialization duration: " + duration + " ns");
        assertTrue("Initialization took too long", duration < TimeUnit.SECONDS.toNanos(1)); // Adjust the threshold as needed
    }

    @Test
    public void testStartButtonAction() {
        JButton startButton = getPrivateField("startButton");
        assertNotNull("Start button should not be null", startButton);

        // Simulate a button click
        startButton.doClick();
    }

    @Test
    public void testQuitButtonAction() {
        JButton quitButton = getPrivateField("quitButton");
        assertNotNull("Quit button should not be null", quitButton);

        // Simulates a button click
        quitButton.doClick();

        // Check if the application exits
        // This test is problematic because System.exit() will terminate the JVM, making it hard to assert if it works or not (but I believe it does...)
    }

    @Test
    public void testRulesButtonAction() {
        JButton rulesButton = getPrivateField("rulesButton");
        assertNotNull("Rules button should not be null", rulesButton);

        // Simulate a button click
        rulesButton.doClick();

        // Checks if the Rules window is opened
    }

    // Helper method to access private fields using reflection
    private JButton getPrivateField(String fieldName) {
        try {
            Field field = QuizGamebyIan.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (JButton) field.get(quizGame);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

