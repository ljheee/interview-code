package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 去除重叠区间：给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * https://leetcode.com/problems/non-overlapping-intervals/
 * <p>
 * 排序，移除区间长的
 * 借助它的区间图 https://juejin.im/post/5c93760e51882506254d082c
 * <p>
 * 官方题解 https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode/
 * 有动态规划解法
 * <p>
 * <p>
 * 更清晰易懂的解法
 * https://leetcode-cn.com/problems/non-overlapping-intervals/solution/tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
 * 区间调度问题
 * 求最大的不重叠区间，贪心解法：每次选择与当前不相交的区间。
 * <p>
 * 《算法导论》活动安排教室问题
 * https://www.cnblogs.com/kexinxin/p/10242232.html
 */
public class NonOverlappingIntervals {


    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0 || intervals.length == 1) {
            return 0;
        }
        // 对数组排序，从小到大排序，首先比较start，再比较end
        // 排序前 [ [1,2], [2,3], [3,4], [1,3] ]
        // 排序后 [ [1,2], [1,3]，[2,3], [3,4] ]
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int needRemove = 0;
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // 移除图示 5，1，2、3情况的区间 https://juejin.im/post/5c93760e51882506254d082c
            int[] currentInterval = intervals[i];
            if (currentInterval[0] == prev[0]) {
                needRemove++;
                //移除的是当前currentInterval，prev不变
            } else if ((currentInterval[0] > prev[0] && currentInterval[0] < prev[1])
                    && currentInterval[1] > prev[1]) {
                needRemove++;
                //移除的是当前currentInterval，prev不变
            } else if (currentInterval[0] > prev[0] && currentInterval[1] <= prev[1]) {
                needRemove++;
                //移除的是prev，prev变成当前currentInterval
                prev = currentInterval;
            } else {
                // 正常进位
                prev = currentInterval;
            }
        }
        return needRemove;
    }

}
