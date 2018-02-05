package com.mkunkel.backprop;

public class ActivationFunction {
	public static double sigmoid(double x) {

		return (1. / (1. + Math.exp(-x)));
	}

	public static double dSigmoid(double x) {

		// return (sigmoid(activation) * (1 - sigmoid(activation)));
		return x * (1 - x);// because the argument for dSigmoid was derived from
							// the sigmoid
	}
}
