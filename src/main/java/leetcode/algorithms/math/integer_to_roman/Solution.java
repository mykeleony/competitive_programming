package leetcode.algorithms.math.integer_to_roman;

/**
 * Solution for LeetCode problem #12. Integer to Roman.
 * <p>
 * Given an integer, convert it to a Roman numeral.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/integer-to-roman/">LeetCode 12</a>
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println("1: " + intToRoman(1));
        System.out.println("5: " + intToRoman(5));
        System.out.println("10: " + intToRoman(10));
        System.out.println("50: " + intToRoman(50));
        System.out.println("100: " + intToRoman(100));
        System.out.println("500: " + intToRoman(500));
        System.out.println("1000: " + intToRoman(1000));
        System.out.println("1004: " + intToRoman(1004));
        System.out.println("3749: " + intToRoman(3749));
    }
    
    /**
     * Converts an integer to a Roman numeral.
     * It iterates through predefined decimal values and their corresponding Roman symbols,
     * subtracting the decimal values from the input number and appending the symbols to the result.
     *
     * @param num the integer to convert (1 <= num <= 3999)
     * @return the Roman numeral representation of the integer
     */
    public static String intToRoman(int num) {
        int[] decimals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder resultingRoman = new StringBuilder();

        for (int i = 0; i < decimals.length && num > 0; i++) {
            while (num >= decimals[i]) {
                num -= decimals[i];
                resultingRoman.append(romans[i]);
            }
        }

        return resultingRoman.toString();
    }
}
