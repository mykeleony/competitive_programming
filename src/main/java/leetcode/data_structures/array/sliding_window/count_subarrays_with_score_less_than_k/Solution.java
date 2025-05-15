package leetcode.data_structures.array.sliding_window.count_subarrays_with_score_less_than_k;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #2302. Count Subarrays With Score Less Than K.
 * <p>
 * Given an array of positive integers nums and a positive integer k, return the number of subarrays
 * where the product of all the elements in the subarray is strictly less than k.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-subarrays-with-score-less-than-k/">LeetCode 2302</a>
 */
public class Solution {

    public static void main(String[] args) {
        // Standard test cases
        test(new int[]{2, 1, 4, 3, 5}, 10, 6);
        test(new int[]{1, 2, 3}, 0, 0);

        // Edge cases
        test(null, 10, 0);
        test(new int[]{}, 10, 0);
        test(new int[]{100}, 10, 0);
        test(new int[]{1}, 1, 0);
        test(new int[]{1, 1, 1}, 1, 0);
    }

    /**
     * Counts the number of subarrays where the product of all the elements in the subarray is strictly less than k.
     * This solution has a time complexity of O(n) using a sliding window approach.
     *
     * @param nums the input array of positive integers
     * @param k    the upper bound for the product of subarray elements
     * @return the number of subarrays with a product less than k
     */
    public long countSubarrays(int[] nums, long k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return 0;
        }

        long count = 0;
        long sum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Moves left until the problem condition is satisfied
            while (sum * (right - left + 1) >= k && left <= right) {
                sum -= nums[left];
                left++;
            }

            // All subarrays starting at left and ending at right are valid
            count += right - left + 1;
        }

        return count;
    }

    /**
     * Counts the number of subarrays where the product of all the elements in the subarray is strictly less than k.
     * This is a less efficient, O(n^2) worst-case, solution for comparison.
     *
     * @param nums the input array of positive integers
     * @param k    the upper bound for the product of subarray elements
     * @return the number of subarrays with a product less than k
     */
    public long n2CountSubarrays(int[] nums, long k) {
        int left = 0;
        int right = 0;
        int validSubarrays = 0;
        long accumulator = 0;

        while (left < nums.length) {
            accumulator += nums[right++];
            boolean isValid = (right - left) * accumulator < k;

            if (isValid) {
                validSubarrays++;
            }

            if (!isValid || right == nums.length) {
                left++;
                right = left;
                accumulator = 0;
            }
        }

        return validSubarrays;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param k        the upper bound k
     * @param expected the expected number of subarrays
     */
    private static void test(int[] nums, long k, long expected) {
        Solution solution = new Solution();
        long result = solution.countSubarrays(nums, k);
        System.out.printf("Input: %-15s | k: %-5d | Result: %-5d | Expected: %-5d | %s%n",
                Arrays.toString(nums),
                k,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}