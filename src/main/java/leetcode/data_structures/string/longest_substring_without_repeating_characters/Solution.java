package leetcode.data_structures.string.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution for LeetCode problem #3. Longest Substring Without Repeating Characters.
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">LeetCode 3</a>
 */
public class Solution {

    public static void main(String[] args) {
        // Standard test cases
        test("abcabcbb", 3);
        test("bbbbb", 1);
        test("pwwkew", 3);
        test(null, 0);
        test("", 0);
        test(" ", 1);
        test("au", 2);
        test("dvdf", 3);
        test("anviaj", 5);

        // More complex cases
        test("abcdefghijklmnopqrstuvwxyz", 26);
        test("aaaaabbbbb", 2);
        test("abacaba", 3);
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     * This solution uses a sliding window approach with a HashMap to keep track of character indices,
     * offering a time complexity of O(n) and space complexity of O(min(m, n)), where n is the
     * length of the string and m is the size of the character set.
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            if (charIndexMap.containsKey(current)) {
                left = Math.max(left, charIndexMap.get(current) + 1);
            }

            charIndexMap.put(current, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     * This is an initial attempt using a sliding window with a HashSet to track characters.
     * It has a time complexity of O(n) and space complexity of O(min(m, n)), where n is the
     * length of the string and m is the size of the character set.
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring_firstAttempt(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0;
        int maxLength = 0;
        Set<Character> chars = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            while (chars.contains(current)) {
                chars.remove(s.charAt(left++));
            }

            chars.add(current);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param s        the input string
     * @param expected the expected length of the longest substring
     */
    private static void test(String s, int expected) {
        Solution solution = new Solution();
        int result = solution.lengthOfLongestSubstring(s);
        System.out.printf("Input: %-10s | Result: %2d | Expected: %2d | %s%n",
                "\"" + s + "\"",
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}