package leetcode.algorithms.binary_search.search_insert;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #35. Search Insert Position.
 * <p>
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/search-insert-position/">LeetCode 35</a>
 */
public class Solution {
    public static void main(String[] args) {
        // Standard test cases
        test(new int[]{1, 3, 5, 6}, 5, 2);
        test(new int[]{1, 3, 5, 6}, 2, 1);
        test(new int[]{1, 3, 5, 6}, 7, 4);
        test(new int[]{1, 3}, 0, 0);
        test(new int[]{1, 3}, 1, 0);
        test(new int[]{1, 3}, 2, 1);
        test(new int[]{1, 3}, 3, 1);
        test(new int[]{1, 3}, 4, 2);
        
        // Edge cases
        test(new int[]{1}, 1, 0);
        test(new int[]{1}, 0, 0);
        test(new int[]{1}, 2, 1);
        test(new int[]{}, 5, 0);
    }
    
    /**
     * Given a sorted array of distinct integers and a target value, returns the index if the
     * target is found. If not, returns the index where it would be if it were inserted in order.
     * <p>
     * This implementation uses binary search to achieve O(log n) runtime complexity.
     *
     * @param nums   the sorted array of distinct integers
     * @param target the target value to search for
     * @return the index of the target if found, or the index where it would be inserted
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int middle = (left + right) / 2;
            
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        return left;
    }
    
    /**
     * Helper method to test the searchInsert function with expected output.
     *
     * @param nums     the input array
     * @param target   the target value
     * @param expected the expected index
     */
    private static void test(int[] nums, int target, int expected) {
        int result = searchInsert(nums, target);
        System.out.printf("Input: nums = %-15s, target = %-3d | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums),
                target,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}
