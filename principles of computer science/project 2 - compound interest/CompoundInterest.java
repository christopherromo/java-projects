
/**
 * CompoundInterest.java
 * 
 * This file defines the CompoundInterest class, which calculates various
 * aspects of compound interest based on user input.
 * 
 * @author Christopher Romo
 * @since 6/20/2022
 * @version 1.0
 */

import java.util.*;
import java.text.DecimalFormat;

/**
 * The CompoundInterest class contains a main method that calculates
 * various aspects of compound interest based on user input.
 */
public class CompoundInterest {
    // static decimal format
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Main method - calculates compound interest based on user input.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // find initial investment amount
        System.out.print("Enter the investment amount (ex: 120000.95): ");
        double initialInvest = input.nextDouble();
        input.nextLine();

        // find interest rate
        System.out.print("Enter annual interest rate (ex: 8.25): ");
        double interestRate = input.nextDouble();
        input.nextLine();

        // find compounding rate
        System.out.print("Enter the rate of compounding per year (ex: 12): ");
        int compoundRate = input.nextInt();
        input.nextLine();

        // find periodic payment
        System.out.print("Enter the periodic payment (ex: 100.00): ");
        double periodicPay = input.nextDouble();
        input.nextLine();

        // find number of years
        System.out.print("Enter number of years as an integer (ex: 5): ");
        int yearNbr = input.nextInt();

        // convert interest rate to decimal
        double interestPercent = interestRate * 0.01;

        // create base numbers for equations
        double rn = (interestPercent / compoundRate) + 1;
        double rnB = (interestPercent / compoundRate);
        double nt = compoundRate * yearNbr;

        // use said bases in order to calculate & print values
        double function = Math.pow(rn, nt);
        double futureInvestment = initialInvest * function;
        System.out.println("Future value of your investment is: " + df.format(futureInvestment));

        double functionTwo = periodicPay * (function - 1);
        double functionThree = functionTwo / rnB;
        double futurePayment = functionThree * (1 + rnB);
        System.out.println("Future value of your payments is: " + df.format(futurePayment));

        double totalInvestment = futureInvestment + futurePayment;
        System.out.println("Future value of your total investment: " + df.format(totalInvestment));

        input.close();
    }
}
