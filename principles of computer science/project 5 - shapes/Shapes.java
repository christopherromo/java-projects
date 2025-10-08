
/**
 * Shapes.java
 * 
 * This file defines the Shapes class, which allows you to draw shapes
 * to the console based on user input.
 * 
 * @author Christopher Romo
 * @since 6/30/2022
 * @version 1.0
 */

import java.util.*;

/**
 * The Shapes class contains a main method that allows you to draw shapes
 * to the console based on user input.
 */
public class Shapes {
    /**
     * Main method - allows you to draw shapes to the console based on user input.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // prompt menu
        System.out.println("Pick a shape to draw: ");
        System.out.println("1 - Rectangle");
        System.out.println("2 - Right Triangle");
        System.out.println("3 - Upside-down Right Triangle");
        System.out.println("4 - Isosceles Triangle");
        System.out.println("5 - Diamond");
        System.out.println("6 - Hourglass");
        System.out.println("7 - Quit");
        int response = input.nextInt();

        while (response != 7) {
            switch (response) {
                case 1:
                    System.out.println("You have chosen a Rectangle.");
                    System.out.println("Enter the height: ");
                    int height = input.nextInt();
                    System.out.println("Enter the width: ");
                    int width = input.nextInt();
                    for (int x = 1; x <= height; x++) {
                        for (int y = 1; y <= width; y++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.println("You have chosen a Right Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeRT = input.nextInt();
                    for (int x = 1; x <= sizeRT; x++) {
                        for (int y = 1; y <= x; y++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println("You have chosen an Upside-down Right Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeUDRT = input.nextInt();
                    for (int x = sizeUDRT; x >= 1; x--) {
                        for (int y = 1; y <= x; y++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("You have chosen an Isosceles Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeIT = input.nextInt();
                    for (int x = 1; x <= sizeIT; x++) {
                        for (int y = sizeIT; y >= x; y--) {
                            System.out.print(" ");
                        }

                        for (int w = 1; w <= (2 * x - 1); w++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.println("You have chosen a Diamond.");
                    System.out.println("Enter the size: ");
                    int sizeD = input.nextInt();
                    int a = sizeD - 1;
                    for (int x = 1; x <= sizeD; x++) {
                        for (int y = sizeD; y >= x; y--) {
                            System.out.print(" ");
                        }

                        for (int w = 1; w <= (2 * x - 1); w++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                    for (int x = 1; x <= a; x++) {
                        for (int y = 0; y <= x; y++) {
                            System.out.print(" ");
                        }
                        for (int k = 1; k <= (2 * (a - x) + 1); k++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("You have chosen an Hourglass.");
                    System.out.println("Enter the size: ");
                    int sizeH = input.nextInt();
                    for (int x = 1; x <= sizeH; x++) {
                        for (int y = 1; y <= x; y++) {
                            System.out.print(" ");
                        }
                        for (int k = 1; k <= (2 * (sizeH - x) + 1); k++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                    for (int x = 2; x <= sizeH; x++) {
                        for (int y = sizeH; y >= x; y--) {
                            System.out.print(" ");
                        }

                        for (int w = 1; w <= (2 * x - 1); w++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;

                default:
                    System.out.println("Invalid choice, choose again.");
                    break;

            }
            System.out.println("Pick a shape to draw: ");
            System.out.println("1 - Rectangle");
            System.out.println("2 - Right Triangle");
            System.out.println("3 - Upside-down Right Triangle");
            System.out.println("4 - Isosceles Triangle");
            System.out.println("5 - Diamond");
            System.out.println("6 - Hourglass");
            System.out.println("7 - Quit");
            response = input.nextInt();
        }
        System.out.println("You have chosen to quit. Goodbye.");

        input.close();
    }
}
