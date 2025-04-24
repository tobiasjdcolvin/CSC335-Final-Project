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
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class LoginModel {
    private String userInput;
    private LoginView currView;
    private ArrayList<Observer> observers;
    private ArrayList<String> userList;
    private static HashMap<String, int[]> users;

    private boolean DEBUG = true;

    public LoginModel(LoginView view) {
        this.currView = view;
        // build an ArrayList of all the usernames and passwords
        userList = new ArrayList<String>();
        users = new HashMap<String, int[]>();
        
        try  {
            BufferedReader myReader = new BufferedReader(new FileReader("src/store/UserData.csv"));
            String currLine = myReader.readLine();
            currLine = myReader.readLine();
            while (currLine != null) {
                // add each line in the csv file to the list:
            	String line = currLine.stripTrailing();
                userList.add(line);
                String name = line.split(", ")[0];
                String victories = line.split(", ")[1].strip();
                String losses = line.split(", ")[2].strip();
                users.put(name, new int[] {Integer.valueOf(victories),Integer.valueOf(losses)});
                System.out.println(userList);
                currLine = myReader.readLine();
            }
        } catch (Exception exc) {
            System.out.println("Unable to read UserData.csv");
        }
        
        this.observers = new ArrayList<Observer>();
    }

    public boolean submitUserLogin(String username, String password) {
        for (String currUser : userList) {
            if (passwordCheck(currUser, username, password)) {
                // if the user successfully logs in, do this:
                currView.dispose(); // found this in a YouTube tutorial for changing windows
                LandingView newView = new LandingView(username);
                return true;
            }
        }
        observers.get(0).newText("Unable to login, please try again.", Color.lightGray);
        return false;
    }

    public void removeDebug() {
        this.DEBUG = false;
    }

    private boolean passwordCheck(String currUser, String username, String password) {
        String currUsername = currUser.split(", ")[0];
        
        String currVictories = currUser.split(", ")[1];
        String currLosses = currUser.split(", ")[2];
        
        String currPassword = currUser.split(", ")[3];

        String salt = currUser.split(", ")[4];
        String hashedPassword = hash(password, salt);
        if (DEBUG) System.out.println("CurrUser:" + currUser);

        if (DEBUG) System.out.println("Given:" + username + ", "+currVictories+", "+currLosses+", " + password + ", " + salt);

        return currUsername.equals(username) && hashedPassword.equals(currPassword);
    }

    public boolean submitUserRegister(String username, String password) {
        // first check if the user already exists:
        for (String userNameCurr : userList) {

            String currName = userNameCurr.split(", ")[0];
            
            if (currName.toLowerCase().equals(username.toLowerCase())) {
                observers.get(0).newText("Unable to register, please try again.", Color.lightGray);
                return false;
            }
        }
        
        String salt = createSalt();
       
        // add username and password to both the file and the userList
        String combinedNamePass = username + ", "+"0, "+ "0, "+ hash(password, salt) + ", " + salt;
   
        try (FileWriter myFW = new FileWriter(new File("src/store/UserData.csv"), true);
                BufferedWriter myBW = new BufferedWriter(myFW)) {
                myBW.write(combinedNamePass + "\n");
       } catch (Exception exc) {
           observers.get(0).newText("Unable to register, please try again.", Color.lightGray);
           return false;
       }
        
        userList.add(combinedNamePass);
        users.put(username,new int[] {0,0});
        observers.get(0).newText("Successfully registered. Please login.", Color.lightGray);
        return true;
    }

    private String createSalt() {
        String charSet = "qwertyuiopasdfghjklzxcvbnm./;'[]1234567890-=QWERTYUIOPASDFGHJKLZXCVBNM<>?:{}|";
        Random random = new Random();
        String salt = "";
        for (int i = 0; i < 64; i++) {
            salt += charSet.charAt(random.nextInt(charSet.length()));
        }
        return salt;
    }

    public int getObserversLen() {
        return this.observers.size();
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
    
    public int[] getUserWinLosses(String username) {
    	return new int[] {users.get(username)[0], users.get(username)[1]};
    }
    
    protected static void updateUserWinLoss(String username, int win, int loss) {
    	users.get(username)[0] += win;
    	users.get(username)[1] += loss;
    	updateUserData(username);
    }
    
    private static void updateUserData(String username) {
    	String newData = "";
    	
    	try  {
			BufferedReader myReader = new BufferedReader(new FileReader("src/store/UserData.csv"));
			String currLine = myReader.readLine();
			while (currLine != null) {
				
				String line = currLine.stripTrailing();
			    String name = line.split(", ")[0];
			    String pass = line.split(", ")[3];
			    String salt = line.split(", ")[4];
			
			    if(!name.equals(username)) {
			    	newData+=line+"\n";
			    }
			    else {
			    	String updatedUser = name + ", "+users.get(username)[0]+", "+ users.get(username)[1]+", "+pass+ ", " + salt+"\n";
			    	newData+=updatedUser;
			    }
			    currLine = myReader.readLine();
			}
        } catch (Exception exc) {
            System.out.println("Unable to read UserData.csv");
        }
    	
    	System.out.println(newData);
    	try (FileWriter myFW = new FileWriter(new File("src/store/UserData.csv"), false);
            BufferedWriter myBW = new BufferedWriter(myFW)) {
            myBW.write(newData);
    	} catch (Exception exc) {
    	}
    }
}