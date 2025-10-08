
/**
 * Salad.java
 * 
 * This file defines the Salad class, which creates a salad object.
 * 
 * @author Christopher Romo
 * @since 8/2/2022
 * @version 1.0
 */

package src.pizzeria;

import java.util.Scanner;

/**
 * The Salad class creates a salad object.
 */
public class Salad extends OrderItem {
    // instance variables
    String name;
    double cost;

    // prompts for details
    @Override
    public void promptForDetails(Scanner input) {
        System.out.println("What dressing do you want on your salad?");
        name = input.nextLine();
    }

    // calculates cost
    @Override
    public double calculateCost() {
        cost = 7.50;
        return cost;
    }

    // converts name to title case
    private String nameToTitleCase() {
        return String.valueOf(Character.toUpperCase(name.charAt(0)))
                + name.substring(1, name.length());
    }

    // toString
    @Override
    public String toString() {
        return String.format("%-30s%10.2f\n",
                "Salad with " + nameToTitleCase() + " dressing",
                calculateCost());
    }
}