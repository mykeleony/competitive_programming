package leetcode.data_structures.string.group_anagrams;

import java.util.*;

/**
 * Solution for LeetCode problem #49. Group Anagrams.
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/group-anagrams/">LeetCode 49</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat")));
        test(new String[]{""}, List.of(List.of("")));
        test(new String[]{"a"}, List.of(List.of("a")));
        test(new String[]{"ddddddddddg", "dgggggggggg"}, List.of(List.of("ddddddddddg"), List.of("dgggggggggg")));

        // Edge cases
        test(new String[]{}, Collections.emptyList());
        test(new String[]{"abc", "bca", "cab"}, List.of(List.of("abc", "bca", "cab")));
        test(new String[]{"listen", "silent"}, List.of(List.of("listen", "silent")));
    }

    /**
     * Groups the anagrams from the input array of strings.
     * It uses a HashMap where the key is a character count representation of the string (to identify anagrams)
     * and the value is a list of strings that are anagrams of each other.
     *
     * @param strs the array of strings to group
     * @return a list of lists, where each inner list contains a group of anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] count = new char[26];

            for (char c : s.toCharArray()) count[c - 'a']++;

            String key = String.valueOf(count);

            // Add the current string to the list associated with its anagram group
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Helper method to test the groupAnagrams function with expected output.
     *
     * @param strs     the input array of strings
     * @param expected the expected list of lists of anagrams
     */
    private static void test(String[] strs, List<List<String>> expected) {
        List<List<String>> result = groupAnagrams(strs);
        boolean pass = result.size() == expected.size() && expected.containsAll(result) && result.containsAll(expected);
        System.out.printf("Input: %-30s | Result: %-40s | Expected: %-40s | %s%n",
                Arrays.toString(strs),
                result,
                expected,
                pass ? "✓ PASS" : "✗ FAIL");
    }
}