package leetcode.list;

/**
 * 单链表 移除重复元素
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesfromSortedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {

        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            if (next == null) {
                break;
            }

            if (current.val == next.val) {
                // remove current
                if (prev == null) {
                    head = next;
                    current = head;
                    prev = null;
                } else {
                    prev.next = next;
                    current = next;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }


}
