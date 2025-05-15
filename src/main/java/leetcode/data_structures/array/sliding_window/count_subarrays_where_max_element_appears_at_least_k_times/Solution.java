package leetcode.data_structures.array.sliding_window.count_subarrays_where_max_element_appears_at_least_k_times;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #2962. Count Subarrays Where Max Element Appears at Least k Times.
 * <p>
 * You are given an integer array nums and a positive integer k.
 * <p>
 * Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
 * <p>
 * A subarray is a contiguous sequence of elements within an array.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/">LeetCode 2962</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{1, 3, 2, 3, 3}, 2, 6);
        test(new int[]{1, 4, 2, 1}, 3, 0);
        test(new int[]{1}, 1, 1);
        test(new int[]{1, 2, 3}, 1, 3);
        test(new int[]{3, 2, 1}, 1, 3);
        test(new int[]{28, 5, 58, 91, 24, 91, 53, 9, 48, 85, 16, 70, 91, 91, 47, 91, 61, 4, 54, 61, 49}, 1, 187);

        // Edge cases
        test(null, 1, 0);
        test(new int[]{1}, 1, 1);
    }

    /**
     * Counts the number of subarrays where the maximum element of nums appears at least k times.
     *
     * @param nums the input array of integers
     * @param k    the minimum number of times the maximum value should appear in a subarray
     * @return the count of subarrays with at least k occurrences of the maximum value
     */
    public static long countSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        long result = 0;
        int left = 0;
        int maxCount = 0;   // Space complexity: O(1)

        for (int right = 0; right < nums.length; right++) {     // Time complexity: O(n)
            if (nums[right] == max) {
                maxCount++;
            }

            while (maxCount >= k) {
                result += nums.length - right;

                if (nums[left++] == max) {
                    maxCount--;
                }
            }
        }

        return result;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param k        the minimum count of the maximum element
     * @param expected the expected number of good subarrays
     */
    private static void test(int[] nums, int k, long expected) {
        long result = countSubarrays(nums, k);
        System.out.printf("Input: %-15s | k: %-3d | Result: %-5d | Expected: %-5d | %s%n",
                Arrays.toString(nums),
                k,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}