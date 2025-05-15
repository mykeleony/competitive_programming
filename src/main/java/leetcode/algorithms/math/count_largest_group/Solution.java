package leetcode.algorithms.math.count_largest_group;

/**
 * Solution for LeetCode Problem #1399: Count Largest Group
 * <p>
 * This class provides a solution to count the number of largest groups when integers from 1 to n
 * are grouped by the sum of their digits. Each group contains numbers with the same digit sum.
 * <p>
 * Problem Link: https://leetcode.com/problems/count-largest-group/
 */
public class Solution {

    public static void main(String[] args) {
        // Test cases demonstrating the solution
        testCountLargestGroup(13, 4);
        testCountLargestGroup(2, 2);
        testCountLargestGroup(15, 6);
        testCountLargestGroup(24, 5);
        testCountLargestGroup(10000, 1);
    }

    /**
     * Counts the number of largest groups when numbers from 1 to n are grouped by digit sum
     * <p>
     * Time Complexity: O(n * d) where d is the average number of digits
     * Space Complexity: O(d) where d is the number of possible digit sums
     *
     * @param n the upper bound of numbers to consider
     * @return the count of largest groups
     */
    public static int countLargestGroup(int n) {
        int maxSum = calculateMaxSum(n);
        int[] counts = new int[maxSum + 1];
        int largestGroupSize = 0;
        int occurrences = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;

            for (int num = i; num > 0; num /= 10) {
                sum += num % 10;
            }

            if (++counts[sum] > largestGroupSize) {
                largestGroupSize = counts[sum];
                occurrences = 1;
            } else if (counts[sum] == largestGroupSize) {
                occurrences++;
            }
        }

        return occurrences;
    }

    /**
     * Calculates the maximum possible digit sum for numbers up to n
     *
     * @param n the upper bound number
     * @return the maximum possible digit sum
     */
    private static int calculateMaxSum(int n) {
        int maxSum = 0;
        while (n > 0) {
            maxSum += 9;
            n /= 10;
        }
        return maxSum;
    }

    /**
     * Tests the countLargestGroup method with the given input and expected output
     *
     * @param n           the input number
     * @param expected    the expected result
     */
    private static void testCountLargestGroup(int n, int expected) {
        int result = countLargestGroup(n);
        System.out.printf("Input: %d | Result: %d | Expected: %d | %s%n",
                n,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}