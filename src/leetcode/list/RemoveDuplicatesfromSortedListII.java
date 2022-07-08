package leetcode.list;

/**
 * 单链表 移除重复元素
 * 只留下非重复元素
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * 一个Node 被移除，有两种情况：
 * 1、this.val==next.val 移除this
 * 2、this.val==next.val 下一步时，移除next
 */
public class RemoveDuplicatesfromSortedListII {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {

        boolean removeNext = false;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            if (next == null) {
                break;
            }

            if (current.val == next.val || removeNext) {
                // remove current And mark removeNext
                removeNext = current.val == next.val;// 更新removeNext的值，给下一步使用
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

        // 判断最后一个元素，是否需要移除：因为最后一个next == null时 break了
        if (removeNext) {
            // remove current
            if (prev == null) {
                return null;
            } else {
                prev.next = null;
            }
        }

        return head;
    }


}
