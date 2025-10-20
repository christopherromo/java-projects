
/**
 * TourDeTurtles.java
 * 
 * This file defines the TourDeTurtles class, which reads in turtle data from a file,
 * creates SeaTurtle objects, and prints out their details along with tour information.
 * 
 * @author Christopher Romo
 * @since 9/8/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The TourDeTurtles class contains a main method that reads in turtle data from
 * a file, creates SeaTurtle objects, and prints out their details along with
 * tour information.
 */
public class TourDeTurtles {
    /**
     * Main method - reads in turtle data from a file, creates SeaTurtle objects,
     * and prints out their details along with tour information.
     * 
     * @param args command line arguments
     * @throws IOException if file not found
     */
    public static void main(String[] args) throws IOException {
        // open file
        File inputFileName = new File("input/data structures and algorithms input/tourdeturtles_input.txt");
        Scanner inputFile = new Scanner(inputFileName);

        // declare variables
        int counter = 0;
        int turtleCount = inputFile.nextInt();
        SeaTurtle[] turtles = new SeaTurtle[turtleCount];

        // read file and copy into array
        while (inputFile.hasNext()) {
            String type = inputFile.next();
            double milesTraveled = inputFile.nextDouble();
            int daysTracked = inputFile.nextInt();
            int tourYear = inputFile.nextInt();
            String name = inputFile.nextLine();

            switch (type) {
                case "hawkbill":
                    turtles[counter] = new Hawksbill(name, daysTracked, milesTraveled, tourYear);
                    counter++;
                    break;

                case "loggerhead":
                    turtles[counter] = new Loggerhead(name, daysTracked, milesTraveled, tourYear);
                    counter++;
                    break;

                case "greenturtle":
                    turtles[counter] = new GreenTurtle(name, daysTracked, milesTraveled, tourYear);
                    counter++;
                    break;

                case "leatherback":
                    turtles[counter] = new Leatherback(name, daysTracked, milesTraveled, tourYear);
                    counter++;
                    break;

                default:
                    break;
            }
        }
        inputFile.close();

        // print out tracked turtles
        System.out.println("Tracked Turtles\n" +
                "-------------------------------------------------------------------------------------\n" +
                " Name   	Type		Days	Miles	Threats to Survival\n" +
                "    	    			Tracked Traveled\n" +
                "-------------------------------------------------------------------------------------");
        for (int i = 0; i < turtles.length; i++) {
            String result = String.format("%-15s	%s	%d	%.1f	%s", turtles[i].getName(), turtles[i].getType(),
                    turtles[i].getDaysTracked(), turtles[i].getMilesTraveled(), turtles[i].threatsToSurvival());
            System.out.println(result);
        }

        // setup and print the tour for 2021
        Tour firstTour = new Tour(2021);
        firstTour.setupTour(turtles);
        firstTour.printTourDetails();

    } // main
} // TourDeTurtles

/**
 * The SeaTurtle class creates a sea turtle object.
 */
class SeaTurtle {
    // instance variables
    private String type;
    private String name;
    private int daysTracked;
    private double milesTraveled;
    private int tourYear;

    /**
     * constructor that creates a sea turtle object
     * 
     * @param type
     * @param name
     * @param daysTracked
     * @param milesTraveled
     * @param tourYear
     */
    public SeaTurtle(String type, String name, int daysTracked, double milesTraveled, int tourYear) {
        this.type = type;
        this.name = name;
        this.daysTracked = daysTracked;
        this.milesTraveled = milesTraveled;
        this.tourYear = tourYear;
    }

    // getters
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getDaysTracked() {
        return daysTracked;
    }

    public double getMilesTraveled() {
        return milesTraveled;
    }

    public int getTourYear() {
        return tourYear;
    }

    // behavior
    public String threatsToSurvival() {
        return "I am a turtle!";
    }
} // SeaTurtle

/**
 * The Hawksbill class creates a hawksbill turtle object.
 */
