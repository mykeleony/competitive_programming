package leetcode.data_structures.array.default_approach.rotate_array;

import java.util.Arrays;

/**
 * Solution for LeetCode Problem #189: Rotate Array
 * <p>
 * This class provides two approaches to rotate an array to the right by k steps:
 * 1. Using O(n) space with array copy
 * 2. In-place rotation using array reversal (O(1) space)
 * <p>
 * Problem Link: https://leetcode.com/problems/rotate-array/
 */
public class Solution {

    public static void main(String[] args) {
        // Test cases demonstrating both approaches
        testRotateApproaches(new int[]{-1, -100, 3, 99}, 2);
        testRotateApproaches(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        testRotateApproaches(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        testRotateApproaches(new int[]{1, 2, 3, 4}, 2);
    }

    /**
     * Rotates the array using O(n) space approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums the array to rotate
     * @param k    the number of steps to rotate
     */
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;

        int[] rotated = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            rotated[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(rotated, 0, nums, 0, nums.length);
    }

    /**
     * Rotates the array in-place using reversal algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums the array to rotate
     * @param k    the number of steps to rotate
     */
    public static void rotateInPlace(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;

        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * Reverses a portion of the array between start and end indices
     *
     * @param nums  the array
     * @param start the starting index (inclusive)
     * @param end   the ending index (inclusive)
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];

            nums[end] = nums[start];
            nums[start] = temp;

            start++;
            end--;
        }
    }

    /**
     * Tests both rotation approaches with the given input
     *
     * @param input the input array
     * @param k     the rotation steps
     */
    private static void testRotateApproaches(int[] input, int k) {
        System.out.println("\nInput: " + Arrays.toString(input) + ", k = " + k);

        // Test standard approach
        int[] nums1 = Arrays.copyOf(input, input.length);
        rotate(nums1, k);
        System.out.println("Standard rotation: " + Arrays.toString(nums1));

        // Test in-place approach
        int[] nums2 = Arrays.copyOf(input, input.length);
        rotateInPlace(nums2, k);
        System.out.println("In-place rotation: " + Arrays.toString(nums2));
    }
}