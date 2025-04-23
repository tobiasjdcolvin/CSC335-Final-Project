package controller;

import model.LandingModel;
import view.Observer;
import view.LandingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingController implements ActionListener{
    private LandingModel model;
    private LandingView myView;

    public LandingController(LandingModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("play")) {
            model.play();
        } else if (command.equals("leaderboard")) {
            model.leaderboard();
        } else if (command.equals("logout")) {
            model.logout();
        }
    }

    public void setMyView(LandingView view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}