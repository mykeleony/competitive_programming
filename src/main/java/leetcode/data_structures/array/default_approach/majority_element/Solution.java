package leetcode.data_structures.array.default_approach.majority_element;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #169. Majority Element.
 * <p>
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than n / 2 times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/majority-element/">LeetCode 169</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{3, 2, 3}, 3);
        test(new int[]{2, 2, 1, 1, 1, 2, 2}, 2);
        test(new int[]{1}, 1);
        test(new int[]{5, 5, 5, 1, 2}, 5);

        // Edge cases
        test(new int[]{1, 1}, 1);
        test(new int[]{2, 1, 2}, 2);
    }

    /**
     * Finds the majority element in the given array.
     * This algorithm has a time complexity of O(n) and space complexity of O(1).
     *
     * @param nums the input array of integers
     * @return the majority element
     */
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length < 1) return 0;

        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param expected the expected majority element
     */
    private static void test(int[] nums, int expected) {
        int result = majorityElement(nums);

        System.out.printf("Input: %-15s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}