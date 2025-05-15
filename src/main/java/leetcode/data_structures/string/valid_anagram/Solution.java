package leetcode.data_structures.string.valid_anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem #242. Valid Anagram.
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/valid-anagram/">LeetCode 242</a>
 */
public class Solution {
    public static void main(String[] args) {
        test("anagram", "nagaram", true);
        test("rat", "car", false);
        test("a", "a", true);
        test("ab", "ba", true);
        test("aabb", "abab", true);

        // Edge cases
        test("", "", true);
        test("a", "b", false);
        test("abc", "abcd", false);
        test("abcd", "abc", false);

        // Unicode test cases
        testUnicode("你好", "好你", true);
        testUnicode("你好啊", "你好", false);
    }

    /**
     * Determines if string t is an anagram of string s. This method assumes lowercase English letters.
     * It uses an integer array to count character frequencies.
     * Time complexity: O(s.length() + t.length()), which is O(n) where n is the length of the strings.
     * Space complexity: O(1) since the array size is fixed at 26.
     *
     * @param s the first string
     * @param t the second string
     * @return true if t is an anagram of s, false otherwise
     */
    public static boolean isAnagram(String s, String t) {
        if (t.length() != s.length()) return false;

        int[] charCount = new int[26];

        for (char ch : s.toCharArray()) charCount[ch - 'a']++;

        for (char ch : t.toCharArray()) {
            if (--charCount[ch - 'a'] < 0) return false;
        }

        return true;
    }

    /**
     * Determines if string t is an anagram of string s, handling Unicode characters.
     * It uses a HashMap to count character frequencies.
     * Time complexity: O(s.length() + t.length()), which is O(n).
     * Space complexity: O(k), where k is the number of unique characters in the strings.
     *
     * @param s the first string
     * @param t the second string
     * @return true if t is an anagram of s, false otherwise
     */
    public static boolean isUnicodeStringAnagram(String s, String t) {
        if (t.length() != s.length()) return false;

        Map<Character, Integer> charCount = new HashMap<>();

        for (char ch : s.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {
            if (!charCount.containsKey(ch)) return false;

            charCount.put(ch, charCount.get(ch) - 1);

            if (charCount.get(ch) == 0) charCount.remove(ch);
        }

        return true;
    }

    /**
     * Helper method to test the isAnagram function with expected output.
     *
     * @param s        the first string
     * @param t        the second string
     * @param expected true if t is an anagram of s, false otherwise
     */
    private static void test(String s, String t, boolean expected) {
        boolean result = isAnagram(s, t);
        System.out.printf("s: \"%s\", t: \"%s\" | Result: %-5b | Expected: %-5b | %s%n",
                s,
                t,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }

    /**
     * Helper method to test the isUnicodeStringAnagram function with expected output.
     *
     * @param s        the first string
     * @param t        the second string
     * @param expected true if t is an anagram of s, false otherwise
     */
    private static void testUnicode(String s, String t, boolean expected) {
        boolean result = isUnicodeStringAnagram(s, t);
        System.out.printf("(Unicode) s: \"%s\", t: \"%s\" | Result: %-5b | Expected: %-5b | %s%n",
                s,
                t,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}