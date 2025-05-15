package leetcode.algorithms.dynamic_programming.domino_and_tromino_tiling;

/**
 * Solution for LeetCode problem #790. Domino and Tromino Tiling.
 * <p>
 * You have two types of tiles: a 2x1 domino shape and a L-tromino shape. Both shapes may be rotated.
 * Given an integer n, return the number of ways to tile a 2 x n board.
 * Since the number of ways may be very large, return it modulo 10^9 + 7.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/domino-and-tromino-tiling/">LeetCode 790</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		test(1, 1);
		test(2, 2);
		test(3, 5);
		test(4, 11);
		test(5, 24);
		test(6, 53);
	}
	
	/**
	 * Calculates the number of ways to tile a 2 x n board using domino and tromino tiles.
	 * It uses dynamic programming to build up the solution based on smaller subproblems.
	 * The recurrence relation used is T(n) = 2 * T(n - 1) + T(n - 3).
	 *
	 * @param n the length of the 2 x n board
	 * @return the number of ways to tile the board modulo 10^9 + 7
	 */
	public static int numTilings(int n) {
		// Base cases
		if (n == 1) return 1;
		if (n == 2) return 2;
		
		int[] dp = new int[n + 1];
		
		// Modulo value to prevent integer overflow
		int mod = (int) Math.pow(10, 9) + 7;
		
		dp[0] = 1; // Represents an empty board (for recurrence relation)
		dp[1] = 1; // One way to tile a 1x2 board
		dp[2] = 2; // Two ways to tile a 2x2 board
		
		// T(n) = 2 * T(n - 1) + T(n - 3)
		for (int i = 3; i <= n; i++) {
			dp[i] = ((2 * dp[i - 1]) % mod + dp[i - 3]) % mod;
		}
		
		return dp[n];
	}
	
	private static void test(int n, int expected) {
		int result = numTilings(n);
		System.out.printf("Input: %-2d | Result: %-10d | Expected: %-10d | %s%n",
				n,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}