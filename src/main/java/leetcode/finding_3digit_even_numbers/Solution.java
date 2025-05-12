package leetcode.finding_3digit_even_numbers;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution for LeetCode problem #2094. Finding 3-Digit Even Numbers.
 * <p>
 * You are given an integer array digits, where each element is a digit. The array may contain duplicates.
 * You need to find all the unique integers that follow the given requirements:
 * <p>
 * The integer consists of the concatenation of three elements from digits in any arbitrary order.
 * The integer does not have leading zeros.
 * The integer is even.
 * <p>
 * Return a sorted array of the unique integers.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/finding-3-digit-even-numbers/">LeetCode 2094</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		test(new int[]{2, 1, 3, 0}, new int[]{102, 120, 130, 132, 210, 230, 302, 310, 312, 320});
		test(new int[]{2, 2, 8, 8, 2}, new int[]{222, 228, 282, 288, 822, 828, 882});
		test(new int[]{0, 0, 0}, new int[]{});
		test(new int[]{0, 1, 2}, new int[]{102, 120, 210});
		test(new int[]{0, 2, 0}, new int[]{200});
		
		// Edge cases
		test(new int[]{0}, new int[]{});
		test(new int[]{0, 0, 0}, new int[]{});
		test(new int[]{0, 9, 9}, new int[]{990});
		test(new int[]{0, 0, 1}, new int[]{100});
		test(new int[]{9, 9, 8}, new int[]{998});
		test(new int[]{1, 1, 1, 1, 1}, new int[]{});
		test(new int[]{}, new int[]{});
		test(null, new int[]{});
	}
	
	/**
	 * Finds all unique 3-digit even numbers that can be formed using the given digits.
	 * It counts the occurrences of each digit and then iterates through all possible 3-digit even numbers,
	 * checking if they can be formed using the available digits. The result is a sorted array of unique numbers.
	 *
	 * @param digits the input array of digits
	 * @return a sorted array of unique 3-digit even numbers that can be formed
	 */
	public static int[] findEvenNumbers(int[] digits) {
		if (digits == null) return new int[]{};
		
		int[] givenDigitCount = new int[10];
		
		// Count the occurrences of each digit in the input array
		for (int digit : digits) {
			givenDigitCount[digit]++;
		}
		
		// Ensure unique and ordered numbers
		Set<Integer> validNumbers = new TreeSet<>();
		
		// Iterate through all possible 3-digit even numbers
		for (int number = 100; number < 1000; number += 2) {
			int[] currentDigitCount = new int[10];
			
			// Create a copy of the available digit counts for the current number check
			System.arraycopy(givenDigitCount, 0, currentDigitCount, 0, 10);
			
			int currentNumber = number;
			boolean isValidNumber = true;
			
			// Check if the current 3-digit even number can be formed using the available digits
			while (currentNumber > 0) {
				int digit = currentNumber % 10;
				
				if (--currentDigitCount[digit] < 0) {
					isValidNumber = false;  // Not enough of this digit
					break;
				}
				
				currentNumber /= 10;
			}
			
			if (isValidNumber) {
				validNumbers.add(number);
			}
		}
		
		return validNumbers.stream().mapToInt(Integer::intValue).toArray();
	}
	
	private static void test(int[] digits, int[] expected) {
		int[] result = findEvenNumbers(digits);
		
		Arrays.sort(result);
		Arrays.sort(expected);
		
		System.out.printf("Input: %-20s | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(digits),
				Arrays.toString(result),
				Arrays.toString(expected),
				Arrays.equals(result, expected) ? "✓ PASS" : "✗ FAIL");
	}
}