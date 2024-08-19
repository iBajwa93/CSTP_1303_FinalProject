package quiz_application_fp_1303;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Score extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private PreparedStatement ps;
    private Connection con;

    // Update with your actual connection details
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/1303_quiz_application";
    private final String dbUsername = "root";
    private final String dbPassword = "password";

    public Score(String user, int score, int totalQuestions) {
        setBounds(250, 50, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Calculate the percentage score
        double percentage = ((double) score / totalQuestions) * 100;

        // Create and configure components
        JLabel titleLabel = new JLabel("Quiz Score");
        titleLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        titleLabel.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        JLabel scoreLabel = new JLabel("Congratulations, " + user + "!");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.black);
        gbc.gridy = 1;
        add(scoreLabel, gbc);
        
        // Display happy or sad face with color based on score
        JLabel resultLabel = new JLabel();
        if (percentage >= 50) {
            resultLabel.setText("<html><div style='text-align: center;'>"
                    + "<div style='font-size: 60px; color: black; background-color: yellow; display: inline-block; padding: 10px;'>😊</div><br>"
                    + "Well done! You scored " + score + " out of " + (totalQuestions +1) + "</div></html>");
            getContentPane().setBackground(Color.CYAN); // Happy color
        } else {
            resultLabel.setText("<html><div style='text-align: center;'>"
                    + "<div style='font-size: 60px; color: white; background-color: blue; display: inline-block; padding: 10px;'>😢</div><br>"
                    + "Better luck next time! You scored " + score + " out of " + (totalQuestions +1) + "</div></html>");
            getContentPane().setBackground(Color.RED); // Sad color
        }
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resultLabel.setForeground(Color.black);
        gbc.gridy = 2;
        add(resultLabel, gbc);

        // Insert the score into the database
        try {
            // Establishing a database connection
            con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            System.out.println("Connection successful!");

            String query = "INSERT INTO quiz (name, score) VALUES (?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setInt(2, score);
            ps.executeUpdate();

            // Clean up
            ps.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            // Ensure the connection is closed
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        JButton okButton = new JButton("Exit");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        okButton.setBackground(Color.black);
        okButton.setForeground(Color.cyan);

        // Set GridBagConstraints for the button
        gbc.gridy = 3; // Row below the emoji
        gbc.weighty = 1.0; // Allow vertical space to expand
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        add(okButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Modify the following values to test different scenarios
        String testUser = "Test User";
        int testScore = 5; // Example score
        int totalQuestions = 10; // Example total questions

        // Creating an instance of Score with dynamic values
        new Score(testUser, testScore, totalQuestions).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Handle other actions if needed
    }
}

