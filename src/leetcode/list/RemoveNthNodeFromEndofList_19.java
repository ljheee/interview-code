package leetcode.list;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/
 * 移除链表倒数第n个node
 */
public class RemoveNthNodeFromEndofList_19 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode dalay = head;

        // 记录删除节点的前驱，防止删除的是最后一个元素
        ListNode preOfDelay = null;

        while (cur != null) {
            cur = cur.next;
            n--;
            if (n < 0) {
                preOfDelay = dalay;
                dalay = dalay.next;
            }
        }

        // remove delay
        ListNode next = dalay.next;
        if (next != null) {
            dalay.val = next.val;
            dalay.next = next.next;
            return head;
        }

        if (preOfDelay == null) {
            head = null;
        } else {
            preOfDelay.next = null;
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 5; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

//        new RemoveNthNodeFromEndofList_19().removeNthFromEnd(listNode,2);
        new RemoveNthNodeFromEndofList_19().removeNthFromEnd(listNode, 1);
//        new RemoveNthNodeFromEndofList_19().removeNthFromEnd(listNode, 1);
    }
}
