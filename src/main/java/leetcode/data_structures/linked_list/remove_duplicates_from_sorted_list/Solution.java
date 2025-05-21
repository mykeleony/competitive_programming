package leetcode.data_structures.linked_list.remove_duplicates_from_sorted_list;

import leetcode.data_structures.linked_list.utils.ListNode;

import static leetcode.data_structures.linked_list.utils.ListNode.copyList;

/**
 * Solution for LeetCode problem <b>#83. Remove Duplicates from Sorted List.</b>
 * <p>
 * Given the {@code head} of a sorted linked list, <i>delete all duplicates such that each element appears only once</i>.
 * Return <i>the linked list <b>sorted</b> as well</i>.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list/">LeetCode 83</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new ListNode(1,
				new ListNode(1, new ListNode(2))), new ListNode(1, new ListNode(2)));
		test(new ListNode(1,
				new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3))))), new ListNode(1, new ListNode(2, new ListNode(3))));
		
		// Edge cases
		test(new ListNode(1), new ListNode(1)); // Single element list
		
		test(new ListNode(1, new ListNode(1, new ListNode(1))),
				new ListNode(1)); // All duplicates
		
		test(new ListNode(1, new ListNode(2, new ListNode(3))),
				new ListNode(1, new ListNode(2, new ListNode(3)))); // No duplicates
		
		test(new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3)))),
				new ListNode(1, new ListNode(2, new ListNode(3))));
	}
	
	/**
	 * Deletes all duplicates from a sorted singly linked list such that each element appears only once.
	 * The list remains sorted.
	 *
	 * @param head the head of the sorted linked list.
	 * @return the head of the linked list with duplicates removed.
	 */
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode current = head;
		
		while (current != null && current.next != null) {
			if (current.val == current.next.val) {
				// Current node's value is same as next. Skip the next node (effectively deleting it)
				current.next = current.next.next;
			} else {
				// Values are different. Move to the next node
				current = current.next;
			}
		}
		
		return head;
	}
	
	/**
	 * An alternative approach to delete duplicates from a sorted linked list.
	 * This version might be more complex than the primary solution.
	 *
	 * @param head the head of the sorted linked list.
	 * @return the {@code head} of the linked list with duplicates removed.
	 */
	public static ListNode deleteDuplicatesFirstTry(ListNode head) {
		ListNode start = head;
		ListNode previous;
		
		while (head != null) {
			previous = head;
			head = head.next;
			
			if (head != null && head.val == previous.val) {
				previous.next = head.next;
				head = previous;
			}
		}
		
		return start;
	}
	
	/**
	 * Helper method to test the deleteDuplicates function with expected output.
	 *
	 * @param head     the head of the input linked list.
	 * @param expected the head of the expected linked list after duplicate removal.
	 */
	private static void test(ListNode head, ListNode expected) {
		// Create a deep copy of the input list to avoid modification during the test
		ListNode headCopy = copyList(head);
		ListNode result = deleteDuplicates(headCopy);
		
		System.out.printf("Input: %-25s | Result: %-25s | Expected: %-25s | %s%n",
				head,
				result,
				expected,
				result.equals(expected) ? "✓ PASS" : "✗ FAIL");
	}
}
