package leetcode.algorithms.dynamic_programming.longest_unequal_adjacent_groups_subsequence_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Solution for LeetCode problem #2901. Longest Unequal Adjacent Groups Subsequence II.
 * <p>
 * You are given a string array words and a binary array groups both of length n, where words[i] is associated with groups[i].
 * <p>
 * Your task is to select the longest alternating subsequence from words. A subsequence of words is alternating if for any two consecutive strings in the sequence, their corresponding elements in the binary array groups differ, and the Hamming distance between them is 1.
 * <p>
 * Return the selected subsequence. If there are multiple answers, return any of them.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/">LeetCode 2901</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new String[]{"e", "a", "b"}, new int[]{0, 0, 1}, List.of("e", "b"));
		test(new String[]{"a", "b", "c", "d"}, new int[]{1, 2, 3, 4}, List.of("a", "b", "c", "d"));
		test(new String[]{"bab","dab","cab"}, new int[]{1,2,2}, List.of("bab","dab"));
		test(new String[]{"adbe","acace"}, new int[]{0, 1, 0, 1}, List.of("adbe"));
		test(new String[]{"abc", "def", "ghi"}, new int[]{1, 1, 1}, List.of("abc"));
		test(new String[]{"bdb","aaa","ada"}, new int[]{2, 1, 3}, List.of("aaa","ada"));
	}
	
	/**
	 * Finds the longest subsequence of words where adjacent words have different groups and a Hamming distance of 1.
	 *
	 * @param words  The array of words.
	 * @param groups The array of group assignments for each word.
	 * @return The longest subsequence of words that meets the criteria.
	 */
	public static List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
		int n = words.length;
		
		// Stores the length of the longest valid subsequence ending at index i.
		int[] dp = new int[n];
		// Stores the index of the previous word in the longest valid subsequence ending at index i.
		int[] prev = new int[n];
		
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			prev[i] = -1;
		}
		
		int maxLength = 0;
		int endIndex = -1;
		
		// Iterate through the words to find the longest subsequence
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (groups[i] != groups[j]
						    && isHammingDistanceOne(words[i], words[j])
						    && (dp[j] + 1 > dp[i])) {
					dp[i] = dp[j] + 1;
					prev[i] = j;
				}
			}
			
			// Update the maximum length and the ending index of the longest subsequence
			if (dp[i] > maxLength) {
				maxLength = dp[i];
				endIndex = i;
			}
		}
		
		// Build the longest subsequence from the prev array
		List<String> longestSubsequence = new ArrayList<>();
		if (endIndex != -1) {
			int currentIndex = endIndex;
			
			while (currentIndex != -1) {
				longestSubsequence.add(words[currentIndex]);
				currentIndex = prev[currentIndex];
			}
			
			Collections.reverse(longestSubsequence); // Reverse to get the correct order
		}
		
		return longestSubsequence;
	}
	
	/**
	 * Calculates the Hamming distance between two strings.
	 *
	 * @param s1 the first string.
	 * @param s2 the second string.
	 * @return true if the Hamming distance is 1, false otherwise.
	 */
	private static boolean isHammingDistanceOne(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		
		int diffCharsCount = 0;
		
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i) && (++diffCharsCount > 1)) {
				return false;
			}
		}
		
		return diffCharsCount == 1;
	}
	
	private static void test(String[] words, int[] groups, List<String> expected) {
		List<String> result = getWordsInLongestSubsequence(words, groups);
		
		System.out.printf("words: %-30s, groups: %-15s | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(words),
				Arrays.toString(groups),
				result,
				expected,
				result.equals(expected) ? "✓ PASS" : "✗ FAIL");
	}
}
