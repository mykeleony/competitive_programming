package leetcode.data_structures.array.default_approach.count_equal_and_divisible_pairs;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #2176. Count Equal and Divisible Pairs in an Array.
 * <p>
 * Counts the number of pairs (i, j) where:
 * • 0 <= i < j < nums.length
 * • nums[i] == nums[j]
 * • (i * j) is divisible by k
 * <p>
 * Problem Link: https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution, new int[]{3,1,2,2,2,1,3}, 2, 4);
        test(solution, new int[]{1,2,3,4}, 1, 0);
        test(solution, new int[]{1}, 1, 0); // Edge case
    }

    public int countPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) return 0;

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {    // O(n²) time complexity
                if ((i * j % k == 0) && nums[i] == nums[j]) {
                    result++;
                }
            }
        }

        return result;  // O(1) space complexity
    }

    /**
     * Helper method to test the solution
     */
    private static void test(Solution solution, int[] nums, int k, int expected) {
        int result = solution.countPairs(nums, k);
        String status = result == expected ? "✓ PASS" : "✗ FAIL";
        System.out.printf("Input: %-15s | k: %2d | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums), k, result, expected, status);
    }
}
