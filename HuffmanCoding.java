import java.util.PriorityQueue;
import java.util.Scanner;

// Node class representing each node in the Huffman tree
class HuffmanNode {
    int frequency; // Frequency of the character
    char character; // Character
    HuffmanNode left; // Left child node
    HuffmanNode right; // Right child node
}

// Comparator class to sort based on frequency
class FrequencyComparator implements java.util.Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

public class HuffmanCoding {
    
    // Method to print Huffman codes
    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root.left == null && root.right == null && Character.isLetter(root.character)) {
            System.out.println(root.character + ": " + code);
            return;
        }
        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input characters and their frequencies
            System.out.println("Enter the number of characters:");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Error: Number of characters must be greater than 0.");
                return;
            }

            char[] characters = new char[n];
            int[] frequencies = new int[n];

            System.out.println("Enter the characters and their frequencies:");
            for (int i = 0; i < n; i++) {
                System.out.print("Character: ");
                characters[i] = scanner.next().trim().charAt(0); // Fix potential whitespace issues
                System.out.print("Frequency: ");
                frequencies[i] = scanner.nextInt();
                if (frequencies[i] <= 0) {
                    System.out.println("Error: Frequency must be positive.");
                    return;
                }
            }

            // Build the Huffman tree using a priority queue
            PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(n, new FrequencyComparator());
            for (int i = 0; i < n; i++) {
                HuffmanNode node = new HuffmanNode();
                node.character = characters[i];
                node.frequency = frequencies[i];
                node.left = null;
                node.right = null;
                priorityQueue.add(node);
            }

            HuffmanNode root = null;

            // Build the Huffman tree by combining the two lowest frequency nodes
            while (priorityQueue.size() > 1) {
                HuffmanNode x = priorityQueue.poll();
                HuffmanNode y = priorityQueue.poll();

                HuffmanNode newNode = new HuffmanNode();
                newNode.frequency = x.frequency + y.frequency;
                newNode.character = '-';
                newNode.left = x;
                newNode.right = y;
                root = newNode;

                priorityQueue.add(newNode);
            }

            // Print the Huffman codes
            System.out.println("The Huffman codes are:");
            printHuffmanCodes(root, "");
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please try again.");
        } finally {
            scanner.close();
        }
    }
}
