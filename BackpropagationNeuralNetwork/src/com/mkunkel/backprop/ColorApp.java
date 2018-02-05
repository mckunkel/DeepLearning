package com.mkunkel.backprop;

public class ColorApp {

	public static void main(String[] args) {
		double[][] trainingData = new double[][] {
				// yellow --> {1,0,0}
				new double[] { 0.1, 0.2 }, new double[] { 0.13, 0.2 }, new double[] { 0.15, 0.58 },
				new double[] { 0.45, 0.7 }, new double[] { 0.4, 0.9 },

				// green --> {0,1,0}
				new double[] { 0.4, 1.2 }, new double[] { 0.45, 0.95 }, new double[] { 0.42, 1 },
				new double[] { 0.5, 1.1 }, new double[] { 0.53, 1.45 },

				// blue --> {0,0,1}
				new double[] { 0.6, 0.2 }, new double[] { 0.75, 0.7 }, new double[] { 0.9, 0.34 },
				new double[] { 0.85, 0.76 }, new double[] { 0.8, 0.34 }, };
		double[][] trainingResults = new double[][] { new double[] { 1, 0, 0 }, new double[] { 1, 0, 0 },
				new double[] { 1, 0, 0 }, new double[] { 1, 0, 0 }, new double[] { 1, 0, 0 },

				new double[] { 0, 1, 0 }, new double[] { 0, 1, 0 }, new double[] { 0, 1, 0 }, new double[] { 0, 1, 0 },
				new double[] { 0, 1, 0 },

				new double[] { 0, 0, 1 }, new double[] { 0, 0, 1 }, new double[] { 0, 0, 1 }, new double[] { 0, 0, 1 },
				new double[] { 0, 0, 1 }

		};

		BackpropNeuralNetwork bNetwork = new BackpropNeuralNetwork(2, 4, 3);
		// training
		for (int iteration = 0; iteration < NeuralNetworkConstants.ITERATIONS; iteration++) {
			for (int i = 0; i < trainingResults.length; i++) {
				bNetwork.train(trainingData[i], trainingResults[i], NeuralNetworkConstants.LEARNING_RATE,
						NeuralNetworkConstants.MOMENTUM);
			}
		}
		// testing
		double[] result = bNetwork.run(new double[] { 1, 0.5 });
		System.out.println(result[0] + " - " + result[1] + " - " + result[2]);
	}

}
