
/**
 * Music.java
 * 
 * This file defines the Music class, which allows you to manage
 * a music album collection.
 * 
 * @author Christopher Romo
 * @since 7/24/2022
 * @version 1.0
 */

package src.music;

import java.util.*;

/**
 * The Music class contains a main method that allows you to manage
 * a music album collection.
 */
public class Music {
    /**
     * displays the menu
     */
    public static void displayMenu() {
        System.out.println("Please make a selection:\n" +
                "1 - Add a new album\n" +
                "2 - Update an album\n" +
                "3 - Remove an album\n" +
                "4 - Print entire collection\n" +
                "5 - Print by artist\n" +
                "6 - Print by year\n" +
                "7 - Quit");
    }

    /**
     * adds an album to the collection
     * 
     * @param input       input scanner
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     * @return the updated array of albums
     */
    public static Album[] addAlbum(Scanner input, Album[] albums, int nbrOfAlbums) {
        System.out.println("Please enter a title: ");
        String title = input.nextLine();
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        boolean isValidYear = year >= 1900 && year <= 2100;
        if (!isValidYear) {
            while (!isValidYear) {
                System.out.println("Invalid response, try again.");
                year = input.nextInt();
                isValidYear = year >= 1900 && year <= 2100;
            }
        }
        if (input.hasNextLine())
            input.nextLine();

        albums[nbrOfAlbums] = new Album(title, artist, year);
        return albums;
    }

    /**
     * changes an existing album in the collection
     * 
     * @param input       input scanner
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     * @return the updated array of albums
     */
    public static Album[] changeAlbum(Scanner input, Album[] albums, int nbrOfAlbums) {
        System.out.println("Please enter a title to change: ");
        String originalTitle = input.nextLine();
        System.out.println("Please enter a title: ");
        String newTitle = input.nextLine();
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        boolean isValidYear = year >= 1900 && year <= 2100;
        if (!isValidYear) {
            while (!isValidYear) {
                System.out.println("Invalid response, try again.");
                year = input.nextInt();
                isValidYear = year >= 1900 && year <= 2100;
            }
        }
        if (input.hasNextLine())
            input.nextLine();

        for (int i = 0; i < nbrOfAlbums; i++) {
            if (originalTitle.equals(albums[i].getTitle())) {
                albums[i].setTitle(newTitle);
                albums[i].setArtist(artist);
                albums[i].setYear(year);
            }
        }
        return albums;
    }

    /**
     * deletes an album from the collection
     * 
     * @param input       input scanner
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     * @return the updated array of albums
     */
    public static Album[] deleteAlbum(Scanner input, Album[] albums, int nbrOfAlbums) {
        System.out.println("Please enter a title: ");
        String title = input.nextLine();

        for (int i = 0; i < nbrOfAlbums; i++) {
            if (title.equals(albums[i].getTitle())) {
                // isValid = true;
                for (int j = i; j < nbrOfAlbums - 1; j++) {
                    albums[i] = albums[i + 1];
                    i++;
                }
                break;
            }
        }
        return albums;
    }

    /**
     * prints the entire collection
     * 
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     */
    public static void printCollection(Album[] albums, int nbrOfAlbums) {
        for (int i = 0; i < nbrOfAlbums; ++i) {
            albums[i].print();
        }
    }

    /**
     * prints the albums by artist
     * 
     * @param input       input scanner
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     */
    public static void printByArtist(Scanner input, Album[] albums, int nbrOfAlbums) {
        System.out.println("Please enter an artist: ");
        String artist = input.nextLine();

        for (int i = 0; i < nbrOfAlbums; ++i) {
            if (artist.equals(albums[i].getArtist())) {
                albums[i].print();
            }
        }
    }

    /**
     * prints the albums by year
     * 
     * @param input       input scanner
     * @param albums      array of albums
     * @param nbrOfAlbums number of albums
     */
    public static void printByYear(Scanner input, Album[] albums, int nbrOfAlbums) {
        System.out.println("Please enter a year: ");
        int year = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        for (int i = 0; i < nbrOfAlbums; ++i) {
            if (year == albums[i].getYear()) {
                albums[i].print();
            }
        }
    }

    /**
     * Main method - allows you to manage a music album collection.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // initialize variables
        int nbrOfAlbums = 0;
        Album[] albums = new Album[100];

        // scan for decision
        displayMenu();
        int decision = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        // carry out decision
        while (decision != 7) {
            switch (decision) {
                case 1:
                    albums = addAlbum(input, albums, nbrOfAlbums);
                    nbrOfAlbums++;
                    break;

                case 2:
                    albums = changeAlbum(input, albums, nbrOfAlbums);
                    break;

                case 3:
                    albums = deleteAlbum(input, albums, nbrOfAlbums);
                    nbrOfAlbums--;
                    break;

                case 4:
                    printCollection(albums, nbrOfAlbums);
                    break;

                case 5:
                    printByArtist(input, albums, nbrOfAlbums);
                    break;

                case 6:
                    printByYear(input, albums, nbrOfAlbums);
                    break;

                default:
                    System.out.println("Invalid response, try again.");
                    break;
            }
            // loop the menu
            if (decision >= 1 && decision <= 6) {
                displayMenu();
            }
            decision = input.nextInt();
            if (input.hasNextLine())
                input.nextLine();
        }
        if (decision == 7) {
            // exit program
            System.out.println("Goodbye!");
        }

        input.close();
    }
}
