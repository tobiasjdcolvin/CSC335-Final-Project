package controller;

import model.VictoryModel;
import view.Observer;
import view.VictoryView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryController implements ActionListener{
    private VictoryModel model;
    private VictoryView myView;

    public VictoryController(VictoryModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("play")) {
            model.play();
        } else if (command.equals("leaderboard")) {
            model.leaderboard();
        } else if (command.equals("back")){
            model.back();
        }
    }

    public void setMyView(VictoryView view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}