package model;

import view.LandingView;
import view.LeaderView;
import view.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LeaderModel {
    private String username;
    private LeaderView currView;
    private ArrayList<Observer> observers;

    public LeaderModel(LeaderView view, String username) {
        this.username = username;
        System.out.println("current user: " + this.username);
        this.currView = view;
        this.observers = new ArrayList<Observer>();
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