package com.game.TicTacToeApp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Date 15-06-2019.
 *
 * @author JAVADEV7585
 */
public class TicTocApp {

	/** The player 1 wins. */
	static boolean player1Wins = false;
	
	/** The player 2 wins. */
	static boolean player2Wins = false;
	
	/** The draw match. */
	static boolean drawMatch = false;
	
	/** The skip filled positions. */
	static boolean skipFilledPositions = false;
	
	/** The no one wins. */
	boolean noOneWins = false;
	
	/** The swap players. */
	static boolean swapPlayers = false;

	/** The player 1. */
	int player1 = 1;
	
	/** The player 2. */
	int player2 = 2;
	
	/** The player 1 counter. */
	static int player1Counter = 0;
	
	/** The player 2 counter. */
	static int player2Counter = 0;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		TicTocApp ttapp = new TicTocApp();

		// Creata a Board 3*3
		int size = Integer.parseInt(args[0]);
		String board[][] = ttapp.createGameBoard(size);

		// Ensure the 3*3 board is correct or not
		boolean isCreated = ttapp.ensureBoardisCreated(board);

		boolean isBoardFilledwithWinningCombinations  = fillPositions(board);
		drawMatch = checkForDraw(board, drawMatch);
		if (drawMatch) {
			System.out.print("Game is draw and nobody wins");
		}

	}

	/**
	 * Fill positions.
	 *
	 * @param board the board
	 * @return true, if successful
	 */
	static boolean fillPositions(String[][] board) {
		// Player 1 should always goes first.
		System.out.println("Player 1 play first");
		outer :for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				// Player can't fill the already filled postion
				skipFilledPositions = isPositionAlreadyFilled(i, j, board);
				if (!skipFilledPositions) {
					// System.out.println("Swap players flag " + swapPlayers);
					if (!swapPlayers) {
						board[i][j] = "*";
						swapPlayers = true;
						player1Counter++;
						printBoard(board);
						if (player1Counter >= 3) {
							player1Wins = checkWinningPositions(i,j,board,player1Wins);
							if (player1Wins) {
								System.out.print("Player 1 Wins");
								break;
							} else {
								continue;
							}
						}
					} else {
						board[i][j] = "O";
						swapPlayers = false;
						player2Counter++;
						printBoard(board);
						if (player2Counter >= 3) {
							player2Wins = checkWinningPositions(i,j,board,player2Wins);
							if (player2Wins) {
								System.out.print("Player 2 Wins");
								break;
							} else {
								continue;
							}
						}
					}
				}
			}
		}
		if(player1Wins || player2Wins)
		return true;
		return false;
	}

	/**
	 * Ensure board is created.
	 *
	 * @param board the board
	 * @return true, if successful
	 */
	boolean ensureBoardisCreated(String[][] board) {
		int i = 0, j = 0;
		boolean isBoardCorrectFlag = false;
		for (i = 0; i < 3; i++) {
			System.out.println();
			for (j = 0; j < 3; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
		}
		System.out.println();

		if (i == j) {
			System.out.println("3*3 Board created successfully");
			isBoardCorrectFlag = true;
		}
		return isBoardCorrectFlag;
	}

	/**
	 * Creates the game board.
	 *
	 * @param size the size
	 * @return the string[][]
	 */
	String[][] createGameBoard(int size) {
		String board[][] = new String[size][size];
		// Create a 3*3 board first
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = "@";
			}
		}
		System.out.println("Board created successfully");
		return board;

	}

	/**
	 * Prints the board.
	 *
	 * @param board the board
	 */
	private static void printBoard(String[][] board) {
		// Ensure the 3*3 board is correct or not
		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board.length; j++) {
				System.out.print("[ " + board[i][j] + " ]");
			}
		}
		System.out.println();
	}

	/**
	 * Checks if is position already filled.
	 *
	 * @param i integer
	 * @param j integer
	 * @param board the board
	 * @return true, if is position already filled
	 */
	static boolean isPositionAlreadyFilled(int i, int j, String[][] board) {
		boolean isOccupied = false;
		if (board[i][j] == "*" || board[i][j] == "O") {
			System.out.println(i + " " + j + "board is " + board[i][j]
					+ "The current place is already filled Please choose the other place to fill");
			isOccupied = true;
		}
		return isOccupied;
	}

	/**
	 * Check winning positions.
	 *
	 * @param i integer
	 * @param j integer
	 * @param board the board
	 * @param playerWins the player wins
	 * @return true, if successful
	 */
	static boolean checkWinningPositions(int i,int j,String[][] board, boolean playerWins) {
				playerWins = checkForParallelPositions(i, j, board, playerWins);
				if (playerWins) {
					System.out.println("Filled positions in winning postion at Parallel shape");
				}
				playerWins = checkForVerticalPositions(i, j, board, playerWins);
				if (playerWins) {
					System.out.println("Filled positions in winning postion at Vertical shape");
				}
				playerWins = checkForDiagonalPositions(i, j, board, playerWins);
				if (playerWins) {
					System.out.println("Filled positions in winning postion at Diagonal shape");
				}
		return playerWins;
	}

	/**
	 * Check for diagonal positions.
	 *
	 * @param i integer
	 * @param j integer
	 * @param board the board
	 * @param playerWins the player wins
	 * @return true, if successful
	 */
	static boolean checkForDiagonalPositions(int i, int j, String[][] board, boolean playerWins) {
		if ((board[i][j].equals(board[0][2]) && board[0][2].equals("*") && board[i][j].equals(board[1][1])
				&& board[1][1].equals("*") && board[i][j].equals(board[2][0]) && board[2][0].equals("*"))
				|| (board[i][j].equals(board[0][0]) && board[0][0].equals("*") && board[i][j].equals(board[1][1])
						&& board[1][1].equals("*") && board[i][j].equals(board[2][2]) && board[2][2].equals("*"))) {
			playerWins = true;
		}
		return playerWins;
	}

	/**
	 * Check for vertical positions.
	 *
	 * @param i integer
	 * @param j integer
	 * @param board the board
	 * @param playerWins the player wins
	 * @return true, if successful
	 */
	static boolean checkForVerticalPositions(int i, int j, String[][] board, boolean playerWins) {
		if (board[i][j].equals(board[0][0]) && board[0][0].equals("*") && board[i][j].equals(board[1][0])
				&& board[1][0].equals("*") && board[i][j].equals(board[2][0]) && board[2][0].equals("*")
				|| board[i][j].equals(board[0][1]) && board[0][1].equals("*") && board[i][j].equals(board[1][1])
						&& board[1][1].equals("*") && board[i][j].equals(board[2][1]) && board[2][1].equals("*")
				|| board[i][j].equals(board[0][2]) && board[0][2].equals("*") && board[i][j].equals(board[1][2])
						&& board[1][2].equals("*") && board[i][j].equals(board[2][2]) && board[2][2].equals("*")) {
			playerWins = true;
		}
		return playerWins;
	}

	/**
	 * Check for parallel positions.
	 *
	 * @param i integer
	 * @param j integer
	 * @param board the board
	 * @param playerWins the player wins
	 * @return true, if successful
	 */
	static boolean checkForParallelPositions(int i, int j, String[][] board, boolean playerWins) {
		if (board[i][j].equals(board[0][0]) && board[0][0].equals("*") && board[i][j].equals(board[0][1])
				&& board[0][1].equals("*") && board[i][j].equals(board[0][2]) && board[0][2].equals("*")
				|| board[i][j].equals(board[1][0]) && board[1][0].equals("*") && board[i][j].equals(board[1][1])
						&& board[1][1].equals("*") && board[i][j].equals(board[1][2]) && board[1][2].equals("*")
				|| board[i][j].equals(board[2][0]) && board[2][0].equals("*") && board[i][j].equals(board[2][1])
						&& board[2][1].equals("*") && board[i][j].equals(board[2][2]) && board[2][2].equals("*")) {
			playerWins = true;
		}
		return playerWins;
	}

	/**
	 * Check for draw.
	 *
	 * @param board the board
	 * @param drawMatch the draw match
	 * @return true, if successful
	 */
	static boolean checkForDraw(String[][] board, boolean drawMatch) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (checkForDiagonalPositions(i, j, board, drawMatch)
						|| checkForVerticalPositions(i, j, board, drawMatch)
						|| checkForParallelPositions(i, j, board, drawMatch)) {
					drawMatch = false;
				} else {
					drawMatch = true;
				}
			}
		}
		return drawMatch;
	}
}
