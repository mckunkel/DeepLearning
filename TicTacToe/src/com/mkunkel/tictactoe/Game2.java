package com.mkunkel.tictactoe;

import java.util.Random;

public class Game2 {

	private Board board;
	private Random random;
	private boolean simulation = false;
	private CellState startGame;

	public Game2() {
		initializeGame();
		displayBoard();
		makeFirstMove();
		playGame(simulation);
		checkStatus();
	}

	private void initializeGame() {
		this.board = new Board();
		this.random = new Random();
		this.board.setupBoard();
	}

	private void displayBoard() {
		this.board.displayBoard();
	}

	private void makeFirstMove() {
		System.out.println("Who starts? 1 - COMPUTER ; 2 - USER ; 3 - Simulation");
		int choice = board.getScanner().nextInt();
		if (choice != 3) {

			if (choice == 1) {
				Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
				board.move(cell, CellState.COMPUTER);
				board.displayBoard();
			}
		} else {
			simulation = true;
		}
	}

	private void playGame(boolean simulation) {
		if (!simulation) {
			while (board.isRunning()) {
				System.out.println("User move: " + CellState.USER);
				Cell userCell = new Cell(board.getScanner().nextInt(), board.getScanner().nextInt());
				board.move(userCell, CellState.USER);
				board.displayBoard();

				if (!board.isRunning()) {
					break;
				}
				board.callMiniMax(0, CellState.COMPUTER);
				for (Cell cell : board.getRootValues()) {
					System.out.println("Cell values: " + cell + " minimaxValue: " + cell.getMinimaxValue());
				}
				board.move(board.getBestMove(), CellState.COMPUTER);
				board.displayBoard();
			}
		} else {
			System.out.println("I will run a cmp vs. cmp simulation here");
			// first I will start with "computer" first, later I will randomize
			// this
			startGame = CellState.COMPUTER;

			Cell firstMove = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
			board.move(firstMove, CellState.COMPUTER);
			board.displayBoard();
			System.out.println(startGame + " before switch");
			switchPlayer();
			System.out.println(startGame + " after switch");

			while (board.isRunning()) {
				if (!board.isRunning()) {
					break;
				}
				// board.callMiniMax(0, CellState.COMPUTER, true);
				// for (Cell cell : board.getRootValues()) {
				// System.out.println("Cell values: " + cell + " minimaxValue: "
				// + cell.getMinimaxValue());
				// }
				// board.move(board.getBestMove(), CellState.COMPUTER);
				// board.displayBoard();
				if (startGame.equals(CellState.USER)) {
					board.callMiniMaxUser(0, startGame);
				} else {
					board.callMiniMaxComp(0, startGame);
				}
				for (Cell cell : board.getRootValues()) {
					System.out
							.println(startGame + " Cell values: " + cell + " minimaxValue: " + cell.getMinimaxValue());
				}
				board.move(board.getBestMove(), startGame);
				board.displayBoard();
				switchPlayer();
			}
		}

	}

	private void switchPlayer() {
		if (startGame.equals(CellState.COMPUTER)) {
			startGame = CellState.USER;
		} else
			startGame = CellState.COMPUTER;
	}

	private void checkStatus() {
		if (board.isWinning(CellState.COMPUTER)) {
			System.out.println("Computer has won...");
		} else if (board.isWinning(CellState.USER)) {
			System.out.println("The user has won...");
		} else {
			System.out.println("It is a draw...");

		}
	}
}
