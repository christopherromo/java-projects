
/**
 * Address.java
 * 
 * This file defines the Address class, which creates an address object.
 * 
 * @author Christopher Romo
 * @since 7/26/2022
 * @version 1.0
 */

package src.mailorder;

/**
 * The Address class creates an address object.
 */
public class Address {
    // instance variables
    private String streetAddress;
    private String city;
    private String state;
    private int zip;

    /**
     * constructor that creates an address object
     * 
     * @param streetAddress
     * @param city
     * @param state
     * @param zip
     */
    public Address(String streetAddress, String city, String state, int zip) {
        // initializes variables
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    // getters
    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    // mutators
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * parses an address from two strings
     * 
     * @param streetAddress
     * @param cityStateZip
     * @return the address
     */
    public static Address parse(String streetAddress, String cityStateZip) {
        String[] cityLine = cityStateZip.split(", ");
        String[] stateZip = cityLine[1].split(" ");
        int zipper = Integer.parseInt(stateZip[1]);
        return new Address(streetAddress, cityLine[0], stateZip[0], zipper);
    }

    // toString
    @Override
    public String toString() {
        // returns a string
        return String.format("%s\n%s, %s %05d", streetAddress, city, state, zip);
    }
}