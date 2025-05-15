package leetcode.data_structures.array.sliding_window.count_subarrays_with_fixed_bounds;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #2444. Count Subarrays With Fixed Bounds.
 * <p>
 * You are given an integer array nums and two integers minK and maxK.
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-subarrays-with-fixed-bounds/">LeetCode 2444</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{1, 3, 5, 2, 7, 5}, 1, 5, 2);
		test(new int[]{1, 1, 1, 1}, 1, 1, 10);
		test(new int[]{5, 5, 5, 5}, 5, 5, 10);
		test(new int[]{1, 2, 3, 4, 5}, 1, 5, 1);
		
		// Edge cases
		test(new int[]{}, 1, 5, 0);
		test(new int[]{1}, 1, 1, 1);
		test(new int[]{2}, 1, 3, 0);
	}
	
	/**
	 * Counts the number of subarrays where min is minK and max is maxK.
	 *
	 * @param nums The input array of integers
	 * @param minK The minimum value required in subarrays
	 * @param maxK The maximum value required in subarrays
	 * @return The count of valid subarrays
	 */
	public static long countSubarrays(int[] nums, int minK, int maxK) {
		long result = 0;
		int minPos = -1;
		int maxPos = -1;
		int leftBound = -1;
		
		for (int i = 0; i < nums.length; i++) {
			// Reset window if current number is outside [minK, maxK]
			if (nums[i] < minK || nums[i] > maxK) {
				leftBound = i;
				minPos = -1;
				maxPos = -1;
				continue;
			}
			
			if (nums[i] == minK) minPos = i;
			if (nums[i] == maxK) maxPos = i;
			
			// minK AND maxK are in current window
			if (minPos != -1 && maxPos != -1) {
				// Number of valid subarrays ending at i
				result += Math.min(minPos, maxPos) - leftBound;
			}
		}
		
		return result;
	}
	
	/**
	 * Helper method to test the solution with expected output.
	 *
	 * @param nums     The input array
	 * @param minK     The minimum value
	 * @param maxK     The maximum value
	 * @param expected The expected result
	 */
	private static void test(int[] nums, int minK, int maxK, long expected) {
		long result = countSubarrays(nums, minK, maxK);
		String status = result == expected ? "✓ PASS" : "✗ FAIL";
		
		System.out.printf("Input: %-20s | minK: %2d | maxK: %2d | Result: %3d | Expected: %3d | %s%n",
				Arrays.toString(nums), minK, maxK, result, expected, status);
	}
}
