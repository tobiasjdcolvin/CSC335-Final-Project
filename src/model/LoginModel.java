package model;

import view.LoginView;
import view.Observer;
import view.LandingView;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class LoginModel {
    private String userInput;
    private LoginView currView;
    private ArrayList<Observer> observers;
    private ArrayList<String> userList;

    private boolean DEBUG = true;

    public LoginModel(LoginView view) {
        this.currView = view;
        // build an ArrayList of all the usernames and passwords
        userList = new ArrayList<String>();
        try  {
            BufferedReader myReader = new BufferedReader(new FileReader("src/store/NamePassStore.txt"));
            String currLine = myReader.readLine();
            while (currLine != null) {
                // add each line in the txt file to the list:
                userList.add(currLine.stripTrailing());
                currLine = myReader.readLine();
            }
        } catch (Exception exc) {
            System.out.println("Unable to read NamePassStore.txt");
        }

        this.observers = new ArrayList<Observer>();
    }

    public boolean submitUserLogin(String username, String password) {
        //if (userList.contains(username.toLowerCase() + " " + password.toLowerCase())) {
            for (String currUser : userList) {
                if (passwordCheck(currUser, username, password)) {
                    // if the user successfully logs in, do this:
                    currView.dispose(); // found this in a YouTube tutorial for changing windows
                    LandingView newView = new LandingView(username);
                    return true;
                }
            }
        //}
        observers.get(0).newText("Unable to login, please try again.", Color.lightGray);
        return false;
    }

    private boolean passwordCheck(String currUser, String username, String password) {
        String currUsername = currUser.split(" ")[0];
        String currPassword = currUser.split(" ")[1];
        String salt = currUser.split(" ")[2];
        String hashedPassword = hash(password, salt);
        if (DEBUG) System.out.println("CurrUser:" + currUser);
        if (DEBUG) System.out.println("Given:" + username + " " + password + " " + salt);
        return currUsername.equals(username) && hashedPassword.equals(currPassword);
    }

    public boolean submitUserRegister(String username, String password) {
        // first check if the user already exists:
        ArrayList<String> usernames = new ArrayList<String>();
        for (String userNameCurr : userList) {
            String currName = userNameCurr.split(" ")[0];
            if (currName.toLowerCase().equals(username.toLowerCase())) {
                observers.get(0).newText("Unable to register, please try again.", Color.lightGray);
                return false;
            }
        }
        String salt = createSalt();
        // add username and password to both the file and the userList
        String combinedNamePass = username + " " + hash(password, salt) + " " + salt;

        try (FileWriter myFW = new FileWriter(new File("src/store/NamePassStore.txt"), true);
             BufferedWriter myBW = new BufferedWriter(myFW)) {
             myBW.write(combinedNamePass + "\n");
        } catch (Exception exc) {
            observers.get(0).newText("Unable to register, please try again.", Color.lightGray);
            return false;
        }

        userList.add(combinedNamePass);
        observers.get(0).newText("Successfully registered. Please login.", Color.lightGray);
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