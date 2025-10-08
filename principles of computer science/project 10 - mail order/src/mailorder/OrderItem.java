
/**
 * OrderItem.java
 * 
 * This file defines the OrderItem class, which creates an order item object.
 * 
 * @author Christopher Romo
 * @since 7/26/2022
 * @version 1.0
 */

package src.mailorder;

/**
 * The OrderItem class creates an order item object.
 */
public class OrderItem {
    // instance variables
    private String description;
    private double cost;

    /**
     * constructor that creates an order item object
     * 
     * @param description
     * @param cost
     */
    public OrderItem(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    // getters
    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    // mutators
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // toString
    @Override
    public String toString() {
        return String.format("\n%-30s  $%7.2f*", description, cost);
    }
}
