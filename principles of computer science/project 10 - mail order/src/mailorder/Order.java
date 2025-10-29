
/**
 * Order.java
 * 
 * This file defines the Order class, which creates an order object.
 * 
 * @author Christopher Romo
 * @since 07/26/2022
 * @version 1.0
 */

package src.mailorder;

import java.util.ArrayList;

/**
 * The Order class creates an order object.
 */
public class Order {
    // instance variables
    private final int orderNbr;
    private double sum;
    private final Customer customer;
    private final Address shippingAddress;
    private final ArrayList<OrderItem> items = new ArrayList<OrderItem>();
    private static int nbrOfOrders;

    /**
     * constructor that creates an order object
     * 
     * @param customer
     * @param shippingAddress
     */
    public Order(Customer customer, Address shippingAddress) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        sum = 0.0;
        orderNbr = nbrOfOrders;
        nbrOfOrders++;
    }

    // getters
    public String getCustomer() {
        return customer.toString();
    }

    public String getAddress() {
        return shippingAddress.toString();
    }

    public int getOrderNbr() {
        return orderNbr;
    }

    public int getZip() {
        return shippingAddress.getZip();
    }

    public static int getNbrOfOrders() {
        return nbrOfOrders;
    }

    /**
     * adds an item to the order
     * 
     * @param item the item to add
     */
    public void addItem(OrderItem item) {
        items.add(item);
        sum = sum + item.getCost();
    }

    // toString
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Order Number: " + orderNbr)
                .append("\nPurchased by:\nCustomer Nbr: " + customer.getCustomerNbr() + "\n")
                .append(customer.getLastName() + ", ")
                .append(customer.getFirstName() + "\n")
                .append("Billing Address:\n")
                .append(customer.getAddressString())
                .append("\nShipped to:\n" + shippingAddress)
                .append("\nItems Ordered:"
                        + items.toString().replace("[", "").replace("]", "").replace("*, ", "").replace("*", ""));

        stringBuilder.append(String.format("\n%-30s  $%7.2f",
                "Order Total: ", sum));

        return stringBuilder.toString();
        
    }
} // Order