package com.mkunkel.SA;

import java.util.Random;

public class SimulatedAnnealing {

	private Random random;
	private double currentCoordinateX;
	private double nextCoordinateX;
	private double bestCoordinateX;

	public SimulatedAnnealing() {
		this.random = new Random();
	}

	public double getEnergy(double x) {
		return f(x);
	}

	public double f(double x) {
		return Math.pow((x - 0.3), 3) - 5.0 * x + Math.pow(x, 2) - 2;
	}

	public double acceptanceProbability(double energy, double newEnergy, double temperature) {
		if (newEnergy > energy) {
			return 1;
		}
		return Math.exp((energy - newEnergy) / temperature);
	}

	public void findOptimum() {
		double temperature = Constants.MAX_TEMPERATURE;
		while (temperature > Constants.MIN_TEMPERATURE) {
			nextCoordinateX = getRandomX();
			double actualEnergy = getEnergy(currentCoordinateX);
			double newEnergy = getEnergy(nextCoordinateX);

			if (acceptanceProbability(actualEnergy, newEnergy, temperature) > Math.random()) {
				currentCoordinateX = nextCoordinateX;
			}
			if (f(currentCoordinateX) > f(bestCoordinateX)) {
				bestCoordinateX = currentCoordinateX;
			}
			temperature *= 1 - Constants.COOLING_RATE;
		}
		System.out.println("Global extreme guess: " + bestCoordinateX + " f(x)=" + f(bestCoordinateX));
	}

	private double getRandomX() {
		return random.nextDouble() * (Constants.MAX_COORDINATE - Constants.MIN_COORDINATE) + Constants.MIN_COORDINATE;
	}

}
