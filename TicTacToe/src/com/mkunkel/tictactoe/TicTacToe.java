package com.mkunkel.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TicTacToe implements ScoreEventListener {

	private JFrame frame;
	private String startGame = "O";
	private int xCount = 0;
	private int oCount = 0;
	private int dCount = 0;

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;

	private JButton btnExit;
	private JButton btnStart;

	private ButtonGroup buttonMenuItem;
	private JRadioButton rdbtnHumanStarts;
	private JRadioButton rdbtnComputerStarts;
	private JRadioButton rdbtnCompVsComp;
	private JRadioButton learnButton;
	private boolean gameReady;

	private Board board;
	private Random random;

	private int x;
	private int y;
	private volatile boolean playersMove;
	private JTextField txtCountO;
	private JTextField txtCountX;
	private JTextField drawCount;

	private int nSims = 500;

	/**
	 * Create the application.
	 */
	public TicTacToe() {

		initialize();
	}

	private void makeFirstMove() {
		if (rdbtnComputerStarts.isSelected()) {
			startGame = CellState.COMPUTER.toString();
			Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
			board.move(cell, CellState.COMPUTER);
			updateGUI(cell.getX(), cell.getY(), CellState.COMPUTER);
			playersMove = true;
			playGame();

		} else if (rdbtnHumanStarts.isSelected()) {
			playersMove = true;
			playGame();
		} else if (rdbtnCompVsComp.isSelected()) {
			playersMove = true;
			if (learnButton.isSelected()) {
				dCount = 0;
				oCount = 0;
				xCount = 0;
				System.out.println("learning");
				int sleeping = nSims * 10;
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						for (int i = 1; i <= nSims; i++) {
							try {
								Thread.sleep(sleeping / i);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							playByItself();
							initBoard();

						}
						System.out.println(dCount + "  " + nSims);
						if (nSims == dCount) {
							System.out.println("all is draws");
							Text2Speech.dospeak(
									"Greetings Doctor Koongkel. Interesting game. Seems like the only move is not to play. How about a nice game of chess. ",
									"kevin16");
						}
					}
				});
				thread.start();

			} else {
				playByItself();
			}
		} else {

		}
	}

	private synchronized void playByItself() {
		// first we should randomize which player goes first;

		if (random.nextInt(2) == 0) {
			startGame = CellState.COMPUTER.toString();
			System.out.println("Simulated Computer goes first");
			Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
			board.move(cell, CellState.COMPUTER);
			updateGUI(cell.getX(), cell.getY(), CellState.COMPUTER);
			playersMove = true;
			simulateGame();

		} else {
			startGame = CellState.USER.toString();
			System.out.println("Simulated Player goes first");
			Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
			board.move(cell, CellState.USER);
			updateGUI(cell.getX(), cell.getY(), CellState.USER);
			playersMove = false;
			simulateGame();

		}

	}

	private JButton findButton(int x, int y) {
		JButton aButton = new JButton();
		if (x == 0 && y == 0) {
			return btn1;
		} else if (x == 0 && y == 1) {
			return btn2;
		} else if (x == 0 && y == 2) {
			return btn3;
		} else if (x == 1 && y == 0) {
			return btn4;
		} else if (x == 1 && y == 1) {
			return btn5;
		} else if (x == 1 && y == 2) {
			return btn6;
		} else if (x == 2 && y == 0) {
			return btn7;
		} else if (x == 2 && y == 1) {
			return btn8;
		} else if (x == 2 && y == 2) {
			return btn9;
		} else
			return aButton;
	}

	private void updateGUI(int x, int y, CellState cellState) {
		findButton(x, y).setText(cellState.toString());
		findButton(x, y).setForeground(getStateColor(cellState));
		choosePlayer();

	}

	private Color getStateColor(CellState cellState) {
		if (cellState.toString().equals("X")) {
			return Color.RED;
		} else
			return Color.BLUE;
	}

	private void gameScore() {
		txtCountX.setText(String.valueOf(xCount));
		txtCountO.setText(String.valueOf(oCount));
		drawCount.setText(String.valueOf(dCount));
	}

	private void choosePlayer() {
		if (startGame.equalsIgnoreCase("X")) {
			startGame = "O";
		} else
			startGame = "X";
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gameReady = false;
		this.random = new Random();

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 5, 2, 2));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		initializeButtons();

		panel_1.add(btn1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_2.add(btn2, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		panel_3.add(btn3, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblPlayerO = new JLabel("Player: O");
		lblPlayerO.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblPlayerO.setForeground(Color.BLUE);
		panel_4.add(lblPlayerO, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		txtCountO = new JTextField();
		txtCountO.setHorizontalAlignment(SwingConstants.CENTER);
		txtCountO.setFont(new Font("Tahoma", Font.BOLD, 48));
		txtCountO.setText("0");
		panel_5.add(txtCountO, BorderLayout.CENTER);
		txtCountO.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		panel_6.add(btn4, BorderLayout.CENTER);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		panel_7.add(btn5, BorderLayout.CENTER);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		panel_8.add(btn6, BorderLayout.CENTER);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JLabel lblPlayerX = new JLabel("Player: X");
		lblPlayerX.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblPlayerX.setForeground(Color.RED);
		panel_9.add(lblPlayerX, BorderLayout.CENTER);

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		txtCountX = new JTextField();
		txtCountX.setFont(new Font("Tahoma", Font.BOLD, 48));
		txtCountX.setHorizontalAlignment(SwingConstants.CENTER);
		txtCountX.setText("0");
		panel_12.add(txtCountX, BorderLayout.CENTER);
		txtCountX.setColumns(10);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));

		panel_11.add(btn7, BorderLayout.CENTER);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		panel_10.add(btn8, BorderLayout.CENTER);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		panel_13.add(btn9, BorderLayout.CENTER);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));

		btnStart = new JButton("Start");
		panel_15.add(btnStart, BorderLayout.CENTER);
		btnStart.addActionListener(e -> btnStart());
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 46));

		btnExit = new JButton("Exit");
		panel.add(btnExit);
		btnExit.addActionListener(e -> btnExit());
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 58));

		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		panel.add(lblNewLabel_4);

		JPanel panel_16 = new JPanel();
		panel.add(panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[] { 52, 179, 0 };
		gbl_panel_16.rowHeights = new int[] { 23, 36, 0, 0, 0, 0 };
		gbl_panel_16.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_16.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_16.setLayout(gbl_panel_16);

		GridBagConstraints gbc_rdbtnHumanStarts = new GridBagConstraints();
		gbc_rdbtnHumanStarts.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbtnHumanStarts.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnHumanStarts.gridx = 1;
		gbc_rdbtnHumanStarts.gridy = 0;
		panel_16.add(rdbtnHumanStarts, gbc_rdbtnHumanStarts);

		GridBagConstraints gbc_rdbtnComputerStarts = new GridBagConstraints();
		gbc_rdbtnComputerStarts.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnComputerStarts.fill = GridBagConstraints.BOTH;
		gbc_rdbtnComputerStarts.gridx = 1;
		gbc_rdbtnComputerStarts.gridy = 1;
		panel_16.add(rdbtnComputerStarts, gbc_rdbtnComputerStarts);

		GridBagConstraints gbc_rdbtnCompVsComp = new GridBagConstraints();
		// gbc_rdbtnCompVsComp.gridwidth = 2;
		gbc_rdbtnCompVsComp.anchor = GridBagConstraints.NORTHWEST;

		gbc_rdbtnCompVsComp.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnCompVsComp.gridx = 1;
		gbc_rdbtnCompVsComp.gridy = 2;
		panel_16.add(rdbtnCompVsComp, gbc_rdbtnCompVsComp);

		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_rdbtnCompVsComp.insets = new Insets(0, 0, 15, 0);

		// gbc_radioButton.gridwidth = 2;
		gbc_radioButton.gridx = 1;
		gbc_radioButton.gridy = 3;
		panel_16.add(learnButton, gbc_radioButton);

		Panel panel_17 = new Panel();
		panel.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));

		drawCount = new JTextField();
		drawCount.setText("0");
		drawCount.setHorizontalAlignment(SwingConstants.CENTER);
		drawCount.setFont(new Font("Tahoma", Font.BOLD, 48));
		drawCount.setColumns(10);
		panel_17.add(drawCount, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Draws");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_17.add(lblNewLabel_1, BorderLayout.NORTH);
	}

	private void buttonClicked(ActionEvent e) {
		if (!gameReady) {
			return;
		}
		JButton aButton = (JButton) e.getSource();
		if (!aButton.getText().isEmpty()) {
			return;
		}
		aButton.setText(startGame);
		if (startGame.equalsIgnoreCase("X")) {
			aButton.setForeground(Color.RED);
		} else {
			aButton.setForeground(Color.BLUE);

		}

		choosePlayer();
		setXY(aButton);
		Cell userCell = new Cell(this.x, this.y);
		board.move(userCell, CellState.USER);
		updateGUI(userCell.getX(), userCell.getY(), CellState.USER);
		// checkStatus();
		playersMove = false;
		playGame();
	}

	private void setXY(JButton aButton) {
		if (aButton.equals(btn1)) {
			this.x = 0;
			this.y = 0;
		} else if (aButton.equals(btn2)) {
			this.x = 0;
			this.y = 1;
		} else if (aButton.equals(btn3)) {
			this.x = 0;
			this.y = 2;
		} else if (aButton.equals(btn4)) {
			this.x = 1;
			this.y = 0;
		} else if (aButton.equals(btn5)) {
			this.x = 1;
			this.y = 1;
		} else if (aButton.equals(btn6)) {
			this.x = 1;
			this.y = 2;
		} else if (aButton.equals(btn7)) {
			this.x = 2;
			this.y = 0;
		} else if (aButton.equals(btn8)) {
			this.x = 2;
			this.y = 1;
		} else if (aButton.equals(btn9)) {
			this.x = 2;
			this.y = 2;
		}

	}

	private void initializeButtons() {
		btn1 = new JButton("");
		btn1.addActionListener(e -> buttonClicked(e));
		btn1.setFont(new Font("Tahoma", Font.BOLD, 96));

		btn2 = new JButton("");
		btn2.addActionListener(e -> buttonClicked(e));

		btn2.setFont(new Font("Tahoma", Font.BOLD, 96));
		btn3 = new JButton("");
		btn3.addActionListener(e -> buttonClicked(e));

		btn3.setFont(new Font("Tahoma", Font.BOLD, 96));

		btn4 = new JButton("");
		btn4.addActionListener(e -> buttonClicked(e));

		btn4.setFont(new Font("Tahoma", Font.BOLD, 96));

		btn5 = new JButton("");
		btn5.addActionListener(e -> buttonClicked(e));

		btn5.setFont(new Font("Tahoma", Font.BOLD, 96));
		btn6 = new JButton("");
		btn6.addActionListener(e -> buttonClicked(e));

		btn6.setFont(new Font("Tahoma", Font.BOLD, 96));

		btn7 = new JButton("");
		btn7.addActionListener(e -> buttonClicked(e));

		btn7.setFont(new Font("Tahoma", Font.BOLD, 96));
		btn8 = new JButton("");
		btn8.addActionListener(e -> buttonClicked(e));

		btn8.setFont(new Font("Tahoma", Font.BOLD, 96));
		btn9 = new JButton("");
		btn9.addActionListener(e -> buttonClicked(e));
		btn9.setFont(new Font("Tahoma", Font.BOLD, 96));

		buttonMenuItem = new ButtonGroup();

		this.rdbtnHumanStarts = new JRadioButton("Human Starts");
		this.rdbtnComputerStarts = new JRadioButton("Computer Starts");
		this.rdbtnCompVsComp = new JRadioButton("Comp vs. Comp");
		this.learnButton = new JRadioButton("C vs. C Learn");
		this.learnButton.setVisible(false);
		this.rdbtnHumanStarts.addActionListener(e -> {
			learnButton.setVisible(!rdbtnHumanStarts.isSelected());
			dCount = 0;
			oCount = 0;
			xCount = 0;
			gameScore();
		});
		this.rdbtnComputerStarts.addActionListener(e -> {
			learnButton.setVisible(!rdbtnComputerStarts.isSelected());
			dCount = 0;
			oCount = 0;
			xCount = 0;
			gameScore();
		});
		this.rdbtnCompVsComp.addActionListener(e -> {
			learnButton.setVisible(rdbtnCompVsComp.isSelected());
		});

		this.rdbtnComputerStarts.setSelected(true);
		buttonMenuItem.add(rdbtnComputerStarts);
		buttonMenuItem.add(rdbtnHumanStarts);
		buttonMenuItem.add(rdbtnCompVsComp);

	}

	private void resetButton() {
		btn1.setText("");
		btn2.setText("");
		btn3.setText("");
		btn4.setText("");
		btn5.setText("");
		btn6.setText("");
		btn7.setText("");
		btn8.setText("");
		btn9.setText("");

		btn1.setBackground(Color.BLUE);
		btn2.setBackground(Color.BLUE);
		btn3.setBackground(Color.BLUE);
		btn4.setBackground(Color.BLUE);
		btn5.setBackground(Color.BLUE);
		btn6.setBackground(Color.BLUE);
		btn7.setBackground(Color.BLUE);
		btn8.setBackground(Color.BLUE);
		btn9.setBackground(Color.BLUE);

		startGame = "X";
		gameReady = false;
	}

	private void btnExit() {

		frame = new JFrame("Exit");
		if (JOptionPane.showConfirmDialog(frame, "Confirm Close", "Tic Tac Toe",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			System.exit(0);
		}

	}

	private void btnStart() {
		resetButton();
		gameReady = true;
		initBoard();
		makeFirstMove();
	}

	private void initBoard() {
		this.board = new Board();
		this.board.setupBoard();
	}

	private void playGame() {
		if (board.isRunning() && !playersMove) {

			board.callMiniMax(0, CellState.COMPUTER);
			for (Cell cell : board.getRootValues()) {
				System.out.println("Cell values: " + cell + " minimaxValue: " + cell.getMinimaxValue());
			}
			System.out.println("#########");
			board.move(board.getBestMove(), CellState.COMPUTER);
			updateGUI(board.getBestMove().getX(), board.getBestMove().getY(), CellState.COMPUTER);
			playersMove = true;

		}
		if (!board.isRunning()) {
			checkStatus();
			gameScore();

			gameReady = false;
		}

	}

	private void simulateGame() {
		System.out.println(board.isRunning() + "  " + playersMove);
		while (board.isRunning()) {

			if (playersMove) {
				board.callMiniMaxUser(0, CellState.USER);
				for (Cell cell : board.getRootValues()) {
					System.out.println("User Cell values: " + cell + " minimaxValue: " + cell.getMinimaxValue());
				}
				board.move(board.getBestMove(), CellState.USER);
				updateGUI(board.getBestMove().getX(), board.getBestMove().getY(), CellState.USER);
				playersMove = false;
				board.displayBoard();
			}
			if (!board.isRunning()) {

				checkStatus();
				gameScore();

				gameReady = false;
				break;
			}
			if (!playersMove) {
				board.callMiniMaxComp(0, CellState.COMPUTER);
				for (Cell cell : board.getRootValues()) {
					System.out.println("Computer Cell values: " + cell + " minimaxValue: " + cell.getMinimaxValue());
				}
				board.move(board.getBestMove(), CellState.COMPUTER);
				updateGUI(board.getBestMove().getX(), board.getBestMove().getY(), CellState.COMPUTER);
				playersMove = true;
				board.displayBoard();
			}
			if (!board.isRunning()) {

				checkStatus();
				gameScore();

				gameReady = false;
				break;
			}
		}
	}

	private void checkStatus() {
		if (board.isWinning(CellState.COMPUTER)) {
			System.out.println("Computer has won...");
			xCount++;
		} else if (board.isWinning(CellState.USER)) {
			System.out.println("The user has won...");
			oCount++;
		} else {
			System.out.println("It is a draw...");
			dCount++;
		}
		gameScore();

		// gameReady = false;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe window = new TicTacToe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void nextIteration(ScoreEvent event) {
		gameScore();
	}

}
