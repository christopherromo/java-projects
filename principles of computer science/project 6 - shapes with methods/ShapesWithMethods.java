
/**
 * ShapesWithMethods.java
 * 
 * This file defines the Shapes class, which allows you to draw shapes
 * to the console based on user input (now with methods).
 * 
 * @author Christopher Romo
 * @since 07/08/2022
 * @version 1.0
 */

import java.util.*;

/**
 * The ShapesWithMethods class contains a main method that allows you to draw
 * shapes to the console based on user input (now with methods).
 */
public class ShapesWithMethods {
    /**
     * prompts menu of choices
     */
    public static void promptMenu() {
        System.out.println("Pick a shape to draw: ");
        System.out.println("1 - Right Triangle");
        System.out.println("2 - Upside-down Right Triangle");
        System.out.println("3 - Isosceles Triangle");
        System.out.println("4 - Upside-down Isosceles Triangle");
        System.out.println("5 - Diamond");
        System.out.println("6 - Hourglass");
        System.out.println("7 - Quit");
    }

    /**
     * draws a right triangle
     * 
     * @param s size
     */
    public static void drawRightTriangle(int s) {
        for (int x = 1; x <= s; x++) {
            for (int y = 1; y <= x; y++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * draws an upside-down right triangle
     * 
     * @param s size
     */
    public static void drawUpsideDownRightTriangle(int s) {
        for (int x = s; x >= 1; x--) {
            for (int y = 1; y <= x; y++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * draws an isosceles triangle
     * 
     * @param s size
     */
    public static void drawIsoscelesTriangle(int s) {
        for (int x = 1; x <= s; x++) {
            for (int y = s; y >= x; y--) {
                System.out.print(" ");
            }

            for (int w = 1; w <= (2 * x - 1); w++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * draws an inverted isosceles triangle
     * 
     * @param s size
     */
    public static void drawInvertedIsoscelesTriangle(int s) {
        for (int x = 1; x <= s; x++) {
            for (int y = 1; y <= x; y++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * (s - x) + 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * draws a diamond
     * 
     * @param s size
     */
    public static void drawDiamond(int s) {
        // use both triangles to create a diamond
        int a = s - 1;
        drawIsoscelesTriangle(s);

        for (int x = 1; x <= a; x++) {
            for (int y = 0; y <= x; y++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * (a - x) + 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * draws a hourglass
     * 
     * @param s size
     */
    public static void drawHourglass(int s) {
        // use triangle and a modified triangle to create hourglass
        drawInvertedIsoscelesTriangle(s);

        for (int x = 2; x <= s; x++) {
            for (int y = s; y >= x; y--) {
                System.out.print(" ");
            }

            for (int w = 1; w <= (2 * x - 1); w++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Main method - allows you to draw shapes to the console based on user input
     * (now with methods).
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // prompt menu
        promptMenu();
        int response = input.nextInt();

        while (response != 7) {
            switch (response) {
                case 1:
                    System.out.println("You have chosen a Right Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeRT = input.nextInt();
                    drawRightTriangle(sizeRT);
                    break;

                case 2:
                    System.out.println("You have chosen an Upside-down Right Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeUDRT = input.nextInt();
                    drawUpsideDownRightTriangle(sizeUDRT);
                    break;

                case 3:
                    System.out.println("You have chosen an Isosceles Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeIT = input.nextInt();
                    drawIsoscelesTriangle(sizeIT);
                    break;

                case 4:
                    System.out.println("You have chosen an Upside-down Isosceles Triangle.");
                    System.out.println("Enter the size: ");
                    int sizeUDIT = input.nextInt();
                    drawInvertedIsoscelesTriangle(sizeUDIT);
                    break;

                case 5:
                    System.out.println("You have chosen a Diamond.");
                    System.out.println("Enter the size: ");
                    int sizeD = input.nextInt();
                    drawDiamond(sizeD);
                    break;

                case 6:
                    System.out.println("You have chosen an Hourglass.");
                    System.out.println("Enter the size: ");
                    int sizeH = input.nextInt();
                    drawHourglass(sizeH);
                    break;

                default:
                    System.out.println("Invalid choice, choose again.");
                    break;

            }
            promptMenu();
            response = input.nextInt();
        }
        System.out.println("You have chosen to quit. Goodbye.");

        input.close();
        
    } // main
} // ShapesWithMethods