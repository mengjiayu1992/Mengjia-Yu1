public class TowerOfHanoi {

    //Prints the game rule

    public static void printRules() {
        System.out.println("Tower of Hanoi Rules:");
        System.out.println("1. Only one disk can be moved at a time.");
        System.out.println("2. A disk can only be placed on top of a larger disk or an empty rod.");
        System.out.println("3. Move all disks from the source rod to the destination rod.");
    }

    
      //Recursive method to solve the Tower of Hanoi problem.
     
      // n           The number of disks
      // source      The source rod
      // auxiliary   The auxiliary rod
      // destination The destination rod
    
    public static void solveHanoi(int n, char source, char auxiliary, char destination) {
        // if only one disk, move it directly to the destination rod
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        // Step 1: Move the top n-1 disks from source to auxiliary, using destination as temporary storage
        solveHanoi(n - 1, source, destination, auxiliary);

        // Step 2: Move the last disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // Step 3: Move the n-1 disks from auxiliary to destination, using source as temporary storage
        solveHanoi(n - 1, auxiliary, source, destination);
    }

    //Calculates the total number of moves required to solve Tower of Hanoi
     
    // n The number of disks
   // The total number of moves, m = 2^n - 1
   
    public static int calculateMoves(int n) {
        return (int) Math.pow(2, n) - 1;
    }

    public static void main(String[] args) {
        // Print the game rules
        printRules();
        System.out.println();

        // Demonstrate output for different values of disks
        int[] diskCounts = {2, 3, 4};  // Different  number of n(disk)
        for (int n : diskCounts) {
            System.out.println("Tower of Hanoi solution steps for n = " + n + ":");
            solveHanoi(n, 'A', 'B', 'C');  // Call solveHanoi method
            int moves = calculateMoves(n);  // Calculate the number of moves
            System.out.println("Total number of moves m = " + moves + " (Minimum moves)"); 
            System.out.println();
        }
    }
}
