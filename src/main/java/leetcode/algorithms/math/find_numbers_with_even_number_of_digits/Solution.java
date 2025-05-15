package leetcode.algorithms.math.find_numbers_with_even_number_of_digits;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #1295. Find Numbers with Even Number of Digits.
 * <p>
 * Given an array nums of integers, return how many of them contain an even number of digits.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/find-numbers-with-even-number-of-digits/">LeetCode 1295</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{12, 345, 2, 6, 7896}, 2);
        test(new int[]{555, 901, 482, 1771}, 1);

        // Edge cases
        test(null, 0);
        test(new int[]{}, 0);
        test(new int[]{0, 10, 100, 1000, 99, 9999}, 4);
    }

    /**
     * Finds the count of numbers in the given array that have an even number of digits.
     *
     * @param nums the input array of integers
     * @return the number of integers with an even number of digits
     */
    public static int findNumbers(int[] nums) {
        if (nums == null || nums.length < 1) return 0;

        int validNumbersCount = 0;

        for (int num : nums) {
            // Original problem prevents 0 with constraints. Dispensable edge case check
            int digitCount = (num == 0) ? 1 : (int) Math.log10(num) + 1;

            if (digitCount % 2 == 0) {
                validNumbersCount++;
            }
        }

        return validNumbersCount;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param nums     the input array
     * @param expected the expected count of numbers with an even number of digits
     */
    private static void test(int[] nums, int expected) {
        int result = findNumbers(nums);
        System.out.printf("Input: %-15s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(nums),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}