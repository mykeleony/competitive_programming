package leetcode.data_structures.string.longest_common_prefix;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #14. Longest Common Prefix.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-common-prefix/">LeetCode 14</a>
 */
public class Solution {
    
    public static void main(String[] args) {
        test(new String[]{"flower", "flow", "flight"}, "fl");
        test(new String[]{"dog", "racecar", "car"}, "");
        test(new String[]{"apple", "apricot", "april"}, "ap");
        test(new String[]{"a"}, "a");
        test(new String[]{"", "a"}, "");
        test(new String[]{"a", ""}, "");
        test(new String[]{"unique"}, "unique");
    }
    
    /**
     * Finds the longest common prefix string amongst an array of strings.
     * It iterates through the characters of the first string and compares them with the characters at the same position in other strings.
     * If a mismatch is found or the end of a string is reached, the common prefix up to that point is returned.
     *
     * @param strs the array of strings to find the common prefix from
     * @return the longest common prefix string, or an empty string if there is no common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        
        // Iterate through the characters of the first string
        for (int i = 0; i < strs[0].length(); i++) {
            char currentChar = strs[0].charAt(i);
            
            // Compare the current character with the character at the same position in the remaining strings
            for (int j = 1; j < strs.length; j++) {
                // If the current string is shorter or the characters don't match
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) {
                    // Return the common prefix found so far
                    return strs[0].substring(0, i);
                }
            }
        }
        
        // If all characters of the first string are common prefixes of all other strings
        return strs[0];
    }
    
    private static void test(String[] strs, String expected) {
        String result = longestCommonPrefix(strs);
        System.out.printf("Input: %-30s | Result: %-10s | Expected: %-10s | %s%n",
                Arrays.toString(strs),
                result,
                expected,
                result.equals(expected) ? "✓ PASS" : "✗ FAIL");
    }
}