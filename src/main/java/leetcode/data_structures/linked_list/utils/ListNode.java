package leetcode.data_structures.linked_list.utils;

import java.util.Objects;

/**
 * Represents a node in a singly linked list.
 */
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}
	
	public ListNode(int val) {
		this.val = val;
	}
	
	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	/**
	 * Helper method to create a deep copy of a ListNode.
	 * This is crucial for testing functions that modify linked lists in-place.
	 *
	 * @param head the head of the original ListNode.
	 * @return a deep copy of the ListNode.
	 */
	public static ListNode copyList(ListNode head) {
		if (head == null) {
			return null;
		}
		
		ListNode dummy = new ListNode(0);
		ListNode currentCopy = dummy;
		ListNode currentOriginal = head;
		
		while (currentOriginal != null) {
			currentCopy.next = new ListNode(currentOriginal.val);
			currentCopy = currentCopy.next;
			currentOriginal = currentOriginal.next;
		}
		
		return dummy.next;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[" + val);
		
		while (next != null) {
			sb.append(", ");
			sb.append(next.val);
			
			next = next.next;
		}
		
		return sb.append("]").toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		ListNode l1 = this;
		ListNode l2 = (ListNode) o;
		
		// Traverse both lists and compare values
		while (l1 != null && l2 != null) {
			if (l1.val != l2.val) {
				return false;
			}
			
			l1 = l1.next;
			l2 = l2.next;
		}
		
		// Both lists must reach their end at the same time for them to be equal
		return l1 == null && l2 == null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(val, next);
	}
}
