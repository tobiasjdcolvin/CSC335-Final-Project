package controller;

import model.Model;
import view.Observer;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Controller implements ActionListener{
    private Model model;
    private View myView;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("submit")) {
            String userInput = myView.getUserInput();
            // Added this part from Model to have it in here
            if (userInput.length() > 5) {
                myView.getWarrningLabel().setText("Your word is more than 5 characters long.");
            } else if (userInput.length() < 5) {
                myView.getWarrningLabel().setText("Your word is less than 5 characters long.");
            }else if(!Model.getWordList().contains(userInput)) {
            	myView.getWarrningLabel().setText("Your word is not a valid word.");
            }
            else if(Arrays.asList(Model.getGuesses()).contains(userInput)) {
            	myView.getWarrningLabel().setText("You have already guessed this word.");
            }
            else {
                myView.getWarrningLabel().setText("");
                model.submitUserInput(userInput);
            }
        }
    }

    public void setMyView(View view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}