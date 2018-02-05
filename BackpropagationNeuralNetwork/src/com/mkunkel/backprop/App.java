package com.mkunkel.backprop;

public class App {
	public static void main(String[] args) {
		double input[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] output = { { 0, 0, 0, 1 } }; // LogicalAND
		// double[] output = { 0, 1, 1, 0 }; // LogicalXOR
		// double[] output = { 0, 1, 1, 1 }; // LogicalOR

		double[][] trainingData = new double[][] { new double[] { 0, 0 }, new double[] { 0, 1 }, new double[] { 1, 0 },
				new double[] { 1, 1 } };
		// double[][] trainingResults = new double[][] { new double[] { 0 }, new
		// double[] { 0 }, new double[] { 0 },
		// new double[] { 1 } }; //LogicalAND

		double[][] trainingResults = new double[][] { new double[] { 0 }, new double[] { 1 }, new double[] { 1 },
				new double[] { 0 } }; // LogicalXOR
		// double[][] trainingResults = new double[][] { new double[] { 0 }, new
		// double[] { 1 }, new double[] { 1 },
		// new double[] { 1 } }; // LogicalOR

		BackpropNeuralNetwork bNetwork = new BackpropNeuralNetwork(2, 3, 1);
		for (int iteration = 0; iteration < NeuralNetworkConstants.ITERATIONS; iteration++) {
			System.out.println("Num of iteration: " + (iteration + 1));
			for (int i = 0; i < trainingResults.length; i++) {
				bNetwork.train(trainingData[i], trainingResults[i], NeuralNetworkConstants.LEARNING_RATE,
						NeuralNetworkConstants.MOMENTUM);
			}
			System.out.println();
			for (int i = 0; i < trainingResults.length; i++) {
				double[] t = trainingData[i];
				System.out.printf("%.1f, %.1f --> %.3f\n", t[0], t[1], bNetwork.run(t)[0]);

			}
		}
	}
}
