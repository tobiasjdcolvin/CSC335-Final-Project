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

    public LandingModel(LandingView view) {
        this.currView = view;
        this.observers = new ArrayList<Observer>();
    }

    public boolean play() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        View newView = new View();
        return true;
    }

    public boolean leaderboard() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LeaderView newView = new LeaderView();
        return true;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}