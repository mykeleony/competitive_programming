package leetcode.data_structures.hash_table.two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem #1. Two Sum.
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/two-sum/">LeetCode 1</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1});
		test(new int[]{3, 2, 4}, 6, new int[]{1, 2});
		test(new int[]{3, 3}, 6, new int[]{0, 1});
		test(new int[]{1, 2, 3}, 4, new int[]{0, 2});
		test(new int[]{2, 5, 5, 11}, 10, new int[]{1, 2});
		
		// Edge cases
		test(new int[]{-1, -2, -3, -4, -5}, -8, new int[]{2, 4});
	}
	
	/**
	 * Finds the indices of two numbers in the array that add up to the target sum.
	 * It uses a HashMap to store the complement of each number and its index, allowing for efficient lookup.
	 *
	 * @param nums   the array of integers
	 * @param target the target sum
	 * @return an array containing the indices of the two numbers that add up to the target
	 */
	public static int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return new int[]{};
		}
		
		Map<Integer, Integer> complementIndexMap = new HashMap<>();
		int[] addends = new int[2];
		
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			
			if (complementIndexMap.containsKey(complement)) {
				addends[0] = i;
				addends[1] = complementIndexMap.get(complement);
				return addends;
			}
			
			complementIndexMap.put(nums[i], i);
		}
		
		return addends;
	}
	
	/**
	 * Helper method to test the twoSum function with expected output.
	 *
	 * @param nums     the input array of integers
	 * @param target   the target sum
	 * @param expected the expected array of indices
	 */
	private static void test(int[] nums, int target, int[] expected) {
		int[] result = twoSum(nums, target);
		Arrays.sort(result);
		Arrays.sort(expected);
		System.out.printf("Input: nums = %-15s, target = %-3d | Result: [%2d, %2d] | Expected: [%2d, %2d] | %s%n",
				Arrays.toString(nums),
				target,
				result[0], result[1],
				expected[0], expected[1],
				Arrays.equals(result, expected) ? "✓ PASS" : "✗ FAIL");
	}
}
