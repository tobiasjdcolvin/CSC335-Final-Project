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
    private TextLabel textLabel;
    private Controller controller;

    public View() {
        this.controller = new Controller(new Model());
        this.setTitle("Javordle");
        this.setSize(800,600);
        this.setUp();
    }


    private void setUp() {
        // setting up the main (will be for the game itself) panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        textLabel = new TextLabel();
        controller.addObserver(textLabel);
        this.add(mainPanel);

        // setting up the login page panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.darkGray);
        // TODO: UNCOMMENT AND WORK ON THIS:this.add(loginPanel);

        /* setting up main panel labels */
        JLabel titleLabel = new JLabel();
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setText("Javordle (Java Wordle)");

        textLabel.setForeground(Color.white);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adding the label to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainPanel.add(textLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

        /* setting up main panel buttons */
        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(controller);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(submitButton);



        /* setting up login panel labels */
        JLabel loginTitleLabel = new JLabel();
        loginTitleLabel.setForeground(Color.LIGHT_GRAY);
        loginTitleLabel.setText("Login or Register:");
        // adding the label to the login panel
        loginPanel.add(loginTitleLabel);


        //adding a window listener for closing the app
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.setVisible(true);
    }


    public static void main(String[] args) {
        View view = new View();
    }
}
