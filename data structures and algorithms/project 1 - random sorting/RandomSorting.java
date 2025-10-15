
/**
 * RandomSorting.java
 * 
 * This file defines the RandomSorting class, which generates 35 random integers,
 * writes them to a file, reads them back, and sorts them in descending order.
 * 
 * 
 * @author Christopher Romo
 * @since 9/1/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

/**
 * The RandomSorting class contains a main method that generates random
 * integers, writes them to a file, reads them back, and sorts them in
 * descending order.
 */
public class RandomSorting {
	/**
	 * Main method - generates random integers, writes them to a file, reads them
	 * back, and sorts them in descending order.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws IOException {
		// initialize file and output
		File fileName = new File("output/data structures and algorithms output/randomsorting_output.txt");
		PrintWriter outputFile = new PrintWriter(fileName);

		// initialize & declare variables
		int min = -25;
		int max = 25;
		int numToCheck;
		int totalCount;
		int newSum = 0;

		int[] numbers = new int[35];
		int[] newNums = new int[35];
		int[] instances = new int[35];
		int[] newInstances = new int[35];

		// print header
		System.out.println("Generating 35 random values and writing to a file\n"
				+ "----------------------------------------------------");

		// output random numbers to file
		for (int i = 0; i < 35; i++) {
			int randNum = (int) (Math.random() * (max - min + 1) + min);
			System.out.println("Writing to file: " + randNum);
			outputFile.println(randNum);
		}
		outputFile.close();

		// fill array from file
		Scanner readFile = new Scanner(fileName);
		for (int i = 0; i < 35; i++) {
			numbers[i] = readFile.nextInt();
		}
		readFile.close();

		// sort array and copy into new array backwards
		Arrays.sort(numbers);
		for (int i = 34; i >= 0; i--) {
			newNums[i] = numbers[newSum];
			newSum++;
		}

		// find instances
		for (int i = 0; i < 35; i++) {
			numToCheck = numbers[i];
			totalCount = 0;

			for (int k = 0; k < 35; k++) {
				if (numbers[k] == numToCheck) {
					totalCount = totalCount + 1;
				}
				instances[i] = totalCount;
			}
		}

		// reverse instance array to match newNums array
		newSum = 0;
		for (int i = 34; i >= 0; i--) {
			newInstances[i] = instances[newSum];
			newSum++;
		}

		// print results
		System.out.println("\n" +
				"Array in descending sorted order\n" +
				"----------------------------------------------------\n" +
				"Numbers[i]	Number #        Times Appears\n" +
				"----------------------------------------------------");

		for (int i = 0; i < 35; i++) {
			String result = String.format("%d		%d		%d", i + 1, newNums[i], newInstances[i]);
			System.out.println(result);
		}

		System.out.println("\nFile is in directory: " + fileName.getAbsolutePath());
	}
}