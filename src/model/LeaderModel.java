package model;

import view.LandingView;
import view.LeaderView;
import view.Observer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
// Learned about in 345, I LOVE BSTS YIPPIE 
import java.util.TreeMap;

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

    public ArrayList<String> getTopUsers() {
        ArrayList<String> topUsers = new ArrayList<>();
        TreeMap<Integer, ArrayList<String>> victoriesToUsers = new TreeMap<>(Collections.reverseOrder());
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/store/UserData.csv"));
            String line;
            // This part skips the first line took a sec to figure out lol
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                    String user = parts[0].trim();
                    int victories = Integer.parseInt(parts[1].trim());
                    if (!victoriesToUsers.containsKey(victories)) {
                        victoriesToUsers.put(victories, new ArrayList<>());
                    }
                    victoriesToUsers.get(victories).add(user);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("UserData.csv not found!");
        } catch (IOException e) {
        }

        for (Map.Entry<Integer, ArrayList<String>> entry : victoriesToUsers.entrySet()) {
            for (String user : entry.getValue()) {
                topUsers.add(user + " - " + entry.getKey() + " victories");
                if (topUsers.size() == 10) {
                    return topUsers;
                }
            }
        }
        return topUsers;
    }
}