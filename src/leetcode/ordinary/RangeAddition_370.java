package leetcode.ordinary;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 初始元素 进行一批区间操作后，返回最终的数组
 * https://www.cnblogs.com/grandyang/p/5628786.html
 * 建立累加和数组的操作实际上是表示当前的数字对之后的所有位置上的数字都有影响，
 */
public class RangeAddition_370 {


    public int[] getModifiedArray(int length, int[][] updates) {

        int[] res = new int[length + 1];

        for (int[] ops : updates) {
            res[ops[0]] += ops[2];
            res[ops[1] + 1] -= ops[2];
        }

        for (int i = 0; i < res.length; i++) {
            if (i == 0) {
                res[i] = res[i];
            }
            res[i] = res[i] + res[i - 1];
        }
        return res;
    }


    /**
     * 范围相加
     * 最大矩阵交集
     * https://leetcode.com/problems/range-addition-ii/submissions/
     * 最大矩阵交集：找出交集在X Y两个轴上的最小值。
     * <p>
     * <p>
     * https://leetcode-cn.com/problems/range-addition-ii/solution/fan-wei-qiu-he-ii-by-leetcode/
     * 暴力法：所有操作总是会影响到 (0,0)(0,0)，所以元素 arr[0][0]arr[0][0] 总是最大的。在所有操作执行完之后，我们数有多少个跟 arr[0][0]arr[0][0] 一样大的元素就是答案。
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {

        if (ops.length == 0) {
            return m * n;
        }
        Arrays.sort(ops, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int minA = ops[0][0];
        Arrays.sort(ops, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int minB = ops[0][1];

        //优化：遍历一遍ops，找出两个轴上的最小值
        return minA * minB;
    }
}
