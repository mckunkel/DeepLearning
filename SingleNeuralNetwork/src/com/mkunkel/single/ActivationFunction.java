package com.mkunkel.single;

public class ActivationFunction {

	public static int stepFunction(float activation) {
		if (activation >= 1) {
			return 1;
		}
		return 0;
	}

	public static float reLU(float activation) {
		if (activation > 0) {
			return activation;
		}
		return 0;
	}
}
