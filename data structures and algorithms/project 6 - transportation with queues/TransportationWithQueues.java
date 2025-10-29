
/**
 * TransportationWithQueues.java
 * 
 * This file defines the TransportationWithQueues class, which simulates
 * a cargo terminal using queues to manage semi-trucks and cargo planes.
 * 
 * @author Christopher Romo
 * @since 10/26/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The TransportationWithQueues class contains a main method that simulates
 * a cargo terminal using queues to manage semi-trucks and cargo planes.
 */
public class TransportationWithQueues {
	/**
	 * Main method - simulates a cargo terminal using queues to manage semi-trucks
	 * and cargo planes.
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

		// declare objects
		AirTrafficController airControl = new AirTrafficController();
		Taxiways mainTaxiway = new Taxiways();
		Runway mainRunway = new Runway();

		// open files for reading
		File trucks = new File("input/data structures and algorithms input/transportationwithqueues_trucks_input.txt");
		File planes = new File("input/data structures and algorithms input/transportationwithqueues_planes_input.txt");
		Scanner readTruckFile = new Scanner(trucks);
		Scanner readPlaneFile = new Scanner(planes);

		// read in size for arrays and initialize cargo terminal
		int numberDocks = readTruckFile.nextInt();
		int numberStands = readPlaneFile.nextInt();
		Terminal mainTerminal = new Terminal(numberDocks, numberStands);

		// use loops to fill arrays
		while (readTruckFile.hasNext()) {
			dockNumber = readTruckFile.nextInt();
			truckNumber = readTruckFile.nextInt();
			destinationCity = readTruckFile.nextLine();

			Truck truck = new Truck(truckNumber, destinationCity);
			mainTerminal.addTruck(dockNumber, truck);
		}

		while (readPlaneFile.hasNext()) {
			standNumber = readPlaneFile.nextInt();
			flightNumber = readPlaneFile.nextInt();
			capacity = readPlaneFile.nextDouble();
			cargoType = readPlaneFile.next();
			destinationCity = readPlaneFile.nextLine();

			Plane plane = new Plane(flightNumber, capacity, cargoType, destinationCity);
			mainTerminal.addPlane(standNumber, plane);
		}

		// display the cargo terminal and use air controller to direct planes
		System.out.println("Loading semi-trucks and planes into cargo terminal...");
		System.out.println();
		mainTerminal.displayTerminal();
		System.out.println();
		airControl.movePlanesToTaxiways(mainTerminal, mainTaxiway);
		System.out.println();
		System.out.println("Show empty tarmac in cargo terminal...");
		System.out.println();
		mainTerminal.displayTerminal();
		System.out.println();
		airControl.movePlanesToRunway(mainTaxiway, mainRunway);
		System.out.println();
		airControl.clearedForTakeoff(mainRunway);

		// close files
		readTruckFile.close();
		readPlaneFile.close();

	} // main
} // TransportationWithQueues

/**
 * The Terminal class creates a terminal object.
 */
class Terminal {
	// instance variables
	private int numberDocks;
	private int numberStands;
	private Plane[] tarmac;
	private Truck[] loadingDock;

	/**
	 * constructor that creates a terminal object
	 * 
	 * @param numberDocks
	 * @param numberStands
	 */
	public Terminal(int numberDocks, int numberStands) {
		this.numberDocks = numberDocks;
		this.numberStands = numberStands;

		loadingDock = new Truck[numberDocks];
		tarmac = new Plane[numberStands];
	}

	// getters
	public int getNumberDocks() {
		return numberDocks;
	}

	public int getNumberStands() {
		return numberStands;
	}

	public Truck getTruck(int dock) {
		if (loadingDock[dock] == null) {
			return null;
		} else {
			return loadingDock[dock];
		}
	}

	public Plane getPlane(int stand) {
		if (tarmac[stand] == null) {
			return null;
		} else {
			return tarmac[stand];
		}
	}

	// behavior

	/**
	 * adds a truck to the loading dock
	 * 
	 * @param dock  dock number
	 * @param truck truck to add
	 */
	public void addTruck(int dock, Truck truck) {
		loadingDock[dock] = truck;
	}

	/**
	 * adds a plane to the tarmac
	 * 
	 * @param stand stand number
	 * @param plane plane to add
	 */
	public void addPlane(int stand, Plane plane) {
		tarmac[stand] = plane;
	}

