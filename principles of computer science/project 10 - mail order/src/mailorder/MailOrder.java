
/**
 * MailOrder.java
 * 
 * This file defines the MailOrder class, which uses various classes to
 * simulate a mail order system.
 * 
 * @author Christopher Romo
 * @since 7/28/2022
 * @version 1.0
 */

package src.mailorder;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The MailOrder class contains a main method that simulates
 * a mail order system.
 */
public class MailOrder {
    // menu string
    public static final String menu = "----------------------------------\n"
            + "1 - Add a Customer\n"
            + "2 - Print all Customers\n"
            + "3 - Create an Order\n"
            + "4 - Print all Orders\n"
            + "5 - Print all Orders Shipped to a Zipcode\n"
            + "6 - Quit\n"
            + "----------------------------------\n"
            + "Please make a selection:";

    /**
     * prompts for an integer
     * 
     * @param input   input scanner
     * @param message message to display
     * @param min     minimum
     * @param max     maximum
     * @return the integer entered
     */
    public static int promptForInteger(Scanner input, String message, int min, int max) {
        System.out.println(message);
        int value = Integer.parseInt(input.nextLine());

        while (value < min || max < value) {
            System.out.println(message);
            value = Integer.parseInt(input.nextLine());
        }
        return value;
    }

    /**
     * prompts for a double
     * 
     * @param input   input scanner
     * @param message message to display
     * @param min     minimum
     * @param max     maximum
     * @return the double entered
     */
    public static double promptForDouble(Scanner input, String message, double min, double max) {
        System.out.println(message);
        double value = Double.parseDouble(input.nextLine());

        while (value < min || max < value) {
            System.out.println(message);
            value = Double.parseDouble(input.nextLine());
        }
        return value;
    }

    /**
     * prompts for a string
     * 
     * @param input   input scanner
     * @param message message to display
     * @return the string entered
     */
    public static String promptForString(Scanner input, String message) {
        System.out.println(message);
        String value = input.nextLine();

        while (value.length() == 0) {
            System.out.println(message);
            value = input.nextLine();
        }
        return value;
    }

    /**
     * prompts for an address
     * 
     * @param input input scanner
     * @return the address entered
     */
    public static Address promptForAddress(Scanner input) {
        System.out.println("Please enter the street address:");
        String streetAddress = input.nextLine();

        System.out.println("Please enter the city, state, and zip. MUST be in form: ");
        System.out.println("City, ST 12345");
        String cityStateZip = input.nextLine();

        return Address.parse(streetAddress, cityStateZip);
    }

    /**
     * adds a customer to the array list
     * 
     * @param input     input scanner
     * @param customers array list of customers
     */
    public static void addCustomer(Scanner input, ArrayList<Customer> customers) {
        String fullName = promptForString(input,
                "Please enter the customer's full name (MUST be first and last name, middle name optional):");

        Address address = promptForAddress(input);

        customers.add(new Customer(fullName, address));
    }

    /**
     * finds a customer by their number
     * 
     * @param customers array list of customers
     * @param number    customer number
     * @return the customer with that number
     */
    public static Customer findCustomerByNumber(ArrayList<Customer> customers, int number) {
        for (Customer customer : customers) {
            if (customer.getCustomerNbr() == number)
                return customer;
        }
        return null;
    }

    /**
     * creates an order
     * 
     * @param input     input scanner
     * @param customers array list of customers
     * @param orders    array list of orders
     * @param orderNbr  order number
     */
    public static void createOrder(Scanner input, ArrayList<Customer> customers, ArrayList<Order> orders,
            int orderNbr) {
        int customerNbr = promptForInteger(input,
                "Please enter the customer's number for the order:",
                0, Customer.getNbrOfCustomers() - 1);

        String shippingAddress = promptForString(input, "Please enter the street address:");
        String cityStateZip = promptForString(input, "Please enter the city, state, and zip. MUST be in form: \n"
                + "City, ST 12345");

        Address addressShip = Address.parse(shippingAddress, cityStateZip);
        Customer customerDemo = findCustomerByNumber(customers, customerNbr);

        orders.add(new Order(customerDemo, addressShip));

        String response;
        do {
            String itemName = promptForString(input,
                    "Please enter the name of the item:");
            double itemPrice = promptForDouble(input,
                    "Please enter the price of the " + itemName + ":",
                    0, Double.MAX_VALUE);

            OrderItem item = new OrderItem(itemName, itemPrice);
            orders.get(orderNbr).addItem(item);

            response = promptForString(input,
                    "Would you like to enter another item? (y/n)");
        } while (response.equals("y") || response.equals("Y"));
    }

    /**
     * prints all customers
     * 
     * @param customers array list of customers
     */
    public static void printAllCustomers(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("************************");
            System.out.println(customer);
        }
    }

    /**
     * prints all orders
     * 
     * @param orders array list of orders
     */
    public static void printAllOrders(ArrayList<Order> orders) {
        for (Order order : orders) {
            System.out.println("************************");
            System.out.println(order);
        }
    }

    /**
     * prints all orders in a given zip code
     * 
     * @param input  input scanner
     * @param orders array list of orders
     */
    public static void printOrdersInZip(Scanner input, ArrayList<Order> orders) {
        int zip = promptForInteger(input, "Please enter the shipping zip code:",
                10000, 99999);

        for (Order order : orders) {
            if (order.getZip() == zip) {
                System.out.println("************************");
                System.out.println(order);
            }
        }
    }

    /**
     * Main method - simulates a mail order system.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // declare variables
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Order> orders = new ArrayList<Order>();
        int orderNbr = 0;

        // preform loop for menu
        int response;
        do {
            response = promptForInteger(input, menu, 1, 6);

            switch (response) {
                case 1:
                    addCustomer(input, customers);
                    break;
                case 2:
                    printAllCustomers(customers);
                    break;
                case 3:
                    createOrder(input, customers, orders, orderNbr);
                    orderNbr++;
                    break;
                case 4:
                    printAllOrders(orders);
                    break;
                case 5:
                    printOrdersInZip(input, orders);
                    break;
                case 6:
                    System.out.println("Thank you, goodbye!");
                    break;
            }
        } while (response != 6);

        input.close();
    }
}
