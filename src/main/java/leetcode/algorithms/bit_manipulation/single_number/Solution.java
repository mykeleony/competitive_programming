package leetcode.algorithms.bit_manipulation.single_number;

import java.util.Arrays;

/**
 * Solution for LeetCode problem <b>#136. Single Number</b>.
 * <p>
 * Given a <b>non-empty</b> array of integers {@code nums}, every element appears <i>twice</i> except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/single-number/">LeetCode 136</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[]{2, 2, 1}, 1);
		test(new int[]{4, 1, 2, 1, 2}, 4);
		test(new int[]{3, 1, 1, 2, 2, 4, 4}, 3);
		test(new int[]{1, 2, 3, 1, 2}, 3);
		test(new int[]{10, 5, 10, 5, 7}, 7);
		
		// Edge cases
		test(new int[]{1}, 1); // Single element array
		test(new int[]{0, 0, 5}, 5); // Contains zero
		test(new int[]{-1, -1, -2}, -2); // Contains negative numbers
	}
	
	/**
	 * Finds the single number in an array where every other element appears twice.
	 * This solution utilizes the XOR bitwise operation. The XOR of a number with itself is 0,
	 * and the XOR of a number with 0 is the number itself.
	 * Therefore, XORing all elements in the array will cancel out pairs, leaving only the single number.
	 * <p>
	 * <b>Time Complexity: O(n)</b>, where n is the number of elements in the array.
	 * <br>
	 * <b>Space Complexity: O(1)</b>, as it uses a constant amount of extra space.
	 *
	 * @param nums the input array of integers.
	 * @return the single number that appears only once.
	 */
	public static int singleNumber(int[] nums) {
		int singleNum = 0; // Initialize with 0, as XORing with 0 does not change the number
		
		// XOR all numbers in the array
		for (int num : nums) {
			singleNum ^= num;
		}
		
		return singleNum; // The remaining value is the single number
	}
	
	/**
	 * Helper method to test the singleNumber function.
	 *
	 * @param nums     the input array of integers.
	 * @param expected the expected single number.
	 */
	private static void test(int[] nums, int expected) {
		int result = singleNumber(nums);
		System.out.printf("Input: %-20s | Result: %-5d | Expected: %-5d | %s%n",
				Arrays.toString(nums),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
