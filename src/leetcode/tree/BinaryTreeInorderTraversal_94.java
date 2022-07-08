package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/
 *
 *
 * @author lijianhua.
 */
public class BinaryTreeInorderTraversal_94 {


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


        // AC
        public List<Integer> inorderTraversal_recursive(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            inorderTraversal(root.left, list);
            list.add(root.val);
            inorderTraversal(root.right, list);
            return list;
        }

        private void inorderTraversal(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            inorderTraversal(node.left, list);
            list.add(node.val);
            inorderTraversal(node.right, list);
        }


        /**
         * 非递归实现
         * 用左边界，划分整棵树
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            Stack<TreeNode> stack = new Stack<>();


            return list;
        }

    }
}

