
/**
 * Transportation.java
 * 
 * This file defines the Transportation class, which creates a cargo terminal
 * with semi-trucks and cargo planes.
 * 
 * @author Christopher Romo
 * @since 09/29/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The Transportation class contains a main method that creates a cargo
 * terminal with semi-trucks and cargo planes, reads in information from files,
 * and prints the terminal display and status.
 */
public class Transportation {
	/**
	 * prints the terminal status
	 * 
	 * @param terminal the cargo terminal
	 */
	public static void printTerminalStatus(CargoTerminal terminal) {
		// declare array lists
		ArrayList<SemiTruck> validTrucks = new ArrayList<SemiTruck>();
		ArrayList<CargoPlane> validPlanes = new ArrayList<CargoPlane>();

		// fill array lists
		for (int i = 0; i < terminal.getNumberDocks(); i++) {
			if (terminal.getSemiTruck(i) == null) {
				// do nothing, continue to next iteration of loop
			} else {
				// add truck to valid trucks array list
				validTrucks.add(terminal.getSemiTruck(i));
			}
		}
		for (int i = 0; i < terminal.getNumberStands(); i++) {
			if (terminal.getCargoPlane(i) == null) {
				// do nothing, continue to next iteration of loop
			} else {
				// add plane to valid planes array list
				validPlanes.add(terminal.getCargoPlane(i));
			}
		}

		// sort the array lists
		Collections.sort(validTrucks);
		Collections.sort(validPlanes);

		// print the terminal status
		System.out.println("**********************************************************************************");
		System.out.println("				Tarmac Status									  ");
		System.out.println("			(Highest to Lowest Capacity)							  ");
		System.out.println("**********************************************************************************");
		System.out.println("Flight		Departing To		Cargo		Capacity(lbs)");
		System.out.println("----------------------------------------------------------------------------------");

		for (CargoPlane plane : validPlanes) {
			String result = plane.toString();
			System.out.println(result);
		}

		System.out.println("**********************************************************************************");
		System.out.println("			      Loading Dock Status									  ");
		System.out.println("		      (Lowest to Highest Truck Number)							  ");
		System.out.println("**********************************************************************************");
		System.out.println("Truck #		Destination City");
		System.out.println("----------------------------------------------------------------------------------");

		for (SemiTruck truck : validTrucks) {
			String result = truck.toString();
			System.out.println(result);

		}		
	} // printTerminalStatus

	/**
	 * Main method - creates a cargo terminal with semi-trucks and cargo planes,
	 * reads in information from files, and prints the terminal display and status.
	 * 
	 * @param args command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// declare variables
		int dockNumber;
		int truckNumber;
		String destinationCity;
		int standNumber;
		int flightNumber;
		double capacity;
		String cargoType;

		// open files for reading
		File trucks = new File("input/data structures and algorithms input/transportation_trucks_input.txt");
		File planes = new File("input/data structures and algorithms input/transportation_planes_input.txt");
		Scanner readTruckFile = new Scanner(trucks);
		Scanner readPlaneFile = new Scanner(planes);

		// read in size for arrays and initialize cargo terminal
		int numberDocks = readTruckFile.nextInt();
		int numberStands = readPlaneFile.nextInt();
		CargoTerminal mainTerminal = new CargoTerminal(numberDocks, numberStands);

		// use loops to fill arrays
		while (readTruckFile.hasNext()) {
			dockNumber = readTruckFile.nextInt();
			truckNumber = readTruckFile.nextInt();
			destinationCity = readTruckFile.next();

			SemiTruck truck = new SemiTruck(truckNumber, destinationCity);
			mainTerminal.addSemiTruck(dockNumber, truck);
		}
		while (readPlaneFile.hasNext()) {
			standNumber = readPlaneFile.nextInt();
			flightNumber = readPlaneFile.nextInt();
			capacity = readPlaneFile.nextDouble();
			cargoType = readPlaneFile.next();
			destinationCity = readPlaneFile.next();

			CargoPlane plane = new CargoPlane(flightNumber, capacity, cargoType, destinationCity);
			mainTerminal.addCargoPlane(standNumber, plane);
		}

		// display the cargo terminal and print status
		mainTerminal.displayCargoTerminal();
		printTerminalStatus(mainTerminal);

		// close files
		readTruckFile.close();
		readPlaneFile.close();

	} // main
} // Transportation

/**
 * The CargoTerminal class creates a cargo terminal object.
 */
class CargoTerminal {
	// instance variables
	private int numberDocks;
	private int numberStands;
	private CargoPlane[] tarmac;
	private SemiTruck[] loadingDock;

	/**
	 * constructor that creates a cargo terminal object
	 * 
	 * @param numberDocks
	 * @param numberStands
	 */
	public CargoTerminal(int numberDocks, int numberStands) {
		this.numberDocks = numberDocks;
		this.numberStands = numberStands;

		loadingDock = new SemiTruck[numberDocks];
		tarmac = new CargoPlane[numberStands];
	}

