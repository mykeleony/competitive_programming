package leetcode.data_structures.array.default_approach.count_interesting_subarrays;

import java.util.*;

/**
 * Solution for LeetCode problem #2845. Count of Interesting Subarrays.
 * <p>
 * Given an integer array nums, an integer modulo, and an integer k,
 * your task is to find the count of subarrays that are interesting.
 * <p>
 * A subarray nums[l...r] is interesting if:
 * <p>
 * Let cnt be the number of indices i in the range [l, r] such that nums[i] % modulo == k. Then, cnt % modulo == k.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/count-of-interesting-subarrays/">LeetCode 2845</a>
 */
public class Solution {
    public static void main(String[] args) {
        testCase(List.of(3, 2, 4), 2, 1, 3);
        testCase(List.of(3, 1, 9, 6), 3, 0, 2);
        testCase(List.of(5,5,5,5), 1, 0, 10);
        testCase(List.of(1), 1, 0, 1);

        // Edge cases
        testCase(null, 0, 1, 0);
        testCase(new ArrayList<>(), 0, 1, 0);
        testCase(List.of(1), 0, 0, 0);
        testCase(List.of(1), 3, 4, 0);
    }

    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        if (nums == null || nums.isEmpty() || modulo < 1 || k > modulo) {
            return 0;
        }

        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        long result = 0;
        int currentPrefix = 0;

        for (int num : nums) {
            boolean isInteresting = (num % modulo == k);

            currentPrefix = (currentPrefix + (isInteresting ? 1 : 0)) % modulo;

            // Calculate the complement needed to form valid subarrays
            int complement = (currentPrefix - k + modulo) % modulo;

            result += prefixCount.getOrDefault(complement, 0);

            prefixCount.put(currentPrefix, prefixCount.getOrDefault(currentPrefix, 0) + 1);
        }

        return result;
    }

    private static void testCase(List<Integer> nums, int modulo, int k, long expected) {
        long result = countInterestingSubarrays(nums, modulo, k);
        String status = result == expected ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Input: %-15s | modulo: %2d | k: %2d | Result: %2d | Expected: %2d | %s%n",
                nums, modulo, k, result, expected, status);
    }
}