package leetcode.algorithms.math.palindrome_number;

/**
 * Solution for LeetCode problem #9. Palindrome Number.
 * <p>
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/palindrome-number/">LeetCode 9</a>
 */
public class Solution {
    public static void main(String[] args) {
        // Standard test cases
        testWithoutStringConversion(121, true);
        testWithoutStringConversion(0, true);
        testWithoutStringConversion(-121, false);
        testWithoutStringConversion(10, false);
        
        // Additional test cases
        testWithoutStringConversion(1221, true);
        testWithoutStringConversion(12321, true);
        testWithoutStringConversion(123, false);
        testWithoutStringConversion(9, true);
        testWithoutStringConversion(11, true);
        testWithoutStringConversion(99, true);
        testWithoutStringConversion(100, false);
    }
    
    /**
     * Checks if an integer is a palindrome without converting it to a string.
     * It reverses the second half of the number and compares it with the first half.
     *
     * @param x the integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public static boolean isPalindromeWithoutStringConversion(int x) {
        // Negative numbers are not palindromes
        // Numbers ending with 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversedHalf = 0;
        
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10; // Append last digit of x to reversedHalf
            x /= 10;                                   // Remove last digit of x
        }
        
        // For odd length numbers, the middle digit doesn't matter (reversedHalf / 10)
        return x == reversedHalf || x == reversedHalf / 10;
    }
    
    private static void testWithoutStringConversion(int x, boolean expected) {
        boolean result = isPalindromeWithoutStringConversion(x);
        System.out.printf("Input: %-6d | Result (No String): %-5b | Expected: %-5b | %s%n",
                x,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}