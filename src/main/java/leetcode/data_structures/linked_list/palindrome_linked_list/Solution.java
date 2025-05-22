package leetcode.data_structures.linked_list.palindrome_linked_list;

import leetcode.data_structures.linked_list.utils.ListNode;

import static leetcode.data_structures.linked_list.reverse_linked_list.Solution.reverseList;

/**
 * Solution for LeetCode problem <b>#234. Palindrome Linked List.</b>
 * <p>
 * Given the {@code head} of a singly linked list, return {@code true} <i>if it is a palindrome or</i> {@code false} <i>otherwise</i>.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/palindrome-linked-list/">LeetCode 234</a>
 */
public class Solution {
	public static void main(String[] args) {
		// Even length palindrome
		test(new ListNode(1,
				new ListNode(2, new ListNode(2, new ListNode(1)))), true);
		
		// Odd length palindrome
		test(new ListNode(1, new ListNode(2,
				new ListNode(3, new ListNode(2, new ListNode(1))))), true);
		
		test(new ListNode(1, new ListNode(2, new ListNode(3,
				new ListNode(4)))), false);
		
		test(new ListNode(1, new ListNode(2)), false);
		test(new ListNode(1), true); // Single node
		test(null, true); // Empty list
		test(new ListNode(1, new ListNode(0, new ListNode(1))), true);
		test(new ListNode(1, new ListNode(1)), true);
		test(new ListNode(1, new ListNode(2, new ListNode(1))), true);
		test(new ListNode(1, new ListNode(2, new ListNode(3))), false);
	}
	
	/**
	 * Determines if a singly linked list is a palindrome using the <b>fast and slow pointer approach</b>
	 * to find the middle, reverse the second half and compare it with the first half.
	 * <p>
	 * <b>Time Complexity: O(n)</b> due to list traversing
	 * <br>
	 * <b>Space Complexity: O(1)</b> since a fixed set of variables is used
	 *
	 * @param head the head of the linked list.
	 * @return {@code true} if the list is a palindrome, {@code false} otherwise.
	 */
	public static boolean isPalindrome(ListNode head) {
		// An empty list or a single-node list is always a palindrome
		if (head == null || head.next == null) {
			return true;
		}
		
		// Step 1: Find the middle of the linked list.
		ListNode slow = head;   // Moves one step at a time
		ListNode fast = head;   // Moves two steps at a time
		ListNode prev = null;   // Pointer to the node before 'slow'
		
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// - If 'fast' is null, the list has an even number of nodes. 'slow' is now the head of the second half.
		// - If 'fast' is not null (meaning fast.next is null), the list has an odd number of nodes.
		//  'slow' is the middle node. The actual second half starts from 'slow.next'.
		ListNode secondHalfStart; // This will be the actual head of the second half to be reversed
		
		if (fast != null) { // Odd-length list
			secondHalfStart = slow.next;
			slow.next = null;   // Disconnect the middle node from the second half to clearly separate the halves.
		} else {
			secondHalfStart = slow;    // 'slow' is already the head of the second half.
		}
		
		// Disconnect the first half from the second half.
		// 'prev' is the last node of the first half. Setting its 'next' to null effectively splits the list.
		// Needed for lists with 1 or 2 nodes where 'prev' might be null.
		prev.next = null;
		
		// Step 2: Reverse the second half of the linked list.
		ListNode reversedSecondHalf = reverseList(secondHalfStart);
		
		// Step 3: Compare the first half with the reversed second half.
		// Compare nodes from the beginning of the original list with nodes from the reversed second half.
		ListNode p1 = head;
		ListNode p2 = reversedSecondHalf;
		boolean isPalindrome = true; // Assume it's a palindrome until a mismatch is found
		
		while (p1 != null && p2 != null) {
			if (p1.val != p2.val) {
				isPalindrome = false; // Mismatch found
				break;
			}
			
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return isPalindrome;
	}
	
	/**
	 * Determines if a singly linked list is a palindrome by converting it to a string
	 * and then checking if the string is a palindrome.
	 * <p>
	 * <b>Time Complexity: O(n)</b> due to string conversion and palindrome check.
	 * <br>
	 * <b>Space Complexity: O(n)</b> for the StringBuilder/String.
	 *
	 * @param head the head of the linked list.
	 * @return true if the list is a palindrome, false otherwise.
	 */
	public static boolean isPalindromeFirstTry(ListNode head) {
		StringBuilder sb = new StringBuilder();
		
		while (head != null) {
			sb.append(head.val);
			
			head = head.next;
		}
		
		String numbers = sb.toString();
		int left = 0;
		int right = numbers.length() - 1;
		
		while (left < right) {
			if (numbers.charAt(left++) != numbers.charAt(right--)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Helper method to test the isPalindrome function.
	 *
	 * @param head     The head of the input linked list.
	 * @param expected The expected boolean result (true if palindrome, false otherwise).
	 */
	private static void test(ListNode head, boolean expected) {
		ListNode headCopy = ListNode.copyList(head);
		boolean result = isPalindrome(headCopy);
		
		System.out.printf("Input: %-30s | Result: %-5b | Expected: %-5b | %s%n",
				head,
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}
