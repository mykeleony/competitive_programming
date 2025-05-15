package leetcode.algorithms.greedy.minimum_domino_rotations_for_equal_row;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem #1007. Minimum Domino Rotations For Equal Row.
 * <p>
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 *
 * <p>
 * Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
 * If it cannot be done, return -1.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/">LeetCode 1007</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}, 2);
		test(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}, -1);
		test(new int[]{2, 3, 2, 1, 1, 1, 2, 2}, new int[]{2, 1, 2, 1, 1, 3, 1, 1}, -1);
		test(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}, 0);
		test(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}, -1);
		
		// Edge cases
		test(new int[]{1}, new int[]{1}, 0);
		test(new int[]{1}, new int[]{2}, 0);
		test(new int[]{1, 1}, new int[]{1, 2}, 0);
		test(new int[]{1, 2}, new int[]{1, 1}, 0);
		test(new int[]{1, 2}, new int[]{3, 4}, -1);
	}
	
	/**
	 * Calculates the minimum number of rotations to make either the top or bottom row have all the same values.
	 * It checks if it's possible to make the top row all the same as the first top element or the bottom row all the same as the first bottom element.
	 *
	 * @param tops    the array representing the top faces of the dominoes
	 * @param bottoms the array representing the bottom faces of the dominoes
	 * @return the minimum number of rotations, or -1 if it's not possible
	 */
	public static int minDominoRotations(int[] tops, int[] bottoms) {
		int rotations = countRotations(tops, bottoms, tops[0]);
		
		if (rotations != -1 || tops[0] == bottoms[0]) return rotations;
		
		return countRotations(tops, bottoms, bottoms[0]);
	}
	
	/**
	 * Helper function to count the minimum rotations needed to make either the top or bottom row equal to the candidate value.
	 *
	 * @param tops      the array of top faces
	 * @param bottoms   the array of bottom faces
	 * @param candidate the target value to make a row equal to
	 * @return the minimum rotations needed, or -1 if it's not possible
	 */
	private static int countRotations(int[] tops, int[] bottoms, int candidate) {
		int topRotations = 0;
		int downRotations = 0;
		
		for (int i = 0; i < tops.length; i++) {
			if (tops[i] != candidate && bottoms[i] != candidate) return -1;
			if (tops[i] != candidate) topRotations++;
			if (bottoms[i] != candidate) downRotations++;
		}
		
		return Math.min(topRotations, downRotations);
	}
	
	/**
	 * First attempt at solving the problem, involving counting occurrences and checking conditions.
	 * This approach might be less efficient or more complex than the final solution.
	 *
	 * @param tops    the array of top faces
	 * @param bottoms the array of bottom faces
	 * @return the minimum rotations needed, or -1 if not possible
	 */
	public int minDominoRotationsFirstTry(int[] tops, int[] bottoms) {
		Map<Integer, Integer> topCount = new HashMap<>();
		Map<Integer, Integer> bottomCount = new HashMap<>();
		int doublet = -1;
		int n = tops.length;
		
		for (int i = 0; i < tops.length; i++) {
			if (tops[i] == bottoms[i]) {
				if (doublet < 0) doublet = tops[i];
				if (tops[i] != doublet) return -1;
				n--;
			} else if (doublet < 0 || tops[i] == doublet || bottoms[i] == doublet) {
				int top = topCount.getOrDefault(tops[i], 0) + 1;
				int bot = bottomCount.getOrDefault(bottoms[i], 0) + 1;
				
				topCount.put(tops[i], top);
				bottomCount.put(bottoms[i], bot);
				
				if (top == n || bot == n) return 0;
			} else return -1;
		}
		
		if (n == 0) return 0;
		if (doublet > 0 && (topCount.getOrDefault(doublet, 0) + bottomCount.getOrDefault(doublet, 0) < n)) return -1;
		
		int minRotations = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Integer> topCountEntry : new HashMap<>(topCount).entrySet()) {
			int up = topCountEntry.getValue();
			int bot = bottomCount.getOrDefault(topCountEntry.getKey(), 0);
			
			if (up + bot >= n) {
				int rotations = Math.min(n - up, n - bot);
				minRotations = Math.min(minRotations, rotations);
			}
		}
		
		return minRotations <= n ? minRotations : -1;
	}
	
	private static void test(int[] tops, int[] bottoms, int expected) {
		int result = minDominoRotations(tops, bottoms);
		System.out.printf("tops: %-15s, bottoms: %-15s | Result: %2d | Expected: %2d | %s%n",
				Arrays.toString(tops),
				Arrays.toString(bottoms),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}