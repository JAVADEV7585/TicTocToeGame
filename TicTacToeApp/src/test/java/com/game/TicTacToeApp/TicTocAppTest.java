package com.game.TicTacToeApp;

import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * Unit test for TicTocApp.
 */

public class TicTocAppTest {

	/** The ttapp. */
	// Intitialize the Board First
	static TicTocApp ttapp = new TicTocApp();
	
	/** The n. */
	static int n = 3;
	
	/** The board. */
	String board[][] = new String[n][n];

	/**
	 * Creates the board to play.
	 */
	@BeforeClass
	public static void createBoardToPlay() {
		ttapp.createGameBoard(n);
	}

	/**
	 * Checks if is board created as expected.
	 */
	@Test
	public void isBoardCreatedAsExpected() {
		String board[][] = ttapp.createGameBoard(n);
		int rowLength = board[0].length;
		int colLength = board[1].length;
		assertEquals(true, ttapp.ensureBoardisCreated(board));
	}

	/**
	 * Fill positions.
	 */
	@Test
	public void fillPositions() {
		String[][] board = ttapp.createGameBoard(3);
		boolean isFilled = TicTocApp.fillPositions(board);
		assertEquals(true, isFilled);

	}

	/**
	 * Checks if is position already filled.
	 */
	@Test
	public void isPositionAlreadyFilled() {
		String board[][] = new String[n][n];
		// int i, int j, String[][] board
		if (TicTocApp.fillPositions(board))
			assertEquals(true, TicTocApp.isPositionAlreadyFilled(0, 0, board));
	}

	/**
	 * Check for parallel positions.
	 */
	@Test
	public void checkForParallelPositions() {
		if (TicTocApp.fillPositions(board))
			//(int i, int j, String[][] board, boolean playerWins
			assertEquals(true, TicTocApp.checkForParallelPositions(0, 0, board,false));
	}

	/**
	 * Check for vertical positions.
	 */
	@Test
	public void checkForVerticalPositions() {
		
			assertEquals(true, TicTocApp.checkForVerticalPositions(0, 0, board,false));
	}

	/**
	 * Check for diagonal positions.
	 */
	@Test
	public void checkForDiagonalPositions() {
		if (TicTocApp.fillPositions(board))
			//(int i, int j, String[][] board, boolean playerWins
			assertEquals(true, TicTocApp.checkForDiagonalPositions(0, 0, board,false));
	}

	/**
	 * Check for draw.
	 */
	@Test
	public void checkForDraw() {
		if (TicTocApp.fillPositions(board))
			//(int i, int j, String[][] board, boolean playerWins
			assertEquals(true, TicTocApp.checkForDraw(board,false));

	}

}
