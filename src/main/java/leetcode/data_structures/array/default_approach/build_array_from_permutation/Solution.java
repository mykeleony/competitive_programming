package leetcode.data_structures.array.default_approach.build_array_from_permutation;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #1920. Build Array from Permutation.
 * <p>
 * Given a zero-based permutation nums (0-indexed), build a new array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
 * A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/build-array-from-permutation/">LeetCode 1920</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		// Test cases for buildArray
		test(new int[]{0, 2, 1, 5, 3, 4}, new int[]{0, 1, 2, 4, 5, 3});
		test(new int[]{5, 0, 1, 2, 3, 4}, new int[]{4, 5, 0, 1, 2, 3});
		test(new int[]{0}, new int[]{0});
		test(new int[]{1, 0}, new int[]{0, 1});
	}
	
	/**
	 * Builds a new array ans where ans[i] = nums[nums[i]]. This solution modifies the input array in-place
	 * to achieve O(1) extra space complexity.
	 *
	 * @param nums the input permutation array
	 * @return the newly built array
	 */
	public static int[] buildArray(int[] nums) {
		int n = nums.length;
		
		// Encoding
		for (int i = 0; i < n; i++) {
			nums[i] = nums[i] + n * (nums[nums[i]] % n);
		}
		
		// Decoding
		for (int i = 0; i < n; i++) {
			nums[i] = nums[i] / n;
		}
		
		return nums;
	}
	
	public int[] buildArrayFirstTry(int[] nums) {
		int size = nums.length;
		int[] permutationArray = new int[size];
		
		for (int i = 0; i < size; i++) {
			permutationArray[i] = nums[nums[i]];
		}
		
		return permutationArray;
	}
	
	private static void test(int[] nums, int[] expected) {
		int[] result = buildArray(Arrays.copyOf(nums, nums.length));
		
		System.out.printf("Input: %-15s | Result: %-15s | Expected: %-15s | %s%n",
				Arrays.toString(nums),
				Arrays.toString(result),
				Arrays.toString(expected),
				Arrays.equals(result, expected) ? "✓ PASS" : "✗ FAIL");
	}
}