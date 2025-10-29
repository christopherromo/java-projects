
/**
 * ShouldIPostThis.java
 * 
 * This file defines the ShouldIPostThis class, which guides users
 * through a decision based on user input.
 * 
 * @author Christopher Romo
 * @since 06/25/2022
 * @version 1.0
 */

import java.util.Scanner;

/**
 * The ShouldIPostThis class contains a main method that guides users
 * through a decision based on user input.
 */
public class ShouldIPostThis {
    /**
     * Main method - guides users through a decision based on user input.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // prompt post type
        System.out.println("SHOULD I POST THIS?");
        System.out.println("1 for Status, 2 for Photo:");
        int postType = input.nextInt();
        input.hasNextLine();

        if (postType == 1) {
            // prompt for abrasive content
            System.out.println("Does it contain expletives or abrasive content?");
            System.out.println("1 for Yes, 2 for No:");
            int explicit = input.nextInt();
            input.hasNextLine();

            if (explicit == 1) {
                System.out.println("F*** NO!");
            } else if (explicit == 2) {
                // prompt for drunk
                System.out.println("Have you been drinking?");
                System.out.println("1 for I'M DRUNK RIGHT NOW, 2 for No:");
                int drunk = input.nextInt();
                input.hasNextLine();

                if (drunk == 1) {
                    System.out.println("DUDE, DON'T POST THAT");
                    System.out.println(
                            "Of recruiters who rejected a candidate due to online content, 44% cited proof of drinking or drug use as the reason");
                } else if (drunk == 2) {
                    // prompt for lyrics
                    System.out.println(
                            "Is your status update a batch of cryptic song lyrics that will make people ask, \"Are you OK?\"");
                    System.out.println("1 for Yes, 2 for No:");
                    int questionable = input.nextInt();
                    input.hasNextLine();

                    if (questionable == 1) {
                        System.out.println("PLEASE SPARE US");
                    } else if (questionable == 2) {
                        // prompt for woes
                        System.out.println(
                                "Does it contain details of relationship woes, a doctor's visit, or bathroom troubles?");
                        System.out.println("1 for Yes, 2 for No:");
                        int woes = input.nextInt();
                        input.hasNextLine();

                        if (woes == 1) {
                            System.out.println("PLEASE SPARE US");
                        } else if (woes == 2) {
                            // prompt for one liners
                            System.out.println("Is it a one word update, ie \"bored,\" \"hungry,\" or \"tired\"?");
                            System.out.println("1 for Yes, 2 for No:");
                            int hungry = input.nextInt();
                            input.hasNextLine();

                            if (hungry == 1) {
                                System.out.println("PLEASE SPARE US");
                            } else if (hungry == 2) {
                                // prompt for slander
                                System.out.println("Does it slander a co-worker, employer, or frenemy?");
                                System.out.println("1 for Yes, 2 for No:");
                                int slander = input.nextInt();
                                input.hasNextLine();

                                if (slander == 1) {
                                    System.out.println("STOP BEING A JERK");
                                    System.out.println(
                                            "Of recruiters who rejected a candidate due to online content, 35% cite online slandering of a colleague or employer as the reason.");
                                } else if (slander == 2) {
                                    // prompt for care
                                    System.out.println("Ask yourself, \"Will anyone care?\" Is the answer:");
                                    System.out.println("1 for Yes, 2 for No:");
                                    int care = input.nextInt();
                                    input.hasNextLine();

                                    if (care == 1) {
                                        System.out.println("GO FOR IT!");
                                    } else if (care == 2) {
                                        System.out.println("PLEASE SPARE US");
                                    } else {
                                        System.out.println("Invalid choice.");
                                    }
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } else if (postType == 2) {
            // prompt for clothing
            System.out.println("Is everyone in it fully clothed?");
            System.out.println("1 for Yes, 2 for No:");
            int photo = input.nextInt();
            input.hasNextLine();

            if (photo == 1) {
                // prompt for illegal
                System.out.println("Are you doing anything illegal in it?");
                System.out.println("1 for Yes, 2 for No:");
                int illegal = input.nextInt();
                input.hasNextLine();

                if (illegal == 1) {
                    System.out.println("DUDE, DON'T POST THAT");
                    System.out.println(
                            "Of recruiters who rejected a candidate due to online content, 44% cited proof of drinking or drug use as the reason");
                } else if (illegal == 2) {
                    // prompt for relationship
                    System.out.println("Is it of you and your boyfriend or girlfriend?");
                    System.out.println("1 for Yes, 2 for No:");
                    int boyfriend = input.nextInt();
                    input.hasNextLine();

                    if (boyfriend == 1) {
                        // prompt for relationship status
                        System.out.println("Are you still together?");
                        System.out.println("1 for Yes, 2 for MAYBE? I THINK SO?:");
                        int maybe = input.nextInt();
                        input.hasNextLine();

                        if (maybe == 1) {
                            // prompt for care
                            System.out.println("Ask yourself, \"Will anyone care?\" Is the answer:");
                            System.out.println("1 for Yes, 2 for No:");
                            int careOne = input.nextInt();
                            input.hasNextLine();

                            if (careOne == 1) {
                                System.out.println("GO FOR IT!");
                            } else if (careOne == 2) {
                                System.out.println("PLEASE SPARE US");
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        } else if (maybe == 2) {
                            System.out.println("GET OVER IT");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else if (boyfriend == 2) {
                        // prompt for randomness
                        System.out.println("Is it of your feet, a baby, or your food?");
                        System.out.println("1 for Yes, 2 for No:");
                        int food = input.nextInt();
                        input.hasNextLine();

                        if (food == 1) {
                            System.out.println("PROBABLY NOT");
                            System.out.println(
                                    "1 in 4 people who upload a picture of their food say they do it simply to show \"What I ate today.\"");
                        } else if (food == 2) {
                            // prompt for care1
                            System.out.println("Ask yourself, \"Will anyone care?\" Is the answer:");
                            System.out.println("1 for Yes, 2 for No:");
                            int careTwo = input.nextInt();
                            input.hasNextLine();

                            if (careTwo == 1) {
                                System.out.println("GO FOR IT!");
                            } else if (careTwo == 2) {
                                System.out.println("PLEASE SPARE US");
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } else if (photo == 2) {
                System.out.println("PUT YOUR PANTS BACK ON");
                System.out.println(
                        "Of recruiters who rejected a candidate due to online content, 53% said the reason was provocative or inappropriate pictures.");
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
        input.close();
        
    } // main
} // ShouldIPostThis