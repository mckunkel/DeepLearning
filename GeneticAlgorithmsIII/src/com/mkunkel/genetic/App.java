package com.mkunkel.genetic;

public class App {

	public static void main(String[] args) {

		GeneticAlgorithms geneticAlgorithms = new GeneticAlgorithms();

		Population population = new Population(100);
		population.init();

		int generationCounter = 0;

		while (generationCounter != Constants.SIMULATION_LENGTH) {
			++generationCounter;

			System.out.println("Generation: " + generationCounter + " - fitest is: "
					+ population.getFittestIndividual().getFitness());

			System.out.println(population.getFittestIndividual() + "\n");

			population = geneticAlgorithms.evolvePopulation(population);
		}

		System.out.println("Solution Found !!!");
		System.out.println(population.getFittestIndividual());

	}
}
