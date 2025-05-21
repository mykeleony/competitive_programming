package leetcode.data_structures.linked_list.reverse_linked_list;

import leetcode.data_structures.linked_list.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static leetcode.data_structures.linked_list.utils.ListNode.copyList;

/**
 * Solution for LeetCode problem <b>#206. Reverse Linked List.</b>
 * <p>
 * Given the {@code head} of a singly linked list, reverse the list, and return <i>the reversed list</i>.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/reverse-linked-list/">LeetCode 206</a>
 */
public class Solution {
	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode expected1 = new ListNode(5, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))));
		test(list1, expected1);
		
		ListNode list2 = new ListNode(1, new ListNode(2));
		ListNode expected2 = new ListNode(2, new ListNode(1));
		test(list2, expected2);
		
		ListNode list3 = new ListNode(1);
		ListNode expected3 = new ListNode(1);
		test(list3, expected3);
		
		// Edge cases
		ListNode list4 = new ListNode(1, new ListNode(1, new ListNode(1)));
		ListNode expected4 = new ListNode(1, new ListNode(1, new ListNode(1)));
		test(list4, expected4);
	}
	
	/**
	 * Reverses a singly linked list iteratively.
	 *
	 * @param head the head of the linked list
	 * @return the head of the reversed linked list
	 */
	public static ListNode reverseList(ListNode head) {
		ListNode previous = null;
		ListNode current = head;
		
		while (current != null) {
			ListNode nextTemp = current.next;
			current.next = previous;
			
			previous = current;
			current = nextTemp;
		}
		
		return previous;
	}
	
	/**
	 * Reverses a singly linked list using a stack.
	 * This approach first pushes all node values onto a stack, then pops them
	 * to construct a new reversed linked list.
	 * Time Complexity: O(n) due to two passes (one for pushing, one for popping/building).
	 * Space Complexity: O(n) for the stack.
	 *
	 * @param head the head of the linked list
	 * @return the head of the newly created reversed linked list
	 */
	public ListNode reverseListFirstTry(ListNode head) {
		Deque<Integer> stack = new ArrayDeque<>();
		ListNode dummy = new ListNode();
		ListNode result = dummy;
		ListNode current = head;
		
		while (current != null) {
			stack.push(current.val);
			current = current.next;
			
		}
		
		while (!stack.isEmpty()) {
			result.next = new ListNode(stack.pop());
			result = result.next;
		}
		
		return dummy.next;
	}
	
	private static void test(ListNode head, ListNode expected) {
		ListNode result = reverseList(copyList(head)); // Pass a copy to avoid modifying the original test input
		
		System.out.printf("Input: %-20s | Result: %-20s | Expected: %-20s | %s%n",
				head,
				result,
				expected,
				expected.equals(result) ? "✓ PASS" : "✗ FAIL");
	}

}
