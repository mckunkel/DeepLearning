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
			int gene = random.nextInt(2);
			genes[i] = gene;
		}
	}

	public double f(double x) {
		return Math.sin(x) * Math.pow((x - 2), 2) + 3;
	}

	public double getFitness() {

		double genesToDoubles = genesToDoubles();
		return f(genesToDoubles);
	}

	public double genesToDoubles() {
		int base = 1;
		double geneInDouble = 0;

		for (int i = 0; i < Constants.GENE_LENGTH; ++i) {
			if (this.genes[i] == 1) {
				geneInDouble += base;
			}
			base = base * 2;
		}
		geneInDouble = geneInDouble / 102.4f;
		return geneInDouble;
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
