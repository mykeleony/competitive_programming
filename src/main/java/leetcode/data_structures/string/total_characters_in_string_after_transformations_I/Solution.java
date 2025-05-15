package leetcode.data_structures.string.total_characters_in_string_after_transformations_I;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #3335. Total Characters in String After Transformations I.
 * <p>
 * You are given a string s and an integer t, representing the number of transformations to perform.
 * In one transformation, every character in s is replaced according to the following rules:
 * If the character is 'z', replace it with the string "ab".
 * Otherwise, replace it with the next character in the alphabet.
 * Return the length of the resulting string after exactly t transformations, modulo 10^9 + 7.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/total-characters-in-string-after-transformations-i/">LeetCode 3335</a>
 */
public class Solution {
	private static final int MOD = 1_000_000_007;
	private static final int ALPHABET_SIZE = 26;
	
	public static void main(String[] args) {
		test("xp", 8, 3);
		test("abcyy", 2, 7);
		test("azbk", 1, 5);
		test("a", 1, 1);
		test("z", 1, 2);
		test("z", 2, 2);
		test("z", 3, 2);
		test("a", 100, 8);
		test("zz", 1, 4);
		
		// Edge cases
		test(null, 1, 0);
		test("", 1, 0);
		test("valid", 0, 0);
	}
	
	/**
	 * Calculates the length of the string after t transformations.
	 * It simulates the transformations by tracking the counts of each character.
	 *
	 * @param s the initial string
	 * @param t the number of transformations to perform
	 * @return the length of the resulting string after t transformations, modulo 10^9 + 7
	 */
	public static int lengthAfterTransformations(String s, int t) {
		if (s == null || s.isEmpty() || t < 1) return 0;
		
		long[] charCounts = new long[ALPHABET_SIZE];
		
		// Count initial occurrences of each character
		for (char c : s.toCharArray()) {
			charCounts[c - 'a'] += 1L;
		}
		
		// Perform t transformations
		for (int i = 0; i < t; i++) {
			long[] nextCounts = new long[ALPHABET_SIZE];
			
			for (int charIndex = 0; charIndex < ALPHABET_SIZE; charIndex++) {
				long currentOccurrences = charCounts[charIndex];
				
				if (currentOccurrences == 0) {
					continue;
				}
				
				// Transformation rule: 'z' becomes "ab"
				if (charIndex == ALPHABET_SIZE - 1) {
					nextCounts[0] = (nextCounts[0] + currentOccurrences) % MOD;
					nextCounts[1] = (nextCounts[1] + currentOccurrences) % MOD;
				} else {
					// Transformation rule: other characters become the next in the alphabet
					int nextCharIndex = charIndex + 1;
					nextCounts[nextCharIndex] = (nextCounts[nextCharIndex] + currentOccurrences) % MOD;
				}
			}
			
			charCounts = nextCounts;
		}
		
		// Calculate the total length after transformations
		return (int) Arrays.stream(charCounts, 0, ALPHABET_SIZE)
				             .reduce(0, (a, b) -> (a + b) % MOD);
	}
	
	private static void test(String s, int t, int expected) {
		int result = lengthAfterTransformations(s, t);
		System.out.printf("Input: s = \"%s\", t = %-2d | Result: %-3d | Expected: %-3d | %s%n",
				s,
				t,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}