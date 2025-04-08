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
        this.setLocationRelativeTo(null); // center the window
        this.setUp();
        this.setVisible(true);
    }


    private void setUp() {
        // setting up the main (will be for the game itself) panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.darkGray);

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textLabel = new TextLabel();
        controller.addObserver(textLabel);
        this.add(mainPanel);

        // setting up the login page panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.darkGray);
        // TODO: UNCOMMENT AND WORK ON THIS:this.add(loginPanel);

        /* setting up main panel labels */
        JLabel titleLabel = new JLabel("Javordle (Java Wordle)");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adding the label to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainPanel.add(textLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

        /* setting up main panel buttons */

        JButton submit = new JButton("Submit");
        submit.setActionCommand("submit");
        submit.addActionListener(controller); 
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(submit);


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
        new View();
    }
}
