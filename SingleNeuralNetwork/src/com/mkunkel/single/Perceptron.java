package com.mkunkel.single;

import java.util.Random;

public class Perceptron {

	private float[] weights;
	private float[][] input;
	private float[] output;
	private int numOfWeights;

	public Perceptron(float[][] input, float[] output) {
		this.input = input;
		this.output = output;
		this.numOfWeights = input[0].length;
		this.weights = new float[numOfWeights];

		initWeights();
	}

	private void initWeights() {
		// for demo lets init weight to be 0
		for (int i = 0; i < numOfWeights; i++) {
			// weights[i] = 0;
			weights[i] = new Random().nextFloat();
		}
	}

	public void train(float learningWeight) {// , int epochs
		float totalError = 1;
		while (totalError != 0) {
			totalError = 0;
			for (int i = 0; i < output.length; i++) {
				float calculatedOutput = calculateOuput(input[i]);
				// float error = Math.abs(output[i] - calculatedOutput);
				float error = output[i] - calculatedOutput;

				totalError += error;
				for (int j = 0; j < numOfWeights; j++) {
					weights[j] = weights[j] + learningWeight * input[i][j] * error;
					System.out.println("Updated weight " + weights[j]);
				}
			}
			System.out.println("Keep on training the network. The error is " + totalError);
		}
	}

	public float calculateOuput(float[] input) {
		float sum = 0f;
		for (int i = 0; i < input.length; i++) {
			sum = sum + weights[i] * input[i];
		}
		return ActivationFunction.stepFunction(sum);
		// return ActivationFunction.reLU(sum / input.length);
	}

}
