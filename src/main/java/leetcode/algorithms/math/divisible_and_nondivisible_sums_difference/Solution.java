package leetcode.algorithms.math.divisible_and_nondivisible_sums_difference;

/**
 * Solution for LeetCode problem <b>#2894. Divisible and Non-divisible Sums Difference.</b>
 * <p>
 * You are given positive integers {@code n} and {@code m}.
 * <p>
 * Define two integers, num1 and num2, as follows:
 * <ul>
 *      <li>{@code num1}: The sum of all integers in the range {@code [1, n]} (both <b>inclusive</b>) that are not divisible by {@code m}.</li>
 *      <li>{@code num2}: The sum of all integers in the range {@code [1, n]} (both <b>inclusive</b>) that are divisible by {@code m}.</li>
 * </ul>
 * Return the integer {@code num1 - num2}.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/divisible-and-non-divisible-sums-difference">LeetCode 2894</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(10, 3, 19);
		test(5, 6, 15);
		test(1, 1, -1);
		test(7, 2, 4);
		test(10, 1, -55); // All numbers are divisible by 1
		test(10, 11, 55); // No numbers are divisible by 11
		
		// Edge cases
		test(1, 2, 1); // n=1, m=2. num1=1, num2=0. 1-0=1
		test(1, 1, -1); // n=1, m=1. num1=0, num2=1. 0-1=-1
	}
	
	/**
	 * Calculates the difference between the sum of numbers not divisible by m (num1)
	 * and the sum of numbers divisible by m (num2) in the range [1, n].
	 * <p>
	 * This solution uses mathematical formulas to compute sums efficiently without iteration.
	 * Sum of all numbers from 1 to n: `n * (n + 1) / 2`
	 * Sum of numbers divisible by m: `m * (1 + 2 + ... + p)` where `p = n / m` (integer division).
	 * This simplifies to `m * (p * (p + 1) / 2)`.
	 * <p>
	 * The desired result is `num1 - num2`.
	 * We know `num1 + num2 = totalSum`.
	 * So, `num1 = totalSum - num2`.
	 * Therefore, `num1 - num2 = (totalSum - num2) - num2 = totalSum - 2 * num2`.
	 * <p>
	 * Time Complexity: O(1)
	 * Space Complexity: O(1)
	 *
	 * @param n the upper bound of the range [1, n].
	 * @param m the divisor.
	 * @return the difference (num1 - num2).
	 */
	public static int differenceOfSums(int n, int m) {
		int totalSum = n * (n + 1) / 2; // Sum of all numbers from 1 to n
		int countMultiples = n / m;     // Number of multiples of m in the range [1, n]
		int sumMultiples = m * (countMultiples * (countMultiples + 1) / 2); // Sum of numbers divisible by m
		
		return totalSum - 2 * sumMultiples;
	}
	
	/**
	 * Calculates the difference between the sum of numbers not divisible by m (num1)
	 * and the sum of numbers divisible by m (num2) in the range [1, n].
	 * <p>
	 * This is a straightforward iterative approach.
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 *
	 * @param n the upper bound of the range [1, n].
	 * @param m the divisor.
	 * @return the difference (num1 - num2).
	 */
	public static int differenceOfSumsFirstTry(int n, int m) {
		int num1 = 0; // Sum of numbers not divisible by m
		int num2 = 0; // Sum of numbers divisible by m
		
		for (int i = 1; i <= n; i++) {
			if (i % m == 0) {
				num2 += i;
			} else {
				num1 += i;
			}
		}
		
		return num1 - num2;
	}
	
	/**
	 * Helper method to test the differenceOfSums function.
	 *
	 * @param n        the upper bound.
	 * @param m        the divisor.
	 * @param expected the expected result.
	 */
	private static void test(int n, int m, int expected) {
		int result = differenceOfSums(n, m);
		System.out.printf("Input: n = %-3d, m = %-3d | Result: %-5d | Expected: %-5d | %s%n",
				n,
				m,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
