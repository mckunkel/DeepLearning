package com.IKP.bruteforce;

public class BruteForce {

	private static double START_X = -1;
	private static double END_X = 2;

	// the interval is START_X and END_X

	public double f(double x) {
		return -1 * (Math.pow(x - 1, 2) + 2);
	}

	public void bruteForceSearch() {

		double startingPointX = START_X;
		double max = f(startingPointX);
		double dx = 0.01;
		double MaxX = START_X;
		for (double i = startingPointX; i < END_X; i += dx) {

			if (f(i) > max) {
				max = f(i);
				MaxX = i;
			}
		}
		System.out.println("The maximum value for f(x) in this interval is = " + max + " and x = " + MaxX);
	}

}
