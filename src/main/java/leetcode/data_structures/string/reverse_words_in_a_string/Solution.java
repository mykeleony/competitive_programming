package leetcode.data_structures.string.reverse_words_in_a_string;

/**
 * Solution for LeetCode problem #151. Reverse Words in a String.
 * <p>
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/reverse-words-in-a-string/">LeetCode 151</a>
 */
public class Solution {
	public static void main(String[] args) {
		test("the sky is blue", "blue is sky the");
		test("  hello world  ", "world hello");
		test("a good   example", "example good a");
		test("  Bob and   Alice  ", "Alice and Bob");
		test("  EPY2giL", "EPY2giL");
		
		// Edge cases
		test(null, "");
		test(" ", "");
		test("single_word", "single_word");
		test("   a   b  ", "b a");
	}
	
	/**
	 * Reverses the order of words in a given string.
	 * <p>
	 * The solution involves three main steps:
	 * 1. Trim leading, trailing, and reduce multiple spaces to single spaces.
	 * 2. Reverse the entire string.
	 * 3. Reverse each individual word in the reversed string.
	 *
	 * @param s the input string
	 * @return the string with the words reversed
	 */
	public static String reverseWords(String s) {
		if (s == null || s.isBlank()) {
			return "";
		}
		
		char[] chars = trim(s).toCharArray();
		int n = chars.length;
		
		// Reverse the entire string
		reverse(chars, 0, n - 1);
		
		// Reverse each word in the reversed string
		int left = 0;
		for (int right = 1; right < n; right++) {
			if (chars[right] == ' ') {
				reverse(chars, left, right - 1);    // Reverse the word before the space
				
				left = right + 1;
			} else if (right == n - 1) {
				reverse(chars, left, right);    // Reverse the last word
			}
		}
		
		StringBuilder reversedSB = new StringBuilder();
		for (char ch : chars) {
			reversedSB.append(ch);
		}
		
		return reversedSB.toString();
	}
	
	/**
	 * Reverses the characters in a specified range of a character array.
	 *
	 * @param chars the character array
	 * @param start the starting index of the range to reverse (inclusive)
	 * @param end   the ending index of the range to reverse (inclusive)
	 */
	public static void reverse(char[] chars, int start, int end) {
		while (start < end) {
			char temp = chars[end];
			
			chars[end] = chars[start];
			chars[start] = temp;
			
			start++;
			end--;
		}
	}
	
	public static String trim(String s) {
		int left = 0;
		int right = s.length() - 1;
		
		// Trim leading spaces
		while (s.charAt(left) == ' ') left++;
		while (s.charAt(right) == ' ') right--;
		
		StringBuilder cleanSB = new StringBuilder();
		boolean isSpaceAllowed = true;
		
		while (left <= right) {
			char ch = s.charAt(left++);
			
			if (ch != ' ') {
				cleanSB.append(ch);
				isSpaceAllowed = true;
			} else if (isSpaceAllowed) {
				cleanSB.append(ch);
				isSpaceAllowed = false;
			}
		}
		
		return cleanSB.toString();
	}
	
	private static void test(String s, String expected) {
		String result = reverseWords(s);
		System.out.printf("Input: \"%-20s\" | Result: \"%-20s\" | Expected: \"%-20s\" | %s%n",
				s,
				result,
				expected,
				result.equals(expected) ? "✓ PASS" : "✗ FAIL");
	}
}
