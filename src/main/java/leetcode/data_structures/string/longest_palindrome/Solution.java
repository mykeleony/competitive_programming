package leetcode.data_structures.string.longest_palindrome;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution for LeetCode problem #409. Longest Palindrome.
 * <p>
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome
 * that can be built with those letters. Letters are case-sensitive, for example, "Aa" is not considered a palindrome.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/longest-palindrome/">LeetCode 409</a>
 */
public class Solution {
	public static void main(String[] args) {
		test("abccccdd", 7);
		test("a", 1);
		test("Aa", 1);
		test("abccccddA", 7);
		
		// Edge cases
		test(null, 0);
		test("", 0);
		test("bb", 2);
		test("ccc", 3);
	}
	
	/**
	 * Calculates the length of the longest palindrome that can be built from the given string.
	 * It counts the number of characters that appear an odd number of times.
	 *
	 * @param s the input string
	 * @return the length of the longest palindrome
	 */
	public static int longestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		
		Set<Character> singleChars = new HashSet<>();
		
		for (char ch : s.toCharArray()) {
			if (!singleChars.add(ch)) {
				singleChars.remove(ch);
			}
		}
		
		return s.length() - singleChars.size() + (singleChars.isEmpty() ? 0 : 1);
	}
	
	public static int longestPalindromeFirstTry(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		
		Map<Character, Integer> charCount = new HashMap<>();
		
		for (char ch : s.toCharArray()) {
			charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
		}
		
		boolean hasSingleChar = false;
		int longestPalindrome = 0;
		
		for (int count : charCount.values()) {
			if (count % 2 == 0) {
				longestPalindrome += count;
			} else {
				if (count > 1) {
					longestPalindrome += count - 1;
				}
				
				hasSingleChar = true;
			}
		}
		
		if (hasSingleChar) {
			longestPalindrome++;
		}
		
		return longestPalindrome;
	}
	
	private static void test(String s, int expected) {
		int result = longestPalindrome(s);
		System.out.printf("Input: \"%s\" | Result: %2d | Expected: %2d | %s%n",
				s,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
