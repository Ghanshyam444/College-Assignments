import java.math.BigInteger;
import java.util.Scanner;

public class LargeIntegerSquare {
    // Define a method to compute the square of a BigInteger using divide and conquer.
    public static BigInteger square(BigInteger num) {
        // Base case: If the number is small enough, calculate the square directly.
        if (num.compareTo(BigInteger.TEN) <= 0) {
            return num.multiply(num);
        }
        // Combine the results using the formula: (a * 10^n + b)^2 = a^2 * 10^(2n) + 2ab * 10^n + b^2
        
        // Convert the number to a string to split it into two halves.
        String numStr = num.toString();
        int length = numStr.length();
        int mid = length / 2;
        
        // Split the number into two halves.
        String leftHalf = numStr.substring(0, mid);
        String rightHalf = numStr.substring(mid);
        
        // Convert the halves back to BigInteger.
        BigInteger left = new BigInteger(leftHalf);
        BigInteger right = new BigInteger(rightHalf);
        
        // Recursively compute the squares of the halves.
        BigInteger leftSquare = square(left);
        BigInteger rightSquare = square(right);
        
        // Combine the results using the divide and conquer approach.
        BigInteger aSquared = leftSquare;
        BigInteger bSquared = rightSquare;
        BigInteger ab = left.multiply(right).multiply(BigInteger.valueOf(2));
                // Combine the results using the formula: (a * 10^n + b)^2 = a^2 * 10^(2n) + 2ab * 10^n + b^2
        BigInteger result = aSquared.multiply(BigInteger.TEN.pow(2 * mid))
                .add(ab.multiply(BigInteger.TEN.pow(mid)))
                .add(bSquared);
        
        return result;
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input.
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a 20-digit large integer.
        System.out.print("Enter a 20-digit large integer: ");
        
        // Read the user's input as a string.
        String input = scanner.nextLine();
        
        try {
            // Attempt to convert the input to a BigInteger.
            BigInteger largeInt = new BigInteger(input);
            
            // Compute the square using the square method.
            BigInteger result = square(largeInt);
            
            // Print the result.
            System.out.println("Square of " + largeInt + " is: " + result);
        } catch (NumberFormatException e) {
            // Handle invalid input.
            System.err.println("Invalid input. Please enter a valid 20-digit integer.");
        }
        
        // Close the scanner to release resources.
        scanner.close();
    }
}
