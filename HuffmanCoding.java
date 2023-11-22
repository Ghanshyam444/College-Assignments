import java.util.*;
import java.util.Map.Entry;

class Node {
    char ch;
    int freq;
    Node left, right;

    public Node getNode(char ch, int freq, Node left, Node right) {
        Node node = new Node();
        node.ch = ch;
        node.freq = freq;
        node.left = left;
        node.right = right;
        return node;
    }
}

class MinHeapComparator implements Comparator<Node> {
    public int compare(Node l, Node r) {
        return l.freq - r.freq;
    }
}

public class HuffmanCoding {
    // Function to traverse the Huffman Tree and store Huffman Codes in a map.
    static void encode(Node root, String str, HashMap<Character, String> huffmanCode) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, str); // Store Huffman code for a character.
        }

        encode(root.left, str + "0", huffmanCode); // Traverse left with '0'.
        encode(root.right, str + "1", huffmanCode); // Traverse right with '1'.
    }

    // Function to decode the encoded string using the Huffman Tree.
    static int decode(Node root, int index, StringBuilder decodedString, String encodedString) {
        if (root == null || index >= encodedString.length()) {
            return index;
        }

        if (root.left == null && root.right == null) {
            decodedString.append(root.ch); // Found a leaf node, append the character to the result.
            return index;
        }

        index++;

        if (encodedString.charAt(index) == '0') {
            return decode(root.left, index, decodedString, encodedString); // Traverse left with '0'.
        } else {
            return decode(root.right, index, decodedString, encodedString); // Traverse right with '1'.
        }
    }

    // Function to build the Huffman Tree and perform encoding and decoding.
    static void buildHuffmanTree(String text) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1); // Count character frequencies.
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new MinHeapComparator());

        for (Map.Entry<Character, Integer> pair : freq.entrySet()) {
            pq.add(new Node().getNode(pair.getKey(), pair.getValue(), null, null)); // Create leaf nodes.
        }

        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new Node().getNode('\0', sum, left, right)); // Merge nodes to build the Huffman Tree.
        }

        Node root = pq.poll(); // Root of the Huffman Tree.

        HashMap<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode); // Generate Huffman codes.

        System.out.println("Huffman Codes are :\n");

        for (Map.Entry<Character, String> pair : huffmanCode.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue()); // Print Huffman codes.
        }

        System.out.println("\nOriginal string was :\n" + text + "\n");

        String encodedString = "";
        for (char ch : text.toCharArray()) {
            encodedString += huffmanCode.get(ch); // Encode the input string.
        }

        System.out.println("Encoded string is :\n" + encodedString + "\n");

        StringBuilder decodedString = new StringBuilder();
        int index = -1;

        while (index < encodedString.length() - 1) {
            index = decode(root, index, decodedString, encodedString); // Decode the encoded string.
        }

        System.out.println("Decoded string is: \n" + decodedString.toString());
    }

    // Main function for Huffman coding algorithm
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        buildHuffmanTree(text);
    }
}
