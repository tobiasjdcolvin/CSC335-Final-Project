package model;

import view.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    private String username;
    private String userInput;
    private ArrayList<Observer> observers;
    private Board board;

    public Model(String username) {
        this.username = username;
        System.out.println("current user: " + this.username);
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
            // TODO: do something here
        }

        // now pick a random word from the list and create a Board with it:
        Random myRandom = new Random();
        int randIndex = myRandom.nextInt(wordList.size());
        String randWord = wordList.get(randIndex);

        // create a board using the random word
        board = new Board(new Word(randWord), 5);

        this.observers = new ArrayList<Observer>();
    }

    public void submitUserInput(String text) {
        this.userInput = text;
        if (userInput.length() > 5) {
            observers.get(0).newText("Your word is more than 5 characters long.", Color.gray);
        } else if (userInput.length() < 5) {
            observers.get(0).newText("Your word is less than 5 characters long.", Color.gray);
        } else {
            // in this case, the user input is 5 characters long.

            // split the user input into individual characters
            String[] userInputArr = userInput.split("");

            // check the guess and output colored strings based upon the result
            Guess[] guessResult = board.checkWord(new Word(userInput.toLowerCase()));
            for (int i = 0; i < guessResult.length; i++) {
                if (guessResult[i] == Guess.GREEN) {
                    observers.get(i).newText(userInputArr[i], Color.green);
                }else if (guessResult[i] == Guess.YELLOW) {
                    observers.get(i).newText(userInputArr[i], Color.yellow);
                } else {
                    observers.get(i).newText(userInputArr[i], Color.gray);
                }
            }
        }
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }
}