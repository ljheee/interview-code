package leetcode.list;

/**
 * 一个链表切成 k段
 * 1.k大于node个数，子列表 不能全都 分到一个node；num == 0 特殊处理
 * 2.其他的情况：循环 k段，每段前进各自的step步（每段取各自 数目的node）
 * https://leetcode.com/problems/split-linked-list-in-parts/
 * <p>
 * 此优化，使用steps[]数组，保存k段，各自的长度
 * AC
 */
public class SplitLinkedListInParts {

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

        int[] steps = new int[k];
        int count = n;
        while (count > 0) {
            for (int i = 0; i < k; i++) {
                if (count == 0) {
                    break;
                }
                steps[i] += 1;
                count--;
            }
        }

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
//            int step = i == 0 ? firstNum : num;
            int step = steps[i];
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

        for (int i = 2; i <= 7; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        ListNode[] listNodes = splitListToParts(listNode, 5);
        System.out.println(listNodes);
    }


}
