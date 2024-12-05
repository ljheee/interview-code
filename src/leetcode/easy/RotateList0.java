package leetcode.easy;

/**
 * 数组后k项 移到前面
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * https://leetcode.com/problems/rotate-list/
 *
 * 本实现思路：每次取链表的tail 当做新head，原tail的前驱 preTail.next = null
 */
public class RotateList0 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return head;
        }

        // 只有head一个元素时，直接返回
        if(head.next == null){
            return head;
        }
        while (k-- > 0) {
            head = rotate(head);
        }
        return head;
    }

    //本实现思路：每次取链表的tail 当做新head，原tail的前驱 preTail.next = null
    public static ListNode rotate(ListNode head) {
        ListNode tail = null;
        ListNode prev = null;
        if (tail == null) {
            ListNode current = head;
            while (current != null) {
                if (current.next == null) {
                    tail = current;
                    break;
                }
                prev = current;
                current = current.next;
            }
        }

        prev.next = null;
        tail.next = head;
        return tail;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 3; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        listNode= rotate(listNode);
        System.out.println(listNode);

    }

}
