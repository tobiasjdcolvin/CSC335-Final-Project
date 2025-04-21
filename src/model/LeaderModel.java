package model;

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

    public LeaderModel(LeaderView view) {
        this.currView = view;
        this.observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}