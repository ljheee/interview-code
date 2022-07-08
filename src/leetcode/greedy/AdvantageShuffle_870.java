package leetcode.greedy;

import java.util.*;

/**
 * “田忌赛马”
 * 返回A数组 比B 元素大的个数 最多的序列
 * https://leetcode.com/problems/advantage-shuffle/
 *
 *
 * Created by lijianhua04 on 2020/2/16.
 */
public class AdvantageShuffle_870 {


    // 超时
    public int[] advantageCount0(int[] A, int[] B) {

        Arrays.sort(A);

        LinkedList<Integer> linkedList = new LinkedList<>();
        Arrays.stream(A).forEachOrdered(i -> linkedList.add(i));

        List<Integer> list = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < B.length; i++) {
            found = false;

            for (int j = 0; j < linkedList.size(); j++) {
                if (linkedList.get(j) > B[i]) {
                    list.add(linkedList.get(j));
                    linkedList.remove(j);
                    found = true;
                    break;
                }
            }
            if (found == false) {
                list.add(linkedList.get(0));
                linkedList.remove(0);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            A[i] = list.get(i);
        }
        return A;
    }


    class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int[] advantageCount(int[] A, int[] B) {

        Arrays.sort(A);

        int[] cloneB = B.clone();

        LinkedList<Node> linkedList1 = new LinkedList<>();

        for (int i = 0; i < B.length; i++) {
            linkedList1.add(new Node(B[i], i));
        }
        Collections.sort(linkedList1, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });

        for (int i = 0; i < A.length; i++) {
            if (A[i] > linkedList1.peekFirst().value) {
                cloneB[linkedList1.removeFirst().index] = A[i];
            } else {
                cloneB[linkedList1.removeLast().index] = A[i];
            }
        }
        return cloneB;
    }


    public static void main(String[] args) {
        new AdvantageShuffle_870().advantageCount(new int[]{8, 2, 4, 4, 5, 6, 6, 0, 4, 7}, new int[]{0, 8, 7, 4, 4, 2, 8, 5, 2, 0});
    }
}
