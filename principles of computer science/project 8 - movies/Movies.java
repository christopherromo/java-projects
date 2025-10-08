
/**
 * Movies.java
 * 
 * This file defines the Movies class, which keeps track and manages a movie collection.
 * 
 * @author Christopher Romo
 * @since 7/21/2022
 * @version 1.0
 */

import java.util.*;

/**
 * The Movies class contains a main method that keeps track and manages a movie
 * collection.
 */
public class Movies {
    /**
     * displays the menu
     */
    public static void displayMenu() {
        System.out.println("Please make a selection:\n" +
                "1 - Add a new movie\n" +
                "2 - Update a movie\n" +
                "3 - Remove a movie\n" +
                "4 - Print entire collection\n" +
                "5 - Quit");
    }

    /**
     * add a movie to the array
     * 
     * @param input    input scanner
     * @param movies   array of movies
     * @param movieNbr number of movies
     * @return the updated array of movies
     */
    public static String[] addMovie(Scanner input, String[] movies, int movieNbr) {
        System.out.println("Please enter a title: ");
        movies[movieNbr] = input.nextLine();
        return movies;
    }

    /**
     * change a movie in the array
     * 
     * @param input    input scanner
     * @param movies   array of movies
     * @param movieNbr number of movies
     * @return the updated array of movies
     */
    public static String[] changeMovie(Scanner input, String[] movies, int movieNbr) {
        System.out.println("Please enter a title: ");
        String message = input.nextLine();
        System.out.println("Please enter a new title: ");
        String newMessage = input.nextLine();

        for (int i = 0; i < movieNbr; i++) {
            if (message.equals(movies[i])) {
                movies[i] = newMessage;
            }
        }
        return movies;
    }

    /**
     * print the movies in the array
     * 
     * @param movies   array of movies
     * @param movieNbr number of movies
     */
    public static void printMovies(String[] movies, int movieNbr) {
        for (int i = 0; i < movieNbr; i++) {
            System.out.println(movies[i]);
        }
    }

    /**
     * Main method - keeps track and manages a movie collection.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // prompt menu and track decision
        displayMenu();
        int decision = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        // initialize variables
        int movieNbr = 0;
        String[] movies = new String[100];

        // react to choices
        while (decision != 5) {
            switch (decision) {
                case 1:
                    System.out.println("You've chosen to add a movie");
                    movies = addMovie(input, movies, movieNbr);
                    movieNbr++;
                    break;

                case 2:
                    System.out.println("You've chosen to update a movie");
                    movies = changeMovie(input, movies, movieNbr);
                    break;

                case 3:
                    System.out.println("You've chosen to remove a movie");
                    System.out.println("Please enter a title: ");
                    String message = input.nextLine();
                    boolean isValid = false;

                    for (int i = 0; i < movieNbr; i++) {
                        if (message.equals(movies[i])) {
                            isValid = true;
                            for (int j = i; j < movieNbr - 1; j++) {
                                movies[i] = movies[i + 1];
                                i++;

                            }
                            break;
                        }
                    }
                    if (isValid) {
                        movieNbr--;
                    }
                    break;

                case 4:
                    System.out.println("You've chosen to print your collection");
                    printMovies(movies, movieNbr);
                    break;

                default:
                    System.out.println("Invalid response, try again.");
                    break;
            }
            displayMenu();
            decision = input.nextInt();
            if (input.hasNextLine())
                input.nextLine();
        }
        if (decision == 5) {
            // exit program
            System.out.println("You've chosen to exit the application");
            System.out.println("Goodbye!");
        }

        input.close();
    }
}
