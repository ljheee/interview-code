package leetcode.medium;

/**
 * Created by lijianhua04 on 2019/11/2.
 * https://leetcode.com/problems/add-two-numbers/
 * <p>
 * 参考 https://blog.csdn.net/crazy1235/article/details/52914703
 *      solution 3
 * <p>
 */
public class AddTwoNumbers_AC {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode point = null;

        // 代表 进位，进位可能不止是1
        int carry = 0;

        // 两个链表 都存在数时
        while (l1 != null && l2 != null) {
            int m = carry + l1.val + l2.val;
            carry = m / 10;
            m = m % 10;

            if (listNode == null) {
                listNode = new ListNode(m);
                point = listNode;
            } else {
                point.next = new ListNode(m);
                point = point.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        // 只剩l1 有元素时
        while (l1 != null) {
            int m = carry + l1.val;
            carry = m / 10;
            m = m % 10;

            point.next = new ListNode(m);
            point = point.next;
            l1 = l1.next;
        }
        // 只剩l2 有元素时
        while (l2 != null) {
            int m = carry + l2.val;
            carry = m / 10;
            m = m % 10;

            point.next = new ListNode(m);
            point = point.next;
            l2 = l2.next;
        }

        if (carry != 0) {
            point.next = new ListNode(carry);
        }

        return listNode;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
