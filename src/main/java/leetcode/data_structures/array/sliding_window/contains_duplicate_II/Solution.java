package leetcode.data_structures.array.sliding_window.contains_duplicate_II;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for LeetCode problem #219. Contains Duplicate II.
 * <p>
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that
 * nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/contains-duplicate-ii/">LeetCode 219</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{1, 2, 3, 1}, 3, true);
		test(new int[]{1, 0, 1, 1}, 1, true);
		test(new int[]{1, 2, 3, 1, 2, 3}, 2, false);
		
		// Edge cases
		test(null, 0, false);
		test(new int[]{}, 0, false);
		test(new int[]{1}, 0, false);
		test(new int[]{1, 1}, 0, false);
		test(new int[]{1, 1}, 1, true);
		test(new int[]{1, 2}, 1, false);
		test(new int[]{1, 2, 1}, 1, false);
		test(new int[]{1, 2, 1}, 2, true);
	}
	
	/**
	 * Checks if there are any two distinct indices i and j in the array such that nums[i] == nums[j] and the absolute difference between their indices is at most k.
	 * It uses a HashMap to store the last seen index of each number.
	 *
	 * @param nums the input array of integers
	 * @param k    the maximum allowed difference between the indices of duplicate numbers
	 * @return true if such a pair of indices exists, false otherwise
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length < 1 || k < 0) {
			return false;
		}
		
		Map<Integer, Integer> lastSeenIndexes = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			Integer lastIndex = lastSeenIndexes.get(nums[i]);
			
			if (lastIndex != null && Math.abs(lastIndex - i) <= k) {
				return true;
			}
			
			lastSeenIndexes.put(nums[i], i);
		}
		
		return false;
	}
	
	private static void test(int[] nums, int k, boolean expected) {
		boolean result = containsNearbyDuplicate(nums, k);
		System.out.printf("Input: %-20s, k: %-3d | Result: %-5b | Expected: %-5b | %s%n",
				Arrays.toString(nums),
				k,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}