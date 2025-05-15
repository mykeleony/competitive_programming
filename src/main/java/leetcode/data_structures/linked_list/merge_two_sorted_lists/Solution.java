package leetcode.data_structures.linked_list.merge_two_sorted_lists;

// Resolution for problem: https://leetcode.com/problems/merge-two-sorted-lists
public class Solution {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode l = mergeTwoLists(l1, l2);

        System.out.println(l);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode result = dummy;

        while (list1 != null || list2 != null) {
            if (list1 == null || (list2 != null && list1.val > list2.val)) {
                result.next = list2;
                result = result.next;
                list2 = list2.next;
            } else {
                result.next = list1;
                result = result.next;
                list1 = list1.next;
            }
        }

        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
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
}
