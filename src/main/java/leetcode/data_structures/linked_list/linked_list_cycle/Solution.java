package leetcode.data_structures.linked_list.linked_list_cycle;

public class Solution {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);

        /**/
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        System.out.println("Must be true: " + floydHasCycle(n1));

        n4.next = null;

        System.out.println("Must be false: " + floydHasCycle(n1));
        System.out.println("Must be false: " + floydHasCycle(new ListNode(1)));

        // 1 -> 2 -> 1
        n2.next = n1;


        System.out.println("Must be true: " + floydHasCycle(n1));
    }
    public static boolean floydHasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

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
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}