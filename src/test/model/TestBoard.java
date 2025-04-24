package test.model;
import model.Board;
import model.Guess;
import model.Word;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoard {
	private Board board = new Board(new Word("apple"),6);

	@Test
	public void testGetter() {
	assertEquals(6, board.getGuessRemaining());
	        board.toggleDebug();
	        assertEquals(6, board.getGuessRemaining());
	}

	@Test
	public void testGuess() {
	Guess[] answer = {Guess.GREEN, Guess.GREEN, Guess.YELLOW, Guess.YELLOW, Guess.NONE};
	assertEquals(answer, board.checkWord(new Word("aplpm")));
	        board.toggleDebug();
	        assertEquals(answer, board.checkWord(new Word("aplpm")));
	}
		
	@Test
	public void testGuess2() {
		Guess[] answer = {Guess.GREEN, Guess.GREEN, Guess.YELLOW, Guess.YELLOW, Guess.NONE};
	        //assertTrue(answer.equals(board.checkWord(new Word("aplpm"))));
	        for(int i = 0; i < 5; i++) {
	        	assertEquals(answer[i], board.checkWord(new Word("aplpm"))[i]);
	        	assertEquals((5-i),board.getGuessRemaining());
	        }
	 	assertEquals(1, board.getGuessRemaining());
	        assertFalse(4 == board.getGuessRemaining());
	 }

}
