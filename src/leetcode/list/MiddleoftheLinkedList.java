package leetcode.list;

/**
 * https://leetcode.com/problems/middle-of-the-linked-list/
 * 返回链表 后一半
 */
public class MiddleoftheLinkedList {
    public RemoveLinkedListElements.ListNode middleNode(RemoveLinkedListElements.ListNode head) {

        //遍历一遍：得到链表长度
        int n = 0;
        RemoveLinkedListElements.ListNode current = head;
        while (current != null) {
            n++;
            current = current.next;
        }

        // 移除前一半
        n = n / 2;
        while (n-- > 0) {
            head = head.next;
        }

        return head;
    }
}
