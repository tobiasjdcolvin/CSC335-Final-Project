package controller;

import model.LeaderModel;
import view.Observer;
import view.LeaderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderController implements ActionListener{
    private LeaderModel model;
    private LeaderView myView;

    public LeaderController(LeaderModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("back")) {
            model.back();
        }
    }

    public void setMyView(LeaderView view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}