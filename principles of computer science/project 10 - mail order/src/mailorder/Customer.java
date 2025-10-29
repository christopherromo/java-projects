
/**
 * Customer.java
 * 
 * This file defines the Customer class, which creates a customer object.
 * 
 * @author Christopher Romo
 * @since 07/26/2022
 * @version 1.0
 */

package src.mailorder;

/**
 * The Customer class creates a customer object.
 */
public class Customer {
    // instance variables
    private final String fullName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Address address;
    private final int customerNbr;
    private static int nbrOfCustomers;

    /**
     * constructor that creates a customer object
     * 
     * @param fullName
     * @param address
     */
    public Customer(String fullName, Address address) {
        // initializes variables
        this.fullName = setFullName(fullName);
        this.address = address;
        customerNbr = nbrOfCustomers;
        nbrOfCustomers++;
    }

    // getters
    public String getFullName() {
        return fullName;
    }

    public String getAddressString() {
        return address.toString();
    }

    public int getCustomerNbr() {
        return customerNbr;
    }

    public static int getNbrOfCustomers() {
        return nbrOfCustomers;
    }

    public Address getAddress() {
        return address;
    }

    public String getStreetAddress() {
        return address.getStreetAddress();
    }

    public String getState() {
        return address.getState();
    }

    public String getCity() {
        return address.getCity();
    }

    public int getZip() {
        return address.getZip();
    }

    public String getFirstName() {
        if (middleName == null) {
            return firstName;
        } else {
            String names = String.format("%s %s", firstName, middleName);
            return names;
        }
    }

    public String getLastName() {
        return lastName;
    }

    // mutators
    public String setFullName(String fullName) {
        // sets the variables for the names
        int space = fullName.indexOf(" ");
        firstName = fullName.substring(0, space);

        int spaceTwo = fullName.indexOf(" ", space + 1);

        if (spaceTwo < 0) {
            middleName = "";
            lastName = fullName.substring(space + 1);
        } else {
            middleName = fullName.substring(space + 1, spaceTwo);
            lastName = fullName.substring(spaceTwo + 1);
        }
        return fullName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // toString
    @Override
    public String toString() {
        // returns a string depending on number of names
        if (middleName == null) {
            return String.format("Customer Nbr: %d\n%s, %s\nBilling Address:\n%s",
                    customerNbr, lastName, firstName, address.toString());
        } else {
            return String.format("Customer Nbr: %d\n%s, %s %s\nBilling Address:\n%s",
                    customerNbr, lastName, firstName, middleName, address.toString());

        }
    }
} // Customer
