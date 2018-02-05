package com.mkunkel.backprop;

public class IrisNameConverter {

	private IrisNameConverter() {

	}

	public static double[][] convertName(String name) {
		double[][] retValue;

		if (name.equals("setosa")) {
			retValue = new double[][] { new double[] { 1, 0, 0 } };
		} else if (name.equals("versicolor")) {
			retValue = new double[][] { new double[] { 0, 1, 0 } };
		} else if (name.equals("virginica")) {
			retValue = new double[][] { new double[] { 0, 0, 1 } };
		} else {
			System.err.println("I don't know this species");
			retValue = new double[][] { new double[] { 0, 0, 0 } };
		}
		return retValue;
	}

	public static void main(String[] args) {
		double[][] val = IrisNameConverter.convertName("versicolor");
		for (double[] ds : val) {

			for (int i = 0; i < ds.length; i++) {
				System.out.println(ds[i]);
			}
		}
	}
}
