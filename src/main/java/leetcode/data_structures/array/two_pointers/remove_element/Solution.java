package leetcode.data_structures.array.two_pointers.remove_element;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #27. Remove Element.
 * <p>
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The relative order of the elements may be changed.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result
 * be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should contain the final result. It does not matter what you leave beyond the
 * first k elements.
 * <p>
 * Return k after you have placed the final result in the first k slots of nums.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/remove-element/">LeetCode 27</a>
 */
public class Solution {
    public static void main(String[] args) {
        test(new int[]{3, 2, 2, 3}, 3, 2, new int[]{2, 2});
        test(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5, new int[]{0, 1, 3, 0, 4});
        test(new int[]{1}, 1, 0, new int[]{});
        test(new int[]{1, 1, 1}, 1, 0, new int[]{});

        // Edge cases
        test(null, 0, 0, new int[]{});
        test(new int[]{}, 5, 0, new int[]{});
        test(new int[]{1, 2, 3}, 4, 3, new int[]{1, 2, 3});
        test(new int[]{1, 2, 3}, 1, 2, new int[]{2, 3});
        test(new int[]{1, 1, 2, 2}, 1, 2, new int[]{2, 2});
    }

    /**
     * Removes all occurrences of the specified value 'val' from the input array 'nums' in-place.
     * It returns the new length of the array after removing the elements.
     * The elements in the first 'k' slots of 'nums' will contain the result.
     *
     * @param nums the input array of integers
     * @param val  the integer value to remove
     * @return the new length of the array after removing all occurrences of 'val'
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null) return 0;

        int validIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[validIndex++] = nums[i];
            }
        }

        return validIndex; // Return the new length of the array
    }

    /**
     * Helper method to test the removeElement function with expected output.
     * It checks the new length and the first 'k' elements of the modified array.
     *
     * @param nums     the input array
     * @param val      the value to remove
     * @param expectedLength the expected new length of the array
     * @param expectedElements the expected elements in the first 'k' slots of the array
     */
    private static void test(int[] nums, int val, int expectedLength, int[] expectedElements) {
        int[] numsCopy = (nums != null) ? Arrays.copyOf(nums, nums.length) : new int[]{};
        int resultLength = removeElement(numsCopy, val);
        boolean lengthMatch = resultLength == expectedLength;
        boolean elementsMatch = Arrays.equals(Arrays.copyOf(numsCopy, expectedLength), expectedElements);

        System.out.printf("Input: %-20s, val: %-3d | Result Length: %-2d, Array: %-15s | Expected Length: %-2d, Array: %-15s | %s%n",
                Arrays.toString(nums),
                val,
                resultLength,
                Arrays.toString(Arrays.copyOf(numsCopy, resultLength)),
                expectedLength,
                Arrays.toString(expectedElements),
                lengthMatch && elementsMatch ? "✓ PASS" : "✗ FAIL");
    }
}