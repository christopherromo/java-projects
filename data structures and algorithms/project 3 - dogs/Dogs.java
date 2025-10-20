
/**
 * Dogs.java
 * 
 * This file defines the Dogs class, which reads in dog data from a file,
 * creates dog objects, and analyzes their behaviors.
 * 
 * @author Christopher Romo
 * @since 9/14/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The Dogs class contains the main method that reads in dog data from a file,
 * creates dog objects, and analyzes their behaviors.
 */
public class Dogs {
	/**
	 * Main method - reads in dog data from a file, creates dog objects,
	 * and analyzes their behaviors.
	 * 
	 * @param args command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// open file
		File inputFileName = new File("input/data structures and algorithms input/dogs_input.txt");
		Scanner inputFile = new Scanner(inputFileName);

		// declare variables
		int counter = 0;
		int dogNum = inputFile.nextInt();
		Dog[] dogs = new Dog[dogNum];
		ArrayList<Dog> protectiveDogs = new ArrayList<Dog>();

		// fill dog array
		while (inputFile.hasNext()) {
			// get dogs information
			String breed = inputFile.next();
			String name = inputFile.next();
			int servingAbility = inputFile.nextInt();
			int herdingAbility = inputFile.nextInt();
			int huntingAbility = inputFile.nextInt();
			int protectingAbility = inputFile.nextInt();

			// add dog and appropriate information
			switch (breed) {
				case "BorderCollie":
					dogs[counter] = new BorderCollie(name, servingAbility, herdingAbility, protectingAbility);
					counter++;
					break;

				case "Boxer":
					dogs[counter] = new Boxer(name, servingAbility, herdingAbility, huntingAbility, protectingAbility);
					counter++;
					break;

				case "GermanShepherd":
					dogs[counter] = new GermanSheppard(name, servingAbility, herdingAbility, protectingAbility);
					counter++;
					break;

				case "GoldenRetriever":
					dogs[counter] = new GoldenRetriever(name, servingAbility, huntingAbility);
					counter++;
					break;

				default:
					break;
			}
		}
		inputFile.close();

		// print the dogs data using methods
		System.out.println("DOGS THAT PROTECT\n"
				+ "--------------------------------------------------------------------------------\n"
				+ "Dog	Protection	Temperament				Breed\n"
				+ "--------------------------------------------------------------------------------");

		// find protective dogs & print
		protectiveDogs = findProtectors(dogs);

		for (Dog dog : protectiveDogs) {
			String result = String.format("%s	%d		%s		%s", dog.getName(), ((Protector) dog).protect(),
					dog.temperament(), dog.getBreed());
			System.out.println(result);
		}

		// find most protective dog
		System.out.println("\n"
				+ "\n"
				+ "BEST PROTECTING DOG\n"
				+ "----------------------");
		Dog bestProtector = findBestProtector(protectiveDogs);
		String protectorLabel = String.format("Name: %s\nBreed: %s\nTemperament: %s\nProtecting Ability %d",
				bestProtector.getName(), bestProtector.getBreed(), bestProtector.temperament(),
				((Protector) bestProtector).protect());
		System.out.println(protectorLabel);

		// find most common ability
		System.out.println("\n"
				+ "\n"
				+ "ABILITY MOST COMMON FOR THE DOGS\n"
				+ "--------------------------------");
		String mostCommon = findMostCommonAbility(dogs);
		System.out.println(mostCommon + " is the most ability among the given dogs");

	} // main

	/**
	 * find dogs that are protectors and return them in an array list
	 * 
	 * @param dogs array of dogs
	 * @return array list of protective dogs
	 */
	public static ArrayList<Dog> findProtectors(Dog[] dogs) {
		ArrayList<Dog> protectiveDogs = new ArrayList<Dog>();

		// find dogs that are protective
		for (Dog dog : dogs) {
			if (dog instanceof Protector) {
				protectiveDogs.add(dog);
			}
		}
		return protectiveDogs;

	} // findProtectors

	/**
	 * find the dog with the best protecting ability and return it
	 * 
	 * @param protectors array list of protective dogs
	 * @return the dog with the best protecting ability
	 */
	public static Dog findBestProtector(ArrayList<Dog> protectors) {
		int max = 0;
		// find max number in set
		for (Dog dog : protectors) {
			if (((Protector) dog).protect() > max) {
				max = ((Protector) dog).protect();
			}
		}

		// return dog with that max number
		for (Dog dog : protectors) {
			if (((Protector) dog).protect() == max) {
				return dog;
			}
		}
		return null;

	} // findBestProtector

	/**
	 * find the most common ability among the dogs and return it
	 * 
	 * @param dogs array of dogs
	 * @return the most common ability
	 */
	public static String findMostCommonAbility(Dog[] dogs) {
		int servingTotal = 0;
		int herdingTotal = 0;
		int huntingTotal = 0;
		int protectingTotal = 0;

		// find instances of interfaces
		for (Dog dog : dogs) {
			if (dog instanceof Server) {
				servingTotal++;
			}
			if (dog instanceof Herder) {
				herdingTotal++;
			}
			if (dog instanceof Hunter) {
				huntingTotal++;
			}
			if (dog instanceof Protector) {
				protectingTotal++;
			}
		}

		// determine which is the max and return
		if (servingTotal >= herdingTotal &&
				servingTotal >= huntingTotal &&
				servingTotal >= protectingTotal) {
			return "Server";
		}
		if (herdingTotal >= servingTotal &&
				herdingTotal >= huntingTotal &&
				herdingTotal >= protectingTotal) {
			return "Herder";
		}
		if (huntingTotal >= servingTotal &&
				huntingTotal >= herdingTotal &&
				huntingTotal >= protectingTotal) {
			return "Hunter";
		}
		if (protectingTotal >= servingTotal &&
				protectingTotal >= herdingTotal &&
				protectingTotal >= huntingTotal) {
			return "Protector";
		} else {
			return "";
		}

	} // findMostCommonAbility
} // Dogs

