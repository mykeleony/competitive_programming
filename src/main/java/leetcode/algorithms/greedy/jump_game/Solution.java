package leetcode.algorithms.greedy.jump_game;

import java.util.Arrays;

/**
 * Solution for LeetCode Problem #55: Jump Game
 * <p>
 * Determines if you can reach the last index of the array starting from the first index,
 * where each element represents your maximum jump length.
 * <p>
 * Problem Link: https://leetcode.com/problems/jump-game/
 * <p>
 * Approach: Uses greedy algorithm with backward traversal (O(n) time, O(1) space)
 */
public class Solution {
    public static void main(String[] args) {
        testCase(new int[]{2, 0, 0}, true, "Small array with possible jumps");
        testCase(new int[]{3, 2, 1, 0, 4}, false, "Impossible jump scenario");
        testCase(new int[]{0, 2, 3}, false, "Stuck at first position");
        testCase(new int[]{0}, true, "Single element array");
        testCase(new int[]{2, 0}, true, "Two elements with possible jump");
        testCase(new int[]{2, 3, 1, 1, 4}, true, "Standard success case");
        testCase(null, false, "Null input");
        testCase(new int[]{}, false, "Empty array");
    }

    /**
     * Determines if the last index is reachable from the first index.
     *
     * @param nums the input array where each element represents maximum jump length
     * @return true if the last index is reachable, false otherwise
     */
    public static boolean canJump(int[] nums) {
        // Edge cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        int lastPosition = nums.length - 1;
        int target = lastPosition;

        // Traverse backwards from the second last element
        for (int i = lastPosition - 1; i >= 0; i--) {
            if (i + nums[i] >= target) {
                target = i; // Update the target if it is reachable from current position
            }
        }

        return target == 0;
    }

    /**
     * Helper method to test and print results for a test case
     *
     * @param nums the input array
     * @param expected the expected result
     * @param description description of the test case
     */
    private static void testCase(int[] nums, boolean expected, String description) {
        System.out.println("\nTest Case: " + description);
        System.out.println("Input: " + (nums == null ? "null" : Arrays.toString(nums)));

        boolean result = canJump(nums);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result == expected ? "PASSED" : "FAILED"));
    }
}