package leetcode.longest_unequal_adjacent_groups_subsequence_I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Solution for LeetCode problem #2900. Longest Unequal Adjacent Groups Subsequence I.
 * <p>
 * You are given a string array words and a binary array groups both of length n, where words[i] is associated with groups[i].
 * Your task is to select the longest alternating subsequence from words. A subsequence of words is alternating if for any two
 * consecutive strings in the sequence, their corresponding elements in the binary array groups differ.
 * <p>
 * Return the selected subsequence. If there are multiple answers, return any of them.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/">LeetCode 2900</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new String[]{"e", "a", "b"}, new int[]{0, 0, 1}, List.of("e", "b"));
		test(new String[]{"a", "b", "c", "d"}, new int[]{1, 0, 1, 1}, List.of("a", "b", "c"));
		test(new String[]{"a", "a", "b", "b"}, new int[]{0, 1, 0, 1}, List.of("a", "a", "b", "b"));
		test(new String[]{"a", "b", "c"}, new int[]{0, 0, 0}, List.of("a"));
		test(new String[]{"a", "b", "c"}, new int[]{1, 1, 1}, List.of("a"));
		test(new String[]{"a"}, new int[]{0}, List.of("a"));
		
		// Edge cases
		test(new String[]{}, new int[]{}, Collections.emptyList());
		test(null, new int[]{}, Collections.emptyList());
		test(new String[]{}, null, Collections.emptyList());
		test(new String[]{"invalid", "string", "amount"}, new int[]{0, 1}, Collections.emptyList());
	}
	
	/**
	 * Selects the longest alternating subsequence from words based on the groups array.
	 * An alternating subsequence has adjacent words whose corresponding groups have different values.
	 *
	 * @param words  the array of words
	 * @param groups the array of group identifiers (0 or 1)
	 * @return the longest alternating subsequence of words
	 */
	public static List<String> getLongestSubsequence(String[] words, int[] groups) {
		List<String> longestSubsequence = new ArrayList<>();
		
		if (words == null || groups == null || words.length < 1
				    || groups.length != words.length) {
			return longestSubsequence;
		}
		
		longestSubsequence.add(words[0]);
		int previousIndex = 0;
		
		for (int index = 1; index < groups.length; index++) {
			// Checks if the current group is different from the previous word's group
			if (groups[index] != groups[previousIndex]) {
				previousIndex = index;
				longestSubsequence.add(words[index]);
			}
		}
		
		return longestSubsequence;
	}
	
	private static void test(String[] words, int[] groups, List<String> expected) {
		List<String> result = getLongestSubsequence(words, groups);
		System.out.printf("words: %-30s, groups: %-15s | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(words),
				Arrays.toString(groups),
				result,
				expected,
				result.equals(expected) ? "✓ PASS" : "✗ FAIL");
	}
}