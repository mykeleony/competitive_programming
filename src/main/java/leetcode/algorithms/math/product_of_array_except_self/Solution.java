package leetcode.algorithms.math.product_of_array_except_self;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #238. Product of Array Except Self.
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/product-of-array-except-self/">LeetCode 238</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		// Standard test cases
		test(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6});
		test(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0});
		test(new int[]{2, 3, 4, 5}, new int[]{60, 40, 30, 24});
		test(new int[]{1, 0}, new int[]{0, 1});
		
		// Edge case: single element array
		test(new int[]{5}, new int[]{1});
	}
	
	/**
	 * Calculates the product of all elements in the array except the element at the current index.
	 * It does this in O(n) time without using division by calculating prefix and suffix products.
	 *
	 * @param nums the input array of integers
	 * @return an array where each element is the product of all numbers in nums except the number at that index
	 */
	public static int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] result = new int[n];
		
		// Calculate prefix products and store in the result array
		result[0] = 1;
		for (int i = 1; i < n; i++) {
			result[i] = result[i - 1] * nums[i - 1];
		}
		
		// Calculate suffix products and multiply with prefix products
		int suffixProduct = 1;
		for (int i = n - 1; i >= 0; i--) {
			result[i] *= suffixProduct;
			suffixProduct *= nums[i];
		}
		
		return result;
	}
	
	/**
	 * Helper method to test the productExceptSelf function with expected output.
	 *
	 * @param nums     the input array
	 * @param expected the expected output array
	 */
	private static void test(int[] nums, int[] expected) {
		int[] result = productExceptSelf(nums);
		System.out.printf("Input: %-20s | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(nums),
				Arrays.toString(result),
				Arrays.toString(expected),
				Arrays.equals(result, expected) ? "✓ PASS" : "✗ FAIL");
	}
}
