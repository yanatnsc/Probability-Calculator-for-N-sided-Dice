//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sides = -1;
        int numberOfDice = -1;
        int numRolls = -1;

        // Validate number of sides
        while (sides <= 0) {
            System.out.print("Enter number of sides (N): ");
            if (scanner.hasNextInt()) {
                sides = scanner.nextInt();
                if (sides <= 0) {
                    System.out.println("Number of sides must be a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Validate number of dice
        while (numberOfDice <= 0) {
            System.out.print("Enter number of dice (M): ");
            if (scanner.hasNextInt()) {
                numberOfDice = scanner.nextInt();
                if (numberOfDice <= 0) {
                    System.out.println("Number of dice must be a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Validate number of rolls
        while (numRolls <= 0) {
            System.out.print("Enter number of simulations (K): ");
            if (scanner.hasNextInt()) {
                numRolls = scanner.nextInt();
                if (numRolls <= 0) {
                    System.out.println("Number of simulations must be a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Calculate and display the probability distribution
        Map<Integer, Double> probabilities = calculateProbability(sides, numberOfDice, numRolls);
        System.out.println("Probability distribution:");
        for (Map.Entry<Integer, Double> entry : probabilities.entrySet()) {
            System.out.println("Sum: " + entry.getKey() + ", Probability: " + entry.getValue());
        }

        scanner.close();
    }

    private static Random rand = new Random();

    // Function to roll dice
    public static int rollDice(int sides, int numberOfDice) {
        int sum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            sum += rand.nextInt(sides) + 1; // Roll a die with `sides` sides
        }
        return sum;
    }

    // Function to simulate multiple rolls
    public static Map<Integer, Integer> simulateRolls(int sides, int numberOfDice, int numRolls) {
        Map<Integer, Integer> results = new HashMap<>();

        for (int i = 0; i < numRolls; i++) {
            int sum = rollDice(sides, numberOfDice);
            results.put(sum, results.getOrDefault(sum, 0) + 1);
        }

        return results;
    }

    // Function to calculate probability distribution using results from simulateRolls
    public static Map<Integer, Double> calculateProbability(int sides, int numberOfDice, int numRolls) {
        // Get frequencies of sums from simulateRolls
        Map<Integer, Integer> frequency = simulateRolls(sides, numberOfDice, numRolls);

        Map<Integer, Double> probability = new HashMap<>();

        // Calculate probabilities from frequencies
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            probability.put(entry.getKey(), entry.getValue() / (double) numRolls);
        }

        return probability;
    }
}