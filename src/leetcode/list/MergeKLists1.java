package leetcode.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将k 个list 有序单列表 进行merge
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 注意：重复元素，需要出现多次
 *
 * 最后一个case，超时，
 * 更优方案：使用优先级Queue，遍历一遍进行排序
 */
public class MergeKLists1 {
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
        ListNode newHead = new ListNode(-1);
        ListNode point = newHead;

        int len = lists.length;
        // 全局保存，lists[i]为空的(i索引)
        List<Integer> nullIndex = new ArrayList<>(len);

        // 需要前进的 lists[i]索引 val映射，val相等时 需要前进多次
        HashMap<Integer, Integer> indexValMap = new HashMap<>();

        while (true) {
            int min = Integer.MAX_VALUE;// 当前一次 val最小值
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    if (nullIndex.indexOf(i) >= 0) {
                    } else {
                        nullIndex.add(i);
                    }
                    continue;
                }
                int val = lists[i].val;
                if (val <= min) {
                    min = val;
                    indexValMap.put(i, val);
                }
            }

            // 所有list为空时，结束
            if (nullIndex.size() == lists.length) {
                break;
            }

            for (Map.Entry<Integer, Integer> entry : indexValMap.entrySet()) {
                if (entry.getValue() == min) {
                    // 前进一步
                    lists[entry.getKey()] = lists[entry.getKey()].next;

                    // 追加一个node
                    point.next = new ListNode(min);
                    point = point.next;
                }
            }
            indexValMap.clear();
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);
        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);

        ListNode listNode = new MergeKLists1().mergeKLists(lists);
        System.out.println(listNode);
    }
}