	/**
	 * removes a plane from the tarmac
	 * 
	 * @param stand stand number
	 * @return the removed plane
	 */
	public Plane removePlane(int stand) {
		if (tarmac[stand] == null) {
			return null;
		} else {
			Queue<Plane> tempQueue = new LinkedList<>();
			tempQueue.offer(tarmac[stand]);
			tarmac[stand] = null;
			return tempQueue.poll();
		}
	}

	/**
	 * displays the terminal
	 */
	public void displayTerminal() {
		int truckCount = 0;
		int planeCount = 0;

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
} // Terminal

/**
 * The Truck class creates a truck object.
 */
class Truck implements Comparable<Truck> {
	// instance variables
	private int truckNumber;
	private String destinationCity;

	/**
	 * constructor that creates a truck object
	 * 
	 * @param truckNumber
	 * @param destinationCity
	 */
	public Truck(int truckNumber, String destinationCity) {
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
	public int compareTo(Truck otherTruck) {
		if (truckNumber < otherTruck.truckNumber) {
			return -1;
		} else if (truckNumber == otherTruck.truckNumber) {
			return 0;
		} else {
			return 1;

		}
	}
} // Truck

/**
 * The Plane class creates a plane object.
 */
class Plane implements Comparable<Plane> {
	// instance variables
	private int flightNumber;
	private String cargoType;
	private String destinationCity;

	/**
	 * constructor that creates a plane object
	 * 
	 * @param flightNumber
	 * @param capacity
	 * @param cargoType
	 * @param destinationCity
	 */
	public Plane(int flightNumber, double capacity, String cargoType, String destinationCity) {
		this.flightNumber = flightNumber;
		// this.capacity = capacity;
		this.cargoType = cargoType;
		this.destinationCity = destinationCity;
	}

	// getters
	public int getFlightNumber() {
		return flightNumber;
	}

	public String getCargoType() {
		return cargoType;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	// behavior
	public boolean isPriority() {
		boolean isPriority;

		if (this.cargoType.equals("Military") || this.cargoType.equals("Medical")
				|| this.cargoType.equals("Perishables")) {
			isPriority = true;
		} else {
			isPriority = false;
		}

		return isPriority;
	}

	public boolean isBasic() {
		boolean isBasic;

		if (!this.cargoType.equals("Military") && !this.cargoType.equals("Medical")
				&& !this.cargoType.equals("Perishables")) {
			isBasic = true;
		} else {
			isBasic = false;
		}

		return isBasic;
	}

	@Override
	public String toString() {
		return String.format("%4d\t\t%-15s\t\t%-10s", flightNumber, destinationCity, cargoType);
	}

	@Override
	public int compareTo(Plane otherPlane) {
		/**
		 * first the type of incoming plane is determined using if, else if, else for
		 * the three types
		 * then, the other planes type is determined, allowing comparisons of planes
		 * with different types
		 * lastly, if both planes have the same type, the flight numbers are compared
		 * (smaller #s first)
		 */
		int compareValue = 0;

		if (cargoType.equals("Military")) {
			if (otherPlane.cargoType.equals("Military")) {
				if (flightNumber < otherPlane.flightNumber) {
					compareValue = -1;
				} else if (flightNumber == otherPlane.flightNumber) {
					compareValue = 0;
				} else {
					compareValue = 1;
				}
			} else {
				compareValue = -1;
			}
		} else if (cargoType.equals("Perishables")) {
			if (otherPlane.cargoType.equals("Military")) {
				compareValue = 1;
			} else if (otherPlane.cargoType.equals("Perishables")) {
				if (flightNumber < otherPlane.flightNumber) {
					compareValue = -1;
				} else if (flightNumber == otherPlane.flightNumber) {
					compareValue = 0;
				} else {
					compareValue = 1;
				}
			} else {
				compareValue = -1;
			}
		} else if (cargoType.equals("Medical")) {
			if (otherPlane.cargoType.equals("Medical")) {
				if (flightNumber < otherPlane.flightNumber) {
					compareValue = -1;
				} else if (flightNumber == otherPlane.flightNumber) {
					compareValue = 0;
				} else {
					compareValue = 1;
				}
			} else {
				compareValue = 1;
			}
		}
		return compareValue;

	}
} // Plane

/**
 * The Taxiways class creates a taxiways object.
 */
class Taxiways {
	// instance variables
	private Queue<Plane> basicTaxiway;
	private PriorityQueue<Plane> priorityTaxiway;

	/**
	 * constructor that creates a taxiways object
	 */
	public Taxiways() {
		basicTaxiway = new LinkedList<>();
		priorityTaxiway = new PriorityQueue<>();
	}

