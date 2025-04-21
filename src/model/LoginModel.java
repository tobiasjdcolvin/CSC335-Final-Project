package model;

import view.LoginView;
import view.Observer;
import view.LandingView;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LoginModel {
    private String userInput;
    private LoginView currView;
    private ArrayList<Observer> observers;

    public LoginModel(LoginView view) {
        this.currView = view;
        // build an ArrayList of words to pick a random one from:
        ArrayList<String> wordList = new ArrayList<String>();
        try  {
            BufferedReader myReader = new BufferedReader(new FileReader("src/model/words.txt"));
            String currLine = myReader.readLine();
            while (currLine != null) {
                // add each line in the words.txt file to the list:
                wordList.add(currLine.stripTrailing().toLowerCase());
                currLine = myReader.readLine();
            }
        } catch (Exception e) {
            // TODO: do something here in case of an exception
        }

        this.observers = new ArrayList<Observer>();
    }

    public boolean submitUserLogin(String username, String password) {
        // TODO: actually handle checking user login here

        // if the user successfully logs in, do this:
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LandingView newView = new LandingView();
        return true;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}