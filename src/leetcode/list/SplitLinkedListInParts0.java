package leetcode.list;

/**
 * 一个链表切成 k段
 * <p>
 * https://leetcode.com/problems/split-linked-list-in-parts/
 * 要求，不能均分的 多余node，放到前面。
 * <p>
 * 此实现不足：5个node，分成3段，会使第一段是3个 其余的都是1个node；
 * 应该变成 2 2 1 =前两段 各有2段
 */
public class SplitLinkedListInParts0 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];

        // 遍历一遍，得到链表长度
        int n = 0;// node 总个数
        ListNode current = root;
        while (current != null) {
            n++;
            current = current.next;
        }

        int num = n / k;//从listNodes[1]起 后续的个数；如果k>n，此时num为0，说明每个node分不到一个
        int firstNum = n - num * k + num;

        ListNode thisNode = root;
        for (int i = 0; i < k; i++) {
            listNodes[i] = thisNode == null ? null : new ListNode(thisNode.val);

            if (num == 0) {
                if (listNodes[i] != null) {
                    listNodes[i].next = null;
                }
                thisNode = thisNode == null ? null : thisNode.next;
                continue;
            }
            // 寻找下一个，断开连接&作为新头

            // 前进step步
            int step = i == 0 ? firstNum : num;
            current = thisNode;
            ListNode listNodesINext = listNodes[i];
            while (step-- > 0) {
                current = current.next;
                listNodesINext.next = current;
                if (listNodesINext.next == null) {
                    break;
                }
                if (step == 0) {
                    listNodesINext.next = null;
                }
                listNodesINext = listNodesINext.next;

            }
            thisNode = current;
        }
        return listNodes;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode point = listNode;

        for (int i = 2; i <= 3; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        ListNode[] listNodes = splitListToParts(listNode, 5);
        System.out.println(listNodes);
    }


}
