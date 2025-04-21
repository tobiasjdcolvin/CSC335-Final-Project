/* We are using SA6 as an example for how to use Swing,
* so some of the code is the same as in SA6. Other functionality
* is from looking through the Swing API and messing around with
* IntelliJ's auto-complete. */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Model;
import controller.Controller;

public class View extends JFrame {

    // 5 labels, one for each character:
    private TextLabel textLabel;
    private TextLabel textLabel2;
    private TextLabel textLabel3;
    private TextLabel textLabel4;
    private TextLabel textLabel5;

    private JTextField textField;
    private Controller controller;

    public View() {
        this.controller = new Controller(new Model());
        controller.setMyView(this);
        this.setTitle("Javordle");
        this.setSize(800,600);
        this.setLocationRelativeTo(null); // centers the window
        this.setUp();
        this.setVisible(true);
    }


    private void setUp() {
        // mainPanel now uses BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.add(mainPanel);

        // panel to contain each of the character labels to together create the string
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.darkGray);
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); // this makes it so each character is not on a new line.
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // center content panel (title and game messages)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.darkGray);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Javordle (Java Wordle)");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);

        // add the panel containing the labels to the center panel
        centerPanel.add(labelPanel);

        textLabel = new TextLabel();
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addObserver(textLabel);

        textLabel2 = new TextLabel();
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addObserver(textLabel2);

        textLabel3 = new TextLabel();
        textLabel3.setForeground(Color.WHITE);
        textLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addObserver(textLabel3);

        textLabel4 = new TextLabel();
        textLabel4.setForeground(Color.WHITE);
        textLabel4.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addObserver(textLabel4);

        textLabel5 = new TextLabel();
        textLabel5.setForeground(Color.WHITE);
        textLabel5.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addObserver(textLabel5);

        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(textLabel);
        labelPanel.add(textLabel2);
        labelPanel.add(textLabel3);
        labelPanel.add(textLabel4);
        labelPanel.add(textLabel5);

        // bottom panel for input field and submit button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(200, 30));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submit = new JButton("Submit");
        submit.setActionCommand("submit");
        submit.addActionListener(controller); 
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottomPanel.add(textField);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(submit);

        // optional login panel (currently unused)
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.darkGray);

        JLabel loginTitleLabel = new JLabel("Login or Register:");
        loginTitleLabel.setForeground(Color.LIGHT_GRAY);
        loginPanel.add(loginTitleLabel);


        // add panels to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);



        //adding a window listener for closing the app
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.setVisible(true);
    }

    public String getUserInput() {
        return this.textField.getText();
    }


    public static void main(String[] args) {
        new View();
    }
}
