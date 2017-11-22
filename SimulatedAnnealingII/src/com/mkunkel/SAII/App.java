package com.mkunkel.SAII;

import org.jlab.groot.ui.TCanvas;

public class App {
	public static void main(String[] args) {

		for (int i = 0; i < 10000; i++) {
			City city = new City();
			Repository.addCity(city);
		}
		// initial distance for this tour should be 303.0
		// Tour: 14 97--> 31 58--> 69 46--> 89 29--> 70 26-->
		// 70 11--> 1 10-->16 47--> 10 60--> 19 92-->

		// Repository.addCity(new City(14, 97));
		// Repository.addCity(new City(31, 58));
		// Repository.addCity(new City(69, 46));
		// Repository.addCity(new City(89, 29));
		// Repository.addCity(new City(70, 26));
		// Repository.addCity(new City(70, 11));
		// Repository.addCity(new City(1, 10));
		// Repository.addCity(new City(16, 47));
		// Repository.addCity(new City(10, 60));
		// Repository.addCity(new City(19, 92));

		SimulatingAnnealing annealing = new SimulatingAnnealing();
		annealing.simulation();

		System.out.println("Final approximated solution's distance is: " + annealing.getBestSolution().getDistance());
		System.out.println("Tour: " + annealing.getBestSolution());

		TCanvas canvas = new TCanvas("", 800, 900);
		canvas.draw(annealing.getBestSolution().toGraphErrors(), "APL");

	}
}
