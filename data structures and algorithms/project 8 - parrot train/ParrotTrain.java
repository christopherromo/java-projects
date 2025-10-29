
/**
 * ParrotTrain.java
 * 
 * This file defines the ParrotTrain class, which simulates a train journey
 * using linked list data structures to manage rail cars.
 * 
 * @author Christopher Romo
 * @since 11/14/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The ParrotTrain class contains a main method that simulates a train journey
 * using linked list data structures to manage rail cars.
 */
public class ParrotTrain {
	/**
	 * Main method - simulates a train journey using linked list data structures to
	 * manage rail cars.
	 * 
	 * @param args command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// declare variables
		int number;
		int result;
		String freight;
		String destination;

		// open file for scanning
		File trainInfo = new File("input/data structures and algorithms input/parrottrain_input.txt");
		Scanner trainScanner = new Scanner(trainInfo);

		// initialize the linked lists
		SingleLinkedList parrotTrain = new SingleLinkedList();
		DoubleLinkedList theParrotTrainTwo = new DoubleLinkedList();

		// iterate through rail car file and add trains to linked list
		while (trainScanner.hasNext()) {
			number = trainScanner.nextInt();
			freight = trainScanner.next();
			destination = trainScanner.nextLine().trim();

			RailCar aRailCar = new RailCar(number, freight, destination);
			parrotTrain.addByDestination(aRailCar);
			theParrotTrainTwo.addToEnd(aRailCar);
		}
		trainScanner.close();

		// print the linked lists and simulate a journey
		System.out.println("Train departs New York\n");
		parrotTrain.print();
		System.out.println("Stop 1: Train Arrives in Washington DC");
		result = parrotTrain.removeByDestination("Washington DC");
		System.out.println("Removed " + result + " Washington DC rail cars\n");
		parrotTrain.print();
		System.out.println("Stop 2: Train Arrives in Charleston");
		result = parrotTrain.removeByDestination("Charleston");
		System.out.println("Removed " + result + " Charleston rail cars\n");
		parrotTrain.print();
		System.out.println("Stop 3: Train Arrives in Orlando");
		result = parrotTrain.removeByDestination("Orlando");
		System.out.println("Removed " + result + " Orlando rail cars");
		parrotTrain.removeByFreight("parrots");
		System.out.println("Remove all rail cars with parrots\n");
		parrotTrain.print();
		System.out.println("Stop 4: Train Arrives in West Palm Beach");
		result = parrotTrain.removeByDestination("West Palm Beach");
		System.out.println("Removed " + result + " West Palm Beach rail cars - train should be empty!\n");
		parrotTrain.print();
		System.out.println("\n\nRail Cars in Doubly Linked List - Printed Backwards\n");
		theParrotTrainTwo.printBackwards();

	} // main
} // ParrotTrain

/**
 * The RailCar class creates a rail car object.
 */
class RailCar {
	// instance variables
	private int number;
	private String freight;
	private String destination;

	/**
	 * constructor that creates a rail car object
	 * 
	 * @param number
	 * @param freight
	 * @param destination
	 */
	public RailCar(int number, String freight, String destination) {
		this.number = number;
		this.freight = freight;
		this.destination = destination;
	}

	// getters
	public String getFreight() {
		return freight;
	}

	public String getDestination() {
		return destination;
	}

	// behavior
	public String toString() {
		return String.format("%d\t\t%-15s\t\t%s\n", number, freight, destination);
	}

	public int compareTo(RailCar otherRailCar) {
		if (destination.equals(otherRailCar.destination)) // Equal RailCars
		{
			return 0;
		} else if (destination.equals("Washington DC")) // Washington DC
		{
			return -1;
		} else if (destination.equals("Charleston")) // Charleston
		{
			if (otherRailCar.destination.equals("West Palm Beach") || otherRailCar.destination.equals("Orlando")) {
				return -1;
			} else {
				return 1;
			}
		} else if (destination.equals("Orlando")) // Orlando
		{
			if (otherRailCar.destination.equals("West Palm Beach")) {
				return -1;
			} else {
				return 1;
			}
		} else // West Palm Beach
		{
			return 1;

		}
	}
} // RailCar

/**
 * The SingleLinkedList class defines a singly linked list of rail cars
 * sorted by destination.
 */
class SingleLinkedList {
	// instance variables
	private Node head;

	/**
	 * constructor that creates a train linked list object
	 */
	public SingleLinkedList() {
		head = null;
	}

	// behavior

