package leetcode.algorithms.math.happy_number;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution for LeetCode problem <b>#202. Happy Number.</b>
 * <p>
 * Write an algorithm to determine if a number {@code n} is happy.
 * A <b>happy number</b> is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it <b>loops endlessly in a cycle</b> which does not include 1.
 * Those numbers for which this process <b>ends in 1</b> are happy.
 * <p>
 * Return {@code true} if {@code n} is a happy number, and {@code false} if not.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/happy-number/">LeetCode 202</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(19, true);
		test(2, false);
		test(7, true);   // 7 -> 49 -> 97 -> 130 -> 10 -> 1
		test(1, true);   // Already 1
		test(4, false);  // 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4 (cycle)
		test(10, true);  // 10 -> 1
		test(11, false); // 11 -> 2 -> 4 (cycle)
	}
	
	/**
	 * Determines if a number is a "happy number" using Floyd's Cycle-Finding algorithm.
	 * This algorithm is used to detect if the sequence of sums of squared digits enters a cycle
	 * that does not include 1.
	 *
	 * @param n the positive integer to check
	 * @return true if n is a happy number, false otherwise
	 */
	public static boolean isHappy(int n) {
		// Floyd's Cycle-Finding to detect unhappy numbers
		int slow = n;   // Moves one step at a time
		int fast = n;   // Moves two steps at a time
		
		do {
			slow = sumSquaredDigits(slow);
			fast = sumSquaredDigits(sumSquaredDigits(fast));
		} while (slow != fast && slow != 1);
		
		return slow == 1; // If pointers meet at 1, the number is happy. Otherwise, a cycle was found.
	}
	
	/**
	 * Calculates the sum of the squares of the digits of a given number.
	 *
	 * @param n the integer whose digits' squares are to be summed
	 * @return the sum of the squares of its digits
	 */
	public static int sumSquaredDigits(int n) {
		int sum = 0;
		
		while (n > 0) {
			int digit = n % 10; // Get the last digit
			sum += digit * digit; // Add its square to the sum
			n /= 10; // Remove the last digit
		}
		
		return sum;
	}
	
	/**
	 * Determines if a number is "happy" using a straightforward approach
	 * with O(n) additional space to store calculated numbers and detect cycles.
	 *
	 * @param n the integer whose digits' squares are to be summed
	 * @return the sum of the squares of its digits
	 */
	public boolean isHappyFirstTry(int n) {
		Set<Integer> seenNumbers = new HashSet<>();
		
		while (n != 1 && seenNumbers.add(n)) {
			int currentSum = 0;
			
			while (n > 0) {
				int currentDigit = n % 10;
				currentSum += currentDigit * currentDigit;
				n /= 10;
			}
			
			n = currentSum;
		}
		
		return n == 1;
	}
	
	/**
	 * Helper method to test the {@code isHappy} function with expected output.
	 *
	 * @param n the input number
	 * @param expected the expected result
	 */
	private static void test(int n, boolean expected) {
		boolean result = isHappy(n);
		System.out.printf("Input: %-5d | Result: %-5b | Expected: %-5b | %s%n",
				n,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
