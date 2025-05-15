package leetcode.data_structures.string.length_of_last_word;

/**
 * Solution for LeetCode problem #58. Length of Last Word.
 * <p>
 * Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/length-of-last-word/">LeetCode 58</a>
 */
public class Solution {
    public static void main(String[] args) {
        test("Hello World", 5);
        test("   fly me   to   the moon  ", 4);
        test("luffy is still joyboy", 6);

        // Edge cases
        test("a", 1);
        test("a ", 1);
        test(" a", 1);
        test(" ", 0);
        test("  ", 0);
        test("b  a  ", 1);
        test("word", 4);
    }

    /**
     * Calculates the length of the last word in the given string.
     * It iterates from the end of the string, skipping trailing spaces and then counting the characters of the last word.
     *
     * @param s the input string containing words and spaces
     * @return the length of the last word in the string
     */
    public static int lengthOfLastWord(String s) {
        int length = 0;
        int p = s.length() - 1;

        while (p >= 0 && s.charAt(p) == ' ') p--;   // Skip any trailing spaces
        while (p >= 0 && s.charAt(p--) != ' ') length++;

        return length;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param s        the input string
     * @param expected the expected length of the last word
     */
    private static void test(String s, int expected) {
        int result = lengthOfLastWord(s);
        System.out.printf("Input: %-30s | Result: %2d | Expected: %2d | %s%n",
                "\"" + s + "\"",
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}