	/**
	 * adds a rail car to the linked list sorted by destination
	 * 
	 * @param railCarToAdd the rail car to add
	 */
	public void addByDestination(RailCar railCarToAdd) {
		// declare variables
		Node previous = null;
		Node current = head;
		Node railCarNode = new Node(railCarToAdd);
		boolean found = false;
		boolean placed = false;

		while (current != null && !found) // positioning
		{
			if (railCarToAdd.compareTo(current.railcar) == 1) {
				if (previous == null) // insertion point at the beggining
				{
					railCarNode.next = head;
					head = railCarNode;
				} else // insertion point in the middle
				{
					current = previous.next;
					previous.next = railCarNode;
					railCarNode.next = current;
				}

				placed = true;
				found = true;
			} else // moves linked list
			{
				previous = current;
				current = current.next;
			}
		}

		// first entry in linked list
		if (head == null) {
			head = railCarNode;
			placed = true;
		}

		// last entry in list
		if (!placed) {
			previous = null;
			current = head;

			while (current.next != null) {
				previous = current;
				current = current.next;
			}

			current.next = railCarNode;
			placed = true;

		}
	} // addByDestination

	/**
	 * removes rail cars by destination from the linked list
	 * 
	 * @param destination the destination to remove
	 * @return the number of rail cars removed
	 */
	public int removeByDestination(String destination) {
		// declare variables
		Node previous = null;
		Node current = head;
		int railCarsRemoved = 0;

		// iterate through linked list
		while (current != null) {
			if (current.railcar.getDestination().equals(destination)) {
				if (previous == null) // removes first element
				{
					// re-assign head
					head = current.next;
					railCarsRemoved++;

					// move linked list
					current = head;
				} else // removes middle element
				{
					// re-assign connection
					previous.next = current.next;
					railCarsRemoved++;

					// move linked list
					current = current.next;
				}
			} else {
				// move linked list
				previous = current;
				current = current.next;
			}
		}

		// return rail cars removed
		return railCarsRemoved;

	} // removeByDestination

	/**
	 * removes rail cars by freight from the linked list
	 * 
	 * @param freight the freight to remove
	 */
	public void removeByFreight(String freight) {
		// declare variables
		Node previous = null;
		Node current = head;

		// iterate through linked list
		while (current != null) {
			if (current.railcar.getFreight().equals(freight)) {
				if (previous == null) // removes first element
				{
					// re-assign head
					head = current.next;

					// move linked list
					current = head;
				} else // removes middle element
				{
					// re-assign connection
					previous.next = current.next;

					// move linked list
					current = current.next;
				}
			} else {
				// move linked list
				previous = current;
				current = current.next;

			}
		}
	} // removeByFreight

	/**
	 * prints the linked list
	 */
	public void print() {
		// declare variables
		Node current = head;

		// print header
		System.out.println("RailCar\t\tFreight\t\t\t Destination City\n"
				+ "-------------------------------------------------------");

		// iterate through linked list
		while (current != null) {
			System.out.println(current.railcar.toString());
			current = current.next;

		}
	} // print

	/**
	 * Node class represents a single node in the linked list
	 */
	private static class Node {
		// instance variables
		private RailCar railcar;
		private Node next;

		/**
		 * constructor that creates a node object
		 * 
		 * @param railcar
		 */
		public Node(RailCar railcar) {
			this.railcar = railcar;
			next = null;

		}
	} // Node
} // SingleLinkedList

/**
 * The DoubleLinkedList class defines a doubly linked list of rail cars.
 */
class DoubleLinkedList {
	// instance variables
	private Node head;
	private Node tail;

	/**
	 * constructor that creates a doubly linked list object
	 */
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	// behavior

	/**
	 * adds a rail car to the end of the linked list
	 * 
	 * @param railCarToAdd the rail car to add
	 */
	public void addToEnd(RailCar railCarToAdd) {
		Node railCarNode = new Node(railCarToAdd);

		if (head == null) {
			head = tail = railCarNode;
		} else {
			tail.next = railCarNode;
			railCarNode.previous = tail;
			tail = railCarNode;

		}
	} // addToEnd

	/**
	 * prints the linked list backwards
	 */
	public void printBackwards() {
		// declare variables
		Node current = tail;

		// print header
		System.out.println("RailCar\t\tFreight\t\t\t Destination City\n"
				+ "-------------------------------------------------------");

		// iterate through linked list
		while (current != null) {
			System.out.println(current.railcar.toString());
			current = current.previous;

		}
	} // printBackwards

	/**
	 * Node class represents a single node in the doubly linked list
	 */
	private static class Node {
		// instance variables
		private RailCar railcar;
		private Node next;
		private Node previous;

		/**
		 * constructor that creates a node object
		 * 
		 * @param railcar
		 */
		public Node(RailCar railcar) {
			this.railcar = railcar;
			next = null;
			previous = null;

		}
	} // Node
} // DoubleLinkedList