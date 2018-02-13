package com.mkunkel.backprop;

import org.jlab.groot.data.H2F;

public class OCRApp {

	public static void main(String[] args) throws Exception {

		double[][] trainingData = new double[][] {
				new double[] { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0,
						0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1,
						0, 0 },
				new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0,
						1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1,
						1, 1 },
				new double[] { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1,
						0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
						0, 0 },
				new double[] { 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1,
						0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0,
						0, 1 },
				new double[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
						0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1,
						1, 0 },
				new double[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1,
						0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1,
						0, 0 },
				new double[] { 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0,
						0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0,
						0, 0 },
				new double[] { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1,
						0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1,
						1, 0 },
				new double[] { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1,
						1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0,
						0, 0 },
				new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0,
						0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
						0, 0 },
				new double[] { 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0,
						0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1,
						1, 1 } };

		double[][] trainingResults = new double[][] { new double[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // "0"
				new double[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // "0"
				new double[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, // "1"
				new double[] { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // "2"
				new double[] { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, // "3"
				new double[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, // "4"
				new double[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, // "5"
				new double[] { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, // "6"
				new double[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, // "7"
				new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, // "8"
				new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }// "9"
		};

		BackpropNeuralNetwork backpropagationNeuralNetworks = new BackpropNeuralNetwork(64, 15, 10);

		for (int iterations = 0; iterations < NeuralNetworkConstants.ITERATIONS; iterations++) {

			for (int i = 0; i < trainingResults.length; i++) {
				backpropagationNeuralNetworks.train(trainingData[i], trainingResults[i],
						NeuralNetworkConstants.LEARNING_RATE, NeuralNetworkConstants.MOMENTUM);
			}

			if ((iterations + 1) % 10000 == 0) {
				System.out.println();
				for (int i = 0; i < trainingResults.length; i++) {
					double[] data = trainingData[i];
					double[] calculatedOutput = backpropagationNeuralNetworks.run(data);
					System.out.println(calculatedOutput[0] + " " + calculatedOutput[1] + " " + calculatedOutput[2] + " "
							+ calculatedOutput[3] + " " + calculatedOutput[4] + " " + calculatedOutput[5] + " "
							+ calculatedOutput[6] + " " + calculatedOutput[7] + " " + calculatedOutput[8] + " "
							+ calculatedOutput[9]);
				}
			}
		}

		System.out.println("---------------------------");

		double[] calculatedOutput = backpropagationNeuralNetworks.run(new double[] { 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0,
				0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1,
				1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1 });
		System.out.println(
				calculatedOutput[0] + " " + calculatedOutput[1] + " " + calculatedOutput[2] + " " + calculatedOutput[3]
						+ " " + calculatedOutput[4] + " " + calculatedOutput[5] + " " + calculatedOutput[6] + " "
						+ calculatedOutput[7] + " " + calculatedOutput[8] + " " + calculatedOutput[9]);

		System.out.println("---------------------------");

		calculatedOutput = backpropagationNeuralNetworks.run(new double[] { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1,
				0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1,
				1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0 });
		System.out.println(
				calculatedOutput[0] + " " + calculatedOutput[1] + " " + calculatedOutput[2] + " " + calculatedOutput[3]
						+ " " + calculatedOutput[4] + " " + calculatedOutput[5] + " " + calculatedOutput[6] + " "
						+ calculatedOutput[7] + " " + calculatedOutput[8] + " " + calculatedOutput[9]);

		System.out.println("---------------------------");

		calculatedOutput = backpropagationNeuralNetworks.run(new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1,
				1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1,
				0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
		System.out.println(
				calculatedOutput[0] + " " + calculatedOutput[1] + " " + calculatedOutput[2] + " " + calculatedOutput[3]
						+ " " + calculatedOutput[4] + " " + calculatedOutput[5] + " " + calculatedOutput[6] + " "
						+ calculatedOutput[7] + " " + calculatedOutput[8] + " " + calculatedOutput[9]);

		// Lets look the prediction images, I will make the with a H2F for the
		// CLAS plotting package
		// It is an 8x8 image
		// I know this is redundant, but I do not feel like retyping this
		double[] testImage1 = new double[] { 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1,
				1, 1, 0, 1 };
		double[] testImage2 = new double[] { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1,
				1, 1, 0, 0 };
		double[] testImage3 = new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1 };

		H2F inputImage1 = new H2F("TestImage1", 8, 0, 7, 8, 0, 7);

	}

}
