package leetcode.data_structures.linked_list.merge_two_sorted_lists;

import leetcode.data_structures.linked_list.utils.ListNode; // Importing ListNode from utils package

/**
 * Solution for LeetCode problem <b>#21. Merge Two Sorted Lists.</b>
 * <p>
 * You are given the heads of two sorted linked lists {@code list1} and {@code list2}.
 * <p>
 * Merge the two lists into one <b>sorted</b> list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return <i>the head of the merged linked list</i>.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/merge-two-sorted-lists/">LeetCode 21</a>
 */
public class Solution {
    public static void main(String[] args) {
        test(new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4)))))));
        
        test(null, new ListNode(0), new ListNode(0));
        test(new ListNode(0), null, new ListNode(0));
        
        test(new ListNode(5),
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(5)))));
        
        test(new ListNode(1,
                new ListNode(2)), new ListNode(3, new ListNode(4)), new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
       
        test(new ListNode(3, new ListNode(4)), new ListNode(1, new ListNode(2)), new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
    }
    
    /**
     * Merges two sorted linked lists into one new sorted linked list.
     * The merged list is created by splicing together the nodes of the input lists.
     *
     * @param list1 the head of the first sorted linked list.
     * @param list2 the head of the second sorted linked list.
     * @return the head of the merged sorted linked list.
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode currentMerged = dummy;
        
        while (list1 != null || list2 != null) {
            if (list1 == null || (list2 != null && list1.val > list2.val)) {
                currentMerged.next = list2;
                list2 = list2.next;         // Move to the next node in list2
            } else {
                currentMerged.next = list1;
                list1 = list1.next;         // Move to the next node in list1
            }
            
            currentMerged = currentMerged.next; // Advance the merged list pointer
        }
        
        return dummy.next;
    }
    
    /**
     * Helper method to test the mergeTwoLists function.
     *
     * @param list1Input the head of the first input linked list.
     * @param list2Input the head of the second input linked list.
     * @param expected   the head of the expected merged linked list.
     */
    private static void test(ListNode list1Input, ListNode list2Input, ListNode expected) {
        ListNode list1 = ListNode.copyList(list1Input);
        ListNode list2 = ListNode.copyList(list2Input);
        
        ListNode result = mergeTwoLists(list1, list2);
        
        System.out.printf("Input: list1 = %-15s, list2 = %-15s | Result: %-25s | Expected: %-25s | %s%n",
                list1Input,
                list2Input,
                result,
                expected,
                result.equals(expected) ? "✓ PASS" : "✗ FAIL");
    }
}
