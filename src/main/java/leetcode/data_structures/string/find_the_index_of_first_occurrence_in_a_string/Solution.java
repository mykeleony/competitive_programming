package leetcode.data_structures.string.find_the_index_of_first_occurrence_in_a_string;

/**
 * Solution for LeetCode problem #28. Find the Index of the First Occurrence in a String.
 * <p>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/">LeetCode 28</a>
 */
public class Solution {

    public static void main(String[] args) {
        test("sadbutsad", "sad", 0);
        test("leetcode", "leeto", -1);
        test("hello", "ll", 2);
        test("mississippi", "issip", 4);
        test("a", "a", 0);
        test("mississippi", "issi", 1);

        // Edge cases
        test("", "", 0);
        test("abc", "", 0);
        test("", "abc", -1);
        test("aaa", "aaaa", -1);
        test("abc", "d", -1);
        test("abcabc", "abc", 0);
        test("abcabc", "bca", 1);
    }

    /**
     * Finds the index of the first occurrence of the needle string within the haystack string.
     * This implementation uses a straightforward approach with nested loops, resulting in a time complexity of O(m*n) in the worst case,
     * where n is the length of haystack and m is the length of needle.
     *
     * @param haystack the string to search within
     * @param needle   the string to find
     * @return the index of the first occurrence of needle in haystack, or -1 if not found
     */
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int i = 0;

        while (haystack.length() - i >= needle.length()) {
            int matchedLength = 0;

            while (haystack.charAt(i++) == needle.charAt(matchedLength++)) {    // O(n * m)
                if (matchedLength == needle.length()) {
                    return i - matchedLength;
                }
            }

            i -= matchedLength - 1;
        }

        return -1;
    }

    /**
     * Helper method to test the strStr function with expected output.
     *
     * @param haystack the string to search within
     * @param needle   the string to find
     * @param expected the expected index of the first occurrence
     */
    private static void test(String haystack, String needle, int expected) {
        int result = strStr(haystack, needle);
        System.out.printf("haystack: \"%s\", needle: \"%s\" | Result: %2d | Expected: %2d | %s%n",
                haystack,
                needle,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}