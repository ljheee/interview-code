package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 用最少的箭 射完气球
 * 就是求：最大不重叠区间个数——区间调度问题;
 * 因为重叠的部分 一支箭就能射掉，花费的箭数目=不重叠区间数目
 */
public class MinimumNumberofArrowstoBurstBalloons {


    public int findMinArrowShots(int[][] points) {

        if (points.length == 0) {
            return 0;
        }
        // 对数组排序，按区间的end 排序，先结束的区间排前面
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int x_end = points[0][1];
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            int start = point[0];
            if (start > x_end) {// 找到一个 比当前区间end还大的(和当前区间不相交的)
                count++;
                x_end = point[1];
            }
        }
        return count;
    }

}
