package leetcode.data_structures.array.default_approach.maximum_difference_between_increasing_elements;

import java.util.Arrays;

/**
 * Solution for LeetCode problem <b>#2016. Maximum Difference Between Increasing Elements.</b>
 * <p>
 * Given a <b>0-indexed</b> integer array {@code nums} of size {@code n}, find the <b>maximum difference</b> between {@code nums[i]} and {@code nums[j]}
 * such that {@code 0 <= i < j < n} and {@code nums[i] < nums[j]}.
 * <p>
 * Return the <i><b>maximum difference</b></i>. If no such {@code i} and {@code j} exist, return {@code -1}.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/">LeetCode 2016</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{7, 1, 5, 4}, 4);
		test(new int[]{9, 4, 3, 2}, -1);
		test(new int[]{1, 5, 2, 10}, 9);
		test(new int[]{1, 2, 3, 4, 5}, 4);
		test(new int[]{5, 4, 3, 2, 1}, -1);
		test(new int[]{10, 1, 10, 2, 10}, 9);
		test(new int[]{0, 0, 0, 0}, -1); // All same values
		
		// Edge cases
		test(new int[]{1, 10}, 9); // Two elements, increasing
		test(new int[]{10, 1}, -1); // Two elements, decreasing
		test(new int[]{1, 1}, -1); // Two elements, same
	}
	
	/**
	 * Finds the maximum difference between two elements nums[i] and nums[j]
	 * such that i < j and nums[i] < nums[j].
	 * <p>
	 * This solution iterates through the array, keeping track of the minimum
	 * element encountered so far. For each number, it calculates the difference
	 * with the current minimum and updates the maximum difference found.
	 * <p>
	 * Time Complexity: O(n), where n is the length of the nums array.
	 * Space Complexity: O(1).
	 *
	 * @param nums the input array of integers.
	 * @return the maximum difference, or -1 if no such pair exists.
	 */
	public static int maximumDifference(int[] nums) {
		int minNum = Integer.MAX_VALUE;
		int maxDiff = -1;
		
		for (int currentNum : nums) {
			minNum = Math.min(currentNum, minNum);
			
			if (currentNum > minNum) {
				maxDiff = Math.max(currentNum - minNum, maxDiff);
			}
		}
		
		return maxDiff;
	}
	
	/**
	 * Helper method to test the maximumDifference function.
	 *
	 * @param nums     the input array of integers.
	 * @param expected the expected maximum difference.
	 */
	private static void test(int[] nums, int expected) {
		int result = maximumDifference(nums);
		System.out.printf("Input: %-15s | Result: %-5d | Expected: %-5d | %s%n",
				Arrays.toString(nums),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