class Hawksbill extends SeaTurtle {
    /**
     * constructor that creates a hawksbill turtle object
     * 
     * @param name
     * @param daysTracked
     * @param milesTraveled
     * @param tourYear
     */
    public Hawksbill(String name, int daysTracked, double milesTraveled, int tourYear) {
        super("Hawksbill", name, daysTracked, milesTraveled, tourYear);
    }

    // behavior
    @Override
    public String threatsToSurvival() {
        return "Harvesting of their shell";
    }
} // Hawksbill

/**
 * The Loggerhead class creates a loggerhead turtle object.
 */
class Loggerhead extends SeaTurtle {
    /**
     * constructor that creates a loggerhead turtle object
     * 
     * @param name
     * @param daysTracked
     * @param milesTraveled
     * @param tourYear
     */
    public Loggerhead(String name, int daysTracked, double milesTraveled, int tourYear) {
        super("Loggerhead", name, daysTracked, milesTraveled, tourYear);
    }

    // behavior
    @Override
    public String threatsToSurvival() {
        return "Loss of nesting habitat";
    }
} // Loggerhead

/**
 * The GreenTurtle class creates a green turtle object.
 */
class GreenTurtle extends SeaTurtle {
    /**
     * constructor that creates a green turtle object
     * 
     * @param name
     * @param daysTracked
     * @param milesTraveled
     * @param tourYear
     */
    public GreenTurtle(String name, int daysTracked, double milesTraveled, int tourYear) {
        super("Green Turtle", name, daysTracked, milesTraveled, tourYear);
    }

    // behavior
    @Override
    public String threatsToSurvival() {
        return "Commercial harvest for eggs & food";
    }
} // GreenTurtle

/**
 * The Leatherback class creates a leatherback turtle object.
 */
class Leatherback extends SeaTurtle {
    /**
     * constructor that creates a leatherback turtle object
     * 
     * @param name
     * @param daysTracked
     * @param milesTraveled
     * @param tourYear
     */
    public Leatherback(String name, int daysTracked, double milesTraveled, int tourYear) {
        super("Leatherback", name, daysTracked, milesTraveled, tourYear);
    }

    // behavior
    @Override
    public String threatsToSurvival() {
        return "Plastic bag mistaken for jellyfish";
    }
} // Leatherback

/**
 * The Tour class creates a tour object.
 */
class Tour {
    // instance variables
    private int tourYear;
    private int numTurtles;
    private int counter;
    private SeaTurtle[] turtlesToTrack;

    /**
     * constructor that creates a tour object
     * 
     * @param tourYear
     */
    public Tour(int tourYear) {
        this.tourYear = tourYear;
    }

    // behavior

    /**
     * sets up the tour for the given year
     * 
     * @param turtles
     */
    public void setupTour(SeaTurtle[] turtles) {
        // find number of turtles
        for (SeaTurtle turtle : turtles) {
            if (turtle.getTourYear() == tourYear) {
                numTurtles++;
            }
        }

        // declare array
        turtlesToTrack = new SeaTurtle[numTurtles];

        // add turtles to the array
        for (int i = 0; i < turtles.length; i++) {
            if (turtles[i].getTourYear() == tourYear) {
                turtlesToTrack[counter] = turtles[i];
                counter++;
            }
        }
    }

    /**
     * prints the tour details
     */
    public void printTourDetails() {
        System.out.println("-----------------------------------\n" +
                "Tour de Turtles\n" +
                "-----------------------------------");
        System.out.println("Tour de Turtles year: " + tourYear);
        System.out.println("Number of turtles in tour: " + numTurtles);
        System.out.println();

        for (int i = 0; i < turtlesToTrack.length; i++) {
            String result = String.format("%-15s	---%.1f", turtlesToTrack[i].getName(),
                    turtlesToTrack[i].getMilesTraveled());
            System.out.println(result);
        }
    }
} // Tour