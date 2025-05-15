package leetcode.algorithms.dynamic_programming.fibonacci_number;

/**
 * Solution for LeetCode problem #509. Fibonacci Number.
 * <p>
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, usually starting with 0 and 1. That is,
 * <p>
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/fibonacci-number/">LeetCode 509</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(-1, 0);
		test(0, 0);
		test(1, 1);
		test(2, 1);
		test(3, 2);
		test(4, 3);
		test(5, 5);
		test(10, 55);
		test(20, 6765);
		test(30, 832040);
	}
	
	/**
	 * Calculates the nth Fibonacci number using an iterative approach with constant space complexity.
	 *
	 * @param n the index of the desired Fibonacci number
	 * @return the nth Fibonacci number
	 */
	public static int fib(int n) {
		if (n <= 0) return 0;
		
		int fibNMinus2 = 1;
		int fibNMinus1 = 1;     // O(1) space complexity
		
		for (int i = 2; i < n; i++) {   // O(n) time complexity
			int currentFib = fibNMinus1;
			
			fibNMinus1 += fibNMinus2;
			fibNMinus2 = currentFib;
		}
		
		return fibNMinus1;
	}
	
	private static void test(int n, int expected) {
		int result = fib(n);
		
		System.out.printf("Input: %-2d | Result: %-10d | Expected: %-10d | %s%n",
				n,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}