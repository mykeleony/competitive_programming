package leetcode.data_structures.linked_list.linked_list_cycle;

import leetcode.data_structures.linked_list.utils.ListNode;

/**
 * Solution for LeetCode problem <b>#141. Linked List Cycle.</b>
 * <p>
 * Given {@code head}, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the {@code next} pointer.
 * Internally, {@code pos} is used to denote the index of the node that tail's {@code next} pointer is connected to. <b>Note that {@code pos} is not passed as a parameter.</b>
 * <p>
 * Return {@code true} <i>if there is a cycle in the linked list</i>. Otherwise, return {@code false}.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/linked-list-cycle/">LeetCode 141</a>
 */
public class Solution {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; // Creates a cycle: 3 -> 2 -> 0 -> -4 -> (back to 2)
        
        test("3 -> 2 -> 0 -> -4 -> (back to 2)", n1, true);
        
        ListNode n1NoCycle = new ListNode(3);
        ListNode n2NoCycle = new ListNode(2);
        ListNode n3NoCycle = new ListNode(0);
        ListNode n4NoCycle = new ListNode(-4);
        n1NoCycle.next = n2NoCycle;
        n2NoCycle.next = n3NoCycle;
        n3NoCycle.next = n4NoCycle;
        n4NoCycle.next = null; // Breaks the cycle
        test("3 -> 2 -> 0 -> -4 -> null", n1NoCycle, false);
        
        // Test case 3: Single node, no cycle
        test("1 -> null", new ListNode(1), false);
        
        // Test case 4: Two nodes, no cycle
        test("1 -> 2 -> null", new ListNode(1, new ListNode(2)), false);
        
        // Test case 5: Cycle with two nodes (1 -> 2 -> 1)
        ListNode cycleHead2Nodes = new ListNode(1);
        ListNode cycleNode2_2Nodes = new ListNode(2);
        cycleHead2Nodes.next = cycleNode2_2Nodes;
        cycleNode2_2Nodes.next = cycleHead2Nodes; // Creates a cycle: 1 -> 2 -> (back to 1)
        test("1 -> 2 -> (back to 1)", cycleHead2Nodes, true);
        
        // Test case 6: Empty list
        test("[]", null, false);
    }
    
    /**
     * Detects if a linked list has a cycle using Floyd's Cycle-Finding Algorithm (Tortoise and Hare).
     * This method uses two pointers, one moving slow (one step at a time) and one moving fast (two steps at a time).
     * <p>
     * If there's a cycle, the fast pointer will eventually catch up to the slow pointer.
     * <p>
     * Time Complexity: <b>O(n)</b>, where n is the number of nodes in the list.
     * <br>
     * Space Complexity: <b>O(1)</b>.
     *
     * @param head The head of the linked list.
     * @return True if the linked list has a cycle, false otherwise.
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head.next; // Start fast one step ahead of slow
        
        // Traverse the list with two pointers
        while (slow != fast) {
            // If fast pointer reaches the end of the list, there is no cycle
            if (fast == null || fast.next == null) {
                return false;
            }
            
            slow = slow.next;       // Slow pointer moves one step
            fast = fast.next.next;  // Fast pointer moves two steps
        }
        
        return true; // Pointers met. A cycle exists
    }
    
    /**
     * Detects if a linked list has a cycle by modifying the node values (destructive method).
     * It marks visited nodes by changing their value to {@code Integer.MIN_VALUE}. If a node with
     * {@code Integer.MIN_VALUE} is encountered again, it indicates a cycle.
     * <p>
     * This method modifies the original list by changing the {@code val} of visited nodes.
     * Therefore, it should be used with caution if the original list's integrity is important.
     * </p>
     * Time Complexity: <b>O(n)</b>, where n is the number of nodes in the list.
     * <br>
     * Space Complexity: <b>O(1)</b>.
     *
     * @param head the head of the linked list.
     * @return true if the linked list has a cycle, false otherwise.
     */
    public static boolean destructiveHasCycle(ListNode head) {
        while (head != null) {
            if (head.val == Integer.MIN_VALUE) {
                return true;
            }
            
            head.val = Integer.MIN_VALUE;
            head = head.next;
        }
        
        return false;
    }
    
    /**
     * Helper method to test the floydHasCycle function.
     *
     * @param description a string describing the input linked list for display in test output.
     * @param head        the head of the input linked list.
     * @param expected    the expected boolean result (true if cycle, false if no cycle).
     */
    private static void test(String description, ListNode head, boolean expected) {
        boolean result = hasCycle(head);
        System.out.printf("Input: %-40s | Result: %-5b | Expected: %-5b | %s%n",
                description,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}
