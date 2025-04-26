package model;

import view.LandingView;

import view.LoserView;
import view.Observer;
import view.View;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class Model {
    private View currView;
    private String username;
    private String userInput;
    private ArrayList<Observer> observers;
    private Board board;
    private int guessCount = 0;
    private static String[] guessList = new String[5];

    public Model(String username, View view) {
        this.currView = view;
        this.username = username;
        System.out.println("current user: " + this.username);
        // build an ArrayList of words to pick a random one from:
        HashSet<String> wordList = getWordList();

        // now pick a random word from the list and create a Board with it:
        Random myRandom = new Random();
        int randIndex = myRandom.nextInt(wordList.size());
        String randWord = new ArrayList<String>(wordList).get(randIndex);   

        // create a board using the random word
        board = new Board(new Word(randWord), 5);

        this.observers = new ArrayList<Observer>();
    }

    public boolean submitUserInput(String text) {
    	if(board.getGuessRemaining()!=0) {
	    	this.userInput = text;
	        // this will keep track of the row (one word) observer amount for guesses
	        // since we need to make one for the word, which is made up of 1 per letter, like how tou had it toby
	        int startIndex = guessCount * 5;
	
	        // in this case, the user input is 5 characters long.
	
	        // split the user input into individual characters
	        String[] userInputArr = userInput.split("");
	
	        // check the guess and output colored strings based upon the result
	        Guess[] guessResult = board.checkWord(new Word(userInput.toLowerCase()));
	        // made it so that it updates the row observer, which is made up of the 5 observers that toby implemted 
	        for (int i = 0; i < guessResult.length; i++) {
	            Observer obs = observers.get(startIndex + i);
	            if (guessResult[i] == Guess.GREEN) {
	                obs.newText(userInputArr[i], Color.GREEN);
	            }else if (guessResult[i] == Guess.YELLOW) {
	                obs.newText(userInputArr[i], Color.YELLOW);
	            } else {
	                obs.newText(userInputArr[i], Color.GRAY);
	            }
	        }
	        guessList[guessCount] = userInput.toLowerCase();
	        guessCount++;
            //this is added part
            boolean correct = true;
            for (Guess guess : guessResult){
                if (guess != Guess.GREEN){
                    correct = false;
                    break;
                }
            }
            if (correct){
            	LoginModel.updateUserWinLoss(this.username,1,0);
                return true;
            }
    	} else{
            lose();
        }
        return false;
    }


    private void lose() {
        currView.dispose(); // found this in a YouTube tutorial for changing windows
        LoginModel.updateUserWinLoss(this.username,0,1);
        LoserView newView = new LoserView(this.username);
        
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
    
    public static HashSet<String> getWordList(){
    	HashSet<String> words = new HashSet<String>();
        try  {
            BufferedReader myReader = new BufferedReader(new FileReader("src/model/words.txt"));
            String currLine = myReader.readLine();
            while (currLine != null) {
                // add each line in the words.txt file to the list:
                words.add(currLine.stripTrailing().toLowerCase());
                currLine = myReader.readLine();
            }
        } catch (Exception e) {
        }
        return words;
    }
    
    public static String[] getGuesses() {
    	return guessList.clone();
    }
    public String getSolution() {
        return this.board.getSolution();
    }
}