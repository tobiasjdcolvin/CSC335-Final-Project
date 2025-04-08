package controller;

import model.Model;
import view.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
    private Model model;

    public Controller(Model model) {
        this.model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("submit")) {
            model.submitUserInput("THE USER INPUTTED!?!!");
        }
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}