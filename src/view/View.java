/* We used SA6 as a template, and built off of it with some help from AI,
* which we documented using comments wherever we AI generated something (only here in the View.) */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Model;
import controller.Controller;

public class View extends JFrame {
    private String username;

    // 5 labels, one for each character:
    private TextLabel textLabel;
    private TextLabel textLabel2;
    private TextLabel textLabel3;
    private TextLabel textLabel4;
    private TextLabel textLabel5;

    private JTextField textField;
    private Controller controller;

    public View(String username) {
        this.username = username;
        this.controller = new Controller(new Model(username));
        controller.setMyView(this);
        this.setTitle("Javordle");
        this.setSize(800,600);
        this.setLocationRelativeTo(null); // centers the window (AI generated)
        this.setUp();
        this.setVisible(true);
    }


    private void setUp() {
        // mainPanel (layout is AI generated)
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // This is AI generated
        this.add(mainPanel);

        // panel to contain each of the character labels to together create the string
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.darkGray);
        // the layout was generated using AI.
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); // (AI generated) this makes it so each character is not on a new line.
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // center content panel (Layout is AI generated)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.darkGray);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // this layout is AI generated.

        JLabel titleLabel = new JLabel("Javordle (Java Wordle)");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // this alignment is AI generated.
        centerPanel.add(titleLabel);

        // add the panel containing the labels to the center panel
        centerPanel.add(labelPanel);

        textLabel = new TextLabel();
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // AI generated
        controller.addObserver(textLabel);

        textLabel2 = new TextLabel();
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT); // AI generated
        controller.addObserver(textLabel2);

        textLabel3 = new TextLabel();
        textLabel3.setForeground(Color.WHITE);
        textLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel3.setAlignmentX(Component.CENTER_ALIGNMENT); // AI generated
        controller.addObserver(textLabel3);

        textLabel4 = new TextLabel();
        textLabel4.setForeground(Color.WHITE);
        textLabel4.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel4.setAlignmentX(Component.CENTER_ALIGNMENT); // AI generated
        controller.addObserver(textLabel4);

        textLabel5 = new TextLabel();
        textLabel5.setForeground(Color.WHITE);
        textLabel5.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel5.setAlignmentX(Component.CENTER_ALIGNMENT); // AI generated
        controller.addObserver(textLabel5);

        labelPanel.add(Box.createRigidArea(new Dimension(0, 20))); // This line is AI generated
        labelPanel.add(textLabel);
        labelPanel.add(textLabel2);
        labelPanel.add(textLabel3);
        labelPanel.add(textLabel4);
        labelPanel.add(textLabel5);

        // bottom panel (layout is AI generated)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // layout is AI generated

        textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(200, 30));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        JButton submit = new JButton("Submit");
        submit.setActionCommand("submit");
        submit.addActionListener(controller); 
        submit.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        bottomPanel.add(textField);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // this line is AI generated
        bottomPanel.add(submit);


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

}
