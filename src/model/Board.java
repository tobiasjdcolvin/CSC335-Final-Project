package ;

/*
 * Class: Board
 *
 * Expected Behavior: Be able to create a board of variable length to show various aspects
 *                    of correctness for a given word, which the board saves internally. The
 *                    board should be able to be constructed with a variable number of guesses
 *                    available and a given word object.
 *                    The board will be how the game interacts with the words, being responsible for
 *                    checking and reporting the correctness of the word to the view.
 *
 */

public class Board {
    private boolean DEBUG = true;

    private final Word solution;
    private Word guess;

    public Board(Word word, int size) {
        this.guess = new Word(word);
    }
}