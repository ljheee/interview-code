package leetcode.list;

/**
 * 给定一个单链表，将其节点进行分组，使得所有的奇数节点排列在前，偶数节点在后。请注意这里的奇偶指的是节点序号而不是节点的值。
 * <p>
 * https://leetcode.com/problems/odd-even-linked-list/
 * <p>
 * 思路：遍历一遍列表，奇数的追加在odd上，偶数的追加在even上；结束后 将odd的oddTail和even的头 建立连接
 */
public class OddEvenLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        // 维护[序号为]奇数的node
        ListNode odd = head;
        ListNode oddTail = odd;

        // 维护[序号为]偶数的node
        ListNode even = head.next;
        ListNode evenTail = even;
        ListNode current = even.next;

        while (current != null) {
            oddTail.next = current;
            oddTail = oddTail.next;
            if (current != null) {
                current = current.next;
            }
            evenTail.next = current;
            evenTail = evenTail.next;
            if (current != null) {
                current = current.next;
            }
        }
        //结束后 将odd的oddTail和even的头 建立连接
        oddTail.next = even;
        return odd;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 5; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        point = oddEvenList(listNode);
        System.out.println(point);

    }
}
