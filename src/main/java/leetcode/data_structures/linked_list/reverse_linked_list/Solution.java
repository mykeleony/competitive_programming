package leetcode.data_structures.linked_list.reverse_linked_list;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Solution for LeetCode problem #206. Reverse Linked List.
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
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
		
		test(null, null);
		
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
				listToString(head),
				listToString(result),
				listToString(expected),
				areListsEqual(result, expected) ? "✓ PASS" : "✗ FAIL");
	}
	
	private static boolean areListsEqual(ListNode l1, ListNode l2) {
		while (l1 != null && l2 != null) {
			if (l1.val != l2.val) return false;
			
			l1 = l1.next;
			l2 = l2.next;
		}
		
		return l1 == null && l2 == null;
	}
	
	private static String listToString(ListNode head) {
		StringBuilder sb = new StringBuilder("[");
		ListNode current = head;
		
		while (current != null) {
			sb.append(current.val);
			
			if (current.next != null) sb.append(", ");
			
			current = current.next;
		}
		
		return sb.append("]").toString();
	}
	
	private static ListNode copyList(ListNode head) {
		if (head == null) return null;
		
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		
		while (head != null) {
			current.next = new ListNode(head.val);
			current = current.next;
			head = head.next;
		}
		
		return dummy.next;
	}
}

class ListNode {
	int val;
	ListNode next;
	
	ListNode() {
	}
	
	ListNode(int val) {
		this.val = val;
	}
	
	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}