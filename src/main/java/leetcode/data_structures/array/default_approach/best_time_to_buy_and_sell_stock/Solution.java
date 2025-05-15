package leetcode.data_structures.array.default_approach.best_time_to_buy_and_sell_stock;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #121. Best Time to Buy and Sell Stock.
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a
 * different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">LeetCode 121</a>
 */
public class Solution {

    public static void main(String[] args) {
        test(new int[]{7, 1, 5, 3, 6, 4}, 5);
        test(new int[]{1, 6, 0}, 5);
        test(new int[]{7, 6, 4, 3, 1}, 0);
        test(new int[]{2, 4, 1}, 2);
        test(new int[]{2, 1, 4}, 3);

        // Edge cases
        test(null, 0);
        test(new int[]{}, 0);
        test(new int[]{1}, 0);
        test(new int[]{1, 2}, 1);
        test(new int[]{2, 1}, 0);
        test(new int[]{1, 1, 1}, 0);
    }

    /**
     * Calculates the maximum profit that can be obtained by buying and selling a stock once.
     * It iterates through the prices, keeping track of the minimum price encountered so far
     * and the maximum profit that can be achieved.
     *
     * @param prices an array of stock prices
     * @return the maximum possible profit
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param prices   the input array of stock prices
     * @param expected the expected maximum profit
     */
    private static void test(int[] prices, int expected) {
        int result = maxProfit(prices);
        System.out.printf("Input: %-15s | Result: %2d | Expected: %2d | %s%n",
                Arrays.toString(prices),
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}