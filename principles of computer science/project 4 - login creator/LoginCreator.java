
/**
 * LoginCreator.java
 * 
 * This file defines the LoginCreator class, which prompts input
 * for a username and password and checks if they are valid.
 * 
 * @author Christopher Romo
 * @since 6/27/2022
 * @version 1.0
 */

import java.util.*;

/**
 * The LoginCreator class contains a main method that prompts input
 * for a username and password and checks if they are valid.
 */
public class LoginCreator {
    /**
     * Main method - prompts input for a username and password and checks if they
     * are valid.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // welcome user to the password checker and explain parameters
        System.out.println("Welcome to the Password Checker!");
        System.out.println("Your username must be at least 8 characters and\n" +
                "no more than 12.");
        System.out.println("Your password cannot contain your username.\n" +
                "It must contain at least one of the following\n" +
                "special characters: !, @, #, $, or &.  It must\n" +
                "be at least 8 characters long and no more than 16");

        // prompt for username
        System.out.println("Please enter your username: ");
        String username = input.nextLine().toLowerCase();
        int usernameLength = username.length();
        boolean isValidUsernameLength;
        isValidUsernameLength = usernameLength >= 8 && usernameLength <= 12;

        // prompt for password
        // length of password
        System.out.println("Please enter your password: ");
        String password = input.nextLine().toLowerCase();
        int passwordLength = password.length();
        boolean isValidPasswordLength;
        isValidPasswordLength = passwordLength >= 8 && passwordLength <= 16;

        // contains special character
        boolean containsE = (password.contains("!"));
        boolean containsA = (password.contains("@"));
        boolean containsH = (password.contains("#"));
        boolean containsD = (password.contains("$"));
        boolean containsN = (password.contains("&"));
        boolean containsSpecial = containsE || containsA || containsH
                || containsD || containsN;

        // contains username
        boolean containsUsername = password.contains(username)
                || password.equalsIgnoreCase(username);

        // check if valid password
        boolean validPassword = !containsUsername && containsSpecial && isValidPasswordLength;

        // check both to see if they are valid and print result
        if (!validPassword || !isValidUsernameLength) {
            if (!isValidUsernameLength) {
                System.out.println("Your username must be between 8 and 12 characters.");
            }

            if (!isValidPasswordLength) {
                System.out.println("Your password must be between 8 and 16 characters.");
            }

            if (containsUsername) {
                System.out.println("Your password cannot contain your username.");
            }

            if (!containsSpecial) {
                System.out.println("Your password must contain one of: ! @ # $ &");
            }

            System.out.println("Sorry, your username or password is invalid.\nPlease try again...");
        } else {
            System.out.println("Congratulations, your username and password are valid!  You may now login...");
        }

        input.close();
    }
}
