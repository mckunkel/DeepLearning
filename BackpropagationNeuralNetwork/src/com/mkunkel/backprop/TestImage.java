package com.mkunkel.backprop;

import org.jlab.groot.data.H2F;
import org.jlab.groot.ui.TCanvas;

public class TestImage {
	public static H2F makeHist(double[] input, String name, int xPixels, int yPixels) {
		H2F image = new H2F("name", xPixels, 0, xPixels, yPixels, 0, yPixels);

		int xPlace = 0;
		int yPlace = 9;
		for (int i = 0; i < input.length; i++) {
			yPlace--;

			if (yPlace == 0) {
				xPlace++;
				yPlace = 8;
			}
			image.setBinContent(xPlace, yPlace - 1, input[i]);
		}

		return image;

	}

	public static void main(String[] args) {
		double[] testImage1 = new double[] { 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1,
				1, 1, 0, 1 };
		double[] testImage2 = new double[] { 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1,
				1, 1, 0, 0 };
		double[] testImage3 = new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1,
				1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1 };
		H2F image1 = makeHist(testImage1, "TestImage1", 8, 8);
		H2F image2 = makeHist(testImage2, "TestImage1", 8, 8);
		H2F image3 = makeHist(testImage3, "TestImage1", 8, 8);

		TCanvas canvas = new TCanvas("Images", 800, 800);
		canvas.divide(1, 3);
		canvas.getCanvas().getPad(0).setPalette("kVisibleSpectrum");
		canvas.getCanvas().getPad(1).setPalette("kVisibleSpectrum");
		canvas.getCanvas().getPad(2).setPalette("kVisibleSpectrum");

		canvas.cd(0);
		canvas.draw(image1);
		canvas.cd(1);
		canvas.draw(image2);
		canvas.cd(2);
		canvas.draw(image3);
	}

}
