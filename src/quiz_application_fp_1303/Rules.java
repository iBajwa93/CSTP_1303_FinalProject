package quiz_application_fp_1303;

import java.awt.*;
import javax.swing.*;

public class Rules extends JFrame {
    private static final long serialVersionUID = 1L;
    private String username;
    private JButton backButton; // Declares backButton as a class member

    public Rules(String username) {
        this.username = username;
        setBounds(100, 50, 600, 400);
        getContentPane().setBackground(Color.cyan);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Title label
        JLabel titleLabel = new JLabel("Rules of the Quiz");
        titleLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        titleLabel.setForeground(Color.blue);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Rules text area
        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setText("1. You will be asked a series of questions.\n\n"
                + "2. Select the correct answer from the options.\n\n"
                + "3. Each correct answer earns you 10 points.\n\n"
                + "4. Press 'Next' to move to the next question.\n\n"
                + "5. Press 'Submit' to finish the quiz and see your score.");
        rulesTextArea.setEditable(false);
        rulesTextArea.setBackground(Color.white);
        rulesTextArea.setForeground(Color.black);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        scrollPane.setPreferredSize(new Dimension(540, 200)); // Set preferred size for scroll pane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.fill = GridBagConstraints.BOTH; // Allow scrolling
        add(scrollPane, gbc);

        // Back button
        backButton = new JButton("Back"); // Initialize the backButton
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.green);
        backButton.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        backButton.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Getter for backButton
    public JButton getBackButton() {
        return backButton;
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
