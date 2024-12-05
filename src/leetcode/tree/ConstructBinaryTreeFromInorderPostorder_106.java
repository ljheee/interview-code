package leetcode.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromInorderPostorder_106 {

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


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != inorder.length || inorder.length == 0) return null;
        return process(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode process(int[] inorder, int L1, int R1, int[] postorder, int L2, int R2) {

        if (L1 > R1) return null;
        TreeNode head = new TreeNode(postorder[R2]);
        if (L1 == R1) return head;

        int idxOfHead = L1;
        for (; postorder[R2] != inorder[idxOfHead]; idxOfHead++) ;

        head.left = process(inorder, L1, idxOfHead - 1, postorder, L2, L2 + idxOfHead - L1 - 1);
        head.right = process(inorder, idxOfHead + 1, R1, postorder, L2 + idxOfHead - L1, R2 - 1);

        return head;
    }
}
