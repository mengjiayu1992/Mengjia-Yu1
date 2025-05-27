import java.util.Scanner;

public class SimpleKnapsackDemo {

    // Function to solve the 0/1 Knapsack Problem using Dynamic Programming
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        // Input weights and values
        System.out.println("Enter weights and values of the items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " weight: ");
            weights[i] = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " value: ");
            values[i] = scanner.nextInt();
        }

        // Input knapsack capacity
        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        // Option 1: Including the heaviest item
        System.out.println("\nOption 1: Including the heaviest item");
        int includeHeaviest = knapsack(weights, values, capacity);
        System.out.println("Maximum value (including heaviest item): " + includeHeaviest);

        // Option 2: Excluding the heaviest item
        System.out.println("\nOption 2: Excluding the heaviest item");
        int[] reducedWeights = new int[n - 1];
        int[] reducedValues = new int[n - 1];
        System.arraycopy(weights, 0, reducedWeights, 0, n - 1);
        System.arraycopy(values, 0, reducedValues, 0, n - 1);
        int excludeHeaviest = knapsack(reducedWeights, reducedValues, capacity);
        System.out.println("Maximum value (excluding heaviest item): " + excludeHeaviest);

        scanner.close();
    }
}
