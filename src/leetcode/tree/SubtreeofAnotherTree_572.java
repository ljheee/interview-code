package leetcode.tree;

/**
 * 判断 t是否为s的子树
 * https://leetcode.com/problems/subtree-of-another-tree/
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 官方题解，比较树的先序遍历串
 * <p>
 * Created by lijianhua04 on 2020/2/22.
 */
public class SubtreeofAnotherTree_572 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        boolean isSubtree = isEqualsTree(s, t);
        if (isSubtree) {
            return true;
        } else {
            boolean left = s.left != null ? isSubtree(s.left, t) : false;
            boolean right = s.right != null ? isSubtree(s.right, t) : false;
            return left || right;
        }
    }

    /**
     * 递归判断 两颗二叉树是否相等
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isEqualsTree(TreeNode s, TreeNode t) {
        if (s.val != t.val) {
            return false;
        }
        if ((s.left != null && t.left == null) ||
                (s.left == null && t.left != null)) {
            return false;
        }
        if ((s.right != null && t.right == null) ||
                (s.right == null && t.right != null)) {
            return false;
        }

        boolean left = false;
        boolean right = false;

        // 都已到叶子节点
        if (s.left == null && t.left == null) {
            left = true;
        }
        if (s.right == null && t.right == null) {
            right = true;
        }
        if (s.left != null && t.left != null) {
            left = isEqualsTree(s.left, t.left);
        }
        if (s.right != null && t.right != null) {
            right = isEqualsTree(s.right, t.right);
        }
        return left && right;
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(3);
        TreeNode t = new TreeNode(4);
        s.left = t;
        s.right = new TreeNode(5);


        t.left = new TreeNode(1);
        t.right = new TreeNode(2);


        new SubtreeofAnotherTree_572().isSubtree(s, t);
    }

}


