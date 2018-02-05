package com.mkunkel.backprop;

import java.util.Arrays;
import java.util.Random;

public class Layer {

	private double[] output;
	private double[] input;
	private double[] weights;
	private double[] dWeights;

	private Random random; // for initializing the weights

	public Layer(int inputSize, int outputSize) {
		this.output = new double[outputSize];
		this.input = new double[inputSize + 1]; // +1 for the bias node
		this.weights = new double[(inputSize + 1) * outputSize]; // +1 for the
																	// bias node
		this.dWeights = new double[weights.length];
		this.random = new Random();

		initWeights();

	}

	private void initWeights() {
		for (int i = 0; i < weights.length; i++) {
			weights[i] = (random.nextDouble() - 0.5) * 4; // [-2,2]
		}
	}

	public double[] run(double[] inputArray) {
		System.arraycopy(inputArray, 0, input, 0, inputArray.length);
		input[input.length - 1] = 1; // set bias node to 1
		int offset = 0;
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < input.length; j++) {
				output[i] += weights[offset + j] * input[j];
			}
			output[i] = ActivationFunction.sigmoid(output[i]);
			offset += input.length;
		}
		return Arrays.copyOf(output, output.length);
	}

	public double[] train(double[] error, double learningRate, double momentum) {

		int offset = 0;
		double[] nextError = new double[input.length];
		for (int i = 0; i < output.length; i++) {
			double delta = error[i] * ActivationFunction.dSigmoid(output[i]);
			for (int j = 0; j < input.length; j++) {
				int weightIndex = offset + j;
				nextError[j] = nextError[j] + weights[weightIndex] * delta;
				double dw = input[j] * delta * learningRate;
				weights[weightIndex] += dWeights[weightIndex] * momentum + dw;
				dWeights[weightIndex] = dw;
			}
			offset += input.length;
		}
		return nextError;

	}
}
