package leetcode.data_structures.array.two_pointers.merge_sorted_array;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #88. Merge Sorted Array.
 * <p>
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/merge-sorted-array/">LeetCode 88</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6});
        test(new int[]{1}, 1, new int[]{}, 0, new int[]{1});
        test(new int[]{0}, 0, new int[]{1}, 1, new int[]{1});

        // Edge cases
        test(new int[]{0, 0, 0, 0, 0}, 0, new int[]{1, 2, 3, 4, 5}, 5, new int[]{1, 2, 3, 4, 5});
        test(new int[]{1, 2, 3, 4, 5}, 5, new int[]{}, 0, new int[]{1, 2, 3, 4, 5});
        test(new int[]{1, 3, 5, 0, 0}, 3, new int[]{2, 4}, 2, new int[]{1, 2, 3, 4, 5});
        test(new int[]{2, 4, 6, 0, 0}, 3, new int[]{1, 3}, 2, new int[]{1, 2, 3, 4, 6});
    }

    /**
     * Merges two sorted arrays nums1 and nums2 into nums1 in non-decreasing order.
     * It uses a three-pointer approach, starting from the end of the arrays to avoid overwriting elements in nums1.
     *
     * @param nums1 the first sorted array (will contain the merged result)
     * @param m     the number of elements in nums1 that are actually valid
     * @param nums2 the second sorted array
     * @param n     the number of elements in nums2
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int writeIndex = n + m - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[writeIndex--] = nums1[p1] > nums2[p2]
                    ? nums1[p1--]
                    : nums2[p2--];
        }

        // If there are remaining elements in nums2, copy them to the beginning of nums1
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * Helper method to test the merge function with expected output.
     *
     * @param nums1    the first array before merging
     * @param m        the number of valid elements in nums1
     * @param nums2    the second array to merge
     * @param n        the number of elements in nums2
     * @param expected the expected merged array in nums1
     */
    private static void test(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        int[] nums1Copy = Arrays.copyOf(nums1, nums1.length);

        merge(nums1Copy, m, nums2, n);

        boolean pass = Arrays.equals(nums1Copy, expected);

        System.out.printf("nums1: %-15s, m: %-2d, nums2: %-15s, n: %-2d | Result: %-15s | Expected: %-15s | %s%n",
                Arrays.toString(Arrays.copyOfRange(nums1, 0, m)),
                m,
                Arrays.toString(nums2),
                n,
                Arrays.toString(nums1Copy),
                Arrays.toString(expected),
                pass ? "✓ PASS" : "✗ FAIL");
    }
}