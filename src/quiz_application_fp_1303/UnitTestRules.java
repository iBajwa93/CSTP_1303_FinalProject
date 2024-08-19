package quiz_application_fp_1303;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class UnitTestRules {

    private Rules rulesFrame;

    @Before
    public void setUp() {
        // Initialize before each test
        rulesFrame = new Rules("Test User");
    }

    @Test
    public void testUIInitializationPerformance() {
        long startTime = System.nanoTime();

        // Initialize the Rules UI
        rulesFrame.setVisible(true);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("UI initialization duration: " + duration + " ns");

        // Assert that the operation is completed within a reasonable time (e.g., 500 ms)
        assertTrue(duration < 500_000_000); // 500 ms in nanoseconds
    }

    @Test
    public void testButtonInitializationPerformance() {
        long startTime = System.nanoTime();

        // Access the back button and check its initialization
        JButton backButton = getBackButton();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Button initialization duration: " + duration + " ns");

        // Assert that the button is initialized and the operation is completed within a reasonable time
        assertNotNull(backButton);
        assertTrue(duration < 100_000_000); // 100 ms in nanoseconds
    }

    @Test
    public void testButtonActionPerformance() {
        long startTime = System.nanoTime();

        // Simulate clicking the back button
        JButton backButton = getBackButton();
        ActionListener[] actionListeners = backButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(backButton, ActionEvent.ACTION_PERFORMED, null));
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Button action duration: " + duration + " ns");

        // Assert that the action is completed within a reasonable time 
        assertTrue(duration < 200_000_000); // 200 ms in nanoseconds
    }

    private JButton getBackButton() {
        try {
            // Access the private field using reflection
            Field field = Rules.class.getDeclaredField("backButton");
            field.setAccessible(true);
            return (JButton) field.get(rulesFrame);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to access the backButton field.");
            return null;
        }
    }
}

