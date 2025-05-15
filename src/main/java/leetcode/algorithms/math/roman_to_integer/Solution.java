package leetcode.algorithms.math.roman_to_integer;

/**
 * Solution for LeetCode problem #13. Roman to Integer.
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/roman-to-integer/">LeetCode 13</a>
 */
public class Solution {
    
    public static void main(String[] args) {
        // Standard test cases
        test("III", 3);
        test("LVIII", 58);
        test("MCMXCIV", 1994);
        
        // Additional test cases
        test("IV", 4);
        test("IX", 9);
        test("XL", 40);
        test("XC", 90);
        test("CD", 400);
        test("CM", 900);
        test("I", 1);
        test("V", 5);
        test("X", 10);
        test("L", 50);
        test("C", 100);
        test("D", 500);
        test("M", 1000);
    }
    
    /**
     * Converts a Roman numeral string to an integer.
     * It iterates through the string from right to left, applying the subtraction rule for certain pairs of Roman numerals.
     *
     * @param s the Roman numeral string
     * @return the integer representation of the Roman numeral
     */
    public static int romanToInt(String s) {
        int previousValue = 0;
        int result = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = RomansEnum.fromChar(s.charAt(i));
            
            // Apply subtraction rule (e.g., IV = 4, IX = 9)
            if (currentValue < previousValue) {
                currentValue *= -1;
            }
            
            result += currentValue;
            previousValue = currentValue;
        }
        
        return result;
    }
    
    private static void test(String roman, int expected) {
        int result = romanToInt(roman);
        System.out.printf("Roman: %-10s | Result: %-5d | Expected: %-5d | %s%n",
                roman,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}

enum RomansEnum {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    
    private final int decimalValue;
    
    public static int fromChar(char ch) {
        return RomansEnum.valueOf(String.valueOf(ch)).getDecimalValue();
    }
    
    RomansEnum(int decimalValue) {
        this.decimalValue = decimalValue;
    }
    
    public int getDecimalValue() {
        return decimalValue;
    }
}