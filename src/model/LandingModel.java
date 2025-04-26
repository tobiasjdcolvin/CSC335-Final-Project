package model;

import view.*;
import java.util.ArrayList;


public class LandingModel {
    private LandingView currView;
    private ArrayList<Observer> observers;
    private String username;

    public LandingModel(LandingView view, String username) {
        this.username = username;
        this.currView = view;
        this.observers = new ArrayList<Observer>();
    }

    public boolean play() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        View newView = new View(this.username);
        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean logout() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LoginView newView = new LoginView();
        return true;
    }

    public boolean leaderboard() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LeaderView newView = new LeaderView(this.username);
        return true;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}