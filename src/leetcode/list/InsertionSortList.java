package leetcode.list;

import java.util.Arrays;

/**
 * 使用单链表 实现插入排序
 * https://leetcode.com/problems/insertion-sort-list/
 * 每次从原列表取出一个，插入新的有序列表
 */
public class InsertionSortList {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return head;

        ListNode newHead = new ListNode(head.val);
        head = head.next;// 每次从原列表取出一个

        while (head != null) {
            boolean hasInsertion = false;
            ListNode prev = null;
            ListNode current = newHead;// 从有序列表头开始往后找，插入合适的位置
            while (current != null && hasInsertion == false) { //防止重复进入
                if (current.val > head.val) {
                    hasInsertion = true;
                    // 插入到current的前面
                    if (prev == null) {
                        prev = new ListNode(head.val);
                        prev.next = current;
                        newHead = prev;
                    } else {
                        prev.next = new ListNode(head.val);
                        prev.next.next = current;
                    }
                }
                prev = current;
                current = current.next;
            }
            if (hasInsertion == false) {
                // 还没插入，是当前最大的，追加到尾部
                prev.next = new ListNode(head.val);
            }
            head = head.next;// 每次从原列表取出一个
        }
        return newHead;
    }

    /**
     * 单链表 实现O(NlogN)的排序
     * https://leetcode.com/problems/sort-list/
     * <p>
     * 从第二个元素起，和前驱比较，比前驱小，就往前移动；如果是双向链表可以这么实现，单链表没有prev指针，不能往前找；
     * <p>
     * 所有只能 使用归并排序;
     * 数组归并 可以基于下标进行切分，但链表不行。
     * https://leetcode-cn.com/problems/sort-list/solution/zi-di-xiang-shang-de-gui-bing-pai-xu-java-dai-ma-b/
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode[] counter = new ListNode[64];
        int maxIndex = 0;

        ListNode current = head;
        while (current != null) {

            ListNode carryNode = current;// 先暂存起来
            current = current.next;
            carryNode.next = null;

            int i = 0;
            while (counter[i] != null) {
                carryNode = mergeTwoLists(carryNode, counter[i]);
                counter[i] = null;
                i++;
            }

            counter[i] = carryNode;// 为空，直接放
            // 记录最多使用到 counter 数组的第几位，最后合并的时候要用上
            if (i > maxIndex) {
                maxIndex = i;
            }
        }

        ListNode res = null;
        for (int i = 0; i <= maxIndex; i++) {
            if (counter[i] != null) {
                res = mergeTwoLists(res, counter[i]);
            }
        }
        return res;

    }


    /**
     * Merge Two Sorted Lists
     * https://leetcode.com/problems/merge-two-sorted-lists/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode newHead = new ListNode(-1);
        ListNode point = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                point.next = new ListNode(l1.val);
                l1 = l1.next;
                point = point.next;
            } else {
                point.next = new ListNode(l2.val);
                l2 = l2.next;
                point = point.next;
            }
        }
        while (l1 != null) {
            point.next = new ListNode(l1.val);
            l1 = l1.next;
            point = point.next;
        }
        while (l2 != null) {
            point.next = new ListNode(l2.val);
            l2 = l2.next;
            point = point.next;
        }
        return newHead.next;
    }




    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        ListNode point = listNode;

        for (int i = 2; i <= 2; i++) {
            point.next = new ListNode(i);
            point = point.next;

            point.next = new ListNode(1);
            point = point.next;
            point.next = new ListNode(3);
            point = point.next;
        }


        ListNode sortList = insertionSortList(listNode);
        System.out.println(sortList);// 1 2 3 4


    }
}
