
/**
 * Pizza.java
 * 
 * This file defines the Pizza class, which creates a pizza object.
 * 
 * @author Christopher Romo
 * @since 8/2/2022
 * @version 1.0
 */

package src.pizzeria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Pizza class creates a pizza object.
 */
public class Pizza extends OrderItem {
    // instance variables
    double cost;
    double toppingCost;
    ArrayList<String> toppings = new ArrayList<String>();

    // prompts for details
    @Override
    public void promptForDetails(Scanner input) {
        System.out.println("What toppings do you want on your pizza? (. to end)");
        String topping = input.nextLine();
        while (!topping.equals(".")) {
            toppings.add(topping);
            System.out.println("What else do you want on your pizza? (. to end)");
            topping = input.nextLine();
        }
    }

    // calculates cost
    @Override
    public double calculateCost() {
        toppingCost = 0.50;
        cost = 10.0 + toppingCost * toppings.size();
        return cost;
    }

    // toString
    @Override
    public String toString() {
        String result = "";
        StringBuilder pizza = new StringBuilder();
        pizza.append(String.format("%-30s%10.2f\n",
                "Pizza", calculateCost()));

        for (String topping : toppings) {
            pizza.append("   ").append(topping).append("\n");
        }

        result = pizza.toString();

        return result;
    }
}