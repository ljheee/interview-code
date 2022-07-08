package leetcode.list;

import java.util.*;

/**
 * 将k 个list 有序单列表 进行merge
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 注意：重复元素，需要出现多次
 */
public class MergeKLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode newHead = null;
        ListNode point = null;

        // 全局保存，lists[i]为空的(i索引)
        List<Integer> nullIndex = new ArrayList<>();
        while (true) {

            int min = Integer.MAX_VALUE;
            // 需要前进的 lists[i]索引，val相等时 需要前进多次
            List<Integer> needNextIndex = new ArrayList<>();
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    if (nullIndex.contains(i)) {
                    } else {
                        nullIndex.add(i);
                    }
                    continue;
                }
                int val = lists[i].val;
                if (val <= min) {
                    min = val;
                    needNextIndex.add(i);
                }
            }
            // 所有list为空时，结束
            if (nullIndex.size() == lists.length) {
                break;
            }
            if (newHead == null) {
                newHead = new ListNode(min);
                point = newHead;
            } else {
                point.next = new ListNode(min);
                point = point.next;
            }
            // val相等时 需要前进多次
            int n = needNextIndex.size() - 1;
            while (n-- > 0) {
                point.next = new ListNode(min);
                point = point.next;
            }

            // 执行 需要前进的lists[i]
            for (Integer idx : needNextIndex) {
                lists[idx] = lists[idx].next;
            }
            needNextIndex.clear();

            // 所有list为空时，结束
            if (nullIndex.size() == lists.length) {
                break;
            }
        }
        return newHead;
    }


    // AC
    public ListNode mergeKLists0(ListNode[] lists) {

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        ListNode newHead = null;
        ListNode point = null;

        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node == null) continue;
            priorityQueue.add(node);
            lists[i] = node.next;
        }
        while (!priorityQueue.isEmpty()) {
            ListNode min = priorityQueue.poll();
            if (newHead == null) {
                newHead = new ListNode(min.val);
                point = newHead;
            } else {
                point.next = new ListNode(min.val);
                point = point.next;
            }

            if (min.next != null) {
                priorityQueue.add(min.next);
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[4];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(2);
        lists[0].next.next = new ListNode(3);
        lists[1] = new ListNode(2);
        lists[2] = new ListNode(3);
        lists[3] = new ListNode(1);

        ListNode listNode = new MergeKLists().mergeKLists(lists);
        System.out.println(listNode);
    }
}