	// getters
	public int getNumberDocks() {
		return numberDocks;
	}

	public int getNumberStands() {
		return numberStands;
	}

	// behavior

	/**
	 * adds a semi-truck to the loading dock
	 * 
	 * @param dock      the dock number
	 * @param semiTruck the semi-truck to add
	 */
	public void addSemiTruck(int dock, SemiTruck semiTruck) {
		loadingDock[dock] = semiTruck;
	}

	/**
	 * adds a cargo plane to the tarmac
	 * 
	 * @param stand the stand number
	 * @param plane the cargo plane to add
	 */
	public void addCargoPlane(int stand, CargoPlane plane) {
		tarmac[stand] = plane;
	}

	/**
	 * gets a semi-truck from the loading dock
	 * 
	 * @param dock the dock number
	 * @return the semi-truck at the dock, or null if none
	 */
	public SemiTruck getSemiTruck(int dock) {
		if (loadingDock[dock] == null) {
			return null;
		} else {
			return loadingDock[dock];
		}
	}

	/**
	 * gets a cargo plane from the tarmac
	 * 
	 * @param stand the stand number
	 * @return the cargo plane at the stand, or null if none
	 */
	public CargoPlane getCargoPlane(int stand) {
		if (tarmac[stand] == null) {
			return null;
		} else {
			return tarmac[stand];
		}
	}

	/**
	 * displays the cargo terminal
	 */
	public void displayCargoTerminal() {
		int truckCount = 0;
		int planeCount = 0;
		System.out.println("Loading semi-trucks and planes into cargo terminal..."
				+ "");

		// dock header print statement
		for (int i = 0; i < numberDocks; i++) {
			// print dock number header
			System.out.printf("Dock %d	", truckCount);

			// increment count for header
			truckCount++;
		}
		System.out.println();

		// truck print statement
		for (int i = 0; i < numberDocks; i++) {
			// print truck in current spot
			if (loadingDock[i] == null) {
				System.out.printf("------	");
			} else {
				System.out.printf("%d	", loadingDock[i].getTruckNumber());
			}
		}
		System.out.println();

		// stand header print statement
		for (int i = 0; i < numberStands; i++) {
			// print stand number header
			System.out.printf("Stand %d	", planeCount);

			// increment count for header
			planeCount++;
		}
		System.out.println();

		// plane print statement
		for (int i = 0; i < numberStands; i++) {
			// print plane in current spot
			if (tarmac[i] == null) {
				System.out.printf("------	");
			} else {
				System.out.printf("%d	", tarmac[i].getFlightNumber());
			}
		}
		System.out.println();

	}
} // CargoTerminal

/**
 * The SemiTruck class creates a semi-truck object.
 */
class SemiTruck implements Comparable<SemiTruck> {
	// instance variables
	private int truckNumber;
	private String destinationCity;

	/**
	 * constructor that creates a semi-truck object
	 * 
	 * @param truckNumber
	 * @param destinationCity
	 */
	public SemiTruck(int truckNumber, String destinationCity) {
		this.truckNumber = truckNumber;
		this.destinationCity = destinationCity;
	}

	// getters
	public int getTruckNumber() {
		return truckNumber;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	// behavior
	@Override
	public String toString() {
		return String.format("%4d\t\t%-15s\t", truckNumber, destinationCity);
	}

	@Override
	public int compareTo(SemiTruck otherSemiTruck) {
		if (truckNumber < otherSemiTruck.truckNumber) {
			return -1;
		} else if (truckNumber == otherSemiTruck.truckNumber) {
			return 0;
		} else {
			return 1;

		}
	}
} // SemiTruck

/**
 * The CargoPlane class creates a cargo plane object.
 */
class CargoPlane implements Comparable<CargoPlane> {
	// instance variables
	private int flightNumber;
	private double capacity;
	private String cargoType;
	private String destinationCity;

	/**
	 * constructor that creates a cargo plane object
	 * 
	 * @param flightNumber
	 * @param capacity
	 * @param cargoType
	 * @param destinationCity
	 */
	public CargoPlane(int flightNumber, double capacity, String cargoType, String destinationCity) {
		this.flightNumber = flightNumber;
		this.capacity = capacity;
		this.cargoType = cargoType;
		this.destinationCity = destinationCity;
	}

	// getters
	public int getFlightNumber() {
		return flightNumber;
	}

	// behavior
	@Override
	public String toString() {
		return String.format("%4d\t\t%-15s\t\t%-10s\t%.2f", flightNumber, destinationCity, cargoType, capacity);
	}

	@Override
	public int compareTo(CargoPlane otherCargoPlane) {
		if (capacity < otherCargoPlane.capacity) {
			return 1;
		} else if (capacity == otherCargoPlane.capacity) {
			return 0;
		} else {
			return -1;

		}
	}
} // CargoPlane