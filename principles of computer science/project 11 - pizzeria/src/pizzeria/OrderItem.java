
/**
 * OrderItem.java
 * 
 * This file defines the OrderItem class, which creates an OrderItem object.
 * 
 * @author Christopher Romo
 * @since 08/02/2022
 * @version 1.0
 */

package src.pizzeria;

import java.util.Scanner;

/**
 * The OrderItem class creates an order item object.
 */
abstract class OrderItem {
    // prompts for details
    public abstract void promptForDetails(Scanner input);

    // calculates cost
    public abstract double calculateCost();

} // OrderItem
