
/**
 * MusicWithFiles.java
 * 
 * This file defines the MusicWithFiles class, which allows you to manage
 * a music album collection (now with file input/output).
 * 
 * @author Christopher Romo
 * @since 7/31/2022
 * @version 1.0
 */

package src.musicwithfiles;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The MusicWithFiles class contains a main method that allows you to manage
 * a music album collection (now with file input/output).
 */
public class MusicWithFiles {
    /**
     * displays the menu
     */
    public static void displayMenu() {
        System.out.println("Please make a selection:\n" +
                "1 - Add an album\n" +
                "2 - Update an album\n" +
                "3 - Remove an album\n" +
                "4 - Print entire collection\n" +
                "5 - Print by artist\n" +
                "6 - Print by year\n" +
                "7 - Load the collection from file\n" +
                "8 - Save the collection to file\n" +
                "9 - Quit");
    }

    /**
     * removes leading spaces from a phrase
     * 
     * @param phrase the phrase to remove leading spaces from
     * @return the phrase without leading spaces
     */
    public static String removeLeadingSpace(String phrase) {
        while (phrase.length() > 0 && phrase.charAt(0) == ' ') {
            phrase = phrase.substring(1);
        }
        return phrase;
    }

    /**
     * adds an album to the collection
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void addAlbum(Scanner input, ArrayList<Album> albums) {
        System.out.println("Please enter a title: ");
        String title = input.nextLine();
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        boolean isValidYear = year >= 1877 && year <= 2020;
        if (!isValidYear) {
            while (!isValidYear) {
                System.out.println("Invalid response, try again.");
                year = input.nextInt();
                isValidYear = year >= 1877 && year <= 2020;
            }
        }
        if (input.hasNextLine())
            input.nextLine();

        albums.add(new Album(title, artist, year));
    }

    /**
     * changes an existing album in the collection
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void changeAlbum(Scanner input, ArrayList<Album> albums) {
        System.out.println("Please enter a title: ");
        String originalTitle = input.nextLine();
        System.out.println("Please enter a title: ");
        String newTitle = input.nextLine();
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        boolean isValidYear = year >= 1877 && year <= 2020;
        if (!isValidYear) {
            while (!isValidYear) {
                System.out.println("Invalid response, try again.");
                year = input.nextInt();
                isValidYear = year >= 1877 && year <= 2020;
            }
        }
        if (input.hasNextLine())
            input.nextLine();

        for (Album album : albums) {
            if (originalTitle.equals(album.getTitle())) {
                album.setTitle(newTitle);
                album.setArtist(artist);
                album.setYear(year);
            }
        }
    }

    /**
     * deletes an album from the collection
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void deleteAlbum(Scanner input, ArrayList<Album> albums) {
        System.out.println("Please enter a title: ");
        String title = input.nextLine();

        for (Album album : albums) {
            if (title.equals(album.getTitle())) {
                albums.remove(album);
                break;
            }
        }
    }

    /**
     * prints the entire collection
     * 
     * @param albums array list of albums
     */
    public static void printCollection(ArrayList<Album> albums) {
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }

    /**
     * prints the albums by artist
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void printByArtist(Scanner input, ArrayList<Album> albums) {
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();

        for (Album album : albums) {
            if (artist.equals(album.getArtist())) {
                System.out.println(album.toString());
            }
        }
    }

    /**
     * prints the albums by year
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void printByYear(Scanner input, ArrayList<Album> albums) {
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        for (Album album : albums) {
            if (year == album.getYear()) {
                System.out.println(album.toString());
            }
        }
    }

    /**
     * reads in a file and adds to array list
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void readInFile(Scanner input, ArrayList<Album> albums) {
        Scanner inputFile = null;
        System.out.println("Now loading musicwithfiles_input.txt...");

        try {
            File inputFileName = new File("input/musicwithfiles_input.txt");
            inputFile = new Scanner(inputFileName);

            while (inputFile.hasNext()) {
                inputFile.next();
                String title = removeLeadingSpace(inputFile.nextLine());
                inputFile.next();
                String artist = removeLeadingSpace(inputFile.nextLine());
                inputFile.next();
                int year = inputFile.nextInt();

                albums.add(new Album(title, artist, year));
            }
        } catch (FileNotFoundException ex) {
            System.out.format("File %s not found.\n", "input/musicwithfiles_input.txt");
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
        }
    }

    /**
     * outputs the current users library to a new file
     * 
     * @param input  input scanner
     * @param albums array list of albums
     */
    public static void outputFile(Scanner input, ArrayList<Album> albums) {
        PrintWriter outputFile = null;
        System.out.println("Now saving musicwithfiles_output.txt...");
        String fileName = "output/musicwithfiles_output.txt";
        try {
            File outputFileName = new File(fileName);
            outputFile = new PrintWriter(outputFileName);

            for (Album album : albums) {
                outputFile.println(album.toString());
            }
        } catch (FileNotFoundException ex) {
            System.out.format("File %s not found.\n", fileName);
        } finally {
            if (outputFile != null) {
                outputFile.close();
            }
        }
    }

    /**
     * Main method - allows you to manage a music album collection (now with file
     * input/output).
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // initialize array list
        ArrayList<Album> albums = new ArrayList<Album>();

        // scan for decision
        displayMenu();
        int decision = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        // carry out decision
        while (decision != 9) {
            switch (decision) {
                case 1:
                    addAlbum(input, albums);
                    break;

                case 2:
                    changeAlbum(input, albums);
                    break;

                case 3:
                    deleteAlbum(input, albums);
                    break;

                case 4:
                    printCollection(albums);
                    break;

                case 5:
                    printByArtist(input, albums);
                    break;

                case 6:
                    printByYear(input, albums);
                    break;

                case 7:
                    readInFile(input, albums);
                    break;

                case 8:
                    outputFile(input, albums);
                    break;

                default:
                    System.out.println("Invalid response, try again.");
                    break;
            }
            // loop the menu
            if (decision >= 1 && decision <= 8) {
                displayMenu();
            }
            decision = input.nextInt();
            if (input.hasNextLine())
                input.nextLine();
        }
        if (decision == 9) {
            // exit program
            System.out.println("Goodbye!");
            input.close();
        }
    }
}
