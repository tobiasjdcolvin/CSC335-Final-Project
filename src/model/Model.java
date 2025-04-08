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
        observers.get(0).newText(userInput);
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}