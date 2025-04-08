package model;

import view.Observer;
import java.util.ArrayList;

public class Model {
    private String userInput;
    private ArrayList<Observer> observers;

    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    public void submitUserInput(String text) {
        this.userInput = text;
        if (userInput.length() > 5) {
            observers.get(0).newText("Your word is more than 5 characters long.");
        } else if (userInput.length() < 5) {
            observers.get(0).newText("Your word is less than 5 characters long.");
        } else {
            observers.get(0).newText(userInput);
        }
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}