package leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 从单链表中 移除 和为0的连续子序列
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 *
 * 初始实现思路： 遍历得到 sum序列，出现回文时，去除回文第二元素~最后元素对应的Node
 *
 * 例如：
 * 1、2、3、-3、4 依次对应的sum
 * 1、3、6、 3、7  =3 6 3构成回文去除6 3对应的node；
 *
 * 示例2： 1 2 3 -3 -2 也满足；
 *
 *
 * 但后来发现不满足的：
 * 1 1 1 1 -3 对应的sum
 * 1 2 3 4  1   =不存在回文子串，但两个1 之间的子序列————去除第二元素~最后元素对应的Node，就满足；
 * 按该思路实现 RemoveZeroSumfromLinkedList2
 */
public class RemoveZeroSumfromLinkedList_1171 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeZeroSumSublists(ListNode head) {

        //遍历一遍：得到链表每个val
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            sum += current.val;
            list.add(sum);
            current = current.next;
        }


        int[] arr = new int[list.size()];
        int left = arr.length;
        int right = -1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        // 找出最大 回文子序列
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (isPalindrome(arr, i, j)) {
                    left = left < i ? left : i;
                    right = right > j ? right : j;
                }
            }
        }

        if (left == arr.length && right == -1) {
            if (list.contains(0)) {

            } else {
                return head;// 不存在
            }
        }


        for (int i = 0; i <= left; i++) {

        }

        for (int i = right + 1; i < arr.length; i++) {

        }

        return head;
    }

    public static boolean isPalindrome(int[] array, int fromIndex, int toIndex) {
        for (int i = fromIndex, j = toIndex; i < j; i++, j--) {
            if (array[i] != array[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {


        // 找到数组中 最大范围的 回文index
        int[] arr = new int[]{-1, 0, 3, 3, 4, 3};
        int left = arr.length;
        int right = -1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (isPalindrome(arr, i, j)) {
                    if (left == arr.length && right == -1) {
                        left = i;
                        right = j;
                    } else {
                        if (right - left < j - i) {
                            left = i;
                            right = j;// 替换成新的
                        }
                    }
                    left = left < i ? left : i;
                    right = right > j ? right : j;
                }
            }
        }

        System.out.println(left + "-" + right);
    }
}
