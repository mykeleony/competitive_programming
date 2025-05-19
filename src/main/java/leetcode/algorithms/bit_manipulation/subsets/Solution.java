package leetcode.algorithms.bit_manipulation.subsets;

import java.util.*;

import static java.util.Collections.emptyList;

/**
 * Solution for LeetCode problem #78. Subsets.
 * <p>
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/subsets/">LeetCode 78</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{1}, List.of(emptyList(), List.of(1)));
		test(new int[]{1, 2}, List.of(emptyList(), List.of(1), List.of(2), List.of(1, 2)));
		test(new int[]{1, 2, 3}, List.of(emptyList(), List.of(1), List.of(2), List.of(1, 2), List.of(3), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3)));
		
		// Edge cases
		test(null, emptyList());
		test(new int[]{}, emptyList());
	}
	
	/**
	 * Generates all possible subsets (the power set) of the given integer array.
	 * This solution uses a bit manipulation approach.  Each subset is represented by a binary bitmask,
	 * where the i-th bit indicates whether the i-th element is included in the subset.
	 *
	 * @param nums The input array of unique integers
	 * @return A list of lists, where each inner list represents a subset
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		if (nums == null || nums.length < 1) {
			return emptyList();
		}
		
		List<List<Integer>> subsets = new ArrayList<>();
		int n = nums.length;
		
		// Iterate through all possible bitmasks from 2^n to (2^(n+1)) - 1
		for (int i = (int) Math.pow(2.0, n); i < (int) Math.pow(2.0, n + 1.0); ++i) {
			// Generate bitmask, from 0..00 to 1..11.  removing the leading '1'
			// from the binary representation of numbers.
			String bitmask = Integer.toBinaryString(i).substring(1);
			
			List<Integer> subset = new ArrayList<>();
			for (int bitIndex = 0; bitIndex < n; ++bitIndex) {
				if (bitmask.charAt(bitIndex) == '1') {
					subset.add(nums[bitIndex]);
				}
			}
			
			subsets.add(subset);
		}
		
		return subsets;
	}
	
	/**
	 * Helper method to test the subsets function with expected output.
	 *
	 * @param nums     The input array of integers
	 * @param expected The expected list of subsets
	 */
	private static void test(int[] nums, List<List<Integer>> expected) {
		List<List<Integer>> result = subsets(nums);
		
		Set<Set<Integer>> resultSet = new HashSet<>();
		for (List<Integer> sub : result) {
			resultSet.add(new HashSet<>(sub));
		}
		
		Set<Set<Integer>> expectedSet = new HashSet<>();
		for (List<Integer> sub : expected) {
			expectedSet.add(new HashSet<>(sub));
		}
		
		boolean pass = resultSet.equals(expectedSet);
		
		System.out.printf("Input: %-15s | Result: %-40s | Expected: %-40s | %s%n",
				Arrays.toString(nums),
				resultSet,
				expectedSet,
				pass ? "✓ PASS" : "✗ FAIL");
	}
}