	// behavior
	public boolean isPriorityTaxiwayEmpty() {
		return priorityTaxiway.isEmpty();
	}

	public void addPlaneToPriorityTaxiway(Plane aPlane) {
		priorityTaxiway.offer(aPlane);
	}

	public Plane removePlaneFromPriorityTaxiway() {
		return priorityTaxiway.poll();
	}

	public boolean isBasicTaxiwayEmpty() {
		return basicTaxiway.isEmpty();
	}

	public void addPlaneToBasicTaxiway(Plane aPlane) {
		basicTaxiway.offer(aPlane);
	}

	public Plane removePlaneFromBasicTaxiway() {
		return basicTaxiway.poll();

	}
} // Taxiways

/**
 * The Runway class creates a runway object.
 */
class Runway {
	// Instance variables
	private Queue<Plane> runway;

	/**
	 * constructor that creates a runway object
	 */
	public Runway() {
		runway = new LinkedList<>();
	}

	// behavior
	public boolean isRunwayEmpty() {
		return runway.isEmpty();
	}

	public void addPlaneToRunway(Plane aPlane) {
		runway.offer(aPlane);
	}

	public Plane removePlaneFromRunway() {
		return runway.poll();

	}
} // Runway

/**
 * The AirTrafficController class creates an air traffic controller object.
 */
class AirTrafficController {
	// moves planes from the cargo terminal tarmac onto the taxiways
	public void movePlanesToTaxiways(Terminal terminal, Taxiways taxiways) {
		System.out.println("Control Tower: Moving planes from cargo terminal tarmac to taxiways: \n"
				+ "---------------------------------------------------------------------------------");
		int standNum = terminal.getNumberStands();

		for (int i = 0; i < standNum; i++) {
			if (terminal.getPlane(i) != null) {
				if (terminal.getPlane(i).isPriority()) {
					System.out.println(String.format("Moved to taxiway Priority flight %4d\t\t%-15s\t\t%-10s",
							terminal.getPlane(i).getFlightNumber(),
							terminal.getPlane(i).getDestinationCity(),
							terminal.getPlane(i).getCargoType()));
					taxiways.addPlaneToPriorityTaxiway(terminal.removePlane(i));
				} else {
					System.out.println(String.format("Moved to taxiway Basic    flight %4d\t\t%-15s\t\t%-10s",
							terminal.getPlane(i).getFlightNumber(),
							terminal.getPlane(i).getDestinationCity(),
							terminal.getPlane(i).getCargoType()));
					taxiways.addPlaneToBasicTaxiway(terminal.removePlane(i));
				}
			}
		}
	}

	// moves planes from the taxiways to the runway
	public void movePlanesToRunway(Taxiways taxiways, Runway runway) {
		System.out.println("Control Tower: Moving cargo planes from taxiways to runway: \n"
				+ "--------------------------------------------------------------------");
		Queue<Plane> tempQueue = new LinkedList<>();

		while (!taxiways.isPriorityTaxiwayEmpty()) {
			tempQueue.offer(taxiways.removePlaneFromPriorityTaxiway());
			System.out.println(String.format("Moved to runway flight %4d\t\t%-15s\t\t%-10s",
					tempQueue.peek().getFlightNumber(), tempQueue.peek().getDestinationCity(),
					tempQueue.peek().getCargoType()));
			runway.addPlaneToRunway(tempQueue.poll());
		}
		while (!taxiways.isBasicTaxiwayEmpty()) {
			tempQueue.offer(taxiways.removePlaneFromBasicTaxiway());
			System.out.println(String.format("Moved to runway flight %4d\t\t%-15s\t\t%-10s",
					tempQueue.peek().getFlightNumber(), tempQueue.peek().getDestinationCity(),
					tempQueue.peek().getCargoType()));
			runway.addPlaneToRunway(tempQueue.poll());
		}
	}

	// clears planes for takeoff
	public void clearedForTakeoff(Runway runway) {
		System.out.println("Control Tower: Ready for takeoff! \n"
				+ "--------------------------------------------------------------------");
		Queue<Plane> tempQueue = new LinkedList<>();

		while (!runway.isRunwayEmpty()) {
			tempQueue.offer(runway.removePlaneFromRunway());
			System.out.println(String.format("Cleared for takeoff flight %4d\t\t%-15s\t\t%-10s",
					tempQueue.peek().getFlightNumber(), tempQueue.peek().getDestinationCity(),
					tempQueue.peek().getCargoType()));
			tempQueue.poll();

		}
	}
} // AirTrafficController