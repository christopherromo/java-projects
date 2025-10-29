
/**
 * Beverage.java
 * 
 * This file defines the Beverage class, which creates a beverage object.
 * 
 * @author Christopher Romo
 * @since 08/02/2022
 * @version 1.0
 */

package src.pizzeria;

import java.util.Scanner;

/**
 * The Beverage class creates a beverage object.
 */
public class Beverage extends OrderItem {
    // instance variables
    String name;
    double cost;

    // prompts for details
    @Override
    public void promptForDetails(Scanner input) {
        System.out.println("What beverage do you want with your meal?");
        name = input.nextLine();
    }

    // calculates cost
    @Override
    public double calculateCost() {
        cost = 1.50;
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
        return String.format("%-30s%10.2f\n", nameToTitleCase(), calculateCost());
        
    }
} // Beverage