package com.mkunkel.tictactoe;

public class ScoreEvent {
	private int value;
	private boolean finished;

	public ScoreEvent(int value, boolean finished) {
		this.value = value;
		this.finished = finished;
	}

	public int getValue() {
		return value;
	}

	public boolean isFinished() {
		return finished;
	}
}
