package controller;

import model.Model;
import view.Observer;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    private Model model;
    private View myView;

    public Controller(Model model) {
        this.model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("submit")) {
            String userInput = myView.getUserInput();
            model.submitUserInput(userInput);
        }
    }

    public void setMyView(View view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}