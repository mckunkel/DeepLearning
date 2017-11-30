package com.mkunkel.tictactoe;

public class Cell {

	private int x;
	private int y;

	private int minimaxValue;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.x + "," + this.y + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMinimaxValue() {
		return minimaxValue;
	}

	public void setMinimaxValue(int minimaxValue) {
		this.minimaxValue = minimaxValue;
	}

}
