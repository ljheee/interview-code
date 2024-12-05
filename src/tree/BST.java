package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijianhua04 on 2020/4/15.
 */
public class BST {

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    public List<Integer> postOrder(Node root) {

        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(postOrder(root.left));
        list.addAll(postOrder(root.right));
        list.add(root.value);

        return list;
    }


    public Node post2BST(int[] postArr, int left, int right) {
        if (left > right) {
            return null;
        }
        Node node = new Node(postArr[right]);

        // 找到小于 postArr[right] 的分界，作为左子树
        int mid = -1;
        for (int i = left; i < right; i++) {
            if (postArr[i] < postArr[right]) {
                mid = i;
            }
        }

        if (mid == -1) {
            node.right = post2BST(postArr, left, right - 1);
        } else if (mid == right - 1) {
            node.left = post2BST(postArr, left, right - 1);
        } else {
            node.left = post2BST(postArr, left, mid);
            node.right = post2BST(postArr, mid + 1, right - 1);
        }

        return node;
    }


    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = new int[]{2, 4, 3, 6, 8, 7, 5};
        Node newTree = bst.post2BST(arr, 0, arr.length - 1);


        int[] ints = bst.postOrder(newTree).stream().mapToInt(a -> a.intValue()).toArray();
        System.out.println(Arrays.toString(ints));

    }

}
