package com.mkunkel.genetic;

public class App {

	public static void main(String[] args) {

		GeneticAlgorithms geneticAlgorithms = new GeneticAlgorithms();

		Population population = new Population(100);
		population.init();

		int generationCounter = 0;

		while (population.getFitestIndividual().getFitness() != Constants.MAX_FITNESS) {
			++generationCounter;

			System.out.println("Generation: " + generationCounter + " - fitest is: "
					+ population.getFitestIndividual().getFitness());

			System.out.println(population.getFitestIndividual() + "\n");

			population = geneticAlgorithms.evolvePopulation(population);
		}

		System.out.println("Solution Found !!!");
		System.out.println(population.getFitestIndividual());

	}
}
