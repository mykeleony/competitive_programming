package leetcode.data_structures.string.valid_palindrome;

/**
 * Solution for LeetCode problem #125. Valid Palindrome.
 * <p>
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/valid-palindrome/">LeetCode 125</a>
 */
public class Solution {
    
    public static void main(String[] args) {
        // Standard test cases
        test("A man, a plan, a canal: Panama", true);
        test("race a car", false);
        test(" ", true);
        test("Was it a car or a cat I saw?", true);
        test("Madam, I'm Adam!", true);
        test("121", true);
        test("123", false);
        test("0P", false);
        
        // Edge cases
        test("", true);
        test("a", true);
        test("aa", true);
        test("ab", false);
    }
    
    /**
     * Checks if a given string is a palindrome, considering only alphanumeric characters and ignoring case.
     *
     * @param s the input string
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            
            // Skip non-alphanumeric characters
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                
                left++;
                right--;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to test the isPalindrome function with expected output.
     *
     * @param s        the input string
     * @param expected true if the string is a palindrome, false otherwise
     */
    private static void test(String s, boolean expected) {
        boolean result = isPalindrome(s);
        System.out.printf("Input: \"%-30s\" | Result: %-5b | Expected: %-5b | %s%n",
                s,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}
