package leetcode.d.bfs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/submissions/
 * 找出所有的N位数，满足相邻数组相差K
 * <p>
 * BFS的本质是在一层找到所有符合条件的点，存起来。然后进行到下一层，把所有符合条件的点再找出来，再存起来。知道最后一层，利用倒数第二层的cache，把所有符合条件的统统一网打尽。
 * <p>
 * DFS本质是，尽快的抓住一个点，尽快的一层一层的递归下去，所以当找到结果的时候，只有一个结果，还需要在返回上一个递归点，找到其他的点，再次递归下去。所以DFS总是单个单个的累计好输出的。
 * BFS和DFS本质上时间复杂度没有区别。
 */
public class NumbersWithSameConsecutiveDifferences_967 {


    /**
     * 递归（实现方法，类似dfs思想）
     * AC
     * <p>
     * 递归方法，哪里存在重复计算问题？
     *
     * @param N
     * @param K
     * @return
     */
    public static int[] numsSameConsecDiff(int N, int K) {

        if (N <= 1) return IntStream.range(0, 10).toArray();

        int start = (int) Math.pow(10, N - 1);
        int end = start * 10 - 1;

        HashSet<Integer> list = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            generate(i, i, start, K, end, list);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void generate(int prefix, int preNum, int start, int k, int end, HashSet<Integer> list) {

        if (prefix >= start || prefix >= end) {
            return;
        }

        if (preNum - k >= 0) {
            int newPrefix = prefix * 10 + (preNum - k);
            if (newPrefix >= start) list.add(newPrefix);
            generate(newPrefix, preNum - k, start, k, end, list);
        }
        if (preNum + k <= 9) {
            int newPrefix = prefix * 10 + (preNum + k);
            if (newPrefix >= start) list.add(newPrefix);
            generate(newPrefix, preNum + k, start, k, end, list);
        }
    }


    /**
     * bfs 寻找每一层的可行解，保存下来，进入下一层
     * https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences/solution/lian-xu-chai-xiang-tong-de-shu-zi-by-leetcode-2/
     * <p>
     * https://leetcode.com/problems/numbers-with-same-consecutive-differences/discuss/211183/JavaC%2B%2BPython-Iterative-Solution
     *
     * @param N
     * @param K
     * @return
     */
    public static int[] numsSameConsecDiff_bfs(int N, int K) {

        if (N <= 1) return IntStream.range(0, 10).toArray();

        Set<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toSet());

        for (int i = 2; i <= N; i++) {
            Set<Integer> temp = new HashSet<>();

            for (Integer prefix : list) {
                int preNum = prefix % 10;
                if (prefix > 0 && preNum - K >= 0) temp.add(prefix * 10 + preNum - K);
                if (prefix > 0 && preNum + K <= 9) temp.add(prefix * 10 + preNum + K);
            }
            list = temp;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



    public static void main(String[] args) {

        System.out.println(Arrays.toString(numsSameConsecDiff(3, 7)));

        int[] ints = {1, 2, 3};
        int[] arr = {3, 2, 1};
        System.out.println(Arrays.equals(ints, arr));

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int[] arr1 = numsSameConsecDiff(i, j);
                int[] arr2 = numsSameConsecDiff_bfs(i, j);
                Arrays.sort(arr1);
                Arrays.sort(arr2);
                if (!Arrays.equals(arr1, arr2)) {
                    System.out.println("xxxxx" + i + "=" + j);
                }
            }
        }
    }
}
