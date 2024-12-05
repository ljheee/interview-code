package leetcode.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 从单链表中 移除 和为0的连续子序列
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 *
 * 遍历得到 sum序列，
 */
public class RemoveZeroSumfromLinkedList2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeZeroSumSublists(ListNode head) {

        ListNode aHead = head;
        //遍历一遍：得到链表每个val
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        ListNode current = aHead;
        ListNode tempPre = null;
        while (current != null) {
            if (current.val == 0) {// 删除val为0的
                // remove it
                if (tempPre == null) {
                    aHead = aHead.next;
                    current = aHead;
                    tempPre = null;
                } else {
                    tempPre.next = current.next;
                    current = current.next;
                }
                continue;
            }
            tempPre = current;

            sum += current.val;
            list.add(sum);
            current = current.next;
        }

        int from = -1;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            index = list.lastIndexOf(list.get(i));
            if (index == -1 || index == i) {
                continue;
            } else {
                from = i;
                break;
            }
        }

        if (from == -1) {
            // 检查 包含0的
            if (list.contains(0)) {
                int indexOfZero = list.lastIndexOf(0);
                // 去除0~indexOfZero：即去除前indexOfZero+1个node
                while (indexOfZero-- >= 0) {
                    aHead = aHead.next;
                }
                return aHead;
            } else {
                return aHead;
            }
        }

        int i = 0;
        ListNode point = null;
        current = aHead;
        ListNode prev = null;
        while (current != null) {
            while (i <= from) {
                prev = current;
                current = current.next;
                i++;
            }
            while (i >= from + 1 && i <= index) {
                current = current.next;
                i++;
            }
            if (i - index == 1) {
                prev.next = current;
                point = prev.next;
                i++;
            } else {
                // 往后加
                point.next = current;
                point = point.next;
            }
            if (current != null) {
                current = current.next;
            }
        }

        List<Integer> listBak = new ArrayList<>(list);
        List<Integer> list1 = list.subList(from, index);
        list.removeAll(list1);
        if (list.contains(0)) {
            int nOfRemove = 0;
            for (int j = 0; j <= from || j>index; j++) {
                if(listBak.get(j) != 0){
                    nOfRemove++;
                }else {
                    break;
                }
            }
            // nOfRemove+1个node
            while (nOfRemove-- >= 0) {
                aHead = aHead.next;
            }
            return aHead;
        }

        return removeZeroSumSublists(aHead);
    }


    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(-2);
        list.add(-1);
        list.add(5);
        list.add(3);
        list.add(-2);

        int from = -1;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            index = list.lastIndexOf(list.get(i));
            if (index == -1 || index == i) {
                continue;
            } else {
                from = i;
                break;
            }
        }
        System.out.println(list.lastIndexOf(list.get(1)));
        System.out.println(from + "-" + index);

        List<Integer> list1 = list.subList(from, index);
        list.removeAll(list1);
        System.out.println(list);


    }
}
