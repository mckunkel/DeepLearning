package com.mkunkel.genetic;

import java.util.Random;

public class Individual {

	private int[] genes;
	private int fitness;
	private Random random;

	public Individual() {
		this.genes = new int[Constants.CHROMOSOME_LENGTH];
		this.random = new Random();
	}

	public void generateIndividual() {
		for (int i = 0; i < Constants.CHROMOSOME_LENGTH; ++i) {
			int gene = random.nextInt(10);
			genes[i] = gene;
		}
	}

	public int getFitness() {
		if (fitness == 0) {
			for (int i = 0; i < Constants.CHROMOSOME_LENGTH; ++i) {
				if (getGene(i) == Constants.SOLUTION_SEQUENCE[i]) {
					this.fitness++;
				}
			}
		}

		return fitness;
	}

	public int getGene(int index) {
		return this.genes[index];
	}

	public void setGene(int index, int value) {
		this.genes[index] = value;
		this.fitness = 0;
	}

	@Override
	public String toString() {

		String string = "";

		for (int i = 0; i < Constants.CHROMOSOME_LENGTH; ++i) {
			string += getGene(i);

		}
		return string;

	}

}
