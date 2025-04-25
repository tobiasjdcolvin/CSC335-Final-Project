package model;
import model.LoginModel;

public class Board {
    private boolean DEBUG = true;

    private final model.Word solution;
    private model.Word[] guess;
    private int guessRemaining;

    public Board(Word solution, int size) {
        this.solution = solution;
        this.guessRemaining = size;

        if (this.DEBUG == true) {
            System.out.println("SOLUTION: " + solution.toString());
        }
    }

    public int getGuessRemaining() {
        return this.guessRemaining;
    }

    public Guess[] checkWord (Word guess) {
        Guess[] ret = {Guess.NONE, Guess.NONE, Guess.NONE, Guess.NONE, Guess.NONE};
        char[] guessChars = guess.getChars();
        char[] solutionChars = this.solution.getChars();

        // Check for exact matches first
        for (int i=0;i<5;i++) {
            if (guessChars[i] == solutionChars[i]) {
                ret[i] = Guess.GREEN;
                solutionChars[i] = '#';
                guessChars[i] = '#';
            }
        }
        // Check for yellows
        for (int i=0;i<5;i++) {
            for (int j = 0; j<5; j++) {
                if (solutionChars[j] == guessChars[i] && guessChars[i] != '#') {
                    ret[i] = Guess.YELLOW;
                    solutionChars[j] = '#';
                    break;
                }
            }
        }
        this.guessRemaining--;
        if (DEBUG) {
            for (Guess g : ret) {
                System.out.println(g.name());
            }
        }
        return ret;
    }

    public boolean toggleDebug() {
        this.DEBUG = !this.DEBUG;
        return this.DEBUG;
    }

    public String getSolution() {
        return this.solution.toString();
    }


}