package quiz_application_fp_1303;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnitTestQuiz {

    private List<Integer> questionOrder;
    private Quiz quiz;
    private JButton nextButton;
    private JButton submitButton;

    @Before
    public void setUp() {
        questionOrder = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            questionOrder.add(i);
        }

        // Initialize the Quiz and buttons
        quiz = new Quiz("TestUser");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");

        // Set buttons in Quiz if necessary
        quiz.add(nextButton);
        quiz.add(submitButton);
    }

    @Test
    public void testShuffleQuestionOrder() {
        long startTime = System.nanoTime();
        Collections.shuffle(questionOrder);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Shuffle duration: " + duration + " ns");
        assertTrue(duration > 0);
    }

    @Test
    public void testLoadQuestionTime() {
        long startTime = System.nanoTime();
        quiz.loadQuestion(0);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Load question duration: " + duration + " ns");
        assertTrue(duration > 0);
    }

    @Test
    public void testLoadQuestionWithDifferentIndices() {
        for (int i = 0; i < 8; i++) {
            long startTime = System.nanoTime();
            quiz.loadQuestion(i);
            long endTime = System.nanoTime();

            long duration = endTime - startTime;
            System.out.println("Load question " + i + " duration: " + duration + " ns");
            assertTrue(duration > 0);
        }
    }

    @Test
    public void testActionPerformedNextButton() {
        Quiz quiz = new Quiz("Test User");

        // Set up the quiz and select an option programmatically
        quiz.selectOption(1); // Automatically select the first option to avoid pop-up prompt

        // Simulate clicking the "Next" button
        quiz.clickNextButton();

        // Validate that the count has increased
        assertEquals(1, Quiz.count);
    }
    @Test
    public void testActionPerformedSubmitButton() {
        // Simulate the 'Submit' button click
        ActionEvent submitEvent = new ActionEvent(submitButton, ActionEvent.ACTION_PERFORMED, "Submit");

        // Simulate a user selection (necessary for Submit button to function properly)
        quiz.opt1.setText("Option 1");
        quiz.opt1.setSelected(true);

        // Call actionPerformed method
        quiz.actionPerformed(submitEvent);

        // Check that the quiz is disposed (this implies that the submit button functionality is triggered)
        assertFalse(quiz.isVisible());
    }
}

