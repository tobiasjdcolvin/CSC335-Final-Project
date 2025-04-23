package controller;

import model.LoserModel;
import view.Observer;
import view.LoserView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoserController implements ActionListener{
    private LoserModel model;
    private LoserView myView;

    public LoserController(LoserModel model) {
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

    public void setMyView(LoserView view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}