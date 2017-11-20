package com.IKP.hillclimbing;

public class HillClimbing {

	private static final double START_X = -2;
	private static final double END_X = 2;

	private double f(double x) {
		return -(x - 1) * (x - 1) + 2;
	}

	public void hillClimbing() {
		double dx = 0.026;
		double actualPointX = START_X;
		double max = f(actualPointX);

		while (f(actualPointX + dx) >= max) {
			max = f(actualPointX + dx);
			System.out.println("x  = " + actualPointX + " f(x) = " + f(actualPointX));
			actualPointX = actualPointX + dx;
		}
		System.out.println("Max with hill climbing  = " + max);

	}

}
