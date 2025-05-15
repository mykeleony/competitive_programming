package leetcode.data_structures.array.sliding_window.count_complete_subarrays;

import java.util.*;

/**
 * Solution for LeetCode problem #2799. Count Complete Subarrays in an Array.
 * <p>
 * Given an array nums consisting of positive integers, returns the number of
 * complete subarrays (a subarray containing all distinct elements present in nums).
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-complete-subarrays-in-an-array/">LeetCode 2799</a>
 */
public class Solution {

    public static void main(String[] args) {
        // Standard test cases
        test(new int[]{1, 3, 1, 2, 2}, 4);
        test(new int[]{5, 5, 5, 5}, 10);

        // Edge cases
        test(new int[]{1}, 1);
        test(new int[]{1, 2, 3}, 1);
        test(new int[]{1, 2, 1, 2, 3}, 3);

        // Larger input
        test(new int[]{1, 2, 3, 4, 1, 2, 3}, 10);
    }

    /**
     * Counts the number of complete subarrays in the given array.
     *
     * @param nums the input array of positive integers
     * @return the number of complete subarrays
     */
    public static int countCompleteSubarrays(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> uniqueElements = new HashSet<>();

        for (int num : nums) {
            uniqueElements.add(num);
        }

        int totalUnique = uniqueElements.size();

        // Sliding window approach
        Map<Integer, Integer> windowCounts = new HashMap<>();
        int left = 0;
        int completeSubarrays = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window to include nums[right]
            windowCounts.put(nums[right], windowCounts.getOrDefault(nums[right], 0) + 1);

            // When window contains all unique elements, count subarrays and shrink from left
            while (windowCounts.size() == totalUnique) {
                // All subarrays starting at left and ending at right or beyond are complete
                completeSubarrays += nums.length - right;

                // Shrink the window from the left
                windowCounts.put(nums[left], windowCounts.get(nums[left]) - 1);

                if (windowCounts.get(nums[left]) == 0) {
                    windowCounts.remove(nums[left]);
                }

                left++;
            }
        }

        return completeSubarrays;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums the input array
     * @param expected the expected number of complete subarrays
     */
    private static void test(int[] nums, int expected) {
        int result = countCompleteSubarrays(nums);
        System.out.printf("Input: %-15s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}