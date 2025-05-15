package leetcode.data_structures.array.two_pointers.container_with_most_water;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #11. Container With Most Water.
 * <p>
 * You are given an integer array height of length n. There are n vertical lines drawn such that
 * the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/container-with-most-water/">LeetCode 11</a>
 */
public class Solution {

    public static void main(String[] args) {
        // Standard test cases
        test(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        test(new int[]{1, 1}, 1);
        test(new int[]{4, 3, 2, 1, 4}, 16);
        test(new int[]{1, 2, 1}, 2);

        // Edge cases
        test(null, 0);
        test(new int[]{1}, 0);
        test(new int[]{}, 0);
    }

    /**
     * Finds the maximum amount of water a container can store given the heights of vertical lines.
     *
     * @param height the input array of heights
     * @return the maximum area of water
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;    // Space complexity: O(1)

        while (left < right) {   // Time complexity: O(n)
            int area = Math.min(height[left], height[right]) * (right - left);

            if (area > maxArea) {
                maxArea = area;
            }

            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param height   the input array of heights
     * @param expected the expected maximum area
     */
    private static void test(int[] height, int expected) {
        int result = maxArea(height);

        System.out.printf("Input: %-20s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(height),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}