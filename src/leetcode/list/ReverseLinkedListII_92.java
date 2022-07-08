package leetcode.list;

/**
 * 将单链表，翻转m~n
 * https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII_92 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * * 翻转整个链表
     * https://leetcode.com/problems/reverse-linked-list/
     * 非递归方式：用prev维护反向链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode prev = head;

        while (cur != null) {
            ListNode node = new ListNode(cur.val);
            node.next = prev;
            prev = node;
            cur = cur.next;
        }
        return prev;
    }


    /**
     * AC
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (m == n) return head;

        ListNode pre = head;
        for (int i = 1; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode bakPre = pre;
        System.out.println(pre.val);

        ListNode cur = pre.next;
        ListNode bakCur = cur;
        if (m == 1) {
            pre = null;
            bakPre = null;
            bakCur = cur = head;
        }

        for (int i = m; i <= n; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (bakCur != null) bakCur.next = cur;
        if (bakPre != null) {
            bakPre.next = pre;
        } else {
            return pre;
        }
        return head;
    }

    public static void main(String[] args) {
        ReverseLinkedListII_92 linkedListII92 = new ReverseLinkedListII_92();


        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 5; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }
        linkedListII92.reverseBetween(listNode, 1, 5);
    }

}
