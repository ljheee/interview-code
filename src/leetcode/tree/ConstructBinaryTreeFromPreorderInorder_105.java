package leetcode.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class ConstructBinaryTreeFromPreorderInorder_105 {


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


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder.length == 0) return null;
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode process(int[] preorder, int L1, int R1, int[] inorder, int L2, int R2) {

        if (L1 > R1) return null;
        TreeNode head = new TreeNode(preorder[L1]);
        if (L1 == R1) return head;

        int idxOfHead = L2;
        for (; preorder[L1] != inorder[idxOfHead]; idxOfHead++) ;

        head.left = process(preorder, L1 + 1, L1  + idxOfHead - L2, inorder, L2, idxOfHead - 1);
        head.right = process(preorder, L1 + idxOfHead - L2 + 1, R1, inorder, idxOfHead + 1, R2);
        return head;

    }


}
