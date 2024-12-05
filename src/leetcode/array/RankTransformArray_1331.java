package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 返回 元素在排序后数组中的新下标
 * https://www.cnblogs.com/wentiliangkaihua/p/12239575.html
 * Created by lijianhua04 on 2020/3/4.
 */
public class RankTransformArray_1331 {
    class Node {
        int val;
        int index;
        int newIndex;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    /**
     * AC 空间100%，时间不高
     *
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr) {

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i], i);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });

        nodes[0].newIndex = 1;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].val == nodes[i - 1].val) {
                nodes[i].newIndex = nodes[i - 1].newIndex;
            } else {
                nodes[i].newIndex = nodes[i - 1].newIndex + 1;
            }
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.index - o2.index;
            }
        });
        for (int i = 0; i < nodes.length; i++) {
            arr[i] = nodes[i].newIndex;
        }

        return arr;
    }


    /**
     * 使用hashmap
     * https://leetcode-cn.com/problems/rank-transform-of-an-array/solution/java-hashmapqiu-jie-by-ele_rapious/
     *
     * @param arr
     * @return
     */
    public static int[] arrayRankTransform2(int[] arr) {
        int a[] = new int[arr.length];
        a = arr.clone();
        Arrays.sort(a);
        Map<Integer, Integer> map = new HashMap<>();
        int index = 1;
        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i] != a[i - 1]) {
                map.put(a[i], index);
                index++;
            }
            if (i == 0) {
                map.put(a[i], index);
                index++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }


}
