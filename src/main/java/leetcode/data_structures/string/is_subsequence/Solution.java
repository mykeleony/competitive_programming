package leetcode.data_structures.string.is_subsequence;

/**
 * Solution for LeetCode problem #392. Is Subsequence.
 * <p>
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/is-subsequence/">LeetCode 392</a>
 */
public class Solution {
    
    public static void main(String[] args) {
        // Standard test cases
        test("abc", "ahbgdc", true);
        test("axc", "ahbgdc", false);
        test("ace", "abcde", true);
        test("aec", "abcde", false);
        
        // Additional test cases
        test("aaaaaa", "bbaaaa", false);
        test("", "ahbgdc", true);
        test("abc", "", false);
        test("", "", true);
        test("a", "a", true);
        test("a", "b", false);
    }
    
    /**
     * Checks if string s is a subsequence of string t.
     * It uses two pointers to iterate through both strings and compares characters.
     *
     * @param s the potential subsequence string
     * @param t the string to check against
     * @return true if s is a subsequence of t, false otherwise
     */
    public static boolean isSubsequence(String s, String t) {
        int indexS = 0;
        int indexT = 0;
        
        while (indexT < t.length() && indexS < s.length()) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
            }
            
            indexT++;
        }
        
        return indexS == s.length();
    }
    
    private static void test(String s, String t, boolean expected) {
        boolean result = isSubsequence(s, t);
        System.out.printf("s: \"%s\", t: \"%s\" | Result: %-5b | Expected: %-5b | %s%n",
                s,
                t,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}