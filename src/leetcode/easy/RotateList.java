package leetcode.easy;

import java.util.LinkedList;

/**
 * 数组后k项 移到前面
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * https://leetcode.com/problems/rotate-list/
 * <p>
 * 注意点：对k特殊处理，k有可能比n大，k取k%len是等价的；——不然有case的k=20w会超时
 */
public class RotateList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }

        LinkedList<ListNode> linkedList = new LinkedList<>();
        ListNode current = head;
        while (current != null) {
            linkedList.addFirst(current);
            current = current.next;
        }

        k = k % linkedList.size();//对k特殊处理，k有可能比n大，k取k%len是等价的；
        while (k-- > 0) {
            ListNode tail = linkedList.removeFirst();
            ListNode preTail = linkedList.peekFirst();
            preTail.next = null;
            tail.next = head;

            head = tail;
            linkedList.addLast(tail);
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 3; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        listNode = rotateRight(listNode, 2);
        System.out.println(listNode);


    }

}
