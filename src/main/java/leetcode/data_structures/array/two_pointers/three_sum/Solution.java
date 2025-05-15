package leetcode.data_structures.array.two_pointers.three_sum;

import java.util.*;

/**
 * Solution for LeetCode problem #15. 3Sum.
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/3sum/">LeetCode 15</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
        test(new int[]{0, 1, 1}, Collections.emptyList());
        test(new int[]{0, 0, 0}, List.of(List.of(0, 0, 0)));
        test(new int[]{-2, 0, 1, 1, 2}, List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)));

        // Edge cases
        test(null, Collections.emptyList());
        test(new int[]{}, Collections.emptyList());
        test(new int[]{0}, Collections.emptyList());
        test(new int[]{0, 0}, Collections.emptyList());
        test(new int[]{1, 2, 3}, Collections.emptyList());
        test(new int[]{-1, -1, -1}, Collections.emptyList());
    }

    /**
     * Finds all unique triplets in the given array that sum to zero.
     * The array is first sorted to efficiently use the two-pointer approach.
     * Duplicate triplets are avoided by skipping duplicate numbers.
     *
     * @param nums the input array of integers
     * @return a list of lists, where each inner list represents a unique triplet that sums to zero
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return Collections.emptyList();

        List<List<Integer>> validTriplets = new ArrayList<>();

        // Sort the array to easily use the two-pointer approach and skip duplicates
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates

            int left = i + 1;
            int right = nums.length - 1;    // Space complexity: O(1) extra space

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];

                if (sum == 0) {
                    validTriplets.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates -> makes time complexity O(n²)
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                }
                else if (sum < 0) left++;
                else right--;
            }
        }

        return validTriplets;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param expected the expected list of triplets
     */
    private static void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = threeSum(nums);

        boolean pass = result.size() == expected.size()
                && new HashSet<>(result).containsAll(expected)
                && new HashSet<>(expected).containsAll(result);

        System.out.printf("Input: %-20s | Result: %-40s | Expected: %-40s | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                pass ? "✓ PASS" : "✗ FAIL");
    }
}