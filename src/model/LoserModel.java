package model;

import view.LandingView;
import view.LeaderView;
import view.Observer;
import view.LoserView;
import view.View;

import java.util.ArrayList;


public class LoserModel {
    private LoserView currView;
    private ArrayList<Observer> observers;
    private String username;

    public LoserModel(LoserView view, String username) {
        this.username = username;
        this.currView = view;
        this.observers = new ArrayList<Observer>();
    }

    public boolean play() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        View newView = new View(this.username);
        return true;
    }

    public boolean leaderboard() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LeaderView newView = new LeaderView(this.username);
        return true;
    }

    public void back() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LandingView newView = new LandingView(this.username);
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}