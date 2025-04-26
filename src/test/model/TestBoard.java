package test.model;

import model.Board;
import model.Guess;
import model.Word;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoard {
    private Board board = new Board(new Word("apple"), 6);

    @Test
    public void testGetter() {
        assertEquals(6, board.getGuessRemaining());
        board.toggleDebug();
        assertEquals(6, board.getGuessRemaining());
    }

    @Test
    public void testGetSolution() {
        board.getSolution();
    }

    @Test
    public void testGuess() {
        Guess[] answer = {Guess.GREEN, Guess.GREEN, Guess.YELLOW, Guess.YELLOW, Guess.NONE};
        assertEquals(answer, board.checkWord(new Word("aplpm")));
        board.toggleDebug();
        assertEquals(answer, board.checkWord(new Word("aplpm")));
    }


}
