package leetcode.list;

import java.util.HashSet;

/**
 * Created by lijianhua04 on 2019/11/13.
 */
public class LinkedListCycleII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 快慢指针检测环
     * https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-jian-ce-de-liang-chong-jie-fa-/
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            // 慢指针每次走一步
            slow = slow.next;
            // 快指针每次走两步
            fast = fast.next.next;
            // 当快慢指针相同时，则说明是在圆环里面
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检测环的起点
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/
     */
    public ListNode detectCycle(ListNode head) {

        //阶段1：检测是否有环，返回在环中相遇的node
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // 阶段2：两个指针，分别从head和环相遇点 以相同的速度前进，新的相遇点就是环的入口
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    //获得相遇点：第一次一定是在环上的某个node相遇
    public ListNode getIntersect(ListNode head) {
        if (head == null || head.next == null) return null;
        if (head.next.next == head) return head;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }


    public ListNode detectCycle_hash(ListNode head) {
        if (head == null || head.next == null) return null;
        if (head.next.next == head) return head;

        HashSet<ListNode> set = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            if (!set.add(current)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
