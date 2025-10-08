
/**
 * Decrypter.java
 * 
 * This file defines the Decrypter class, which allows you to encrypt
 * and decrypt messages using the Caesar cipher.
 * 
 * @author Christopher Romo
 * @since 7/19/2022
 * @version 1.0
 */

import java.util.*;

/**
 * The Decrypter class contains a main method that allows you to encrypt
 * and decrypt messages using the Caesar cipher.
 */
public class Decrypter {
    /**
     * displays intro message
     */
    public static void displayIntro() {
        System.out.println("Welcome to the Code Cracker!\n" +
                "You can encrypt or decrypt messages using the\n" +
                "Caesar cipher with this application.  Simply\n" +
                "select whether you want to encrypt or decrypt,\n" +
                "enter your message, and then enter the number\n" +
                "you want to use as the shift.");
    }

    /**
     * displays the menu
     */
    public static void displayMenu() {
        System.out.println("**************************************\n" +
                "Please make a selection:\n" +
                "1 - Encrypt a message with shift\n" +
                "2 - Decrypt a message with shift\n" +
                "3 - Decrypt a message with brute-force\n" +
                "4 - Quit\n" +
                "**************************************");
    }

    /**
     * prompts for a string
     * 
     * @param input  input scanner
     * @param prompt prompt message
     * @return the string entered
     */
    public static String promptForString(Scanner input, String prompt) {
        System.out.print(prompt);
        String message = input.nextLine();
        return message;
    }

    /**
     * prompts for an integer
     * 
     * @param input  input scanner
     * @param prompt prompt message
     * @return the integer entered
     */
    public static int promptForInteger(Scanner input, String prompt) {
        System.out.print(prompt);
        int integer = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        return integer;
    }

    /**
     * encrypts a single character
     * 
     * @param c character to encrypt
     * @param s shift value
     * @return the encrypted character
     */
    public static char encryptCharacter(char c, int s) {
        if (c >= 'a' && c <= 'z') {
            c += s;
            if ((c > 'z')) {
                c -= 26;
            }
        }
        if (c >= 'A' && c <= 'Z') {
            c += s;
            if ((c > 'Z')) {
                c -= 26;
            }
        }
        return c;
    }

    /**
     * decrypts a single character
     * 
     * @param c character to decrypt
     * @param s shift value
     * @return the decrypted character
     */
    public static char decryptCharacter(char c, int s) {
        if (c >= 'a' && c <= 'z') {
            c -= s;
            if ((c < 'a')) {
                c += 26;
            }
        }
        if (c >= 'A' && c <= 'Z') {
            c -= s;
            if ((c < 'A')) {
                c += 26;
            }
        }
        return c;
    }

    /**
     * encrypts a message
     * 
     * @param message message to encrypt
     * @param shift   shift value
     * @return the encrypted message
     */
    public static String encryptMessage(String message, int shift) {
        String encryptedMessage = "";
        int messageLength = message.length();
        for (int i = 0; i < messageLength; i++) {
            char letter = message.charAt(i);
            char encryptedLetter = encryptCharacter(letter, shift);
            encryptedMessage = encryptedMessage + encryptedLetter;
        }
        return encryptedMessage;
    }

    /**
     * decrypts a message
     * 
     * @param message message to decrypt
     * @param shift   shift value
     * @return the decrypted message
     */
    public static String decryptMessage(String message, int shift) {
        String decryptedMessage = "";
        int messageLength = message.length();
        for (int i = 0; i < messageLength; i++) {
            char letter = message.charAt(i);
            char decryptedLetter = decryptCharacter(letter, shift);
            decryptedMessage = decryptedMessage + decryptedLetter;
        }
        return decryptedMessage;
    }

    /**
     * decrypts a message by brute force
     * 
     * @param message message to decrypt
     */
    public static void decryptByBruteForce(String message) {
        for (int i = 1; i <= 25; i++) {
            String bruteMessage = decryptMessage(message, i);
            System.out.println(bruteMessage);
        }
    }

    /**
     * prompts for encryption
     * 
     * @param input input scanner
     */
    public static void handleEncrypt(Scanner input) {
        String message = promptForString(input, "Enter a message to encrypt:");
        int shift = promptForInteger(input, "Enter a shift value:");
        String encryptedMessage = encryptMessage(message, shift);
        System.out.println("Your message: " + message);
        System.out.println("Encrypts as:  " + encryptedMessage);
    }

    /**
     * prompts for decryption
     * 
     * @param input input scanner
     */
    public static void handleDecrypt(Scanner input) {
        String message = promptForString(input, "Enter a message to decrypt:");
        int shift = promptForInteger(input, "Enter a shift value:");
        String decryptedMessage = decryptMessage(message, shift);
        System.out.println("Your message: " + message);
        System.out.println("Decrypts as:  " + decryptedMessage);
    }

    /**
     * prompts for brute-force decryption
     * 
     * @param input input scanner
     */
    public static void handleBruteForce(Scanner input) {
        String message = promptForString(input, "Enter a message to brute-force:");
        System.out.println("Your message: " + message);
        System.out.println("Brute-force decrypts as:");
        decryptByBruteForce(message);
        System.out.println();
    }

    /**
     * Main method - allows you to encrypt and decrypt messages
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // prompt menu and get decision
        displayIntro();
        displayMenu();
        int decision = input.nextInt();
        if (input.hasNextLine())
            input.nextLine();

        // loop menu and prompt choices
        while (decision != 4) {
            switch (decision) {
                case 1:
                    handleEncrypt(input);
                    break;

                case 2:
                    handleDecrypt(input);
                    break;

                case 3:
                    handleBruteForce(input);
                    break;

                default:
                    System.out.println("Invalid");
                    break;

            }
            // loop the menu
            displayMenu();
            decision = input.nextInt();
            if (input.hasNextLine())
                input.nextLine();
        }
        if (decision == 4) {
            // end the program
            System.out.println("Thank you, goodbye!");
        }

        input.close();
    }
}
