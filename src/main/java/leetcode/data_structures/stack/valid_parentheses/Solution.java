package leetcode.data_structures.stack.valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Solution for LeetCode problem #20. Valid Parentheses.
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * <ol>
 * <li>Open brackets must be closed by the same type of brackets.</li>
 * <li>Open brackets must be closed in the correct order.</li>
 * <li>Note that an empty string is also considered valid.</li>
 * </ol>
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/valid-parentheses/">LeetCode 20</a>
 */
public class Solution {
    public static void main(String[] args) {
        test("()", true);
        test("()[]{}", true);
        test("(]", false);
        test("([)]", false);
        test("{[]}", true);

        // Edge cases
        test(null, false);
        test("", true);
        test("(", false);
        test(")", false);
        test("[", false);
        test("]", false);
        test("{", false);
        test("}", false);
        test("((", false);
        test("))", false);
        test("}{", false);
    }

    /**
     * Determines if the input string of parentheses is valid.
     * It uses a stack to keep track of opening brackets and checks if closing brackets match the last opened one.
     *
     * @param s the input string containing parentheses
     * @return true if the string is valid, false otherwise
     */
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) return false;

        Map<Character, Character> symbolPairs = Map.of(
                '(', ')',
                '[', ']',
                '{', '}'
        );

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (symbolPairs.containsKey(ch)) {
                stack.push(symbolPairs.get(ch));
            } else if (stack.isEmpty() || stack.pop() != ch) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    /**
     * Helper method to test the solution with expected output.
     *
     * @param s        the input string of parentheses
     * @param expected true if the string is valid, false otherwise
     */
    private static void test(String s, boolean expected) {
        Solution solution = new Solution();
        boolean result = solution.isValid(s);
        System.out.printf("Input: %-10s | Result: %-5b | Expected: %-5b | %s%n",
                "\"" + s + "\"",
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}