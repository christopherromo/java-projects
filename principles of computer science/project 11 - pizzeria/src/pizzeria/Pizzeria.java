
/**
 * Pizzeria.java
 * 
 * This file defines the Pizzeria class, which takes a customer's order.
 * 
 * @author Christopher Romo
 * @since 8/2/2022
 * @version 1.0
 */

package src.pizzeria;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Pizzeria class contains a main method that takes a customer's order
 * and prints a receipt.
 */
public class Pizzeria {
    // menu string
    public static String menu = "****************************************\n"
            + "Mamma Mia's Menu:\n"
            + "1 - Beverage (Soda, Ice Tea, Water, etc.)\n"
            + "2 - Salad (w/Ranch, Italian, French, etc.)\n"
            + "3 - Pizza (w/Pepperoni, Sausage, Onions, etc.)\n"
            + "4 - Checkout\n"
            + "****************************************\n"
            + "May I take your order?";

    /**
     * prints a receipt
     * 
     * @param items array list of items
     */
    public static void printReceipt(ArrayList<OrderItem> items) {
        System.out.println("****************************************");
        System.out.println("Your Receipt:");
        double sum = 0.0;

        for (OrderItem item : items) {
            System.out.print(item);
            sum += item.calculateCost();
        }

        System.out.printf("%-30s%10s\n", "", "----------");
        System.out.printf("%-30s%10.2f\n", "Subtotal:", sum);
        System.out.printf("%-30s%10.2f\n", "Tax:", sum * .085);
        System.out.printf("%-30s%10.2f\n", "Total:", sum * 1.085);
        System.out.println("****************************************");
    }

    /**
     * adds a new item to the array list
     * 
     * @param input    input scanner
     * @param items    array list of items
     * @param decision item choice
     */
    public static void addNewItem(Scanner input, ArrayList<OrderItem> items, int decision) {
        // makes a new object and stores in array
        if (decision == 1) {
            OrderItem orderItem = new Beverage();
            orderItem.promptForDetails(input);
            items.add(orderItem);
        } else if (decision == 2) {
            OrderItem orderItem = new Salad();
            orderItem.promptForDetails(input);
            items.add(orderItem);
        } else if (decision == 3) {
            OrderItem orderItem = new Pizza();
            orderItem.promptForDetails(input);
            items.add(orderItem);
        }
    }

    /**
     * Main method - simulates a pizzeria ordering system.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // initialize variables
        Scanner input = new Scanner(System.in);
        int response;
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();

        // print menu
        System.out.println("Welcome to Mamma Mia's Pizzeria!");

        // loop choices and take order
        do {
            System.out.println(menu);
            int decision;
            response = Integer.parseInt(input.nextLine());

            switch (response) {
                case 1:
                    decision = 1;
                    addNewItem(input, items, decision);
                    break;
                case 2:
                    decision = 2;
                    addNewItem(input, items, decision);
                    break;
                case 3:
                    decision = 3;
                    addNewItem(input, items, decision);
                    break;
            }
        } while (response != 4);

        printReceipt(items);

        System.out.println("Thank you for coming!");
        input.close();
    }
}
