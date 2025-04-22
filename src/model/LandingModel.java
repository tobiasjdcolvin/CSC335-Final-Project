package model;

import view.LandingView;
import view.LeaderView;
import view.Observer;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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