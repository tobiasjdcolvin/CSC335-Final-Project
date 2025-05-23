package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.LandingModel;
import controller.LandingController;

public class LandingView extends JFrame {
    private String username;
    private LandingController controller;

    public LandingView(String username) {
        this.username = username;
        this.controller = new LandingController(new LandingModel(this, username));
        controller.setMyView(this);
        this.setTitle("Landing Page");
        this.setSize(800,600);
        this.setLocationRelativeTo(null); // centers the window - generated by AI
        this.setUp();
        this.setVisible(true);
    }


    private void setUp() {
        JPanel mainPanel = new JPanel(new BorderLayout()); // layout generated by AI
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // border generated by AI
        this.add(mainPanel);

        // center content panel (layout generated by AI)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new java.awt.Color(20, 25, 35));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // layout generated by AI

        JLabel2D titleLabel = new JLabel2D("Play or View Leaderboard");     
        titleLabel.setForeground(new java.awt.Color(219, 237, 247));
        titleLabel.setOutlineColor(new java.awt.Color(119, 137, 147));
        titleLabel.setStroke(new BasicStroke(2f));
        titleLabel.setFont(new Font("Stencil", Font.PLAIN, 40));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment generated by AI
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // AI generated
        centerPanel.add(titleLabel);

        // bottom panel (layout generated by AI)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // layout generated by AI

        JButton play = new JButton("Play");
        play.setMaximumSize(new Dimension(200,50));
        play.setActionCommand("play");
        play.addActionListener(controller);
        play.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        JButton leaderboard = new JButton("Leaderboard");
        leaderboard.setMaximumSize(new Dimension(200,50));
        leaderboard.setActionCommand("leaderboard");
        leaderboard.addActionListener(controller);
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        JButton logout = new JButton("Logout");
        logout.setMaximumSize(new Dimension(200,50));
        logout.setActionCommand("logout");
        logout.addActionListener(controller);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT); // alignment is AI generated

        centerPanel.add(Box.createRigidArea(new Dimension(0, 60))); // AI generated
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // AI generated
        centerPanel.add(play);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40))); // AI generated
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // AI generated
        centerPanel.add(leaderboard);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40))); // AI generated
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // AI generated
        centerPanel.add(logout);

        // add panels to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER); // AI generated layout
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // AI generated layout


        //adding a window listener for closing the app
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        this.setVisible(true);
    }

}