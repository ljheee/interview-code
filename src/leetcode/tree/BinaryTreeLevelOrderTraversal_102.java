package leetcode.tree;

import java.util.*;

/**
 * 按层遍历，标记每层的结束位置
 * https://leetcode.com/problems/binary-tree-level-order-traversal/submissions/
 *
 * 需要标记出来每一层结束的node
 * 对于当前curNode，curNode.left curNode.right就可能是下一层的结束点
 *
 * @author lijianhua.
 */
public class BinaryTreeLevelOrderTraversal_102 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // AC
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode curLevelEnd = root;
        TreeNode nextLevelEnd = root.right;


        List<Integer> levelNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            levelNodes.add(cur.val);

            if (cur.left != null) {
                queue.add(cur.left);
                nextLevelEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextLevelEnd = cur.right;
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
