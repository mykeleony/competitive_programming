package leetcode.data_structures.array.two_pointers.remove_duplicates_from_sorted_array_II;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #80: Remove Duplicates from Sorted Array II
 * <p>
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 * <p>
 * Given a sorted array nums, remove duplicates in-place such that duplicates appear at most twice.
 * Returns the new length of the array.
 */
public class Solution {

    public static void main(String[] args) {
        testCase(new int[]{1, 1, 1, 2, 2, 3});          // Expected: [1,1,2,2,3]
        testCase(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}); // Expected: [0,0,1,1,2,3,3]
        testCase(new int[]{1, 1, 1, 1});                // Expected: [1,1]
        testCase(new int[]{1, 2, 3});                   // Expected: [1,2,3]
    }

    /**
     * Removes duplicates in-place, allowing at most two duplicates.
     *
     * @param nums the sorted array with duplicates
     * @return the new length of the array after removal
     */
    public static int removeDuplicates(int[] nums) {
        int writeIndex = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[writeIndex - 2] != nums[i]) {
                nums[writeIndex++] = nums[i];
            }
        }

        return writeIndex;
    }

    private static void testCase(int[] input) {
        System.out.println("Input: " + Arrays.toString(input));

        int[] nums = input.clone();
        int newLength = removeDuplicates(nums);
        int[] result = Arrays.copyOf(nums, newLength);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("New length: " + newLength + "\n");
    }
}