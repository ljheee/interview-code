package leetcode.list;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListwithRandomPointer_138 {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        HashMap<Node, Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node curCopy = map.get(cur);
            curCopy.next = map.get(cur.next);
            curCopy.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
