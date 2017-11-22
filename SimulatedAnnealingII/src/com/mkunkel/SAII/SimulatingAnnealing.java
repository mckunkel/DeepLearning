package com.mkunkel.SAII;

public class SimulatingAnnealing {
	private SingleTour bestSolution;

	public void simulation() {

		double temperature = 10000;
		double coolingRate = 0.03;
		SingleTour currentSolution = new SingleTour();
		currentSolution.generateIndividual();

		System.out.println("Initial solution distance :" + currentSolution.getDistance());

		bestSolution = new SingleTour(currentSolution.getTour());

		while (temperature > 1) {
			SingleTour newSolution = new SingleTour(currentSolution.getTour());
			int randomIndex1 = (int) (newSolution.getTourSize() * Math.random());
			City city1 = newSolution.getCity(randomIndex1);

			int randomIndex2 = (int) (newSolution.getTourSize() * Math.random());
			City city2 = newSolution.getCity(randomIndex2);

			newSolution.setCity(randomIndex2, city1);
			newSolution.setCity(randomIndex1, city2);

			double currentEnergy = currentSolution.getDistance();
			double neightborEnergy = newSolution.getDistance();

			if (acceptanceProbability(currentEnergy, neightborEnergy, temperature) > Math.random()) {
				currentSolution = new SingleTour(newSolution.getTour());
			}
			if (currentSolution.getDistance() < bestSolution.getDistance()) {
				bestSolution = new SingleTour(currentSolution.getTour());
			}

			temperature *= 1 - coolingRate;

		}

	}

	private double acceptanceProbability(double currentEnergy, double neightborEnergy, double temperature) {

		if (neightborEnergy < currentEnergy) {
			return 1.0;
		}

		return Math.exp((currentEnergy - neightborEnergy) / temperature);
	}

	public SingleTour getBestSolution() {
		return bestSolution;
	}

}
