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
    private TextLabel[][] textLabels;
    private JLabel warrningLabel = new JLabel();

    private JTextField textField;
    private Controller controller;

    public View(String username) {
        this.username = username;
        this.controller = new Controller(new Model(username, this));
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
        // I changed this so it fits the new 2d array
        // Found out on docs.oracle.com that there is a GridLayout perfect for our array
        // I dont know how we want the style of the words to look so they are spread out wide asf rn lol
        JPanel labelPanel = new JPanel(new GridLayout(6,5,2,5));
        labelPanel.setBackground(Color.darkGray);
        textLabels = new TextLabel[6][5];
        for (int row = 0; row < 6; row++){
            for (int col = 0; col < 5; col++){
                TextLabel label = new TextLabel();
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Arial", Font.PLAIN, 18));
                textLabels[row][col] = label;
                labelPanel.add(label);
                controller.addObserver(label);
            }
        }

        // center content panel (Layout is AI generated)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.darkGray);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // this layout is AI generated.

        JLabel titleLabel = new JLabel("Javordle (Java Wordle)");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // this alignment is AI generated.
        centerPanel.add(titleLabel);

        // warrning label formatting (lets keep the typo its funny)
        warrningLabel.setForeground(Color.LIGHT_GRAY);
        warrningLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        warrningLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // this alignment is AI generated.
        centerPanel.add(warrningLabel);

        // add the panel containing the labels to the center panel
        centerPanel.add(labelPanel);

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

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // AI generated
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // AI generated

        JButton back = new JButton("Back");
        back.setActionCommand("back");
        back.addActionListener(controller);
        back.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        bottomPanel.add(textField);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // this line is AI generated
        bottomPanel.add(submit);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // this line is AI generated
        bottomPanel.add(back);


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

    public JLabel getWarrningLabel(){
        return this.warrningLabel;
    }

    public String getUserInput() {
        return this.textField.getText();
    }

}
