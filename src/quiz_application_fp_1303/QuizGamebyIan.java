package quiz_application_fp_1303;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizGamebyIan extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton startButton, quitButton, rulesButton;
    private JTextField nameField;

    public QuizGamebyIan() {
        setBounds(250, 50, 800, 600);
        getContentPane().setBackground(Color.CYAN);
        setLayout(null);

        // Centered title label
        JLabel l2 = new JLabel("Quiz By Ian");
        l2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        l2.setForeground(Color.red);
        // Calculate center position
        int labelWidth = 300; // width of the title label
        int labelHeight = 50; // height of the title label
        int x = (getWidth() - labelWidth) / 2;
        int y = 50; // vertical position from the top
        l2.setBounds(x, y, labelWidth, labelHeight);
        add(l2);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameLabel.setBounds(50, 150, 200, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 150, 200, 30);
        add(nameField);

        startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setBounds(100, 200, 150, 30);
        startButton.addActionListener(this);
        add(startButton);

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.BLACK);
        quitButton.setBounds(300, 200, 150, 30);
        quitButton.addActionListener(this);
        add(quitButton);

        // Rules button
        rulesButton = new JButton("Rules");
        rulesButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        rulesButton.setBackground(Color.BLUE);
        rulesButton.setForeground(Color.WHITE);
        rulesButton.setBounds(500, 200, 150, 30);
        rulesButton.addActionListener(this);
        add(rulesButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            String name = nameField.getText();
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name");
            } else {
                dispose();
                new Quiz(name).setVisible(true);
            }
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == rulesButton) {
            // Display the rules with an example username
            new Rules("User").setVisible(true);
        }
    }

    public static void main(String[] args) {
        new QuizGamebyIan();
    }
}