/**
 * Interface for serving ability
 */
interface Server {
	public abstract int serve();
} // Server

/**
 * Interface for herding ability
 */
interface Herder {
	public abstract int herd();
} // Herder

/**
 * Interface for hunting ability
 */
interface Hunter {
	public abstract int hunt();
} // Hunter

/**
 * Interface for protecting ability
 */
interface Protector {
	public abstract int protect();
} // Protector

/**
 * The abstract Dog class creates a dog object.
 */
abstract class Dog {
	// instance variables
	private String name;
	private String breed;

	// getters
	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	// behavior
	public abstract String temperament();

} // Dog

/**
 * The BorderCollie class creates a border collie dog object.
 */
class BorderCollie extends Dog implements Server, Herder, Protector {
	// instance variables
	private int servingAbility;
	private int herdingAbility;
	private int protectingAbility;

	/**
	 * constructor that creates a border collie dog object
	 * 
	 * @param name
	 * @param servingAbility
	 * @param herdingAbility
	 * @param protectingAbility
	 */
	public BorderCollie(String name, int servingAbility, int herdingAbility, int protectingAbility) {
		setName(name);
		setBreed("Border Collie");
		this.servingAbility = servingAbility;
		this.herdingAbility = herdingAbility;
		this.protectingAbility = protectingAbility;
	}

	// behavior
	@Override
	public String temperament() {
		return "Intelligent, tenacious, energic";
	}

	@Override
	public int serve() {
		return servingAbility;
	}

	@Override
	public int herd() {
		return herdingAbility;
	}

	@Override
	public int protect() {
		return protectingAbility;
	}

} // BorderCollie

/**
 * The Boxer class creates a boxer dog object.
 */
class Boxer extends Dog implements Server, Herder, Hunter, Protector {
	// instance variables
	private int servingAbility;
	private int herdingAbility;
	private int huntingAbility;
	private int protectingAbility;

	/**
	 * constructor that creates a boxer dog object
	 * 
	 * @param name
	 * @param servingAbility
	 * @param herdingAbility
	 * @param huntingAbility
	 * @param protectingAbility
	 */
	public Boxer(String name, int servingAbility, int herdingAbility, int huntingAbility, int protectingAbility) {
		setName(name);
		setBreed("Boxer");
		this.servingAbility = servingAbility;
		this.herdingAbility = herdingAbility;
		this.huntingAbility = huntingAbility;
		this.protectingAbility = protectingAbility;
	}

	// behavior
	@Override
	public String temperament() {
		return "Fearless, confident, brave";
	}

	@Override
	public int serve() {
		return servingAbility;
	}

	@Override
	public int herd() {
		return herdingAbility;
	}

	@Override
	public int hunt() {
		return huntingAbility;
	}

	@Override
	public int protect() {
		return protectingAbility;
	}

} // Boxer

/**
 * The GermanSheppard class creates a german sheppard dog object.
 */
class GermanSheppard extends Dog implements Server, Herder, Protector {
	// instance variables
	private int servingAbility;
	private int herdingAbility;
	private int protectingAbility;

	/**
	 * constructor that creates a german sheppard dog object
	 * 
	 * @param name
	 * @param servingAbility
	 * @param herdingAbility
	 * @param protectingAbility
	 */
	public GermanSheppard(String name, int servingAbility, int herdingAbility, int protectingAbility) {
		setName(name);
		setBreed("German Sheppard");
		this.servingAbility = servingAbility;
		this.herdingAbility = herdingAbility;
		this.protectingAbility = protectingAbility;
	}

	// behavior
	@Override
	public String temperament() {
		return "Confident, watchful, courageous";
	}

	@Override
	public int serve() {
		return servingAbility;
	}

	@Override
	public int herd() {
		return herdingAbility;
	}

	@Override
	public int protect() {
		return protectingAbility;
	}

} // GermanSheppard

/**
 * The GoldenRetriever class creates a golden retriever dog object.
 */
class GoldenRetriever extends Dog implements Server, Hunter {
	// instance variables
	private int servingAbility;
	private int huntingAbility;

	/**
	 * constructor that creates a golden retriever dog object
	 * 
	 * @param name
	 * @param servingAbility
	 * @param huntingAbility
	 */
	public GoldenRetriever(String name, int servingAbility, int huntingAbility) {
		setName(name);
		setBreed("Golden Retriever");
		this.servingAbility = servingAbility;
		this.huntingAbility = huntingAbility;
	}

	// behavior
	@Override
	public String temperament() {
		return "Friendly, intelligent, devoted";
	}

	@Override
	public int serve() {
		return servingAbility;
	}

	@Override
	public int hunt() {
		return huntingAbility;
	}

} // GoldenRetriever