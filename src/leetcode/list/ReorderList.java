package leetcode.list;

import java.util.ArrayList;

/**
 * 链表长排序
 * https://leetcode.com/problems/reorder-list/submissions/
 */
public class ReorderList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void reorderList(ListNode head) {

        if (head == null) {
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();

        //遍历一遍：得到链表每个val
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }

        int size = list.size();
        if (size == 1) {
            return;
        }

        // 奇数对
//        boolean odd = ((size & (size - 1)) == 0) ? false : true;
        boolean odd = (size & 1) == 0 ? false : true;

        ListNode point = head;
        int n = size - 1;
        for (int i = 0; i <= n / 2; i++) {

            if (i != 0) {
                // 忽略 第一个(head)
                ListNode nodeI = new ListNode(list.get(i));
                point.next = nodeI;
                point = point.next;
            }

            // 奇数对，忽略最后一个元素
            if (i == n / 2 && odd == true) {
                break;
            }
            ListNode nodeN_I = new ListNode(list.get(n - i));
            point.next = nodeN_I;
            point = point.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 6; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        reorderList(listNode);

        System.out.println(listNode);

        int x = 6;
        System.out.println((x & (x - 1)) == 0);//false
    }


}
