package leetcode.data_structures.hash_table.longest_palindrome_by_concatenating_two_letter_words;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem <b>#2131. Longest Palindrome by Concatenating Two Letter Words.</b>
 * <p>
 * You are given an array of strings {@code words}. Each element of {@code words} consists of two lowercase English letters.
 * <p>
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in <b>any order</b>.
 * Each element can be selected <b>at most once</b>.
 * <p>
 * Return <i>the <b>length</b> of the longest palindrome that you can create</i>. If it is impossible to create any palindrome, return {@code 0}.
 * <p>
 * A <b>palindrome</b> is a string that reads the same forward and backward.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/">LeetCode 2131</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new String[]{"lc", "cl", "gg"}, 6);
		test(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"}, 8);
		test(new String[]{"cc", "ll", "xx"}, 2);
		test(new String[]{"dd", "aa", "bb", "dd", "aa"}, 10); // dd-dd (4), aa-aa (4), bb (center 2)
		test(new String[]{"gg", "gg", "gg"}, 6); // gg-gg (4), gg (center 2)
		test(new String[]{"gg", "gg", "gg", "gg"}, 8); // two gg-gg pairs
		test(new String[]{"ab", "cd", "ef"}, 0);
		test(new String[]{"aa", "bb"}, 2); // aa (center 2) or bb (center 2)
		test(new String[]{"ab", "ba", "cd", "dc", "ee"}, 10); // ab-ba (4), cd-dc (4), ee (center 2)
		
		// Edge cases
		test(null, 0);
		test(new String[]{}, 0);
		test(new String[]{"aa"}, 2);
		test(new String[]{"ab"}, 0);
	}
	
	/**
	 * Calculates the length of the longest palindrome that can be formed by concatenating two-letter words.
	 * It uses a HashMap to count occurrences of words and their complements (reversed words).
	 * Pairs of complementary words (e.g., "lc" and "cl") contribute to the palindrome length.
	 * A single word with identical characters (e.g., "gg") can be placed in the center if available.
	 *
	 * @param words an array of strings, where each string consists of two lowercase English letters.
	 * @return the length of the longest possible palindrome.
	 */
	public static int longestPalindrome(String[] words) {
		if (words == null || words.length < 1) {
			return 0;
		}
		
		Map<String, Integer> wordComplementCount = new HashMap<>();
		int palindromeLength = 0;
		
		for (String word : words) {
			// If the word is a needed complement, a pair was found
			if (wordComplementCount.containsKey(word)) {
				palindromeLength += word.length() * 2;
				int updatedWordCount = wordComplementCount.get(word) - 1;
				
				if (updatedWordCount == 0) {
					wordComplementCount.remove(word);
				} else {
					wordComplementCount.put(word, updatedWordCount);
				}
				
			} else {
				String complementWord = new StringBuilder(word).reverse().toString();
				
				wordComplementCount.put(complementWord,
						wordComplementCount.getOrDefault(complementWord, 0) + 1);
			}
		}
		
		// After forming pairs, check for a palindromic word to place in the center
		for (String remainingWord : wordComplementCount.keySet()) {
			if (isSingleCharString(remainingWord)) {
				palindromeLength += remainingWord.length();
				break;  // Only one such word can be used in the center
			}
		}
		
		return palindromeLength;
	}
	
	/**
	 * Checks if a string consists of only one distinct character repeated.
	 *
	 * @param s the input string.
	 * @return true if the string has identical characters (e.g., "aa"), false otherwise.
	 */
	public static boolean isSingleCharString(String s) {
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Helper method to test the longestPalindrome function.
	 *
	 * @param words    the input array of words.
	 * @param expected the expected length of the longest palindrome.
	 */
	private static void test(String[] words, int expected) {
		int result = longestPalindrome(words);
		
		System.out.printf("Input: %-30s | Result: %2d | Expected: %2d | %s%n",
				Arrays.toString(words),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
