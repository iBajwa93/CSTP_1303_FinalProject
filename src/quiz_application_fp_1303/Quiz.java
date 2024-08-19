package quiz_application_fp_1303;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JButton next, submit;
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup options;
    public static int count = 0;
    public static int score = 0;
    private static final int MAX_QUESTIONS = 8;
    private List<Integer> questionOrder;
    private List<Integer> usedQuestions;
    String[][] q = new String[13][5];
    String username;
    private String correctAnswer;

    Quiz(String username) {
        this.username = username;
        setBounds(250, 50, 968, 572);
        getContentPane().setBackground(Color.CYAN);
        setLayout(null);

        // LABELS
        qno = new JLabel("");
        qno.setBounds(100, 300, 40, 20);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(qno);

        question = new JLabel("");
        question.setBounds(150, 300, 858, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(question);

        // Initialize questions
        initializeQuestions();

        // Randomize question order
        questionOrder = new ArrayList<>();
        for (int i = 0; i < q.length; i++) {
            questionOrder.add(i);
        }
        Collections.shuffle(questionOrder);

        // Options
        opt1 = new JRadioButton("");
        opt1.setFont(new Font("Dialog", Font.PLAIN, 18));
        opt1.setBounds(150, 340, 400, 18);
        opt1.setBackground(Color.cyan);
        add(opt1);

        opt2 = new JRadioButton("");
        opt2.setFont(new Font("Dialog", Font.PLAIN, 18));
        opt2.setBounds(150, 370, 400, 18);
        opt2.setBackground(Color.cyan);
        add(opt2);

        opt3 = new JRadioButton("");
        opt3.setFont(new Font("Dialog", Font.PLAIN, 18));
        opt3.setBounds(150, 400, 400, 18);
        opt3.setBackground(Color.cyan);
        add(opt3);

        opt4 = new JRadioButton("");
        opt4.setFont(new Font("Dialog", Font.PLAIN, 18));
        opt4.setBounds(150, 430, 400, 18);
        opt4.setBackground(Color.cyan);
        add(opt4);

        options = new ButtonGroup();
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);

        // Buttons
        next = new JButton("Next");
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(Color.green);
        next.setForeground(Color.black);
        next.addActionListener(this);
        next.setBounds(650, 430, 150, 30);
        add(next);

        submit = new JButton("Submit");
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setEnabled(false);
        submit.setBackground(Color.red);
        submit.setForeground(Color.black);
        submit.setBounds(650, 480, 150, 30);
        submit.addActionListener(this);
        submit.setVisible(false); // Initially hide the submit button
        add(submit);

        start(0);
    }

    private void initializeQuestions() {
        q[0][0] = "Which game series features a character named 'Link'?";
        q[0][1] = "The Legend of Zelda";
        q[0][2] = "Final Fantasy";
        q[0][3] = "Super Mario";
        q[0][4] = "Metroid";

        q[1][0] = "In which video game do players control a character named 'Master Chief'?";
        q[1][1] = "Halo";
        q[1][2] = "Call of Duty";
        q[1][3] = "Destiny";
        q[1][4] = "Battlefield";

        q[2][0] = "What are the 'Four Weapons' used in Muay Thai?";
        q[2][1] = "Fists, Elbows, Knees, and Shins";
        q[2][2] = "Fists, Elbows, Knees, and Feet";
        q[2][3] = "Fists, Feet, Shoulders, and Knees";
        q[2][4] = "Elbows, Hands, Feet, and Wrists";

        q[3][0] = "Who is considered the founder of Judo?";
        q[3][1] = "Jigoro Kano";
        q[3][2] = "Morihei Ueshiba";
        q[3][3] = "Gichin Funakoshi";
        q[3][4] = "Helio Gracie";

        q[4][0] = "In Karate, what is the term for a formal practice routine or pattern?";
        q[4][1] = "Kata";
        q[4][2] = "Kumite";
        q[4][3] = "Hiki-wake";
        q[4][4] = "Bunkai";

        q[5][0] = "In Skyrim, which dragon is known as The World Eater?";
        q[5][1] = "Alduin";
        q[5][2] = "Paarthurnax";
        q[5][3] = "Nidaan";
        q[5][4] = "Durnehviir";

        q[6][0] = "Which voice actor is famous for their role as 'Homer Simpson' in 'The Simpsons'?";
        q[6][1] = "Dan Castellaneta";
        q[6][2] = "Harry Shearer";
        q[6][3] = "Nancye S. Pimentel";
        q[6][4] = "Julie Kavner";

        q[7][0] = "What is the primary objective in Brazilian Jiu-Jitsu?";
        q[7][1] = "Submission";
        q[7][2] = "Striking";
        q[7][3] = "Throwing";
        q[7][4] = "Grappling";

        q[8][0] = "Which anime features a notebook that can kill anyone whose name is written in it?";
        q[8][1] = "Death Note";
        q[8][2] = "Attack on Titan";
        q[8][3] = "Fullmetal Alchemist";
        q[8][4] = "Naruto";

        q[9][0] = "What is the name of the move where a wrestler lifts and throws their opponent over their shoulder?";
        q[9][1] = "Fireman's Carry";
        q[9][2] = "Double Leg Takedown";
        q[9][3] = "Gut Wrench";
        q[9][4] = "Suplex";

        q[10][0] = "Which game is known for the phrase 'The cake is a lie'?";
        q[10][1] = "Portal";
        q[10][2] = "Half-Life";
        q[10][3] = "Bioshock";
        q[10][4] = "Mass Effect";

        q[11][0] = "Which voice actor is known for voicing the character Solid Snake in the Metal Gear Solid series?";
        q[11][1] = "David Hayter";
        q[11][2] = "Troy Baker";
        q[11][3] = "Nolan North";
        q[11][4] = "Steve Blum";

        q[12][0] = "What is the name of the Geass power possessed by Lelouch vi Britannia in Code Geass?";
        q[12][1] = "The Power of Absolute Obedience";
        q[12][2] = "The Power of Immortality";
        q[12][3] = "The Power of Absolute Command";
        q[12][4] = "The Power of Mind Control";
    }

    public void start(int qn) {
        usedQuestions = new ArrayList<>();
        loadQuestion(qn);
    }

    void loadQuestion(int qn) {
        // Get a random question index from the shuffled list
        int randomQuestionIndex = questionOrder.get(qn);

        // Update the question number label
        qno.setText("" + (qn + 1) + ". ");

        // Update the question label
        question.setText(q[randomQuestionIndex][0]);

        // Create a list of answer options
        List<String> optionsList = new ArrayList<>(Arrays.asList(q[randomQuestionIndex][1], q[randomQuestionIndex][2], q[randomQuestionIndex][3], q[randomQuestionIndex][4]));

        // Shuffle the options to randomize their order
        Collections.shuffle(optionsList);

        // Set the text for each option
        opt1.setText(optionsList.get(0));
        opt2.setText(optionsList.get(1));
        opt3.setText(optionsList.get(2));
        opt4.setText(optionsList.get(3));

        // Clear any previous selection
        options.clearSelection();

        // Keep track of the correct answer
        correctAnswer = q[randomQuestionIndex][1];
        if (opt1.getText().equals(correctAnswer)) {
            correctAnswer = opt1.getText();
        } else if (opt2.getText().equals(correctAnswer)) {
            correctAnswer = opt2.getText();
        } else if (opt3.getText().equals(correctAnswer)) {
            correctAnswer = opt3.getText();
        } else if (opt4.getText().equals(correctAnswer)) {
            correctAnswer = opt4.getText();
        }

        // Handle visibility of Next and Submit buttons
        if (qn == MAX_QUESTIONS - 1) { // If it's the last question
            next.setVisible(false);
            submit.setVisible(true);
        } else {
            next.setVisible(true);
            submit.setVisible(false);
        }

        submit.setEnabled(true);
    }

        // Existing fields and methods

        // Method to select an option programmatically for testing
        public void selectOption(int optionIndex) {
            switch (optionIndex) {
                case 1:
                    opt1.setSelected(true);
                    break;
                case 2:
                    opt2.setSelected(true);
                    break;
                case 3:
                    opt3.setSelected(true);
                    break;
                case 4:
                    opt4.setSelected(true);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option index: " + optionIndex);
            }
        }

        // Method to simulate a click on the "Next" button
        public void clickNextButton() {
            ActionEvent nextButtonEvent = new ActionEvent(next, ActionEvent.ACTION_PERFORMED, "Next");
            actionPerformed(nextButtonEvent);
        }
    public void actionPerformed(ActionEvent e) {
        if (options.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please select an answer");
            return;
        }

        String selectedAnswer = "";
        if (opt1.isSelected()) {
            selectedAnswer = opt1.getText();
        } else if (opt2.isSelected()) {
            selectedAnswer = opt2.getText();
        } else if (opt3.isSelected()) {
            selectedAnswer = opt3.getText();
        } else if (opt4.isSelected()) {
            selectedAnswer = opt4.getText();
        }

        // Update score if the answer is correct
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        }

        if (e.getSource() == next) {
            count++;
            if (count < MAX_QUESTIONS) {
                loadQuestion(count);
            }
        } else if (e.getSource() == submit) {
            JOptionPane.showMessageDialog(null, "Quiz Over! Your score is: " + score);
            dispose();
            new Score(username, score, count).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Quiz("User").setVisible(true);
    }
}
