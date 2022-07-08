package leetcode.list;

/**
 * 将k 个list 有序单列表 进行merge
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * 简单版，只能实现 链表元素不重复的merge
 */
public class MergeKLists0 {
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

        int nullNum = 0;
        while (true) {

            int min = Integer.MAX_VALUE;
            int meIndex = -1; // 需要前进的 listNode
            for (int i = 0; i < lists.length; i++) {    //lists[i] 代表当前node节点
                if (lists[i] == null) {
                    nullNum++;
                    continue;
                }
                int val = lists[i].val;
                if (val < min) {
                    min = val;
                    meIndex = i;
                }
            }
            if (newHead == null) {
                newHead = new ListNode(min);
                point = newHead;
            } else {
                point.next = new ListNode(min);
                point = point.next;
            }
            lists[meIndex] = lists[meIndex].next;

            // 所有list为空时，结束
            if (nullNum == lists.length) {
                break;
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[1] = new ListNode(2);
        lists[2] = new ListNode(3);

        ListNode listNode = new MergeKLists0().mergeKLists(lists);
        System.out.println(listNode);// 1 2 3
    }
}
