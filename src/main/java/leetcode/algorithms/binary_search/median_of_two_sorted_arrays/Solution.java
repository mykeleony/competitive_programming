package leetcode.algorithms.binary_search.median_of_two_sorted_arrays;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #4. Median of Two Sorted Arrays.
 * <p>
 * Given two sorted arrays nums1 and nums2 of sizes m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">LeetCode 4</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{1, 3}, new int[]{2}, 2.0);
        test(new int[]{1, 2}, new int[]{3, 4}, 2.5);
        test(new int[]{0, 0}, new int[]{0, 0}, 0.0);
        test(new int[]{}, new int[]{1}, 1.0);
        test(new int[]{2}, new int[]{}, 2.0);
        test(new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25}, 11.0);
        test(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}, 5.5);
        test(new int[]{1, 4, 7, 10, 12}, new int[]{2, 3, 6, 9}, 6.0);
    }

    /**
     * Finds the median of two sorted arrays using a binary search approach to achieve O(log(m+n)) time complexity.
     * It ensures nums1 is the smaller array to simplify the partitioning logic.
     *
     * @param nums1 the first sorted array
     * @param nums2 the second sorted array
     * @return the median of the two sorted arrays
     * @throws IllegalArgumentException if the input arrays are not sorted (though the problem statement implies they are)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the shorter array to optimize binary search
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right = m;

        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Determine the values around the partition points
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Determine the median of correct partition
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                // Too many elements taken from nums1's left side. Need to reduce partitionX
                right = partitionX - 1;
            } else {
                // Too few. Need to increase partitionX
                left = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Arrays are not sorted as expected.");
    }

    /**
     * A more straightforward approach by merging the arrays (implicitly) until the median position.
     * This has a time complexity of O(m+n).
     *
     * @param nums1 the first sorted array
     * @param nums2 the second sorted array
     * @return the median of the two sorted arrays
     */
    public static double findMedianSortedArraysFirstTry(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int medianPos = totalLength / 2;
        int p1 = 0;
        int p2 = 0;
        int currentPos = 0;
        int previousValue = 0;
        int currentValue = 0;

        while (currentPos++ <= medianPos) {
            previousValue = currentValue;
            // Choose the smaller element from the current pointers of both arrays
            currentValue = p2 == nums2.length || (p1 < nums1.length && nums1[p1] < nums2[p2])
                    ? nums1[p1++]
                    : nums2[p2++];
        }

        if (totalLength % 2 != 0) {
            return currentValue;
        }

        return (currentValue + previousValue) / 2.0;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums1    the first input array
     * @param nums2    the second input array
     * @param expected the expected median
     */
    private static void test(int[] nums1, int[] nums2, double expected) {
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.printf("nums1: %-15s, nums2: %-15s | Result: %5.1f | Expected: %5.1f | %s%n",
                Arrays.toString(nums1),
                Arrays.toString(nums2),
                result,
                expected,
                Math.abs(result - expected) < 1e-5 ? "✓ PASS" : "✗ FAIL");
    }
}