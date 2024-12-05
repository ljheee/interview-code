package leetcode.tree;

import java.util.*;

/**
 * N叉树 按层遍历
 *
 * curNode.children[-1] 就可能是下一层的结束点
 *
 * @author lijianhua.
 */
public class NaryTreeLevelOrderTraversal_429 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Node curLevelEnd = root;
        Node nextLevelEnd = null;


        List<Integer> levelNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            levelNodes.add(cur.val);

            if (cur.children != null) {
                for (Node child : cur.children) {
                    queue.add(child);
                    nextLevelEnd = child;
                }
            }
            if (cur == curLevelEnd) {
                result.add(new ArrayList<>(levelNodes));
                levelNodes.clear();
                curLevelEnd = nextLevelEnd;
            }

        }


        return result;
    }



}
