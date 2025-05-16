package leetcode.data_structures.string.final_value_of_variable_after_performing_operations;

import java.util.Arrays;

/**
 * Solution for LeetCode problem #2011. Final Value of Variable After Performing Operations.
 * <p>
 * There is a programming language with only four operations and one variable X:
 * <ul>
 * <li>++X and X++ increment the value of the variable X by 1.</li>
 * <li>--X and X-- decrement the value of the variable X by 1.</li>
 * </ul>
 * Given an array of strings operations where operations[i] is the ith operation you must perform on X, return the final value of X after performing all the operations.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/final-value-of-variable-after-performing-operations/">LeetCode 2011</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new String[]{"--X", "X++", "X++"}, 1);
		test(new String[]{"++X", "X++", "X++"}, 3);
		test(new String[]{"X++", "++X", "--X", "X--"}, 0);
		
		// Edge cases
		test(null, 0);
		test(new String[]{}, 0);
		test(new String[]{"X++"}, 1);
		test(new String[]{"--X"}, -1);
	}
	
	/**
	 * Calculates the final value of variable X after performing a series of increment/decrement operations.
	 *
	 * @param operations the array of operations to perform on X
	 * @return the final value of X
	 */
	public static int finalValueAfterOperations(String[] operations) {
		if (operations == null || operations.length < 1) {
			return 0;
		}
		
		int finalValue = 0;
		
		for (String operation : operations) {
			// Check the second character to determine if it's increment or decrement
			finalValue += (operation.charAt(1) == '+') ? 1 : -1;
		}
		
		return finalValue;
	}
	
	/**
	 * Helper method to test the solution with expected output.
	 *
	 * @param operations the array of operations
	 * @param expected   the expected final value of X
	 */
	private static void test(String[] operations, int expected) {
		int result = finalValueAfterOperations(operations);
		
		System.out.printf("Input: %-25s | Result: %2d | Expected: %2d | %s%n",
				Arrays.toString(operations),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
