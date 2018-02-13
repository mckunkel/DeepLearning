package com.mkunkel.backprop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class csvTest {
	public static boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(
				"/Volumes/MacStorage/WorkCodes/GitHub/DeepLearning/Java/spark-big-data-analytics/data/iris.csv"));
		scanner.useDelimiter(",");
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] fields = line.split(",");
			if (fields.length >= 4) // At least one address specified.
			{
				if (isNumeric(fields[0])) {

					for (String field : fields)
						System.out.print(field + "|");
					System.out.println();
				}
			} else {
				System.err.println("Invalid record: " + line);
			}
		}
		scanner.close();
	}
}
