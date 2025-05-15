package leetcode.data_structures.array.default_approach.count_subarrays_of_length_3_with_condition;

import java.util.Arrays;

/**
 * Solution for LeetCode problem 3392. Count Subarrays of Length 3 With Condition.
 * <p>
 * Given an integer array nums, return the number of subarrays of length 3 such that
 * the sum of the first and third numbers equals exactly half of the second number.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition">count-subarrays-of-length-three-with-a-condition</a>
 */
public class Solution {

    public static void main(String[] args) {
        // Standard test cases
        test(new int[]{1, 2, 1, 4, 1}, 1);
        test(new int[]{1, 1, 1}, 0);
        test(new int[]{1, 3, 2, 5, 3}, 0);
        test(new int[]{1, 2, 0}, 1);

        // Edge cases
        test(null, 0);
        test(new int[]{}, 0);
        test(new int[]{1, 2}, 0);
        test(new int[]{0, 0, 0}, 1);
    }

    /**
     * Counts the number of subarrays of length 3 where the sum of the first and third
     * numbers equals exactly half of the second number.
     *
     * @param nums the input array of integers
     * @return the number of such subarrays
     */
    public static int countSubarrays(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int j;
        int result = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            j = i + 2;

            double target = nums[j - 1] / 2.0;

            if (nums[i] + nums[j] == target) {
                result++;
            }
        }

        return result;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param expected the expected number of subarrays
     */
    private static void test(int[] nums, int expected) {
        int result = countSubarrays(nums);
        System.out.printf("Input: %-15s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}