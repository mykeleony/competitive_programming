package leetcode.algorithms.math.reverse_integer;

/**
 * Solution for LeetCode problem <b>#7. Reverse Integer.</b>
 * <p>
 * Given a signed 32-bit integer {@code x}, return {@code x} <i>with its digits reversed</i>.
 * If reversing {@code x} causes the value to go outside the signed 32-bit integer range {@code [-2^31, 2^31 - 1]}, then return {@code 0}.
 * <p>
 * <b>Assume the environment does not allow you to store 64-bit integers (signed or unsigned).</b>
 * Problem Link: <a href="https://leetcode.com/problems/reverse-integer/">LeetCode 7</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(123, 321);
		test(-123, -321);
		test(120, 21);
		test(0, 0);
		
		// Edge cases and overflow scenarios
		test(1, 1);
		test(-1, -1);
		test(10, 1);
		test(-10, -1);
		test(Integer.MAX_VALUE, 0); // 2147483647 -> 7463847412 (overflow)
		test(Integer.MIN_VALUE, 0); // -2147483648 -> -8463847412 (overflow)
		test(1534236469, 0); // Example causing overflow: 9646324351
		test(-2147483648, 0); // Specific test for MIN_VALUE
	}
	
	/**
	 * Reverses the digits of a signed 32-bit integer.
	 * If reversing the integer causes the value to go outside the signed 32-bit integer range,
	 * it returns 0.
	 * @param x the signed 32-bit integer to reverse.
	 * @return the reversed integer, or 0 if overflow occurs.
	 */
	public static int reverse(int x) {
		boolean isInputNegative = x < 0;
		if (isInputNegative) {
			x = Math.abs(x);
		}
		
		int reversed = 0;
		while (x > 0) {
			int lastDigit = x % 10;
			
			if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && lastDigit > 7)) {
				return 0;   // Overflow occurred
			}
			
			reversed = reversed * 10 + lastDigit;
			x /= 10; // Remove the last digit
		}
		
		// Apply the original sign to the reversed number if needed
		return isInputNegative ? -reversed : reversed;
	}
	
	/**
	 * Helper method to test the reverse function.
	 *
	 * @param input    the integer input to the reverse function.
	 * @param expected the expected integer output.
	 */
	private static void test(int input, int expected) {
		int result = reverse(input);
		System.out.printf("Input: %-12d | Result: %-12d | Expected: %-12d | %s%n",
				input,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
