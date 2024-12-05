package leetcode.list;

import leetcode.medium.AddTwoNumbers2;

/**
 * 单链表 删除指定元素
 * https://leetcode.com/problems/remove-linked-list-elements/submissions/
 */
public class RemoveLinkedListElements {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {  // remove current节点
                if (prev == null) {// current 是head
                    head = head.next;
                    current = head;
                    prev = null;
                } else {
                    // 删除current
                    prev.next = current.next;
                    current = current.next;
                }
            } else {
                // 跳过current，进入下一个node
                prev = current;
                current = current.next;
            }
        }

        return head;
    }


    /**
     * 从单链表 中，移除 第from~to的node
     * (from,to]
     *
     * @param head
     * @param from
     * @param to
     * @return
     */
    private static ListNode removeElements(ListNode head, int from, int to) {

        int i = 0;
        ListNode point = null;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            while (i <= from) {
                prev = current;
                current = current.next;
                i++;
            }
            while (i >= from + 1 && i <= to) {
                current = current.next;
                i++;
            }
            if (i - to == 1) {
                prev.next = current;
                point = prev.next;
                i++;
            } else {
                // 往后加
                point.next = current;
                point = point.next;
            }
            if(current != null){
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 7; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        removeElements(listNode, 0, 6);

        while (listNode != null) {
            System.out.print(listNode.val + "  ");
            listNode = listNode.next;
        }
    }


}
