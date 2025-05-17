package leetcode.data_structures.array.two_pointers.sort_colors;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #75. Sort Colors.
 * <p>
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/sort-colors/">LeetCode 75</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
		test(new int[]{2, 0, 1}, new int[]{0, 1, 2});
		test(new int[]{1, 2, 0}, new int[]{0, 1, 2});
		test(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2});
		test(new int[]{2, 2, 2, 1, 1, 1, 0, 0, 0}, new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2});
		
		// Edge cases
		test(new int[]{0, 1, 2}, new int[]{0, 1, 2}); // Already sorted
		test(new int[]{2, 1, 0}, new int[]{0, 1, 2}); // Reverse sorted
		test(new int[]{1, 1, 1}, new int[]{1, 1, 1}); // All same color
		test(new int[]{0, 0, 0}, new int[]{0, 0, 0});
		test(new int[]{2, 2, 2}, new int[]{2, 2, 2});
		test(new int[]{0}, new int[]{0});             // Single element
	}
	
	/**
	 * Sorts an array of integers representing colors (0, 1, and 2) in-place.
	 * Uses the Dutch National Flag algorithm (three-pointer approach) to sort the array.
	 *
	 * @param nums the array of integers representing colors
	 */
	public static void sortColors(int[] nums) {
		int low = 0;       // Pointer for the beginning of the 0's section
		int mid = 0;       // Pointer for the current element being processed
		int high = nums.length - 1; // Pointer for the end of the 2's section
		
		while (mid <= high) {
			switch (nums[mid]) {
				case 0 -> swap(nums, low++, mid++); // Move 0 to the beginning and adjust pointers
				case 1 -> mid++;                    // 1 is in the correct position, move to the next element
				case 2 -> swap(nums, mid, high--);  // Move 2 to the end and adjust pointers
				default -> throw new IllegalArgumentException("Invalid color: " + nums[mid]);
			}
		}
	}
	
	/**
	 * Swaps two elements in an array.
	 *
	 * @param nums the array in which to swap elements
	 * @param i    the index of the first element to swap
	 * @param j    the index of the second element to swap
	 */
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	/**
	 * Helper method to test the sortColors function with expected output.
	 *
	 * @param nums     the input array
	 * @param expected the expected sorted array
	 */
	private static void test(int[] nums, int[] expected) {
		int[] numsCopy = Arrays.copyOf(nums, nums.length);
		sortColors(numsCopy);
		System.out.printf("Input: %-20s | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(nums),
				Arrays.toString(numsCopy),
				Arrays.toString(expected),
				Arrays.equals(numsCopy, expected) ? "✓ PASS" : "✗ FAIL");
	}
}
