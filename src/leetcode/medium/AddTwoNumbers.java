package leetcode.medium;

/**
 * Created by lijianhua04 on 2019/11/2.
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int a = getListNode(l1);
        int b = getListNode(l2);
        int c = a + b;

        String[] split = String.valueOf(c).split("");

        ListNode listNode = new ListNode(Integer.parseInt(split[split.length - 1]));
        for (int i = split.length - 2; i > 0; i--) {
            listNode.next = new ListNode(Integer.parseInt(split[i]));
        }


        return listNode;
    }

    private int getListNode(ListNode l1) {
        ListNode curent = l1.next;
        String num = "";
        while (curent != null) {
            num = String.join("", "" + l1.next.val, num);
        }
        return Integer.parseInt(num);
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        System.out.println("123".split("")[2]);
    }


}
