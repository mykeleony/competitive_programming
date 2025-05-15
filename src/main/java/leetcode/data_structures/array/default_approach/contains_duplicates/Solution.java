package leetcode.data_structures.array.default_approach.contains_duplicates;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Solution for LeetCode problem #217. Contains Duplicate.
 * <p>
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false otherwise.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/contains-duplicate/">LeetCode 217</a>
 */
public class Solution {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 1}, true);
        test(new int[]{1, 2, 3, 4}, false);
        test(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true);

        // Edge cases
        test(new int[]{}, false);
        test(new int[]{1}, false);
        test(new int[]{1, 1}, true);
        test(new int[]{1, 2}, false);
        test(new int[]{-1, -1}, true);
        test(new int[]{0}, false);
    }

    /**
     * Determines if the given array contains any duplicate elements using a HashSet.
     * This approach has a time complexity of O(n) and a space complexity of O(n) in the worst case.
     *
     * @param nums the input array of integers
     * @return true if the array contains duplicates, false otherwise
     */
    public static boolean containsDuplicate(int[] nums) {
        var seen = new HashSet<>();

        for (int num : nums) {
            if (!seen.add(num)) return true;
        }

        return false;
    }

    /**
     * Determines if the given array contains any duplicate elements by first sorting the array.
     * This approach has a time complexity of O(n log n) due to sorting and a space complexity of O(1).
     *
     * @param nums the input array of integers
     * @return true if the array contains duplicates, false otherwise
     */
    public static boolean containsDuplicateMemoryOptimized(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }

        return false;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param expected true if the array contains duplicates, false otherwise
     */
    private static void test(int[] nums, boolean expected) {
        boolean result = containsDuplicate(nums);
        System.out.printf("Input: %-20s | Result: %-5b | Expected: %-5b | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}