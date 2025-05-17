package leetcode.data_structures.hash_table.number_of_equivalent_domino_pairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem #1128. Number of Equivalent Domino Pairs.
 * <p>
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a == c and b == d),
 * or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/number-of-equivalent-domino-pairs/">LeetCode 1128</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}, 1);
		test(new int[][]{{1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}}, 10);
		test(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}}, 0);
		
		// Edge cases
		test(null, 0);
		test(new int[][]{}, 0);
		test(new int[][]{{1, 1}}, 0);
		test(new int[][]{{1, 1}, {1, 1}}, 1);
		test(new int[][]{{1, 2}, {1, 2}, {2, 1}}, 3);
		test(new int[][]{{1, 2}, {2, 1}, {1, 2}, {2, 1}}, 6);
	}
	
	/**
	 * Calculates the number of equivalent domino pairs in the given list.
	 * It uses a HashMap to store the counts of each unique domino pair (represented in a canonical form).
	 *
	 * @param dominoes the list of dominoes
	 * @return the number of equivalent domino pairs
	 */
	public static int numEquivDominoPairs(int[][] dominoes) {
		if (dominoes == null) return 0;
		
		Map<String, Integer> pairs = new HashMap<>();
		int count = 0;
		
		for (int[] domino : dominoes) {
			String pair = Math.min(domino[0], domino[1]) + "," + Math.max(domino[0], domino[1]);
			
			// Add the number of existing equivalent pairs to the total count
			count += pairs.getOrDefault(pair, 0);
			
			pairs.put(pair, pairs.getOrDefault(pair, 0) + 1);
		}
		
		return count;
	}
	
	/**
	 * Helper method to test the numEquivDominoPairs function with expected output.
	 *
	 * @param dominoes the input list of dominoes
	 * @param expected the expected number of equivalent domino pairs
	 */
	private static void test(int[][] dominoes, int expected) {
		int result = numEquivDominoPairs(dominoes);
		System.out.printf("Input: %-30s | Result: %2d | Expected: %2d | %s%n",
				Arrays.deepToString(dominoes),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}