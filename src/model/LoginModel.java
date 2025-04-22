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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private String createSalt() {
        String charSet = "qwertyuiopasdfghjklzxcvbnm,./;'[]1234567890-=QWERTYUIOPASDFGHJKLZXCVBNM<>?:{}|";
        Random random = new Random();
        String salt = "";
        for (int i = 0; i < 64; i++) {
            salt += charSet.charAt(random.nextInt(charSet.length()));
        }
        return salt;
    }

    private static String hash(String password, String salt) {
        password = password+salt;
        try {
            StringBuilder hash = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); // Perform hash to get bytes
            for (byte b : bytes) {
                hash.append(Integer.toHexString(0xff & b)); // Convert bytes into hexadecimal strings (0xff is like a modulo)
            }
            return hash.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}