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

    public View() {
        this.setTitle("Javordle");
        this.setSize(800,600);
        this.setUp();
    }

    private void setUp() {
        //setting up the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.darkGray);
        this.add(mainPanel);

        // setting up labels
        JLabel titleLabel = new JLabel();
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setText("Javordle (Java Wordle)");
        // adding the label to the main panel (if you have any formatting
        // issues in the future, make sure you tried adding whatever it is to
        // the main panel, I was stumped for a while here).
        mainPanel.add(titleLabel);


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
